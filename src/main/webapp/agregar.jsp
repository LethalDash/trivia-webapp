<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Pregunta - Trivia</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<div class="navbar">
    <a class="logo" href="${pageContext.request.contextPath}/">🧠 Trivia</a>
    <nav>
        <a href="${pageContext.request.contextPath}/trivia.jsp">Jugar</a>
        <a href="${pageContext.request.contextPath}/ranking">Ranking</a>
    </nav>
</div>

<div class="contenedor">
    <div class="tarjeta">
        <h2>➕ Agregar nueva pregunta</h2>

        <c:if test="${not empty exito}">
            <c:choose>
                <c:when test="${exito}">
                    <div class="mensaje-exito">¡Pregunta agregada con éxito al set de preguntas!</div>
                </c:when>
                <c:otherwise>
                    <div class="mensaje-error">No se pudo agregar la pregunta. Verifica los campos.</div>
                </c:otherwise>
            </c:choose>
        </c:if>

        <form class="form-agregar" method="post" action="${pageContext.request.contextPath}/agregar">

            <label>Pregunta</label>
            <input type="text" name="pregunta" required placeholder="Ej: ¿Cuál es la capital de Francia?">

            <div class="fila-doble">
                <div>
                    <label>Opción A</label>
                    <input type="text" name="opcionA" required>
                </div>
                <div>
                    <label>Opción B</label>
                    <input type="text" name="opcionB" required>
                </div>
            </div>

            <div class="fila-doble">
                <div>
                    <label>Opción C</label>
                    <input type="text" name="opcionC" required>
                </div>
                <div>
                    <label>Opción D</label>
                    <input type="text" name="opcionD" required>
                </div>
            </div>

            <div class="fila-doble">
                <div>
                    <label>Respuesta correcta</label>
                    <select name="respuestaCorrecta" required>
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                    </select>
                </div>
                <div>
                    <label>Dificultad</label>
                    <select name="dificultad">
                        <option value="Facil">Fácil</option>
                        <option value="Media" selected>Media</option>
                        <option value="Dificil">Difícil</option>
                    </select>
                </div>
            </div>

            <label>Categoría</label>
            <input type="text" name="categoria" placeholder="Ej: Historia, Ciencia, Geografía..." value="General">

            <button type="submit" class="btn btn-exito" style="width:100%; margin-top:22px">Guardar Pregunta</button>
        </form>
    </div>
</div>

</body>
</html>
