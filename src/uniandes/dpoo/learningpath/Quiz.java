package uniandes.dpoo.learningpath;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.usuario.Profesor;


public class Quiz extends Actividad {

	List<PreguntaOpcionMultiple> preguntasMultiples;
	int calificacionMinima;
	PreguntaOpcionMultiple pregunta;
	
	
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
	
	public List<PreguntaOpcionMultiple> getPreguntasMultiples() {
        return preguntasMultiples;
    }

    public int getCalificacionMinima() {
        return calificacionMinima;
    }
	
	
}
