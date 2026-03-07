package scoresim;

import scoresim.Jugador; // Asegúrate de importar tu modelo de Jugador
import java.sql.*;
import java.util.List;
import java.util.Random;

public class SimuladorTorneo {

    private Random random = new Random();

    /**
     * Elige qué jugador de los titulares marca el gol.
     * Basado en posición y media (OVR) de forma equilibrada.
     */
    public Jugador elegirGoleador(List<Jugador> titulares) {
        if (titulares == null || titulares.isEmpty()) return null;

        double sumaPesos = 0;
        for (Jugador j : titulares) {
            sumaPesos += calcularPesoFinal(j);
        }

        double randomValue = random.nextDouble() * sumaPesos;
        double acumulado = 0;

        for (Jugador j : titulares) {
            acumulado += calcularPesoFinal(j);
            if (randomValue <= acumulado) {
                return j;
            }
        }
        return titulares.get(0);
    }

    private double calcularPesoFinal(Jugador j) {
        double pesoPosicion = obtenerPesoPorPosicion(j.getPosicion());
        // Usamos factor lineal: Media / 100 (Ej: 85 OVR = 0.85)
        double factorCalidad = j.getValoracionGlobal() / 100.0;

        return pesoPosicion * factorCalidad;
    }

    private double obtenerPesoPorPosicion(String pos) {
        return switch (pos.toUpperCase()) {
            case "DC", "EI", "ED", "SD" -> 70.0;
            case "MCO", "MI", "MD"     -> 25.0;
            case "MC", "MCD"           -> 10.0;
            case "DFC", "LI", "LD", "CAI", "CAD" -> 5.0;
            case "POR", "PO"           -> 0.1;
            default -> 5.0;
        };
    }



    public void resetearEstadisticasTorneo() {
        try (Connection conn = ConexionBD.conectar();
             Statement st = conn.createStatement()) {

            // Simplemente vaciamos. La primera vez que alguien juegue o marque,
            // el método 'registrarSumaEstadistica' creará la fila gracias al ON DUPLICATE KEY.
            st.executeUpdate("TRUNCATE TABLE estadisticas_simulacion");

            System.out.println("✅ Historial de estadísticas limpiado por completo.");
        } catch (SQLException e) {
            System.err.println("Error al resetear stats: " + e.getMessage());
        }
    }
    /**
     * Registra el incremento de 1 unidad en una estadística (Gol, Amarilla, PJ, etc.)
     */
    public void registrarEstadistica(int jugadorId, String columna) {
        registrarSumaEstadistica(jugadorId, columna, 1);
    }

    /**
     * Método maestro que actualiza cualquier columna con cualquier cantidad.
     * Si el jugador no tiene fila en la tabla, la crea automáticamente.
     */
    public void registrarSumaEstadistica(int jugadorId, String columna, int cantidad) {
        // Evitamos procesar si no hay cambios reales (excepto para porterías a cero)
        if (cantidad <= 0 && !columna.equals("porterias_imbatidas")) return;

        // Usamos INSERT ... ON DUPLICATE KEY UPDATE para que sea atómico y seguro
        String sql = "INSERT INTO estadisticas_simulacion (jugador_id, " + columna + ") " +
                "VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE " + columna + " = " + columna + " + ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, jugadorId);
            pstmt.setInt(2, cantidad);
            pstmt.setInt(3, cantidad);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error SQL en columna [" + columna + "]: " + e.getMessage());
        }
    }

    /**
     * Mantenemos este por compatibilidad, pero ahora redirige al método maestro
     */
    public void registrarGolEnDB(int jugadorId) {
        registrarEstadistica(jugadorId, "goles");
    }
}