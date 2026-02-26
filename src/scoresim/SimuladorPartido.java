package scoresim;

import java.util.Random;
import scoresim.Equipo;

/**
 * Clase encargada de ejecutar la lógica de simulación de un partido de fútbol.
 * Calcula las probabilidades de gol basadas en las estadísticas de los equipos.
 */
public class SimuladorPartido {
    private Random random;

    public SimuladorPartido() {
        this.random = new Random();
    }

    /**
     * Simula un partido de 90 minutos entre dos equipos.
     * * @param local Equipo que juega en casa (recibe bono por localía).
     * @param visitante Equipo que juega fuera.
     * @return 1 si gana el local, -1 si gana el visitante, 0 si hay empate.
     */
    public int simular(Equipo local, Equipo visitante){

        // --- CÁLCULO DE POTENCIALES ---
        // Se calcula el potencial ofensivo sumando el ataque y la mitad del control del mediocampo.
        double ataqLocal = local.getAtaque() + (local.getMedio() * 0.5);
        double ataqVisitante = visitante.getAtaque() + (visitante.getMedio() * 0.5);

        // Se calcula la resistencia defensiva
        double defVisitante = visitante.getDefensa() + (visitante.getMedio() * 0.5);
        double defLocal = local.getDefensa() + (local.getMedio() * 0.5);

        // --- CÁLCULO DE PROBABILIDADES POR MINUTO ---

        // Diferencia de nivel para el equipo local
        double difLocal = ataqLocal - defVisitante;
        // Probabilidad base de anotar en un minuto dado
        double probLocal = 0.01 + (difLocal * 0.0005);
        // Ajuste de límites (min 0.5%, max 8%) y aplicación del 10% de ventaja por campo propio
        probLocal = Math.max(0.005, Math.min(0.08, probLocal * 1.1));

        // Diferencia de nivel para el equipo visitante
        double difVisitante = ataqVisitante - defLocal;
        double probVisitante = 0.01 + (difVisitante * 0.0005);
        // El visitante no recibe el bono del 10%
        probVisitante = Math.max(0.005, Math.min(0.08, probVisitante));


        // --- BUCLE DE SIMULACIÓN (90 MINUTOS) ---
        int golesLocal = 0;
        int golesVisitante = 0;

        for(int i = 0; i < 90; i++){
            // En cada minuto, si un número aleatorio es menor a la probabilidad, se marca gol
            if(Math.random() < probLocal) golesLocal++;
            if(Math.random() < probVisitante) golesVisitante++;
        }

        // Retorna el resultado:
        // 1 (Ganador Local), -1 (Ganador Visitante), 0 (Empate)
        return Integer.compare(golesLocal, golesVisitante);
    }
}