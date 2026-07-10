<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jugar - Trivia de Cultura General</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<div class="navbar">
    <a class="logo" href="${pageContext.request.contextPath}/">🧠 Trivia</a>
    <nav>
        <a href="${pageContext.request.contextPath}/ranking">Ranking</a>
        <a href="${pageContext.request.contextPath}/agregar">Agregar Pregunta</a>
    </nav>
</div>

<div class="contenedor">

    <!-- Pantalla 1: ingresar nombre -->
    <div id="pantalla-inicio" class="tarjeta">
        <h2>¡Vamos a jugar!</h2>
        <p>Ingresa tu nombre. Tendrás 15 segundos por pregunta: entre más rápido respondas bien, más puntos ganas.</p>
        <input type="text" id="input-nombre" placeholder="Tu nombre" maxlength="50">
        <button id="btn-iniciar" class="btn btn-primario" style="width:100%">Comenzar Trivia</button>
    </div>

    <!-- Pantalla 2: juego -->
    <div id="pantalla-juego" class="tarjeta" style="display:none">
        <div class="info-partida">
            <span id="pregunta-numero">Pregunta 1 de 10</span>
            <span id="puntaje-actual">Puntaje: 0</span>
        </div>

        <div class="timer-wrap">
            <div class="timer-bar-fondo">
                <div id="timer-bar" class="timer-bar"></div>
            </div>
            <div id="timer-numero" class="timer-numero">15</div>
        </div>

        <div id="pregunta-texto" class="pregunta-texto"></div>
        <div id="opciones" class="opciones"></div>
    </div>

    <!-- Pantalla 3: resultado final -->
    <div id="pantalla-final" class="tarjeta" style="display:none">
        <h2>¡Partida terminada!</h2>
        <div class="puntaje-final" id="puntaje-final">0</div>
        <p class="detalle" id="detalle-final"></p>
        <div class="botones-home">
            <a class="btn btn-primario" href="${pageContext.request.contextPath}/ranking">Ver Ranking</a>
            <a class="btn btn-secundario" href="${pageContext.request.contextPath}/trivia.jsp">Jugar de nuevo</a>
        </div>
    </div>

</div>

<script src="resources/js/trivia.js"></script>
</body>
</html>
