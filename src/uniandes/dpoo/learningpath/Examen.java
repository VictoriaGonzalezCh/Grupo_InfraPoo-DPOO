package uniandes.dpoo.learningpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.util.Map.Entry;


import uniandes.dpoo.usuario.ProfesorCreador;

public class Examen extends Actividad {

	private List<PreguntaAbierta> preguntasAbiertas;
	private String estado;
	private HashMap<PreguntaAbierta, String> respuestasEstudiante = new HashMap<>(); 
	
	public Examen(int id, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada, String resultado,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, ProfesorCreador creador,
            List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
  super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, resultado, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
  this.preguntasAbiertas = new ArrayList<>();
  this.estado = "enviada";
}

	public Examen(int id, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada, String resultado,
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
	    if (enunciado == null || enunciado.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El enunciado no puede estar vacío. Operación cancelada.");
	        return;
	    }

	    // Solicitar la respuesta esperada
	    String explicacion = JOptionPane.showInputDialog(null, "Escriba la respuesta esperada de la pregunta:");
	    if (explicacion == null || explicacion.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "La respuesta esperada no puede estar vacía. Operación cancelada.");
	        return;
	    }

	    // Crear la pregunta y agregarla a la lista
	    PreguntaAbierta pregunta = new PreguntaAbierta(enunciado, explicacion);
	    preguntasAbiertas.add(pregunta);

	    JOptionPane.showMessageDialog(null, "Pregunta agregada exitosamente.");
	}
	
	
	public void nuevoExamen(List<PreguntaAbierta> preguntasAbiertas, String estado) {
		this.preguntasAbiertas = preguntasAbiertas;
		this.setEstado(estado);
		
	}
	
	public void calificarExamen() {
		this.setEstado("calificada");
		System.out.println("El examen ha sido calificado.");
	}
	

	public void mostrarPreguntas() {
        for (PreguntaAbierta pregunta : preguntasAbiertas) {
            pregunta.mostrarPregunta();
            System.out.println();  
        }
    }
	
	
	public void responderPreguntas() {
	    if (preguntasAbiertas == null || preguntasAbiertas.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay preguntas para responder.", "Información", JOptionPane.INFORMATION_MESSAGE);
	        return;
	    }

	    for (PreguntaAbierta pregunta : preguntasAbiertas) {
	        // Mostrar la pregunta
	        String respuestaIngresada = JOptionPane.showInputDialog(null, "Pregunta: " + pregunta.getEnunciado(), "Responder Pregunta", JOptionPane.QUESTION_MESSAGE);

	        if (respuestaIngresada == null || respuestaIngresada.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Respuesta vacía, no se guardará.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        } else {
	            respuestasEstudiante.put(pregunta, respuestaIngresada);
	        }
	    }

	    JOptionPane.showMessageDialog(null, "Todas las respuestas se han guardado exitosamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void mostrarRespuestasEstudiante() {
	    if (respuestasEstudiante.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay respuestas disponibles para esta actividad.", "Información", JOptionPane.INFORMATION_MESSAGE);
	        return;
	    }

	    StringBuilder mensaje = new StringBuilder("Respuestas del estudiante para la actividad:\n\n");
	    for (Entry<PreguntaAbierta, String> entrada : respuestasEstudiante.entrySet()) {
	        PreguntaAbierta pregunta = entrada.getKey();
	        String respuesta = entrada.getValue();

	        mensaje.append("Pregunta: ").append(pregunta.getEnunciado()).append("\n");
	        mensaje.append("Respuesta del estudiante: ").append(respuesta).append("\n\n");
	    }

	    JOptionPane.showMessageDialog(null, mensaje.toString(), "Respuestas del Estudiante", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public HashMap<PreguntaAbierta, String> getRespuestasEstudiante() {
		return respuestasEstudiante;
	}
	
	public List<PreguntaAbierta> getPreguntasAbiertas() {
        return preguntasAbiertas;
    }
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
