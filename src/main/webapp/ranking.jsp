<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ranking - Trivia de Cultura General</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<div class="navbar">
    <a class="logo" href="${pageContext.request.contextPath}/">🧠 Trivia</a>
    <nav>
        <a href="${pageContext.request.contextPath}/trivia.jsp">Jugar</a>
        <a href="${pageContext.request.contextPath}/agregar">Agregar Pregunta</a>
    </nav>
</div>

<div class="contenedor">
    <div class="tarjeta">
        <h2>🏆 Top 10 - Mejores Puntajes</h2>

        <c:choose>
            <c:when test="${empty ranking}">
                <p>Todavía no hay partidas registradas. ¡Sé el primero en jugar!</p>
            </c:when>
            <c:otherwise>
                <table class="ranking">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Jugador</th>
                        <th>Puntaje</th>
                        <th>Aciertos</th>
                        <th>Fecha</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${ranking}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${item.nombreJugador}</td>
                            <td>${item.puntaje}</td>
                            <td>${item.preguntasCorrectas} / ${item.preguntasTotales}</td>
                            <td><fmt:formatDate value="${item.fechaPartida}" pattern="dd/MM/yyyy HH:mm"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <div class="botones-home" style="margin-top:24px">
            <a class="btn btn-primario" href="${pageContext.request.contextPath}/trivia.jsp">Jugar Trivia</a>
        </div>
    </div>
</div>

</body>
</html>
