package com.trivia.servlet;

import com.google.gson.Gson;
import com.trivia.dao.QuestionDAO;
import com.trivia.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * GET /api/preguntas?cantidad=10
 * Devuelve un set aleatorio de preguntas en JSON para que el juego
 * (JavaScript en trivia.jsp) las consuma con fetch().
 *
 * Nota académica: la respuesta correcta viaja en el JSON para poder
 * validar en el cliente sin otra llamada al servidor. Para un entorno
 * de producción real, la validación debería hacerse en el servidor
 * (recibiendo solo el id de la opción elegida) para evitar trampas
 * revisando el código fuente / la consola del navegador.
 */
@WebServlet("/api/preguntas")
public class QuestionsServlet extends HttpServlet {

    private final QuestionDAO questionDAO = new QuestionDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json;charset=UTF-8");

        int cantidad = 10;
        String param = req.getParameter("cantidad");
        if (param != null) {
            try {
                cantidad = Integer.parseInt(param);
            } catch (NumberFormatException ignored) { }
        }

        List<Question> preguntas = questionDAO.getRandomQuestions(cantidad);

        try (PrintWriter out = resp.getWriter()) {
            out.print(gson.toJson(preguntas));
        }
    }
}
