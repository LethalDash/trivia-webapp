# 🧠 Trivia de Cultura General — Proyecto Java Web

Aplicación web de trivia (preguntas de cultura general) hecha con **HTML + CSS + JavaScript** en el frontend y **Java Web (Servlets + JSP + JDBC/MySQL)** en el backend, desplegable en Tomcat 9.

## Características
- Set de preguntas guardado en **MySQL** (tabla `preguntas`), con 20 preguntas de ejemplo ya cargadas.
- **Cronómetro** de 15 segundos por pregunta (JavaScript), con bonus de puntos por responder rápido.
- **Puntaje** en tiempo real durante la partida.
- **Ranking** (tabla `ranking`) con el Top 10 histórico de jugadores.
- Formulario para **agregar nuevas preguntas** al set (`/agregar`), guardadas directamente en la base de datos.
