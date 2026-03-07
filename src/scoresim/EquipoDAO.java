package scoresim;

import java.sql.*;
import java.util.*;

public class EquipoDAO {
    private static Map<String, Equipo> cacheEquipos = new HashMap<>();

    public static Equipo obtenerEquipoDesdeDB(String pais) {
        if (cacheEquipos.containsKey(pais)) return cacheEquipos.get(pais);

        // SQL NUEVO: Separa quirúrgicamente el 11 Titular del resto del Banquillo
        String sql = "SELECT " +
                // MEDIAS PURAS DE TITULARES
                "AVG(CASE WHEN rol = 'TITULAR' AND posicion IN ('DC', 'EI', 'ED', 'SD') THEN valoracion_global END) as atk_titu, " +
                "AVG(CASE WHEN rol = 'TITULAR' AND posicion IN ('MC', 'MCO', 'MCD', 'MI', 'MD') THEN valoracion_global END) as med_titu, " +
                "AVG(CASE WHEN rol = 'TITULAR' AND posicion IN ('DFC', 'LI', 'LD', 'CAI', 'CAD', 'POR', 'PO') THEN valoracion_global END) as def_titu, " +
                // MEDIA PURA DE SUPLENTES (Variable independiente)
                "AVG(CASE WHEN rol = 'SUPLENTE' THEN valoracion_global END) as banquillo_avg " +
                "FROM jugadores WHERE nacionalidad = ? AND rol IN ('TITULAR', 'SUPLENTE')";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pais);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Usamos double para no perder precisión en las medias
                double atk = rs.getDouble("atk_titu") > 0 ? rs.getDouble("atk_titu") : 70.0;
                double med = rs.getDouble("med_titu") > 0 ? rs.getDouble("med_titu") : 70.0;
                double def = rs.getDouble("def_titu") > 0 ? rs.getDouble("def_titu") : 70.0;

                // Si una selección no tiene suplentes en la DB, le ponemos un 55 (banquillo débil)
                double ban = rs.getDouble("banquillo_avg") > 0 ? rs.getDouble("banquillo_avg") : 55.0;

                // Creamos el objeto con las 4 variables
                Equipo nuevoEquipo = new Equipo(pais, def, med, atk, ban);
                cacheEquipos.put(pais, nuevoEquipo);
                return nuevoEquipo;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error SQL al calcular stats 11 Titular + Banquillo: " + e.getMessage());
        }

        // Valor por defecto en caso de error total (75 parejo, 70 banquillo)
        return new Equipo(pais, 70.0, 70.0, 70.0, 50.0);
    }

    public static List<Jugador> obtenerTitulares(String pais) {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, posicion, valoracion_global, nacionalidad " +
                "FROM jugadores WHERE nacionalidad = ? AND rol = 'TITULAR'";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pais);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                lista.add(new Jugador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("posicion"),
                        rs.getInt("valoracion_global"),
                        rs.getString("nacionalidad")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public static void limpiarCache() {
        cacheEquipos.clear();
    }

}