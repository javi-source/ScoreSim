package scoresim.visual;

import scoresim.ConexionBD;
import scoresim.EstadisticasEquipo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Map;
import java.util.Vector;

public class VentanaReporte extends JDialog {

    public VentanaReporte(Map<String, EstadisticasEquipo> historial, int total, String reporteTexto) {
        setTitle("Resultados de la Simulación - ScoreSim 2026");
        setSize(1050, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 13));

        // ─── PESTAÑA 1: PROBABILIDADES MONTECARLO ───
        if (!historial.isEmpty()) {
            String[] columnas = {"Selección", "🏆 CAMPEÓN", "🥈 FINAL", "🥉 SEMIS", "CUARTOS", "OCTAVOS", "16VOS"};
            DefaultTableModel modeloMC = new DefaultTableModel(columnas, 0) {
                @Override public boolean isCellEditable(int r, int c) { return false; }
            };
            historial.entrySet().stream()
                    .sorted((a, b) -> Integer.compare(b.getValue().campeon, a.getValue().campeon))
                    .forEach(entry -> {
                        EstadisticasEquipo s = entry.getValue();
                        Vector<Object> fila = new Vector<>();
                        fila.add(entry.getKey());
                        fila.add(String.format("%.2f%%", (s.campeon * 100.0) / total));
                        fila.add(String.format("%.2f%%", (s.finalista * 100.0) / total));
                        fila.add(String.format("%.2f%%", (s.fueraEnSemis * 100.0) / total));
                        fila.add(String.format("%.2f%%", (s.fueraEn4tos * 100.0) / total));
                        fila.add(String.format("%.2f%%", (s.fueraEn8vos * 100.0) / total));
                        fila.add(String.format("%.2f%%", (s.fueraEn16vos * 100.0) / total));
                        modeloMC.addRow(fila);
                    });
            JTable tablaMC = new JTable(modeloMC);
            tablaMC.setRowHeight(25);
            EstiloUI.prepararTabla(tablaMC);
            tabs.addTab("📊 Probabilidades", new JScrollPane(tablaMC));
        }

        // ─── PESTAÑA 2: CRÓNICA COMPLETA (Grupos + Eliminatorias) ───
        JTextArea areaCronica = new JTextArea(reporteTexto);
        areaCronica.setEditable(false);
        areaCronica.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaCronica.setBackground(new Color(245, 245, 245));
        areaCronica.setCaretPosition(0);
        tabs.addTab("📋 Crónica Completa", new JScrollPane(areaCronica));

        // ─── PESTAÑA 3: GOLEADORES ───
        tabs.addTab("⚽ Goleadores", crearPanelGoleadores());

        add(tabs, BorderLayout.CENTER);

        // Botón cerrar
        JButton btnCerrar = new JButton("Cerrar");
        EstiloUI.prepararBoton(btnCerrar, EstiloUI.AZUL_PRIMARY, EstiloUI.AZUL_HOVER);
        btnCerrar.addActionListener(e -> dispose());
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSur.add(btnCerrar);
        add(panelSur, BorderLayout.SOUTH);

        setModal(true);
        setVisible(true);
    }

    private JPanel crearPanelGoleadores() {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel del podio (top 3)
        JPanel panelPodio = new JPanel(new GridLayout(1, 3, 10, 0));
        panelPodio.setBackground(new Color(44, 62, 80));
        panelPodio.setPreferredSize(new Dimension(600, 150));
        panelPodio.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Tabla completa
        String[] cols = {"Pos", "Jugador", "Equipo", "Partidos", "Goles"};
        DefaultTableModel modeloG = new DefaultTableModel(cols, 0);
        JTable tablaG = new JTable(modeloG);
        tablaG.setRowHeight(28);
        EstiloUI.prepararTabla(tablaG);

        // Cargar datos desde DB
        String sql = "SELECT j.nombre, j.apellido, j.nacionalidad, e.partidos_jugados, e.goles " +
                "FROM estadisticas_simulacion e " +
                "JOIN jugadores j ON e.jugador_id = j.id " +
                "WHERE e.goles > 0 " +
                "ORDER BY e.goles DESC, e.partidos_jugados ASC LIMIT 30";

        try (Connection conn = ConexionBD.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            int ranking = 1;
            while (rs.next()) {
                String nombre = rs.getString("nombre") + " " + rs.getString("apellido");
                String pais = rs.getString("nacionalidad");
                int pj = rs.getInt("partidos_jugados");
                int goles = rs.getInt("goles");

                modeloG.addRow(new Object[]{ranking, nombre, pais, pj, goles});

                if (ranking <= 3) panelPodio.add(crearTarjetaPodio(nombre, goles, ranking));
                ranking++;
            }

            if (ranking == 1) {
                JLabel vacio = new JLabel("No hay goles registrados aún", SwingConstants.CENTER);
                vacio.setForeground(Color.GRAY);
                panelPodio.add(vacio);
            }

        } catch (SQLException e) {
            panelPodio.add(new JLabel("Error al cargar goleadores: " + e.getMessage()));
        }

        panel.add(panelPodio, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaG), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearTarjetaPodio(String nombre, int goles, int pos) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        String medalla = pos == 1 ? "🥇" : pos == 2 ? "🥈" : "🥉";

        JLabel lblMedalla = new JLabel(medalla, SwingConstants.CENTER);
        lblMedalla.setFont(new Font("Segoe UI", Font.BOLD, 36));

        JLabel lblNombre = new JLabel("<html><center>" + nombre + "</center></html>", SwingConstants.CENTER);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        JLabel lblGoles = new JLabel(goles + " goles", SwingConstants.CENTER);
        lblGoles.setForeground(new Color(241, 196, 15));
        lblGoles.setFont(new Font("Segoe UI", Font.BOLD, 15));

        p.add(lblMedalla, BorderLayout.NORTH);
        p.add(lblNombre, BorderLayout.CENTER);
        p.add(lblGoles, BorderLayout.SOUTH);
        return p;
    }
}