package scoresim.visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EstiloUI {

    // Paleta de colores 2026
    public static final Color AZUL_PRIMARY = new Color(52, 152, 219);
    public static final Color AZUL_HOVER = new Color(41, 128, 185);
    public static final Color VERDE_SUCCESS = new Color(46, 204, 113);
    public static final Color VERDE_HOVER = new Color(39, 174, 96);
    public static final Color BLANCO = Color.WHITE;
    public static final Color FONDO_TABLA = new Color(245, 245, 245);

    /**
     * Aplica un estilo moderno a los botones con efectos de iluminación al pasar el ratón.
     */
    public static void prepararBoton(JButton boton, Color colorBase, Color colorHover) {
        boton.setBackground(colorBase);
        boton.setForeground(BLANCO);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setBorderPainted(false);

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { boton.setBackground(colorHover); }
            @Override
            public void mouseExited(MouseEvent e) { boton.setBackground(colorBase); }
            @Override
            public void mousePressed(MouseEvent e) { boton.setBackground(colorBase.darker()); }
        });
    }

    /**
     * Da un aspecto limpio a los campos de texto eliminando bordes toscos.
     */
    public static void prepararCampoTexto(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    /**
     * Estiliza la tabla para que parezca una aplicación moderna.
     */
    public static void prepararTabla(JTable tabla) {
        tabla.setRowHeight(25);
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setSelectionBackground(new Color(232, 242, 254));
        tabla.setShowVerticalLines(false);

        // --- CONFIGURACIÓN DEL ENCABEZADO (Los títulos) ---
        JTableHeader header = tabla.getTableHeader();
        header.setOpaque(false); // Necesario en algunos sistemas para que el color de fondo se vea
        header.setBackground(AZUL_PRIMARY);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(header.getWidth(), 30)); // Da más altura al título
    }
}