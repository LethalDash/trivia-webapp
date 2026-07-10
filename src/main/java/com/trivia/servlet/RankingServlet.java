package com.trivia.servlet;

import com.google.gson.Gson;
import com.trivia.dao.RankingDAO;
import com.trivia.model.RankingEntry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * GET  /ranking             -> muestra la página ranking.jsp con el top 10
 * GET  /ranking?formato=json -> devuelve el top 10 en JSON (usado por trivia.jsp
 *                                al terminar la partida, vía AJAX)
 * POST /ranking             -> guarda el puntaje de una partida (JSON: nombre, puntaje, correctas, totales)
 */
@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {

    private final RankingDAO rankingDAO = new RankingDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<RankingEntry> top = rankingDAO.getTopRanking(10);

        if ("json".equalsIgnoreCase(req.getParameter("formato"))) {
            resp.setContentType("application/json;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.print(gson.toJson(top));
            }
            return;
        }

        req.setAttribute("ranking", top);
        req.getRequestDispatcher("/ranking.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        String nombre = req.getParameter("nombre");
        int puntaje = parseIntSafe(req.getParameter("puntaje"));
        int correctas = parseIntSafe(req.getParameter("correctas"));
        int totales = parseIntSafe(req.getParameter("totales"));

        if (nombre == null || nombre.trim().isEmpty()) {
            nombre = "Anónimo";
        }

        boolean ok = rankingDAO.addScore(nombre.trim(), puntaje, correctas, totales);

        try (PrintWriter out = resp.getWriter()) {
            out.print("{\"ok\":" + ok + "}");
        }
    }

    private int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
}
