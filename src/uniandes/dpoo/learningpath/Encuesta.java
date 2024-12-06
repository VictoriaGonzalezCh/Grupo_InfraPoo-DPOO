package uniandes.dpoo.learningpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.swing.JOptionPane;

import uniandes.dpoo.usuario.ProfesorCreador;

public class Encuesta extends Actividad {
	
	private List<PreguntaAbierta> preguntasAbiertas;
	private String estado = "no enviada";
	private HashMap<PreguntaAbierta, String> respuestasEstudiante = new HashMap<>(); 
	
	public Encuesta(int id, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada, String resultado,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, ProfesorCreador creador,
            List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
  super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, resultado, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
  this.preguntasAbiertas = new ArrayList<>();
  this.estado = "no enviada";
		// TODO Auto-generated constructor stub
	}
	
	public Encuesta(int id, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada, String resultado,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, ProfesorCreador creador,
            List<Actividad> prerequisitos, String estado, List<Actividad> actividadesSeguimientoRecomendadas) {
  super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, resultado, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
  this.preguntasAbiertas = new ArrayList<>();
  this.estado = estado;
}
	
	public void agregarPregunta() {
	    JOptionPane.showMessageDialog(null, "Creando una nueva pregunta...");

	    // Solicitar enunciado de la pregunta
	    String enunciado = JOptionPane.showInputDialog(null, "Escriba el enunciado de la pregunta:");

	    // Solicitar explicación
	    String explicacion = JOptionPane.showInputDialog(null, "Escriba la respuesta esperada de la respuesta correcta:");

	    // Crear la pregunta y agregarla a la lista
	    PreguntaAbierta pregunta = new PreguntaAbierta(enunciado, explicacion);
	    preguntasAbiertas.add(pregunta);

	    JOptionPane.showMessageDialog(null, "Pregunta agregada exitosamente.");
	}
	
	
	public void nuevaEncuesta(List<PreguntaAbierta> preguntasAbiertas, String estado) {
		this.preguntasAbiertas = preguntasAbiertas;
		this.setEstado(estado);
	}
	
	
	public void cambiarEstadoEncuesta() {
		this.setEstado("enviada");
		System.out.println("La encuesta ha sido enviada.");
	}
	

	public void mostrarPreguntas() {
        for (PreguntaAbierta pregunta : preguntasAbiertas) {
            pregunta.mostrarPregunta();
            System.out.println();  
        }
    }
	
	public void responderPreguntas() {
	    if (preguntasAbiertas == null || preguntasAbiertas.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay preguntas para responder.");
	        return;
	    }

	    JOptionPane.showMessageDialog(null, "Responda las siguientes preguntas:");
	    for (PreguntaAbierta pregunta : preguntasAbiertas) {
	        String respuestaIngresada = JOptionPane.showInputDialog(null, "Pregunta: " + pregunta.getEnunciado());
	        if (respuestaIngresada != null && !respuestaIngresada.isEmpty()) {
	            respuestasEstudiante.put(pregunta, respuestaIngresada);
	        } else {
	            JOptionPane.showMessageDialog(null, "No se ingresó una respuesta. Pasando a la siguiente pregunta.");
	        }
	    }
	}
	
	public void mostrarRespuestasEstudiante() {
	    if (respuestasEstudiante.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay respuestas disponibles para esta actividad.");
	        return;
	    }

	    StringBuilder respuestas = new StringBuilder("Respuestas del estudiante para la actividad:\n");
	    for (Map.Entry<PreguntaAbierta, String> entrada : respuestasEstudiante.entrySet()) {
	        PreguntaAbierta pregunta = entrada.getKey();
	        String respuesta = entrada.getValue();

	        respuestas.append("Pregunta: ").append(pregunta.getEnunciado()).append("\n");
	        respuestas.append("Respuesta del estudiante: ").append(respuesta).append("\n\n");
	    }

	    JOptionPane.showMessageDialog(null, respuestas.toString());
	}
	
	public HashMap<PreguntaAbierta, String> getRespuestasEstudiante() {
		return respuestasEstudiante;
	}

	public List<PreguntaAbierta> getPreguntasAbiertas() {
		return preguntasAbiertas;
	}

	public void setPreguntasAbiertas(List<PreguntaAbierta> preguntasAbiertas) {
		this.preguntasAbiertas = preguntasAbiertas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
