package com.trivia.model;

import java.sql.Timestamp;

public class RankingEntry {
    private int id;
    private String nombreJugador;
    private int puntaje;
    private int preguntasCorrectas;
    private int preguntasTotales;
    private Timestamp fechaPartida;

    public RankingEntry() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreJugador() { return nombreJugador; }
    public void setNombreJugador(String nombreJugador) { this.nombreJugador = nombreJugador; }

    public int getPuntaje() { return puntaje; }
    public void setPuntaje(int puntaje) { this.puntaje = puntaje; }

    public int getPreguntasCorrectas() { return preguntasCorrectas; }
    public void setPreguntasCorrectas(int preguntasCorrectas) { this.preguntasCorrectas = preguntasCorrectas; }

    public int getPreguntasTotales() { return preguntasTotales; }
    public void setPreguntasTotales(int preguntasTotales) { this.preguntasTotales = preguntasTotales; }

    public Timestamp getFechaPartida() { return fechaPartida; }
    public void setFechaPartida(Timestamp fechaPartida) { this.fechaPartida = fechaPartida; }
}
