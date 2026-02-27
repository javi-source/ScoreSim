package scoresim.torneo;
import scoresim.torneo.GrupoMundial;
import scoresim.Equipo;
import java.util.*;

/**
 * Configuración oficial y completa de los 12 grupos del Mundial 2026.
 */
public class Mundial2026 {
    public static List<GrupoMundial> crearMundialOficial() {
        List<GrupoMundial> mundial = new ArrayList<>();

        // GRUPO A: México, Sudáfrica, Corea del Sur, Ganador Ruta D UEFA
        GrupoMundial grupoA = new GrupoMundial("Grupo A");
        grupoA.agregarEquipo(new Equipo("México", 79, 81, 78));
        grupoA.agregarEquipo(new Equipo("Sudáfrica", 72, 74, 73));
        grupoA.agregarEquipo(new Equipo("Corea del Sur", 78, 80, 79));
        grupoA.agregarEquipo(new Equipo("UEFA Ruta D", 75, 76, 75));
        mundial.add(grupoA);

        // GRUPO B: Canadá, Ruta A UEFA, Catar, Suiza
        GrupoMundial grupoB = new GrupoMundial("Grupo B");
        grupoB.agregarEquipo(new Equipo("Canadá", 77, 78, 76));
        grupoB.agregarEquipo(new Equipo("UEFA Ruta A", 81, 80, 80));
        grupoB.agregarEquipo(new Equipo("Catar", 71, 73, 70));
        grupoB.agregarEquipo(new Equipo("Suiza", 82, 83, 82));
        mundial.add(grupoB);

        // GRUPO C: Brasil, Marruecos, Haití, Escocia
        GrupoMundial grupoC = new GrupoMundial("Grupo C");
        grupoC.agregarEquipo(new Equipo("Brasil", 90, 87, 88));
        grupoC.agregarEquipo(new Equipo("Marruecos", 82, 84, 85));
        grupoC.agregarEquipo(new Equipo("Haití", 68, 65, 66));
        grupoC.agregarEquipo(new Equipo("Escocia", 76, 79, 78));
        mundial.add(grupoC);

        // GRUPO D: Estados Unidos, Paraguay, Australia, Ruta C UEFA
        GrupoMundial grupoD = new GrupoMundial("Grupo D");
        grupoD.agregarEquipo(new Equipo("Estados Unidos", 81, 82, 80));
        grupoD.agregarEquipo(new Equipo("Paraguay", 76, 77, 79));
        grupoD.agregarEquipo(new Equipo("Australia", 75, 76, 77));
        grupoD.agregarEquipo(new Equipo("UEFA Ruta C", 78, 79, 78));
        mundial.add(grupoD);

        // GRUPO E: Alemania, Curazao, Costa de Marfil, Ecuador
        GrupoMundial grupoE = new GrupoMundial("Grupo E");
        grupoE.agregarEquipo(new Equipo("Alemania", 87, 88, 85));
        grupoE.agregarEquipo(new Equipo("Curazao", 65, 66, 65));
        grupoE.agregarEquipo(new Equipo("Costa de Marfil", 80, 81, 79));
        grupoE.agregarEquipo(new Equipo("Ecuador", 79, 81, 82));
        mundial.add(grupoE);

        // GRUPO F: Países Bajos, Japón, Ruta B UEFA, Túnez
        GrupoMundial grupoF = new GrupoMundial("Grupo F");
        grupoF.agregarEquipo(new Equipo("Países Bajos", 86, 88, 87));
        grupoF.agregarEquipo(new Equipo("Japón", 81, 82, 80));
        grupoF.agregarEquipo(new Equipo("UEFA Ruta B", 79, 78, 77));
        grupoF.agregarEquipo(new Equipo("Túnez", 74, 75, 75));
        mundial.add(grupoF);

        // GRUPO G: Bélgica, Egipto, Irán, Nueva Zelanda
        GrupoMundial grupoG = new GrupoMundial("Grupo G");
        grupoG.agregarEquipo(new Equipo("Bélgica", 84, 85, 82));
        grupoG.agregarEquipo(new Equipo("Egipto", 79, 77, 76));
        grupoG.agregarEquipo(new Equipo("Irán", 75, 76, 78));
        grupoG.agregarEquipo(new Equipo("Nueva Zelanda", 68, 70, 71));
        mundial.add(grupoG);

        // GRUPO H: España, Cabo Verde, Arabia Saudita, Uruguay
        GrupoMundial grupoH = new GrupoMundial("Grupo H");
        grupoH.agregarEquipo(new Equipo("España", 88, 94, 86));
        grupoH.agregarEquipo(new Equipo("Cabo Verde", 70, 71, 72));
        grupoH.agregarEquipo(new Equipo("Arabia Saudita", 73, 75, 72));
        grupoH.agregarEquipo(new Equipo("Uruguay", 85, 84, 86));
        mundial.add(grupoH);

        // GRUPO I: Francia, Senegal, Repesca B, Noruega
        GrupoMundial grupoI = new GrupoMundial("Grupo I");
        grupoI.agregarEquipo(new Equipo("Francia", 91, 83, 89));
        grupoI.agregarEquipo(new Equipo("Senegal", 80, 81, 81));
        grupoI.agregarEquipo(new Equipo("Repesca Llave B", 74, 73, 72));
        grupoI.agregarEquipo(new Equipo("Noruega", 82, 80, 78));
        mundial.add(grupoI);

        // GRUPO J: Argentina, Argelia, Austria, Jordania
        GrupoMundial grupoJ = new GrupoMundial("Grupo J");
        grupoJ.agregarEquipo(new Equipo("Argentina", 88, 88, 91));
        grupoJ.agregarEquipo(new Equipo("Argelia", 78, 79, 77));
        grupoJ.agregarEquipo(new Equipo("Austria", 81, 82, 80));
        grupoJ.agregarEquipo(new Equipo("Jordania", 70, 71, 69));
        mundial.add(grupoJ);

        // GRUPO K: Portugal, Repesca A, Uzbekistán, Colombia
        GrupoMundial grupoK = new GrupoMundial("Grupo K");
        grupoK.agregarEquipo(new Equipo("Portugal", 88, 87, 85));
        grupoK.agregarEquipo(new Equipo("Repesca Llave A", 74, 73, 72));
        grupoK.agregarEquipo(new Equipo("Uzbekistán", 73, 75, 74));
        grupoK.agregarEquipo(new Equipo("Colombia", 83, 81, 82));
        mundial.add(grupoK);

        // GRUPO L: Inglaterra, Croacia, Ghana, Panamá
        GrupoMundial grupoL = new GrupoMundial("Grupo L");
        grupoL.agregarEquipo(new Equipo("Inglaterra", 88, 87, 87));
        grupoL.agregarEquipo(new Equipo("Croacia", 79, 88, 81));
        grupoL.agregarEquipo(new Equipo("Ghana", 76, 77, 75));
        grupoL.agregarEquipo(new Equipo("Panamá", 73, 74, 73));
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

        // 3. Imprimir el ranking por consola para que el usuario lo vea
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