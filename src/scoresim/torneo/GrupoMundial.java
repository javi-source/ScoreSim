package scoresim.torneo;

import scoresim.SimuladorPartido;
import scoresim.Equipo;
import java.util.*;

/**
 * Gestiona la fase de grupos de un torneo.
 * Se encarga de emparejar equipos, registrar resultados y mantener
 * una tabla de posiciones actualizada (puntos, goles, etc.).
 */
public class GrupoMundial {
    private String nombre; // Identificador del grupo (ej: "Grupo A")
    private List<Equipo> equipos;
    private List<String> resultados; // Historial de partidos en formato texto

    // Mapa que vincula a cada equipo con sus estadísticas acumuladas
    private Map<Equipo, EstadisticasGrupo> tabla;

    public GrupoMundial(String nombre) {
        this.nombre = nombre;
        this.equipos = new ArrayList<>();
        this.resultados = new ArrayList<>();
        this.tabla = new HashMap<>();
    }

    /**
     * Registra un equipo en el grupo e inicializa su fila en la tabla.
     */
    public void agregarEquipo(Equipo e) {
        equipos.add(e);
        tabla.put(e, new EstadisticasGrupo());
    }

    /**
     * Ejecuta una vuelta única de todos contra todos (Round Robin simple).
     * Cada equipo se enfrenta a los demás exactamente una vez.
     */
    /**
     * Simula la jornada única (solo ida).
     */
    public void simularJornada() {
        SimuladorPartido motor = new SimuladorPartido();

        for (int i = 0; i < equipos.size(); i++) {
            for (int j = i + 1; j < equipos.size(); j++) {
                Equipo local = equipos.get(i);
                Equipo visitante = equipos.get(j);

                // SOLUCIÓN 2: Implementación de la lógica de goles
                // Simulamos los goles de cada uno de forma independiente
                int golesL = ejecutarSimulacionGoles(motor, local, visitante, true);
                int golesV = ejecutarSimulacionGoles(motor, visitante, local, false);

                // Registro visual del partido
                resultados.add(local.getNombre() + " " + golesL + " - " + golesV + " " + visitante.getNombre());

                // Actualización de la tabla de posiciones
                actualizarEstadisticas(local, golesL, golesV);
                actualizarEstadisticas(visitante, golesV, golesL);
            }
        }
    }

    /**
     * Método auxiliar que extrae la lógica de goles del SimuladorPartido.
     * En un simulador real, esto podría estar dentro de SimuladorPartido,
     * pero aquí lo adaptamos para obtener el número entero de goles.
     */
    private int ejecutarSimulacionGoles(SimuladorPartido motor, Equipo atacante, Equipo defensor, boolean esLocal) {
        int goles = 0;
        // Simulamos 90 minutos de juego
        for (int min = 0; min < 90; min++) {
            // Usamos una probabilidad simplificada basada en el ataque vs defensa
            double prob = 0.01 + (atacante.getAtaque() - defensor.getAtaque()) * 0.0005;
            if (esLocal) prob *= 1.1; // Bono de localía

            if (Math.random() < Math.max(0.005, Math.min(0.08, prob))) {
                goles++;
            }
        }
        return goles;
    }

    /**
     * Actualiza los puntos, goles y partidos jugados en el mapa 'tabla'.
     * @param e Equipo a actualizar.
     * @param favor Goles marcados.
     * @param contra Goles recibidos.
     */
    private void actualizarEstadisticas(Equipo e, int favor, int contra) {
        EstadisticasGrupo stats = tabla.get(e);

        stats.partidos++;
        stats.gFavor += favor;
        stats.gContra += contra;

        if (favor > contra) {
            stats.ganados++;
            stats.puntos += 3; // Victoria: 3 puntos
        } else if (favor == contra) {
            stats.empatados++;
            stats.puntos += 1; // Empate: 1 punto
        } else {
            stats.perdidos++; // Derrota: 0 puntos
        }
    }
    /**
     * Clase interna "POJO" para almacenar el rendimiento de cada equipo.
     * Facilita el cálculo del Goal Average y la clasificación.
     */
    public static class EstadisticasGrupo {
        int puntos = 0;
        int partidos = 0;
        int ganados = 0;
        int empatados = 0;
        int perdidos = 0;
        int gFavor = 0;
        int gContra = 0;

        /**
         * Calcula la diferencia de goles (Criterio de desempate común).
         */
        public int getDiferenciaGoles() {
            return gFavor - gContra;
        }
    }
    /**
     * Genera e imprime la tabla de posiciones actual del grupo.
     * Ordena por: Puntos > Diferencia de Goles > Goles a Favor.
     */
    public void imprimirTabla() {
        // 1. Convertimos los equipos a una lista para poder ordenarlos
        List<Equipo> clasificacion = new ArrayList<>(equipos);

        // 2. Algoritmo de ordenación (Comparator)
        clasificacion.sort((a, b) -> {
            EstadisticasGrupo sA = tabla.get(a);
            EstadisticasGrupo sB = tabla.get(b);

            if (sB.puntos != sA.puntos)
                return Integer.compare(sB.puntos, sA.puntos);

            int difA = sA.getDiferenciaGoles();
            int difB = sB.getDiferenciaGoles();
            if (difB != difA)
                return Integer.compare(difB, difA);

            return Integer.compare(sB.gFavor, sA.gFavor);
        });

        // 3. Diseño de la tabla por consola
        System.out.println("\nTABLA DE POSICIONES - " + nombre.toUpperCase());
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-18s | %2s | %2s | %2s | %2s | %2s | %2s | %3s\n",
                "Selección", "PT", "PJ", "PG", "PE", "PP", "GF", "DIF");
        System.out.println("------------------------------------------------------------");

        for (Equipo e : clasificacion) {
            EstadisticasGrupo s = tabla.get(e);
            System.out.printf("%-18s | %2d | %2d | %2d | %2d | %2d | %2d | %3d\n",
                    e.getNombre(), s.puntos, s.partidos, s.ganados, s.empatados,
                    s.perdidos, s.gFavor, s.getDiferenciaGoles());
        }
        System.out.println("------------------------------------------------------------");
    }
    public List<Equipo> getClasificacion() {
        List<Equipo> clasificados = new ArrayList<>(equipos);

        clasificados.sort((a, b) -> {
            EstadisticasGrupo sA = tabla.get(a);
            EstadisticasGrupo sB = tabla.get(b);

            // Comparar Puntos
            if (sB.puntos != sA.puntos) {
                return Integer.compare(sB.puntos, sA.puntos);
            }
            // Comparar Diferencia de Goles
            int difA = sA.getDiferenciaGoles();
            int difB = sB.getDiferenciaGoles();
            if (difB != difA) {
                return Integer.compare(difB, difA);
            }
            // Comparar Goles a Favor
            return Integer.compare(sB.gFavor, sA.gFavor);
        });

        return clasificados;
    }

    /**
     * Devuelve el equipo que quedó en la tercera posición del grupo
     * tras aplicar los criterios de desempate.
     */
    public Equipo obtenerTercerLugar() {
        List<Equipo> clasificacion = getClasificacion(); // El método que ordena la lista
        return clasificacion.get(2); // Índice 2 es la tercera posición
    }

    /**
     * Devuelve las estadísticas de un equipo específico en este grupo.
     */
    public EstadisticasGrupo getEstadisticas(Equipo e) {
        return tabla.get(e);
    }
    public String getNombre() {
        return this.nombre;
    }
}