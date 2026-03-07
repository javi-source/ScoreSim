package scoresim;

import scoresim.Jugador;
import scoresim.EquipoDAO;
import java.util.List;
import java.util.Random;

public class SimuladorPartido {
    private static final Random random = new Random();
    private static final SimuladorTorneo motorStats = new SimuladorTorneo();
    // TRUE = modo Montecarlo (no escribe en DB, no lee titulares)
    public static boolean modoMontecarlo = false;

    public static int[] simular(Equipo local, Equipo visitante) {
        if (modoMontecarlo) {
            // Modo rápido: solo probabilidades, sin DB
            return simularRapido(local, visitante);
        }

        // Modo completo (simulación única): lee titulares y escribe stats
        List<Jugador> titularesL = EquipoDAO.obtenerTitulares(local.getNombre());
        List<Jugador> titularesV = EquipoDAO.obtenerTitulares(visitante.getNombre());
        registrarPJs(titularesL);
        registrarPJs(titularesV);

        double probLocal = calcularProbabilidad(local, visitante, true);
        double probVisitante = calcularProbabilidad(visitante, local, false);
        int golesL = 0, golesV = 0;

        for (int i = 1; i <= 90; i++) {
            if (random.nextDouble() < probLocal) {
                golesL++;
                asignarEvento(titularesL, "goles");
            }
            if (random.nextDouble() < probVisitante) {
                golesV++;
                asignarEvento(titularesV, "goles");
            }
            if (random.nextDouble() < 0.005) asignarEvento(titularesL, "tarjetas_amarillas");
            if (random.nextDouble() < 0.005) asignarEvento(titularesV, "tarjetas_amarillas");
        }
        procesarStatsPortero(titularesL, golesV);
        procesarStatsPortero(titularesV, golesL);
        return new int[]{golesL, golesV};
    }

    // Nuevo método: simulación pura sin ninguna llamada a DB
    private static int[] simularRapido(Equipo local, Equipo visitante) {
        double probLocal     = calcularProbabilidad(local, visitante, true);
        double probVisitante = calcularProbabilidad(visitante, local, false);
        int golesL = 0, golesV = 0;

        for (int i = 1; i <= 90; i++) {
            if (random.nextDouble() < probLocal)     golesL++;
            if (random.nextDouble() < probVisitante) golesV++;
        }
        return new int[]{golesL, golesV};
    }

    // --- MÉTODOS AUXILIARES DE PERSISTENCIA ---

    private static void asignarEvento(List<Jugador> jugadores, String columna) {
        Jugador seleccionado = motorStats.elegirGoleador(jugadores); // Reutilizamos lógica de peso por posición
        if (seleccionado != null) {
            motorStats.registrarEstadistica(seleccionado.getId(), columna);
        }
    }

    private static void registrarPJs(List<Jugador> jugadores) {
        for(Jugador j : jugadores) {
            motorStats.registrarEstadistica(j.getId(), "partidos_jugados");
        }
    }

    private static void procesarStatsPortero(List<Jugador> jugadores, int golesRecibidos) {
        for(Jugador j : jugadores) {
            if(j.getPosicion().equals("POR")) {
                if(golesRecibidos == 0) motorStats.registrarEstadistica(j.getId(), "porterias_imbatidas");
                // Para goles encajados, necesitamos un método que sume N goles, no solo 1
                motorStats.registrarSumaEstadistica(j.getId(), "goles_encajados", golesRecibidos);
                break;
            }
        }
    }

    private static double calcularProbabilidad(Equipo off, Equipo def, boolean localia) {
        // Fuerza ofensiva vs fuerza defensiva rival, normalizadas sobre 100
        double fuerzaAtaque  = (off.getAtaque() * 0.6) + (off.getMedio() * 0.4);
        double fuerzaDefensa = (def.getDefensa() * 0.6) + (def.getMedio() * 0.4);
        // Base: un equipo de media 75 vs media 75 marca ~1.2 goles/partido
        double base = 0.0133; // 1.2 goles / 90 minutos
        // Ratio de calidad: si atacas con 85 contra defensa de 65, ratio = 85/65 = 1.31
        double ratio = fuerzaAtaque / fuerzaDefensa;
        // Escalar con el ratio: marca diferencia real entre equipos buenos y malos
        double prob = base * ratio;
        // Bonus de localía del 8%
        if (localia) prob *= 1.08;
        // Límites: mínimo 0.4 goles/partido (0.0044/min), máximo 3.5 (0.039/min)
        return Math.max(0.0044, Math.min(0.039, prob));
    }

    /**
     * Simula un partido eliminatorio.
     * Al ser estático, puede llamar a simular() porque ahora también es estático.
     */
    public static ResultadoPartida simularPartidoEliminatorio(Equipo e1, Equipo e2) {
        StringBuilder sb = new StringBuilder();
        sb.append("⚔️ ELIMINATORIA: ").append(e1.getNombre()).append(" vs ").append(e2.getNombre()).append("\n");

        int[] res = simular(e1, e2);
        sb.append("   Resultado: ").append(res[0]).append(" - ").append(res[1]).append("\n");

        if (res[0] > res[1]) {
            sb.append("   ✅ Avanza: ").append(e1.getNombre()).append("\n");
            return new ResultadoPartida(e1, sb.toString());
        }
        if (res[1] > res[0]) {
            sb.append("   ✅ Avanza: ").append(e2.getNombre()).append("\n");
            return new ResultadoPartida(e2, sb.toString());
        }

        // Lógica de penaltis
        sb.append("   ⚖️ Empate... ¡PENALTIS!\n");
        double f1 = e1.getAtaque() + e1.getMedio() + e1.getDefensa();
        double f2 = e2.getAtaque() + e2.getMedio() + e2.getDefensa();

        if ((f1 + random.nextDouble() * 50) > (f2 + random.nextDouble() * 50)) {
            sb.append("   🎯 Gana ").append(e1.getNombre()).append(" en la tanda.\n");
            return new ResultadoPartida(e1, sb.toString());
        } else {
            sb.append("   🎯 Gana ").append(e2.getNombre()).append(" en la tanda.\n");
            return new ResultadoPartida(e2, sb.toString());
        }
    }
    public static class ResultadoPartida {
        public Equipo ganador;
        public String relato;

        public ResultadoPartida(Equipo ganador, String relato) {
            this.ganador = ganador;
            this.relato = relato;
        }
    }

}