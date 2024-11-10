package uniandes.dpoo.learningpath;

public class PreguntaVerdaderoFalso {

	private String enunciado;
    private boolean respuestaCorrecta;
    private String explicacion;

    public PreguntaVerdaderoFalso() {
    }

    public PreguntaVerdaderoFalso(String enunciado, boolean respuestaCorrecta, String explicacion) {
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
        this.explicacion = explicacion;
    }

    public void nuevoEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void establecerRespuestaCorrecta(boolean respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public void crearExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    public boolean verificarRespuesta(boolean respuesta) {
        return this.respuestaCorrecta == respuesta;
    }

    public void mostrarPregunta() {
        System.out.println("Enunciado: " + enunciado);
        System.out.println("Responda con 'true' o 'false'");
    }

    public void mostrarRespuestaCorrecta() {
        System.out.println("Respuesta correcta: " + respuestaCorrecta);
        System.out.println("Explicación: " + explicacion);
    }

    public String getEnunciado() {
        return enunciado;
    }

    public boolean isRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setRespuestaCorrecta(boolean respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }
}
