package uniandes.dpoo.learningpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

import uniandes.dpoo.usuario.Profesor;


public class Quiz extends Actividad {

	private List<PreguntaOpcionMultiple> preguntasMultiples;
	private int calificacionMinima;
	private PreguntaOpcionMultiple pregunta;
	private String resultado;
	private HashMap<PreguntaOpcionMultiple, String> respuestasEstudiante = new HashMap<>();
	
	
	public Quiz(int id, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, Profesor creador,
            List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
		super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
		this.preguntasMultiples = new ArrayList<>();
	}
	
	public Quiz(int id, int calificacionMinima, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, Profesor creador,
            List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
  super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
		this.preguntasMultiples = new ArrayList<>();
		this.calificacionMinima = calificacionMinima;
	}
	
	
	public void agregarPregunta(Scanner scanner) {
        System.out.println("Creando una nueva pregunta...");

        // Solicitar enunciado de la pregunta
        System.out.println("Escriba el enunciado de la pregunta: ");
        String enunciado = scanner.nextLine();

        // Solicitar opciones
        List<String> opciones = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            System.out.println("Escriba la opción " + i + ": ");
            String opcion = scanner.nextLine();
            opciones.add(opcion);
        }

        // Solicitar la opción correcta
        System.out.println("¿Cuál es el número de la opción correcta (1-4)?: ");
        int opcionCorrecta = Integer.parseInt(scanner.nextLine());
        String respuestaCorrecta = opciones.get(opcionCorrecta - 1);

        // Solicitar explicación
        System.out.println("Escriba la explicación de la respuesta correcta: ");
        String explicacion = scanner.nextLine();

        // Crear la pregunta y agregarla a la lista
        PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple(enunciado, opciones, respuestaCorrecta, explicacion);
        preguntasMultiples.add(pregunta);

        System.out.println("Pregunta agregada exitosamente.");
    }
	
	
	
	public void nuevoQuiz(List<PreguntaOpcionMultiple> preguntasMultiples, int calificacionMinima) {
		this.preguntasMultiples = preguntasMultiples;
		this.calificacionMinima = calificacionMinima;
	}
	
	
	public void establecerCalificacionMinima(int calificacionMinima) {
		this.calificacionMinima = calificacionMinima;
	}
	
	public void mostrarPreguntas() {
        for (PreguntaOpcionMultiple pregunta : preguntasMultiples) {
            pregunta.mostrarPregunta();
            System.out.println(); 
        }
    }
	
	public void responderPreguntas(Scanner scanner, Quiz quiz) {
		int contadorCorrectas = 0;
		
		if (preguntasMultiples == null || preguntasMultiples.isEmpty()) {
	        System.out.println("No hay preguntas para responder.");
	        return;
	    }
		
		System.out.println("Responda las siguientes preguntas: ");
        for (PreguntaOpcionMultiple pregunta : preguntasMultiples) {
            pregunta.mostrarPregunta();
        	String respuestaIngresada = scanner.nextLine();
        	boolean respuesta = pregunta.verificarRespuesta(respuestaIngresada);
        	if (respuesta) {respuestasEstudiante.put(pregunta, "correcto"); contadorCorrectas ++;}
        	else {respuestasEstudiante.put(pregunta, "incorrecto");}
        }   
        
        System.out.println("Ahora que ya ha completado el quiz las respuestas correctas y su explicacion son: ");
        for (PreguntaOpcionMultiple pregunta : preguntasMultiples) {
            pregunta.mostrarRespuestas();}
        
        if (contadorCorrectas >= calificacionMinima) {
            quiz.setResultado("Exitoso"); // Asegúrate de que quiz está correctamente referenciado
        } else {
            quiz.setResultado("No exitoso"); // No es un método estático, usa quiz en lugar de Quiz
        }
    }
	
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public void mostrarRespuestasEstudiante() {
	    if (respuestasEstudiante.isEmpty()) {
	        System.out.println("No hay respuestas disponibles para esta actividad.");
	        return;
	    }

	    System.out.println("Respuestas del estudiante para la actividad:");
	    for (Entry<PreguntaOpcionMultiple, String> entrada : respuestasEstudiante.entrySet()) {
	    	PreguntaOpcionMultiple pregunta = entrada.getKey();
	        String respuesta = entrada.getValue();
	        
	        System.out.println("Pregunta: " + pregunta.getEnunciado());
	        System.out.println("Respuesta del estudiante: " + respuesta);
	        System.out.println();
	    }
	}
	
	public HashMap<PreguntaOpcionMultiple, String> getRespuestasEstudiante() {
		return respuestasEstudiante;
	}
	
	
	public List<PreguntaOpcionMultiple> getPreguntasMultiples() {
        return preguntasMultiples;
    }

    public int getCalificacionMinima() {
        return calificacionMinima;
    }
    
	
	
}
