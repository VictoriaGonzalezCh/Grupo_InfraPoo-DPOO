package uniandes.dpoo.learningpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

import uniandes.dpoo.usuario.Profesor;
import uniandes.dpoo.usuario.ProfesorCreador;


public class Quiz extends Actividad {

	private List<PreguntaOpcionMultiple> preguntasMultiples;
	private List<PreguntaVerdaderoFalso> preguntasVF;
	private int calificacionMinima;
	private String tipoQuiz;
	private String resultado;
	private HashMap<PreguntaOpcionMultiple, String> respuestasEstudiante = new HashMap<>();
	private HashMap<PreguntaVerdaderoFalso, String> respuestasEstudianteVF = new HashMap<>();
	
	
	public Quiz(int id, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, ProfesorCreador creador,
            List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
		super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
		this.preguntasMultiples = new ArrayList<>();
		this.preguntasVF = new ArrayList<>();
	}
	
	public Quiz(int id, int calificacionMinima, String tipoQuiz, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, ProfesorCreador creador,
            List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
  super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
		this.preguntasMultiples = new ArrayList<>();
		this.preguntasVF = new ArrayList<>();
		this.calificacionMinima = calificacionMinima;
		this.tipoQuiz = tipoQuiz;
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
	
	public void agregarPreguntaVerdaderoFalso(Scanner scanner) {
        System.out.println("Creando una nueva pregunta de verdadero/falso...");

        System.out.println("Escriba el enunciado de la pregunta: ");
        String enunciado = scanner.nextLine();

        System.out.println("¿La respuesta es verdadera o falsa? (true/false): ");
        boolean respuestaCorrecta = Boolean.parseBoolean(scanner.nextLine());

        System.out.println("Escriba la explicación de la respuesta correcta: ");
        String explicacion = scanner.nextLine();

        PreguntaVerdaderoFalso pregunta = new PreguntaVerdaderoFalso(enunciado, respuestaCorrecta, explicacion);
        preguntasVF.add(pregunta);

        System.out.println("Pregunta de verdadero/falso agregada exitosamente.");
    }
	
	
	public void nuevoQuiz(List<PreguntaOpcionMultiple> preguntasMultiples, int calificacionMinima) {
		this.preguntasMultiples = preguntasMultiples;
		this.calificacionMinima = calificacionMinima;
	}
	
	
	public void establecerCalificacionMinima(int calificacionMinima) {
		this.calificacionMinima = calificacionMinima;
	}
	
	//public void mostrarPreguntas() {
    //    for (PreguntaOpcionMultiple pregunta : preguntasMultiples) {
    //        pregunta.mostrarPregunta();
    //        System.out.println(); 
    //    }
    //}
	
	public void mostrarPreguntas() {
        if (!preguntasMultiples.isEmpty()) {
            for (PreguntaOpcionMultiple pregunta : preguntasMultiples) {
                pregunta.mostrarPregunta();
                System.out.println();
            }
        } else if (!preguntasVF.isEmpty()) {
            for (PreguntaVerdaderoFalso pregunta : preguntasVF) {
                pregunta.mostrarPregunta();
                System.out.println();
            }
        } else {
            System.out.println("No hay preguntas en este quiz.");
        }
    }
	
	
	
	public void responderPreguntas(Scanner scanner) {
        int contadorCorrectas = 0;
        if (!preguntasMultiples.isEmpty()) {
            System.out.println("Responda las preguntas de opción múltiple: ");
            for (PreguntaOpcionMultiple pregunta : preguntasMultiples) {
                pregunta.mostrarPregunta();
                String respuestaIngresada = scanner.nextLine();
                boolean respuesta = pregunta.verificarRespuesta(respuestaIngresada);
                if (respuesta) { respuestasEstudiante.put(pregunta, "correcto"); contadorCorrectas++; }
                else { respuestasEstudiante.put(pregunta, "incorrecto"); }
            }
        } else if (!preguntasVF.isEmpty()) {
            System.out.println("Responda las preguntas de verdadero/falso: ");
            for (PreguntaVerdaderoFalso pregunta : preguntasVF) {
                pregunta.mostrarPregunta();
                String respuestaIngresada = scanner.nextLine();
                boolean respuesta = pregunta.verificarRespuesta(Boolean.parseBoolean(respuestaIngresada));
                if (respuesta) { respuestasEstudianteVF.put(pregunta, "correcto"); contadorCorrectas++; }
                else { respuestasEstudianteVF.put(pregunta, "incorrecto"); }
            }
        } else {
            System.out.println("No hay preguntas para responder.");
        }

        if (contadorCorrectas >= calificacionMinima) {
            setResultado("Exitoso");
        } else {
            setResultado("No exitoso");
        }
    }
	
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public List<PreguntaVerdaderoFalso> getPreguntasVF() {
        return preguntasVF;
    }

	
	public void mostrarRespuestasEstudiante() {
	    if ((respuestasEstudiante == null || respuestasEstudiante.isEmpty()) 
	        && (respuestasEstudianteVF == null || respuestasEstudianteVF.isEmpty())) {
	        System.out.println("No hay respuestas disponibles para esta actividad.");
	        return;
	    }

	    System.out.println("Respuestas del estudiante para la actividad:");

	    // Mostrar respuestas de preguntas de opción múltiple
	    if (respuestasEstudiante != null && !respuestasEstudiante.isEmpty()) {
	        System.out.println("Respuestas a preguntas de opción múltiple:");
	        for (Entry<PreguntaOpcionMultiple, String> entrada : respuestasEstudiante.entrySet()) {
	            PreguntaOpcionMultiple pregunta = entrada.getKey();
	            String respuesta = entrada.getValue();

	            System.out.println("Pregunta: " + pregunta.getEnunciado());
	            System.out.println("Respuesta del estudiante: " + respuesta);
	            System.out.println();
	        }
	    }

	    // Mostrar respuestas de preguntas de verdadero/falso
	    if (respuestasEstudianteVF != null && !respuestasEstudianteVF.isEmpty()) {
	        System.out.println("Respuestas a preguntas de verdadero/falso:");
	        for (Entry<PreguntaVerdaderoFalso, String> entrada : respuestasEstudianteVF.entrySet()) {
	            PreguntaVerdaderoFalso pregunta = entrada.getKey();
	            String respuesta = entrada.getValue();

	            System.out.println("Pregunta: " + pregunta.getEnunciado());
	            System.out.println("Respuesta del estudiante: " + (respuesta != null ? "Verdadero" : "Falso"));
	            System.out.println();
	        }
	    }
	}
	
	
	
	
	public HashMap<PreguntaOpcionMultiple, String> getRespuestasEstudiante() {
		return respuestasEstudiante;
	}
	
	public HashMap<PreguntaVerdaderoFalso, String> getRespuestasEstudianteVF() {
		return respuestasEstudianteVF;
	}
	
	
	public List<PreguntaOpcionMultiple> getPreguntasMultiples() {
        return preguntasMultiples;
    }

    public int getCalificacionMinima() {
        return calificacionMinima;
    }

    public void setPreguntasMultiples(List<PreguntaOpcionMultiple> preguntasMultiples) {
        if (preguntasMultiples != null) {
            this.preguntasMultiples = preguntasMultiples;
        } else {
            System.out.println("La lista de preguntas no puede ser nula.");
        }
    }
    
    public void setPreguntasVF(List<PreguntaVerdaderoFalso> preguntasVF) {
        if (preguntasVF != null) {
            this.preguntasVF = preguntasVF;
        } else {
            System.out.println("La lista de preguntas de verdadero/falso no puede ser nula.");
        }
    }
    
    public boolean tienePreguntasVerdaderoFalso() {
        return preguntasVF != null && !preguntasVF.isEmpty();
    }
    
    public boolean tienePreguntasOpcionMultiple() {
        return preguntasMultiples != null && !preguntasMultiples.isEmpty();
    }
    
    
	
	
}
