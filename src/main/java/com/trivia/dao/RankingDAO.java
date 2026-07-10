package com.trivia.dao;

import com.trivia.model.RankingEntry;
import com.trivia.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankingDAO {

    /** Guarda el resultado de una partida */
    public boolean addScore(String nombre, int puntaje, int correctas, int totales) {
        String sql = "INSERT INTO ranking (nombre_jugador, puntaje, preguntas_correctas, preguntas_totales) "
                + "VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setInt(2, puntaje);
            ps.setInt(3, correctas);
            ps.setInt(4, totales);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** Devuelve el top N de puntajes, ordenado de mayor a menor */
    public List<RankingEntry> getTopRanking(int limite) {
        List<RankingEntry> lista = new ArrayList<>();
        String sql = "SELECT * FROM ranking ORDER BY puntaje DESC, fecha_partida ASC LIMIT ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, limite);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RankingEntry r = new RankingEntry();
                    r.setId(rs.getInt("id"));
                    r.setNombreJugador(rs.getString("nombre_jugador"));
                    r.setPuntaje(rs.getInt("puntaje"));
                    r.setPreguntasCorrectas(rs.getInt("preguntas_correctas"));
                    r.setPreguntasTotales(rs.getInt("preguntas_totales"));
                    r.setFechaPartida(rs.getTimestamp("fecha_partida"));
                    lista.add(r);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
