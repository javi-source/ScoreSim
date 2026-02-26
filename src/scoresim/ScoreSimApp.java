package scoresim;

import scoresim.Equipo;
import scoresim.AnalisisMontecarlo;

/**
 * Clase principal que orquestra la simulación.
 * Aquí se definen los contendientes y se lanza el motor estadístico.
 */
public class ScoreSimApp {

    public static void main(String[] args) {
        // 1. Creación de los equipos con sus atributos (Ataque, Medio, Defensa)
        // Nota: Asumimos que el constructor de Equipo es (Nombre, Ataque, Medio, Defensa)
        Equipo Argentina = new Equipo("Argentina", 88, 85, 87);
        Equipo Brasil = new Equipo("Brasil", 88, 82, 89);
        Equipo Francia = new Equipo("Francia", 90, 78, 88);
        Equipo España = new Equipo("España", 85, 92, 86);
        Equipo Mexico = new Equipo("Mexico", 78, 76, 75);
        // 2. Definición del número de iteraciones para el Método de Montecarlo
        // A mayor número, mayor precisión pero más tiempo de procesamiento.
        int simulaciones = 10000;

        // 3. Inicialización del analista
        AnalisisMontecarlo analista = new AnalisisMontecarlo(simulaciones);

        System.out.println("Iniciando simulación de alto rendimiento...");
        System.out.println("Enfrentando a " + Argentina.getNombre() + " vs " + Mexico.getNombre());

        // 4. Ejecución del análisis
        // Este proceso llama internamente a SimuladorPartido 10,000 veces
        AnalisisMontecarlo.ResultadoEstadistico resultado = analista.analizar(Argentina, Mexico);

        // 5. Impresión de resultados finales formateados
        System.out.println("\n--- RESULTADOS ESTADÍSTICOS ---");
        System.out.println(resultado.toString());
    }
}