package uniandes.dpoo.pruebas;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.learningpath.*;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.PreguntaOpcionMultiple;
import uniandes.dpoo.learningpath.Quiz;
import uniandes.dpoo.sistema.Sistema;

public class ArchivoPruebasQuiz {

	public static void main(String[] args) {
	    // Crear una nueva actividad de tipo Quiz
	    Quiz nuevoQuiz = new Quiz(0, 0, null, null, null, null, null, null, null, false, null, null, null);
	    nuevoQuiz.setId(Sistema.generarIDUnicoActividades());
	    nuevoQuiz.setTituloActividad("Evaluación de Java");
	    nuevoQuiz.setDescripcion("Un quiz para evaluar conocimientos en Java.");
	    nuevoQuiz.setObjetivo("Evaluar el conocimiento de los estudiantes en Java");
	    nuevoQuiz.setDuracionEsperada("60"); // Duración en minutos
	    nuevoQuiz.setObligatoria(true);
	    nuevoQuiz.setNivelDificultad("medio");
	    nuevoQuiz.setFechaLimite("15/11/2024");

	    // Establecer calificación mínima
	    nuevoQuiz.establecerCalificacionMinima(3); // Supongamos que se califica de 1 a 5

	    // Agregar actividades previas y prerequisitos si es necesario
	    nuevoQuiz.setActividadesPreviasSugeridas(new ArrayList<>());
	    nuevoQuiz.setPrerequisitos(new ArrayList<>());
	    nuevoQuiz.setActividadesSeguimientoRecomendadas(new ArrayList<>());

	    // Crear algunas preguntas de opción múltiple
	    List<PreguntaOpcionMultiple> preguntas = new ArrayList<>();

	    // Crear preguntas y agregarlas a la lista
	    for (int i = 0; i < 3; i++) {
	        List<String> opciones = new ArrayList<>();
	        opciones.add("Opción A");
	        opciones.add("Opción B");
	        opciones.add("Opción C");
	        opciones.add("Opción D");

	        PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple();
	        pregunta.nuevoEnunciado("Pregunta " + (i + 1) + "?");
	        pregunta.crearOpciones(opciones);
	        pregunta.establecerRespuestaCorrecta("Opción A"); // Supongamos que la opción A es correcta
	        pregunta.crearExplicacion("Explicación de la respuesta correcta.");

	        preguntas.add(pregunta);
	    }

	    // Establecer las preguntas en el quiz
	    nuevoQuiz.setPreguntasMultiples(preguntas);
	    
	    imprimirDetallesQuiz(nuevoQuiz);
	}
	 
	public static void imprimirDetallesQuiz(Quiz nuevoQuiz) {
	    // Mostrar información del quiz
	    System.out.println("Quiz creado:");
	    System.out.println("ID: " + nuevoQuiz.getId());
	    System.out.println("Título: " + nuevoQuiz.getTituloActividad());
	    System.out.println("Descripción: " + nuevoQuiz.getDescripcion());
	    System.out.println("Objetivo: " + nuevoQuiz.getObjetivo());
	    System.out.println("Duración esperada: " + nuevoQuiz.getDuracionEsperada() + " minutos");
	    System.out.println("Obligatoria: " + nuevoQuiz.isObligatoria());
	    System.out.println("Nivel de dificultad: " + nuevoQuiz.getNivelDificultad());
	    System.out.println("Fecha límite: " + nuevoQuiz.getFechaLimite());
	    System.out.println("Calificación mínima: " + nuevoQuiz.getCalificacionMinima());

	    // Mostrar preguntas
	    System.out.println("Preguntas del quiz:");
	    for (PreguntaOpcionMultiple pregunta : nuevoQuiz.getPreguntasMultiples()) {
	        pregunta.mostrarPregunta();
	    }
	}
}