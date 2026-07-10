package com.trivia.dao;

import com.trivia.model.Question;
import com.trivia.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    /** Devuelve N preguntas en orden aleatorio (para jugar una ronda) */
    public List<Question> getRandomQuestions(int cantidad) {
        List<Question> preguntas = new ArrayList<>();
        String sql = "SELECT * FROM preguntas ORDER BY RAND() LIMIT ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    preguntas.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preguntas;
    }

    /** Devuelve todas las preguntas (por ejemplo, para administración) */
    public List<Question> getAllQuestions() {
        List<Question> preguntas = new ArrayList<>();
        String sql = "SELECT * FROM preguntas ORDER BY id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                preguntas.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preguntas;
    }

    /** Inserta una nueva pregunta en el set */
    public boolean addQuestion(Question q) {
        String sql = "INSERT INTO preguntas "
                + "(pregunta, opcion_a, opcion_b, opcion_c, opcion_d, respuesta_correcta, categoria, dificultad) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, q.getPregunta());
            ps.setString(2, q.getOpcionA());
            ps.setString(3, q.getOpcionB());
            ps.setString(4, q.getOpcionC());
            ps.setString(5, q.getOpcionD());
            ps.setString(6, q.getRespuestaCorrecta());
            ps.setString(7, q.getCategoria());
            ps.setString(8, q.getDificultad());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int countQuestions() {
        String sql = "SELECT COUNT(*) FROM preguntas";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Question mapRow(ResultSet rs) throws SQLException {
        Question q = new Question();
        q.setId(rs.getInt("id"));
        q.setPregunta(rs.getString("pregunta"));
        q.setOpcionA(rs.getString("opcion_a"));
        q.setOpcionB(rs.getString("opcion_b"));
        q.setOpcionC(rs.getString("opcion_c"));
        q.setOpcionD(rs.getString("opcion_d"));
        q.setRespuestaCorrecta(rs.getString("respuesta_correcta"));
        q.setCategoria(rs.getString("categoria"));
        q.setDificultad(rs.getString("dificultad"));
        return q;
    }
}
