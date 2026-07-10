package com.trivia.model;

public class Question {
    private int id;
    private String pregunta;
    private String opcionA;
    private String opcionB;
    private String opcionC;
    private String opcionD;
    private String respuestaCorrecta; // "A", "B", "C" o "D"
    private String categoria;
    private String dificultad;

    public Question() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPregunta() { return pregunta; }
    public void setPregunta(String pregunta) { this.pregunta = pregunta; }

    public String getOpcionA() { return opcionA; }
    public void setOpcionA(String opcionA) { this.opcionA = opcionA; }

    public String getOpcionB() { return opcionB; }
    public void setOpcionB(String opcionB) { this.opcionB = opcionB; }

    public String getOpcionC() { return opcionC; }
    public void setOpcionC(String opcionC) { this.opcionC = opcionC; }

    public String getOpcionD() { return opcionD; }
    public void setOpcionD(String opcionD) { this.opcionD = opcionD; }

    public String getRespuestaCorrecta() { return respuestaCorrecta; }
    public void setRespuestaCorrecta(String respuestaCorrecta) { this.respuestaCorrecta = respuestaCorrecta; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDificultad() { return dificultad; }
    public void setDificultad(String dificultad) { this.dificultad = dificultad; }
}
