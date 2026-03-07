package scoresim.visual;

import scoresim.EstadisticasEquipo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*; // Necesario para BorderLayout si lo usas
import java.util.Map;
import java.util.Vector;

public class VentanaReporte extends JDialog {
    public VentanaReporte(Map<String, EstadisticasEquipo> historial, int total) {
        setTitle("Resultados de la Simulación - ScoreSim 2026");
        setSize(900, 600); // Un poco más grande para que quepan los 48
        setLocationRelativeTo(null);

        String[] columnas = {"Selección", "🏆 CAMPEÓN", "🥈 FINAL", "🥉 SEMIS", "CUARTOS", "OCTAVOS", "16VOS"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        // LLENAR LA TABLA (Sin el límite de 32 para que salga Ghana y los demás)
        historial.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue().campeon, a.getValue().campeon))
                .forEach(entry -> { // <--- HEMOS QUITADO EL .limit(32)
                    EstadisticasEquipo s = entry.getValue();
                    Vector<Object> fila = new Vector<>();

                    // Calculamos los porcentajes basándonos en el total de simulaciones
                    fila.add(entry.getKey());
                    fila.add(String.format("%.2f%%", (s.campeon * 100.0) / total));
                    fila.add(String.format("%.2f%%", (s.finalista * 100.0) / total));
                    fila.add(String.format("%.2f%%", (s.fueraEnSemis * 100.0) / total));
                    fila.add(String.format("%.2f%%", (s.fueraEn4tos * 100.0) / total));
                    fila.add(String.format("%.2f%%", (s.fueraEn8vos * 100.0) / total));
                    fila.add(String.format("%.2f%%", (s.fueraEn16vos * 100.0) / total));

                    modelo.addRow(fila);
                });

        JTable tabla = new JTable(modelo);

        // --- MEJORA VISUAL ---
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabla.setRowHeight(25);

        add(new JScrollPane(tabla));

        // Hacerla modal para que el usuario la vea antes de seguir
        setModal(true);
    }
}