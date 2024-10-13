package uniandes.dpoo.learningpath;

import java.util.List;

public class PreguntaOpcionMultiple {
	
	
	String enunciado;
	List<String> opciones;
	String respuestaCorrecta;
	String explicacion;
	
	public PreguntaOpcionMultiple() {
		}
	
	public PreguntaOpcionMultiple(String enunciado, List<String> opciones, String respuestaCorrecta, String explicacion) {
		this.enunciado = enunciado;
		this.opciones = opciones;
		this.respuestaCorrecta = respuestaCorrecta;
		this.explicacion = explicacion;
	}
	
	public void nuevoEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public void crearOpciones(List<String> opciones) {
		this.opciones = opciones;
	}
	
	public void establecerRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}
	
	public void crearExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}
	
	public boolean verificarRespuesta(String respuesta) {
		boolean resultado = false;
		if (respuestaCorrecta.equals(respuesta)) { resultado = true;}
		return resultado;
		
	}
	
	public void mostrarPregunta() {
        System.out.println("Enunciado: " + enunciado);
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println((i + 1) + ". " + opciones.get(i));
        }
    }
	
	public String getEnunciado() {
        return enunciado;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public String getExplicacion() {
        return explicacion;
    }
	

}
