package scoresim.visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import scoresim.ScoreSimApp;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("ScoreSim 2026 - Control Center");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal oscuro
        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(new Color(23, 32, 42));
        panelFondo.setLayout(new BorderLayout());
        panelFondo.setBorder(new EmptyBorder(30, 40, 30, 40));

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("SCORESIM 2026", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(236, 240, 241));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 30, 0));
        panelFondo.add(lblTitulo, BorderLayout.NORTH);

        // --- PANEL DE BOTONES ---
        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 0, 25));
        panelBotones.setOpaque(false);

        // Botón 1: Simulación (Verde sólido)
        JButton btnSimular = crearBotonModerno("Lanzar Simulación", "🚀", new Color(46, 204, 113));
        btnSimular.addActionListener(e -> {
            new Thread(() -> {
                var resultados = ScoreSimApp.ejecutarSimulacionDesdeUI();
                new VentanaReporte(resultados, 10000).setVisible(true);
            }).start();
        });

        // Botón 2: Gestión (Azul sólido)
        JButton btnGestion = crearBotonModerno("Panel de Gestión", "⚙️", new Color(52, 152, 219));
        btnGestion.addActionListener(e -> new VentanaGestion());

        panelBotones.add(btnSimular);
        panelBotones.add(btnGestion);
        panelFondo.add(panelBotones, BorderLayout.CENTER);

        // --- FOOTER ---
        JLabel lblFooter = new JLabel("Engine v3.0 - Powered by MariaDB", SwingConstants.CENTER);
        lblFooter.setForeground(new Color(127, 140, 141));
        lblFooter.setFont(new Font("Monospaced", Font.PLAIN, 12));
        lblFooter.setBorder(new EmptyBorder(20, 0, 0, 0));
        panelFondo.add(lblFooter, BorderLayout.SOUTH);

        add(panelFondo);
    }

    private JButton crearBotonModerno(String texto, String emoji, Color colorFondo) {
        JButton btn = new JButton("<html><center><font size='6'>" + emoji + "</font><br>" + texto + "</center></html>");

        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);

        // Bloque crítico para Windows 11:
        btn.setBackground(colorFondo);
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);

        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Listener único de Hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Al entrar se oscurece un poco para dar feedback
                btn.setBackground(colorFondo.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Al salir vuelve al color original
                btn.setBackground(colorFondo);
            }
        });

        return btn;
    }
}