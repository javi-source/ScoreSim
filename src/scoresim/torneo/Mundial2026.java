package scoresim.torneo;
import scoresim.torneo.GrupoMundial;
import scoresim.Equipo;
import java.util.*;
import scoresim.EquipoDAO;
/**
 * Configuración oficial y completa de los 12 grupos del Mundial 2026.
 */
public class Mundial2026 {
    public static List<GrupoMundial> crearMundialOficial() {
        List<GrupoMundial> mundial = new ArrayList<>();

// GRUPO A: México, Sudáfrica, Corea del Sur, Ganador Ruta D UEFA
        GrupoMundial grupoA = new GrupoMundial("Grupo A");
        grupoA.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("México"));
        grupoA.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Sudáfrica"));
        grupoA.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Corea del Sur"));
        grupoA.agregarEquipo(new Equipo("UEFA Ruta D", 71, 66, 70, 50.0));
        mundial.add(grupoA);

        // GRUPO B: Canadá, Ruta A UEFA, Catar, Suiza
        GrupoMundial grupoB = new GrupoMundial("Grupo B");
        grupoB.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Canadá"));
        grupoB.agregarEquipo(new Equipo("UEFA Ruta A", 70, 70, 70, 50.0));
        grupoB.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Catar"));
        grupoB.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Suiza"));
        mundial.add(grupoB);

        // GRUPO C: Brasil, Marruecos, Haití, Escocia
        GrupoMundial grupoC = new GrupoMundial("Grupo C");
        grupoC.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Brasil"));
        grupoC.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Marruecos"));
        grupoC.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Haití"));
        grupoC.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Escocia"));
        mundial.add(grupoC);

        // GRUPO D: Estados Unidos, Paraguay, Australia, Ruta C UEFA
        GrupoMundial grupoD = new GrupoMundial("Grupo D");
        grupoD.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Estados Unidos"));
        grupoD.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Paraguay"));
        grupoD.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Australia"));
        grupoD.agregarEquipo(new Equipo("UEFA Ruta C", 70.0, 70.0, 70.0, 50.0));
        mundial.add(grupoD);

        // GRUPO E: Alemania, Curazao, Costa de Marfil, Ecuador
        GrupoMundial grupoE = new GrupoMundial("Grupo E");
        grupoE.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Alemania"));
        grupoE.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Curazao"));
        grupoE.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Costa de Marfil"));
        grupoE.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Ecuador"));
        mundial.add(grupoE);

        // GRUPO F: Países Bajos, Japón, Ruta B UEFA, Túnez
        GrupoMundial grupoF = new GrupoMundial("Grupo F");
        grupoF.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Países Bajos"));
        grupoF.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Japón"));
        grupoF.agregarEquipo(new Equipo("UEFA Ruta B", 65.0, 75.0, 67.0, 50.0));
        grupoF.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Túnez"));
        mundial.add(grupoF);

        // GRUPO G: Bélgica, Egipto, Irán, Nueva Zelanda
        GrupoMundial grupoG = new GrupoMundial("Grupo G");
        grupoG.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Bélgica"));
        grupoG.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Egipto")); // Corregido el punto faltante
        grupoG.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Irán"));
        grupoG.agregarEquipo(new Equipo("Nueva Zelanda", 68.0, 70.0, 65.0, 50.0));
        mundial.add(grupoG);

        // GRUPO H: España, Cabo Verde, Arabia Saudí, Uruguay
        GrupoMundial grupoH = new GrupoMundial("Grupo H");
        grupoH.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("España"));
        grupoH.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Cabo Verde"));
        grupoH.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Arabia Saudí")); // Actualizado según tu lista
        grupoH.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Uruguay"));
        mundial.add(grupoH);

        // GRUPO I: Francia, Senegal, Repesca B, Noruega
        GrupoMundial grupoI = new GrupoMundial("Grupo I");
        grupoI.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Francia"));
        grupoI.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Senegal"));
        grupoI.agregarEquipo(new Equipo("Repesca Llave B", 70.0, 70.0, 70.0, 50.0));
        grupoI.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Noruega"));
        mundial.add(grupoI);

        // GRUPO J: Argentina, Argelia, Austria, Jordania
        GrupoMundial grupoJ = new GrupoMundial("Grupo J");
        grupoJ.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Argentina"));
        grupoJ.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Argelia"));
        grupoJ.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Austria"));
        grupoJ.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Jordania"));
        mundial.add(grupoJ);

        // GRUPO K: Portugal, Repesca A, Uzbekistán, Colombia
        GrupoMundial grupoK = new GrupoMundial("Grupo K");
        grupoK.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Portugal"));
        grupoK.agregarEquipo(new Equipo("Repesca Llave A", 70.0, 68.0, 69.0, 50.0));
        grupoK.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Uzbekistán"));
        grupoK.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Colombia"));
        mundial.add(grupoK);

        // GRUPO L: Inglaterra, Croacia, Ghana, Panamá
        GrupoMundial grupoL = new GrupoMundial("Grupo L");
        grupoL.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Inglaterra"));
        grupoL.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Croacia"));
        grupoL.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Ghana"));
        grupoL.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Panama")); // "Panama" sin tilde según tu lista
        mundial.add(grupoL);

        return mundial;
    }

    /**
     * Compara a los 12 terceros de todos los grupos y devuelve los 8 mejores.
     */
    public static List<Equipo> obtenerMejoresTerceros(List<GrupoMundial> todosLosGrupos) {
        List<Equipo> terceros = new ArrayList<>();
        // Mapa para recordar de qué grupo viene cada tercero y consultar sus stats
        Map<Equipo, GrupoMundial.EstadisticasGrupo> statsTerceros = new HashMap<>();

        // 1. Recolectamos al 3º de cada uno de los 12 grupos
        for (GrupoMundial g : todosLosGrupos) {
            Equipo tercero = g.obtenerTercerLugar();
            terceros.add(tercero);
            statsTerceros.put(tercero, g.getEstadisticas(tercero));
        }

        // 2. Ordenamos el Ranking de Terceros
        terceros.sort((a, b) -> {
            GrupoMundial.EstadisticasGrupo sA = statsTerceros.get(a);
            GrupoMundial.EstadisticasGrupo sB = statsTerceros.get(b);

            // Puntos
            if (sB.puntos != sA.puntos) return Integer.compare(sB.puntos, sA.puntos);
            // Diferencia de Goles
            int difA = sA.gFavor - sA.gContra;
            int difB = sB.gFavor - sB.gContra;
            if (difB != difA) return Integer.compare(difB, difA);
            // Goles a Favor
            return Integer.compare(sB.gFavor, sA.gFavor);
        });

        // 3. Imprimir el ranking por consola
        System.out.println("\n========== RANKING DE MEJORES TERCEROS ==========");
        System.out.printf("%-18s | %2s | %2s | %2s | %2s | %3s\n", "Selección", "PT", "PJ", "GF", "GC", "DIF");
        for (int i = 0; i < terceros.size(); i++) {
            Equipo e = terceros.get(i);
            GrupoMundial.EstadisticasGrupo s = statsTerceros.get(e);
            String marca = (i < 8) ? "[CLASIFICA]" : "[ELIMINADO]";
            System.out.printf("%-18s | %2d | %2d | %2d | %2d | %3d  %s\n",
                    e.getNombre(), s.puntos, s.partidos, s.gFavor, s.gContra, (s.gFavor - s.gContra), marca);
        }

        // 4. Devolvemos solo los primeros 8
        return terceros.subList(0, 8);
    }
        public static List<Equipo> obtenerMejoresTercerosSilencioso(List<GrupoMundial> mundial) {
            List<Equipo> terceros = new ArrayList<>();
            Map<Equipo, GrupoMundial.EstadisticasGrupo> statsTerceros = new HashMap<>();

            for (GrupoMundial g : mundial) {
                Equipo tercero = g.obtenerTercerLugar();
                terceros.add(tercero);
                statsTerceros.put(tercero, g.getEstadisticas(tercero));
            }

            // Ordenar por puntos, diferencia de goles y goles a favor
            terceros.sort((a, b) -> {
                GrupoMundial.EstadisticasGrupo sA = statsTerceros.get(a);
                GrupoMundial.EstadisticasGrupo sB = statsTerceros.get(b);
                if (sB.puntos != sA.puntos) return Integer.compare(sB.puntos, sA.puntos);
                int difA = sA.gFavor - sA.gContra;
                int difB = sB.gFavor - sB.gContra;
                if (difB != difA) return Integer.compare(difB, difA);
                return Integer.compare(sB.gFavor, sA.gFavor);
            });

            return terceros; // Devuelve la lista ordenada pero NO imprime nada
        }




}