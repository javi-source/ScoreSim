package scoresim.torneo;
import scoresim.SimuladorPartido;
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
        grupoG.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Egipto"));
        grupoG.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Irán"));
        grupoG.agregarEquipo(new Equipo("Nueva Zelanda", 68.0, 70.0, 65.0, 50.0));
        mundial.add(grupoG);

        // GRUPO H: España, Cabo Verde, Arabia Saudí, Uruguay
        GrupoMundial grupoH = new GrupoMundial("Grupo H");
        grupoH.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("España"));
        grupoH.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Cabo Verde"));
        grupoH.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Arabia Saudí"));
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
        grupoL.agregarEquipo(EquipoDAO.obtenerEquipoDesdeDB("Panama"));
        mundial.add(grupoL);

        return mundial;
    }

    public static List<Equipo> obtenerMejoresTerceros(List<GrupoMundial> todosLosGrupos) {
        List<Equipo> terceros = new ArrayList<>();
        Map<Equipo, GrupoMundial.EstadisticasGrupo> statsTerceros = new HashMap<>();

        for (GrupoMundial g : todosLosGrupos) {
            Equipo tercero = g.obtenerTercerLugar();
            terceros.add(tercero);
            statsTerceros.put(tercero, g.getEstadisticas(tercero));
        }

        terceros.sort((a, b) -> {
            GrupoMundial.EstadisticasGrupo sA = statsTerceros.get(a);
            GrupoMundial.EstadisticasGrupo sB = statsTerceros.get(b);
            if (sB.puntos != sA.puntos) return Integer.compare(sB.puntos, sA.puntos);
            int difA = sA.gFavor - sA.gContra;
            int difB = sB.gFavor - sB.gContra;
            if (difB != difA) return Integer.compare(difB, difA);
            return Integer.compare(sB.gFavor, sA.gFavor);
        });

        System.out.println("\n========== RANKING DE MEJORES TERCEROS ==========");
        System.out.printf("%-18s | %2s | %2s | %2s | %2s | %3s\n", "Selección", "PT", "PJ", "GF", "GC", "DIF");
        for (int i = 0; i < terceros.size(); i++) {
            Equipo e = terceros.get(i);
            GrupoMundial.EstadisticasGrupo s = statsTerceros.get(e);
            String marca = (i < 8) ? "[CLASIFICA]" : "[ELIMINADO]";
            System.out.printf("%-18s | %2d | %2d | %2d | %2d | %3d  %s\n",
                    e.getNombre(), s.puntos, s.partidos, s.gFavor, s.gContra, (s.gFavor - s.gContra), marca);
        }

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

        terceros.sort((a, b) -> {
            GrupoMundial.EstadisticasGrupo sA = statsTerceros.get(a);
            GrupoMundial.EstadisticasGrupo sB = statsTerceros.get(b);
            if (sB.puntos != sA.puntos) return Integer.compare(sB.puntos, sA.puntos);
            int difA = sA.gFavor - sA.gContra;
            int difB = sB.gFavor - sB.gContra;
            if (difB != difA) return Integer.compare(difB, difA);
            return Integer.compare(sB.gFavor, sA.gFavor);
        });

        return terceros;
    }

    public String ejecutarSegundaFase(List<GrupoMundial> mundial) {
        StringBuilder reporte = new StringBuilder();

        // 1. RECOLECTAR CLASIFICADOS
        List<Equipo> tercerosParaSorteo = Mundial2026.obtenerMejoresTercerosSilencioso(mundial);
        Map<String, Equipo> primeros = new HashMap<>();
        Map<String, Equipo> segundos = new HashMap<>();

        for (GrupoMundial g : mundial) {
            List<Equipo> clasificadosGrupo = g.getClasificacion();
            String letra = g.getNombre().replace("Grupo ", "");
            primeros.put(letra, clasificadosGrupo.get(0));
            segundos.put(letra, clasificadosGrupo.get(1));
        }
        // 0. FASE DE GRUPOS
        reporte.append("═".repeat(55)).append("\n");
        reporte.append("         ⚽  FASE DE GRUPOS  ⚽\n");
        reporte.append("═".repeat(55)).append("\n");
        for (GrupoMundial g : mundial) {
            // Partidos del grupo
            reporte.append("\n▶ PARTIDOS - ").append(g.getNombre().toUpperCase()).append("\n");
            for (String r : g.getResultados()) {
                reporte.append("  ").append(r).append("\n");
            }
            // Tabla de posiciones
            reporte.append(g.getTablaComoTexto());
        }

        reporte.append("\n--- 🏆 DIECISEISAVOS DE FINAL ---\n");
        List<Equipo> ganadoresR32 = new ArrayList<>();

        // ─── DIECISEISAVOS ───
// P73: 2A vs 2B
        ganadoresR32.add(simularYRegistrar(segundos.get("A"),  segundos.get("B"),          reporte));
// P74: 1E vs 3(A/B/C/D/F)
        ganadoresR32.add(simularYRegistrar(primeros.get("E"),  tercerosParaSorteo.get(0),  reporte));
// P75: 1F vs 2C
        ganadoresR32.add(simularYRegistrar(primeros.get("F"),  segundos.get("C"),          reporte));
// P76: 1C vs 2F
        ganadoresR32.add(simularYRegistrar(primeros.get("C"),  segundos.get("F"),          reporte));
// P77: 1I vs 3(C/D/F/G/H)
        ganadoresR32.add(simularYRegistrar(primeros.get("I"),  tercerosParaSorteo.get(1),  reporte));
// P78: 2E vs 2I
        ganadoresR32.add(simularYRegistrar(segundos.get("E"),  segundos.get("I"),          reporte));
// P79: 1A vs 3(C/E/F/H/I)
        ganadoresR32.add(simularYRegistrar(primeros.get("A"),  tercerosParaSorteo.get(2),  reporte));
// P80: 1L vs 3(E/H/I/J/K)
        ganadoresR32.add(simularYRegistrar(primeros.get("L"),  tercerosParaSorteo.get(3),  reporte));
// P81: 1D vs 3(B/E/F/I/J)
        ganadoresR32.add(simularYRegistrar(primeros.get("D"),  tercerosParaSorteo.get(4),  reporte));
// P82: 1G vs 3(A/E/H/I/J)
        ganadoresR32.add(simularYRegistrar(primeros.get("G"),  tercerosParaSorteo.get(5),  reporte));
// P83: 2K vs 2L
        ganadoresR32.add(simularYRegistrar(segundos.get("K"),  segundos.get("L"),          reporte));
// P84: 1H vs 2J
        ganadoresR32.add(simularYRegistrar(primeros.get("H"),  segundos.get("J"),          reporte));
// P85: 1B vs 3(E/F/G/I/J)
        ganadoresR32.add(simularYRegistrar(primeros.get("B"),  tercerosParaSorteo.get(6),  reporte));
// P86: 1J vs 2H
        ganadoresR32.add(simularYRegistrar(primeros.get("J"),  segundos.get("H"),          reporte));
// P87: 1K vs 3(D/E/I/J/L)
        ganadoresR32.add(simularYRegistrar(primeros.get("K"),  tercerosParaSorteo.get(7),  reporte));
// P88: 2D vs 2G
        ganadoresR32.add(simularYRegistrar(segundos.get("D"),  segundos.get("G"),          reporte));


        reporte.append("\n").append("═".repeat(55)).append("\n\n");
        reporte.append("\n✅ Dieciseisavos completados.\n");

        // 3. OCTAVOS
        reporte.append("\n--- 🏟️ OCTAVOS DE FINAL ---\n");
        List<Equipo> ganadoresR16 = new ArrayList<>();
        for (int i = 0; i < ganadoresR32.size(); i += 2) {
            ganadoresR16.add(simularYRegistrar(ganadoresR32.get(i), ganadoresR32.get(i+1), reporte));
        }

        // 4. CUARTOS
        reporte.append("\n--- 📊 CUARTOS DE FINAL ---\n");
        List<Equipo> ganadoresR8 = new ArrayList<>();
        for (int i = 0; i < ganadoresR16.size(); i += 2) {
            ganadoresR8.add(simularYRegistrar(ganadoresR16.get(i), ganadoresR16.get(i+1), reporte));
        }

        // 5. SEMIFINALES
        reporte.append("\n--- ⚡ SEMIFINALES ---\n");
        Equipo ganadorS1 = simularYRegistrar(ganadoresR8.get(0), ganadoresR8.get(1), reporte);
        Equipo perdedorS1 = (ganadorS1 == ganadoresR8.get(0)) ? ganadoresR8.get(1) : ganadoresR8.get(0);

        Equipo ganadorS2 = simularYRegistrar(ganadoresR8.get(2), ganadoresR8.get(3), reporte);
        Equipo perdedorS2 = (ganadorS2 == ganadoresR8.get(2)) ? ganadoresR8.get(3) : ganadoresR8.get(2);

        // 6. TERCER PUESTO
        reporte.append("\n🥉 TERCER PUESTO:\n");
        Equipo tercerPuesto = simularYRegistrar(perdedorS1, perdedorS2, reporte);

        // 7. GRAN FINAL
        reporte.append("\n🏆 GRAN FINAL:\n");
        Equipo campeon = simularYRegistrar(ganadorS1, ganadorS2, reporte);
        Equipo subcampeon = (campeon == ganadorS1) ? ganadorS2 : ganadorS1;

        // --- CUADRO DE HONOR EN EL REPORTE ---
        reporte.append("\n").append("=".repeat(35)).append("\n");
        reporte.append("🎊 ¡FINAL DEL MUNDIAL 2026! 🎊\n");
        reporte.append("=".repeat(35)).append("\n");
        reporte.append("🥇 CAMPEÓN:    ").append(campeon.getNombre().toUpperCase()).append("\n");
        reporte.append("🥈 SUBCAMPEÓN: ").append(subcampeon.getNombre().toUpperCase()).append("\n");
        reporte.append("🥉 3er PUESTO: ").append(tercerPuesto.getNombre().toUpperCase()).append("\n");
        reporte.append("=".repeat(35)).append("\n");

        return reporte.toString();
    }

    /**
     * Método auxiliar para simular y registrar el relato en el reporte.
     */
    private Equipo simularYRegistrar(Equipo e1, Equipo e2, StringBuilder reporte) {
        // Llamamos al motor que ahora devuelve ResultadoPartida
        SimuladorPartido.ResultadoPartida rp = SimuladorPartido.simularPartidoEliminatorio(e1, e2);
        // Añadimos el relato (con ⚔️ y resultados) al reporte global
        reporte.append(rp.relato);
        // Devolvemos el equipo ganador para la lógica de listas
        return rp.ganador;
    }
}