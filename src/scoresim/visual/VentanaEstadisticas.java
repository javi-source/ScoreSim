package scoresim.visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import scoresim.ConexionBD;

public class VentanaEstadisticas extends JFrame {

    private DefaultTableModel modelo;
    private JPanel panelPodio;

    public VentanaEstadisticas() {
        setTitle("🏆 Bota de Oro - Mundial 2026");
        setSize(600, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Panel de encabezado (Podio)
        panelPodio = new JPanel(new GridLayout(1, 3, 10, 0));
        panelPodio.setBackground(new Color(44, 62, 80));
        panelPodio.setPreferredSize(new Dimension(600, 160));
        panelPodio.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 2. Tabla de goleadores
        String[] columnas = {"Pos", "Jugador", "Equipo", "Partidos", "Goles"};
        modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        cargarDatos(); // Carga inicial

        JScrollPane scroll = new JScrollPane(tabla);
        add(panelPodio, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // 3. Panel de Botones Inferior
        JPanel panelAcciones = new JPanel(new GridLayout(1, 2, 10, 0));
        panelAcciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnLimpiar = new JButton("🗑️ Limpiar Historial");
        btnLimpiar.setBackground(new Color(192, 57, 43));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(e -> limpiarHistorial());

        JButton btnCerrar = new JButton("Volver al Menú");
        btnCerrar.addActionListener(e -> dispose());

        panelAcciones.add(btnLimpiar);
        panelAcciones.add(btnCerrar);

        add(panelAcciones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void cargarDatos() {
        // Limpiar vista actual antes de recargar
        modelo.setRowCount(0);
        panelPodio.removeAll();

        String sql = "SELECT j.nombre, j.apellido, j.nacionalidad, e.partidos_jugados, e.goles " +
                "FROM estadisticas_simulacion e " +
                "JOIN jugadores j ON e.jugador_id = j.id " +
                "WHERE e.goles > 0 " +
                "ORDER BY e.goles DESC, e.partidos_jugados ASC LIMIT 20";

        try (Connection conn = ConexionBD.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            int ranking = 1;
            while (rs.next()) {
                String nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellido");
                String pais = rs.getString("nacionalidad");
                int pj = rs.getInt("partidos_jugados");
                int goles = rs.getInt("goles");

                modelo.addRow(new Object[]{ranking, nombreCompleto, pais, pj, goles});

                if (ranking <= 3) {
                    panelPodio.add(crearTarjetaPodio(nombreCompleto, goles, ranking));
                }
                ranking++;
            }

            // Si no hay datos, mostrar mensaje en el podio
            if (ranking == 1) {
                JLabel lblVacio = new JLabel("No hay datos de goles registrados", SwingConstants.CENTER);
                lblVacio.setForeground(Color.GRAY);
                panelPodio.add(lblVacio);
            }

            panelPodio.revalidate();
            panelPodio.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void limpiarHistorial() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas borrar todas las estadísticas del Mundial?",
                "Confirmar Borrado", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = ConexionBD.conectar();
                 Statement st = conn.createStatement()) {

                // Borramos los datos de la tabla
                st.executeUpdate("DELETE FROM estadisticas_simulacion");

                JOptionPane.showMessageDialog(this, "Historial borrado correctamente.");
                cargarDatos(); // Refrescamos la ventana (quedará vacía)

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al borrar: " + e.getMessage());
            }
        }
    }

    private JPanel crearTarjetaPodio(String nombre, int goles, int pos) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        String medalla = (pos == 1) ? "🥇" : (pos == 2) ? "🥈" : "🥉";

        JLabel lblMedalla = new JLabel(medalla, SwingConstants.CENTER);
        lblMedalla.setFont(new Font("Segoe UI", Font.BOLD, 40));

        JLabel lblNombre = new JLabel("<html><center>" + nombre + "</center></html>", SwingConstants.CENTER);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JLabel lblGoles = new JLabel(goles + " G", SwingConstants.CENTER);
        lblGoles.setForeground(new Color(241, 196, 15));
        lblGoles.setFont(new Font("Segoe UI", Font.BOLD, 16));

        p.add(lblMedalla, BorderLayout.NORTH);
        p.add(lblNombre, BorderLayout.CENTER);
        p.add(lblGoles, BorderLayout.SOUTH);
        return p;
    }
}