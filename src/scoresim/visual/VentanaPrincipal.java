package scoresim.visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import scoresim.ScoreSimApp;
import scoresim.SimuladorTorneo;
import scoresim.torneo.GrupoMundial;
import scoresim.torneo.Mundial2026;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("ScoreSim 2026 - Control Center");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(new Color(23, 32, 42));
        panelFondo.setLayout(new BorderLayout());
        panelFondo.setBorder(new EmptyBorder(30, 40, 30, 40));

        JLabel lblTitulo = new JLabel("SCORESIM 2026", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(236, 240, 241));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 30, 0));
        panelFondo.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 0, 20));
        panelBotones.setOpaque(false);

        // --- BOTÓN 1: GESTIÓN ---
        JButton btnGestion = crearBotonModerno("Panel de Gestión", "⚙️", new Color(52, 152, 219));
        btnGestion.addActionListener(e -> new VentanaGestion());

        /// --- BOTÓN 2: SIMULACIÓN RÁPIDA ---
        JButton btnSimular = crearBotonModerno("10.000 Simulaciones", "🚀", new Color(46, 204, 113));
        btnSimular.addActionListener(e -> {
            // Usamos un cursor de espera para indicar que está trabajando
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            new Thread(() -> {
                try {
                    // 1. Ejecuta la simulación masiva (10,000 mundiales)
                    var resultados = ScoreSimApp.ejecutarSimulacionDesdeUI();

                    // 2. Generamos la crónica textual requerida por el nuevo constructor
                    Mundial2026 gestor = new Mundial2026();
                    List<GrupoMundial> mundialParaCronica = Mundial2026.crearMundialOficial();
                    String cronicaDetallada = gestor.ejecutarSegundaFase(mundialParaCronica);

                    // 3. Abrimos la ventana con los 3 parámetros: historial, total y crónica
                    SwingUtilities.invokeLater(() -> {
                        setCursor(Cursor.getDefaultCursor());
                        new VentanaReporte(resultados, 10000, cronicaDetallada).setVisible(true);
                    });

                } catch (Exception ex) {
                    ex.printStackTrace();
                    SwingUtilities.invokeLater(() -> setCursor(Cursor.getDefaultCursor()));
                }
            }).start();
        });

        // --- BOTÓN 3: MUNDIAL COMPLETO (Simulación Única y Real) ---
        JButton btnMundial = crearBotonModerno("Simular Mundial 2026", "🏆", new Color(212, 172, 13));
        btnMundial.addActionListener(e -> {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            new Thread(() -> {
                try {
                    // 1. Limpiar estadísticas previas de la base de datos (Goleadores)
                    SimuladorTorneo motorStats = new SimuladorTorneo();
                    motorStats.resetearEstadisticasTorneo();

                    // 2. Crear la estructura del mundial
                    List<GrupoMundial> mundial = Mundial2026.crearMundialOficial();

                    // 3. SIMULAR FASE DE GRUPOS
                    // Esto registrará goles y partidos en la DB para la Bota de Oro
                    for (GrupoMundial g : mundial) {
                        g.simularJornada();
                    }

                    // 4. SIMULAR ELIMINATORIAS Y CAPTURAR TEXTO
                    Mundial2026 gestor = new Mundial2026();
                    // Este método ya hace los cruces y devuelve el String con los resultados
                    String cronica = gestor.ejecutarSegundaFase(mundial);

                    // 5. MOSTRAR RESULTADOS
                    SwingUtilities.invokeLater(() -> {
                        setCursor(Cursor.getDefaultCursor());

                        // Abrimos la ventana de reporte.
                        // Pasamos un Mapa vacío porque en esta simulación única no nos importan los % de Montecarlo
                        new VentanaReporte(new java.util.HashMap<>(), 1, cronica).setVisible(true);

                        // Preguntar por goleadores al terminar
                        int respuesta = JOptionPane.showConfirmDialog(this,
                                "¡Mundial Finalizado!\n¿Deseas ver la tabla de goleadores?",
                                "Simulación Única",
                                JOptionPane.YES_NO_OPTION);

                        if (respuesta == JOptionPane.YES_OPTION) {
                            new VentanaEstadisticas().setVisible(true);
                        }
                    });

                } catch (Exception ex) {
                    ex.printStackTrace();
                    SwingUtilities.invokeLater(() -> {
                        setCursor(Cursor.getDefaultCursor());
                        JOptionPane.showMessageDialog(this, "Error en la simulación: " + ex.getMessage());
                    });
                }
            }).start();
        });

        panelBotones.add(btnGestion);
        panelBotones.add(btnSimular);
        panelBotones.add(btnMundial);

        panelFondo.add(panelBotones, BorderLayout.CENTER);

        JLabel lblFooter = new JLabel("Engine v3.0 - Powered by MariaDB", SwingConstants.CENTER);
        lblFooter.setForeground(new Color(127, 140, 141));
        lblFooter.setFont(new Font("Monospaced", Font.PLAIN, 12));
        lblFooter.setBorder(new EmptyBorder(20, 0, 0, 0));

        JLabel lblAutor = new JLabel("Creado por Javi", SwingConstants.CENTER);
        lblAutor.setForeground(new Color(236, 240, 241));
        lblAutor.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        add(panelFondo);
        JPanel panelFooter = new JPanel(new GridLayout(2, 1, 0, 2));
        panelFooter.setOpaque(false);
        panelFooter.add(lblFooter);
        panelFooter.add(lblAutor);
        panelFondo.add(panelFooter, BorderLayout.SOUTH);
    }

    private JButton crearBotonModerno(String texto, String emoji, Color colorFondo) {
        JButton btn = new JButton("<html><center><font size='5'>" + emoji + "</font><br>" + texto + "</center></html>");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(colorFondo);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btn.setBackground(colorFondo.darker()); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btn.setBackground(colorFondo); }
        });

        return btn;
    }
}