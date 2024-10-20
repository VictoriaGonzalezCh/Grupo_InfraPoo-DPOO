package uniandes.dpoo.learningpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import uniandes.dpoo.usuario.Profesor;

public class Encuesta extends Actividad {
	
	private List<PreguntaAbierta> preguntasAbiertas;
	private String estado = "no enviada";
	private HashMap<PreguntaAbierta, String> respuestasEstudiante = new HashMap<>(); 
	
	public Encuesta(int id, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, Profesor creador,
            List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
  super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
  this.preguntasAbiertas = new ArrayList<>();
  this.estado = "no enviada";
		// TODO Auto-generated constructor stub
	}
	
	public Encuesta(int id, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, Profesor creador,
            List<Actividad> prerequisitos, String estado, List<Actividad> actividadesSeguimientoRecomendadas) {
  super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
  this.preguntasAbiertas = new ArrayList<>();
  this.estado = estado;
}
	
	public void agregarPregunta(Scanner scanner) {
        System.out.println("Creando una nueva pregunta...");

        // Solicitar enunciado de la pregunta
        System.out.println("Escriba el enunciado de la pregunta: ");
        String enunciado = scanner.nextLine();

        // Solicitar explicación
        System.out.println("Escriba la respuesta esperada de la respuesta correcta: ");
        String explicacion = scanner.nextLine();

        // Crear la pregunta y agregarla a la lista
        PreguntaAbierta pregunta = new PreguntaAbierta(enunciado, explicacion);
        preguntasAbiertas.add(pregunta);

        System.out.println("Pregunta agregada exitosamente.");
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
	
	public void responderPreguntas(Scanner scanner) {
		if (preguntasAbiertas == null || preguntasAbiertas.isEmpty()) {
	        System.out.println("No hay preguntas para responder.");
	        return;
	    }
		
		System.out.println("Responda las siguientes preguntas: ");
        for (PreguntaAbierta pregunta : preguntasAbiertas) {
            pregunta.mostrarPregunta();
        	String respuestaIngresada = scanner.nextLine();
        	respuestasEstudiante.put(pregunta, respuestaIngresada); 
        }
        
    }
	
	public void mostrarRespuestasEstudiante() {
	    if (respuestasEstudiante.isEmpty()) {
	        System.out.println("No hay respuestas disponibles para esta actividad.");
	        return;
	    }

	    System.out.println("Respuestas del estudiante para la actividad:");
	    for (Entry<PreguntaAbierta, String> entrada : respuestasEstudiante.entrySet()) {
	        PreguntaAbierta pregunta = entrada.getKey();
	        String respuesta = entrada.getValue();
	        
	        System.out.println("Pregunta: " + pregunta.getEnunciado());
	        System.out.println("Respuesta del estudiante: " + respuesta);
	        System.out.println();
	    }
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
