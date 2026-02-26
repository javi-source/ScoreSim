package scoresim;

import scoresim.Equipo;

/**
 * Realiza un análisis de probabilidad ejecutando miles de simulaciones
 * para obtener una predicción estadística fiable del resultado.
 */
public class AnalisisMontecarlo {

    private SimuladorPartido simulador;
    private int iteraciones;

    public AnalisisMontecarlo(int iteraciones) {
        this.iteraciones = iteraciones;
        this.simulador = new SimuladorPartido();
    }

    /**
     * Ejecuta el bucle de simulación N veces y recolecta los resultados.
     */
    public ResultadoEstadistico analizar(Equipo local, Equipo visitante) {
        int vLocal = 0;
        int empates = 0;
        int vVisitante = 0;

        for (int i = 0; i < iteraciones; i++) {
            // El simulador devuelve: 1 (Local), 0 (Empate), -1 (Visitante)
            int resultado = simulador.simular(local, visitante);

            if (resultado > 0) vLocal++;
            else if (resultado < 0) vVisitante++;
            else empates++;
        }

        return new ResultadoEstadistico(
                local.getNombre(),
                visitante.getNombre(),
                iteraciones,
                vLocal,
                empates,
                vVisitante
        );
    }

    /**
     * Clase interna para almacenar y formatear los datos finales del análisis.
     */
    public static class ResultadoEstadistico {
        private String nombreLocal;
        private String nombreVisitante;
        private int total;
        private int victoriasLocal;
        private int empates;
        private int victoriasVisitante;

        public ResultadoEstadistico(String local, String visitante, int total, int victoriasLocal, int empates, int victoriasVisitante) {
            this.nombreLocal = local;
            this.nombreVisitante = visitante;
            this.total = total;
            this.victoriasLocal = victoriasLocal;
            this.empates = empates;
            this.victoriasVisitante = victoriasVisitante;
        }

        @Override
        public String toString() {
            // Cálculo de porcentajes para una lectura más humana
            double pctLocal = (victoriasLocal / (double) total) * 100.0;
            double pctVisitante = (victoriasVisitante / (double) total) * 100.0;
            double pctEmpate = (empates / (double) total) * 100.0;

            return String.format(
                    "--- RESULTADOS DEL ANÁLISIS (%d simulaciones) ---\n" +
                            "%s (Local): %.2f%% de victoria (%d)\n" +
                            "Empate: %.2f%% (%d)\n" +
                            "%s (Visitante): %.2f%% de victoria (%d)\n" +
                            "------------------------------------------------",
                    total, nombreLocal, pctLocal, victoriasLocal,
                    pctEmpate, empates, nombreVisitante, pctVisitante, victoriasVisitante
            );
        }
    }
}