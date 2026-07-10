<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trivia de Cultura General</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<div class="navbar">
    <a class="logo" href="${pageContext.request.contextPath}/">🧠 Trivia</a>
    <nav>
        <a href="${pageContext.request.contextPath}/trivia.jsp">Jugar</a>
        <a href="${pageContext.request.contextPath}/ranking">Ranking</a>
        <a href="${pageContext.request.contextPath}/agregar">Agregar Pregunta</a>
    </nav>
</div>

<div class="contenedor">
    <div class="hero">
        <h1>Trivia de Cultura General</h1>
        <p>Pon a prueba tus conocimientos con cronómetro, puntaje y ranking en vivo.</p>
    </div>

    <div class="tarjeta">
        <div class="botones-home">
            <a class="btn btn-primario" href="${pageContext.request.contextPath}/trivia.jsp">🎮 Jugar Trivia</a>
            <a class="btn btn-secundario" href="${pageContext.request.contextPath}/ranking">🏆 Ver Ranking</a>
            <a class="btn btn-secundario" href="${pageContext.request.contextPath}/agregar">➕ Agregar Pregunta</a>
        </div>
    </div>
</div>

</body>
</html>
