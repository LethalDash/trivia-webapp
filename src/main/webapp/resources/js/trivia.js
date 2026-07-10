// ==========================================================
// Lógica del juego de Trivia
// Consume /api/preguntas (JSON) y /ranking (POST) via fetch
// ==========================================================

const SEGUNDOS_POR_PREGUNTA = 15;
const CANTIDAD_PREGUNTAS = 10;

let preguntas = [];
let indiceActual = 0;
let puntaje = 0;
let correctas = 0;
let nombreJugador = "";
let tiempoRestante = SEGUNDOS_POR_PREGUNTA;
let temporizadorId = null;

const pantallaInicio = document.getElementById("pantalla-inicio");
const pantallaJuego = document.getElementById("pantalla-juego");
const pantallaFinal = document.getElementById("pantalla-final");

const inputNombre = document.getElementById("input-nombre");
const btnIniciar = document.getElementById("btn-iniciar");

const elPreguntaNumero = document.getElementById("pregunta-numero");
const elPreguntaTexto = document.getElementById("pregunta-texto");
const elOpciones = document.getElementById("opciones");
const elTimerBar = document.getElementById("timer-bar");
const elTimerNumero = document.getElementById("timer-numero");
const elPuntajeActual = document.getElementById("puntaje-actual");

const elPuntajeFinal = document.getElementById("puntaje-final");
const elDetalleFinal = document.getElementById("detalle-final");

btnIniciar.addEventListener("click", iniciarJuego);

async function iniciarJuego() {
    nombreJugador = inputNombre.value.trim() || "Anónimo";

    btnIniciar.disabled = true;
    btnIniciar.textContent = "Cargando preguntas...";

    try {
        const resp = await fetch(`api/preguntas?cantidad=${CANTIDAD_PREGUNTAS}`);
        preguntas = await resp.json();
    } catch (e) {
        alert("No se pudieron cargar las preguntas. Verifica la conexión con el servidor/base de datos.");
        btnIniciar.disabled = false;
        btnIniciar.textContent = "Comenzar Trivia";
        return;
    }

    if (!preguntas || preguntas.length === 0) {
        alert("No hay preguntas cargadas en la base de datos todavía.");
        btnIniciar.disabled = false;
        btnIniciar.textContent = "Comenzar Trivia";
        return;
    }

    indiceActual = 0;
    puntaje = 0;
    correctas = 0;

    pantallaInicio.style.display = "none";
    pantallaJuego.style.display = "block";

    mostrarPregunta();
}

function mostrarPregunta() {
    detenerTemporizador();

    const p = preguntas[indiceActual];
    elPreguntaNumero.textContent = `Pregunta ${indiceActual + 1} de ${preguntas.length}`;
    elPreguntaTexto.textContent = p.pregunta;
    elPuntajeActual.textContent = `Puntaje: ${puntaje}`;

    elOpciones.innerHTML = "";
    const opciones = [
        { letra: "A", texto: p.opcionA },
        { letra: "B", texto: p.opcionB },
        { letra: "C", texto: p.opcionC },
        { letra: "D", texto: p.opcionD }
    ];

    opciones.forEach(op => {
        const btn = document.createElement("button");
        btn.className = "opcion";
        btn.textContent = `${op.letra}) ${op.texto}`;
        btn.dataset.letra = op.letra;
        btn.addEventListener("click", () => responder(op.letra));
        elOpciones.appendChild(btn);
    });

    tiempoRestante = SEGUNDOS_POR_PREGUNTA;
    actualizarBarraTiempo();
    temporizadorId = setInterval(tick, 1000);
}

function tick() {
    tiempoRestante--;
    actualizarBarraTiempo();
    if (tiempoRestante <= 0) {
        detenerTemporizador();
        responder(null); // se acabó el tiempo, cuenta como no respondida
    }
}

function actualizarBarraTiempo() {
    const porcentaje = Math.max(0, (tiempoRestante / SEGUNDOS_POR_PREGUNTA) * 100);
    elTimerBar.style.width = porcentaje + "%";
    elTimerNumero.textContent = Math.max(0, tiempoRestante);

    if (tiempoRestante <= 5) {
        elTimerBar.style.background = "#ef4444";
    } else if (tiempoRestante <= 8) {
        elTimerBar.style.background = "#fbbf24";
    } else {
        elTimerBar.style.background = "linear-gradient(90deg, #22c55e, #fbbf24)";
    }
}

function detenerTemporizador() {
    if (temporizadorId) {
        clearInterval(temporizadorId);
        temporizadorId = null;
    }
}

function responder(letraElegida) {
    detenerTemporizador();

    const p = preguntas[indiceActual];
    const correcta = p.respuestaCorrecta;

    const botones = elOpciones.querySelectorAll(".opcion");
    botones.forEach(btn => {
        btn.disabled = true;
        if (btn.dataset.letra === correcta) {
            btn.classList.add("correcta");
        } else if (btn.dataset.letra === letraElegida) {
            btn.classList.add("incorrecta");
        }
    });

    if (letraElegida === correcta) {
        // más puntos si responde rápido
        const puntosBase = 100;
        const bonusTiempo = tiempoRestante * 5;
        puntaje += puntosBase + bonusTiempo;
        correctas++;
    }

    setTimeout(siguientePregunta, 1200);
}

function siguientePregunta() {
    indiceActual++;
    if (indiceActual < preguntas.length) {
        mostrarPregunta();
    } else {
        finalizarJuego();
    }
}

async function finalizarJuego() {
    pantallaJuego.style.display = "none";
    pantallaFinal.style.display = "block";

    elPuntajeFinal.textContent = puntaje;
    elDetalleFinal.textContent =
        `${nombreJugador}, respondiste correctamente ${correctas} de ${preguntas.length} preguntas.`;

    // Enviar el puntaje al servidor para el ranking
    try {
        await fetch("ranking", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: new URLSearchParams({
                nombre: nombreJugador,
                puntaje: puntaje,
                correctas: correctas,
                totales: preguntas.length
            })
        });
    } catch (e) {
        console.error("No se pudo guardar el puntaje en el ranking", e);
    }
}
