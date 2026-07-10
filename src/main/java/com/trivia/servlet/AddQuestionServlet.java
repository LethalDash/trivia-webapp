package com.trivia.servlet;

import com.trivia.dao.QuestionDAO;
import com.trivia.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * GET  /agregar  -> muestra el formulario (agregar.jsp)
 * POST /agregar  -> inserta la nueva pregunta y redirige con un mensaje
 */
@WebServlet("/agregar")
public class AddQuestionServlet extends HttpServlet {

    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/agregar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Question q = new Question();
        q.setPregunta(req.getParameter("pregunta"));
        q.setOpcionA(req.getParameter("opcionA"));
        q.setOpcionB(req.getParameter("opcionB"));
        q.setOpcionC(req.getParameter("opcionC"));
        q.setOpcionD(req.getParameter("opcionD"));
        q.setRespuestaCorrecta(req.getParameter("respuestaCorrecta"));
        q.setCategoria(req.getParameter("categoria"));
        q.setDificultad(req.getParameter("dificultad"));

        boolean ok = false;
        if (q.getPregunta() != null && !q.getPregunta().trim().isEmpty()) {
            ok = questionDAO.addQuestion(q);
        }

        req.setAttribute("exito", ok);
        req.getRequestDispatcher("/agregar.jsp").forward(req, resp);
    }
}
