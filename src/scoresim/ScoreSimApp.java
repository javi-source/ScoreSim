package scoresim;

import scoresim.torneo.GrupoMundial;
import scoresim.torneo.Mundial2026;

import javax.swing.*;
import java.util.*;

public class ScoreSimApp {

    // Método principal original para seguir lanzándolo desde consola
    public static void main(String[] args) {
        try {
            // Esto pone el estilo del sistema operativo actual
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new scoresim.visual.VentanaPrincipal().setVisible(true);
        });
    }

    // Este método ejecutarSimulacionDesdeUI es el que llamará el botón de la ventana visual.

    public static Map<String, EstadisticasEquipo> ejecutarSimulacionDesdeUI() {
        int numSimulaciones = 10000;
        Map<String, EstadisticasEquipo> historialGlobal = new HashMap<>();

        System.out.println("Iniciando simulación masiva de 10,000 Mundiales...");
        long tiempoInicio = System.currentTimeMillis();

        for (int i = 0; i < numSimulaciones; i++) {
            // crearMundialOficial ahora usará el EquipoDAO con los cambios de Lamine Yamal
            List<GrupoMundial> listaGrupos = Mundial2026.crearMundialOficial();
            Map<String, Equipo> p = new HashMap<>();

            for (GrupoMundial g : listaGrupos) {
                // Pasamos el motor de simulación para que sea eficiente
                g.simularJornada();
                List<Equipo> clasificados = g.getClasificacion();

                String nombreG = g.getNombre();
                String letra = nombreG.substring(nombreG.length() - 1);

                p.put("1" + letra, clasificados.get(0));
                p.put("2" + letra, clasificados.get(1));
                p.put("3" + letra, clasificados.get(2));

                contarFase(historialGlobal, clasificados.get(3).getNombre(), "grupos");
            }

            List<Equipo> tercerosRanked = Mundial2026.obtenerMejoresTercerosSilencioso(listaGrupos);
            for (int j = 8; j < tercerosRanked.size(); j++) {
                contarFase(historialGlobal, tercerosRanked.get(j).getNombre(), "grupos");
            }

            ejecutarEliminatorias(p, tercerosRanked, historialGlobal);
        }

        long tiempoFin = System.currentTimeMillis();
        imprimirReporteFinal(historialGlobal, numSimulaciones, tiempoFin - tiempoInicio);
        return historialGlobal;
    }

    private static void ejecutarEliminatorias(Map<String, Equipo> p, List<Equipo> t, Map<String, EstadisticasEquipo> h) {
        // DIECISEISAVOS (Perdedores van a historial como "fuera en 16vos")
        Equipo p73 = resolver(p.get("2A"), p.get("2B"), h, "16vos");
        Equipo p74 = resolver(p.get("1E"), t.get(0), h, "16vos");
        Equipo p75 = resolver(p.get("1F"), p.get("2C"), h, "16vos");
        Equipo p76 = resolver(p.get("1C"), p.get("2F"), h, "16vos");
        Equipo p77 = resolver(p.get("1I"), t.get(1), h, "16vos");
        Equipo p78 = resolver(p.get("2E"), p.get("2I"), h, "16vos");
        Equipo p79 = resolver(p.get("1A"), t.get(2), h, "16vos");
        Equipo p80 = resolver(p.get("1L"), t.get(3), h, "16vos");
        Equipo p81 = resolver(p.get("1D"), t.get(4), h, "16vos");
        Equipo p82 = resolver(p.get("1G"), t.get(5), h, "16vos");
        Equipo p83 = resolver(p.get("2K"), p.get("2L"), h, "16vos");
        Equipo p84 = resolver(p.get("1H"), p.get("2J"), h, "16vos");
        Equipo p85 = resolver(p.get("1B"), t.get(6), h, "16vos");
        Equipo p86 = resolver(p.get("1J"), p.get("2H"), h, "16vos");
        Equipo p87 = resolver(p.get("1K"), t.get(7), h, "16vos");
        Equipo p88 = resolver(p.get("2D"), p.get("2G"), h, "16vos");

        // OCTAVOS
        Equipo p89 = resolver(p74, p77, h, "8vos");
        Equipo p90 = resolver(p73, p75, h, "8vos");
        Equipo p91 = resolver(p76, p78, h, "8vos");
        Equipo p92 = resolver(p79, p80, h, "8vos");
        Equipo p93 = resolver(p83, p84, h, "8vos");
        Equipo p94 = resolver(p81, p82, h, "8vos");
        Equipo p95 = resolver(p86, p88, h, "8vos");
        Equipo p96 = resolver(p85, p87, h, "8vos");

        // CUARTOS
        Equipo p97 = resolver(p89, p90, h, "4tos");
        Equipo p98 = resolver(p93, p94, h, "4tos");
        Equipo p99 = resolver(p91, p92, h, "4tos");
        Equipo p100 = resolver(p95, p96, h, "4tos");

        // SEMIFINALES
        Equipo p101 = resolver(p97, p98, h, "semis");
        Equipo p102 = resolver(p99, p100, h, "semis");

        // FINAL
        Equipo campeon = resolver(p101, p102, h, "finalista");
        contarFase(h, campeon.getNombre(), "campeon");
    }

    private static Equipo resolver(Equipo e1, Equipo e2, Map<String, EstadisticasEquipo> h, String fase) {
        Equipo ganador = SimuladorPartido.simularPartidoEliminatorio(e1, e2);
        Equipo perdedor = (ganador == e1) ? e2 : e1;
        contarFase(h, perdedor.getNombre(), fase);
        return ganador;
    }

    private static void contarFase(Map<String, EstadisticasEquipo> mapa, String nombre, String fase) {
        mapa.putIfAbsent(nombre, new EstadisticasEquipo());
        EstadisticasEquipo stats = mapa.get(nombre);
        switch (fase) {
            case "grupos" -> stats.fueraEnGrupos++;
            case "16vos" -> stats.fueraEn16vos++;
            case "8vos" -> stats.fueraEn8vos++;
            case "4tos" -> stats.fueraEn4tos++;
            case "semis" -> stats.fueraEnSemis++;
            case "finalista" -> stats.finalista++;
            case "campeon" -> stats.campeon++;
        }
    }

    private static void imprimirReporteFinal(Map<String, EstadisticasEquipo> mapa, int total, long tiempo) {
        System.out.println("\n" + "=".repeat(115));
        // Cabecera de la tabla con todas las fases
        System.out.printf("%-18s | %-8s | %-8s | %-8s | %-8s | %-8s | %-8s | %-8s\n",
                "Selección", "CAMPEÓN", "SUBCAMPEÓN", "SEMIS", "CUARTOS", "OCTAVOS", "16VOS", "GRUPOS");
        System.out.println("=".repeat(115));

        mapa.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue().campeon, a.getValue().campeon))
                .limit(32) // Mostramos el Top 32 para que quepa en pantalla
                .forEach(entry -> {
                    EstadisticasEquipo s = entry.getValue();
                    System.out.printf("%-18s | %7.2f%% | %7.2f%% | %7.2f%% | %7.2f%% | %7.2f%% | %7.2f%% | %7.2f%%\n",
                            entry.getKey(),
                            (s.campeon * 100.0 / total),
                            (s.finalista * 100.0 / total),
                            (s.fueraEnSemis * 100.0 / total),
                            (s.fueraEn4tos * 100.0 / total),
                            (s.fueraEn8vos * 100.0 / total),
                            (s.fueraEn16vos * 100.0 / total),
                            (s.fueraEnGrupos * 100.0 / total)
                    );
                });

        System.out.println("=".repeat(115));
        System.out.printf("Simulación de %d mundiales completada en: %.3f segundos.\n", total, (tiempo / 1000.0));
    }
}