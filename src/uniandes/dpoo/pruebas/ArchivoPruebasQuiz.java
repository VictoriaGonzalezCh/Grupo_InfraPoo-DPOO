package uniandes.dpoo.pruebas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	    
	    Scanner scanner = new Scanner(System.in);
	    
	    System.out.println("Ingrese la calificación mínima para pasar (por ejemplo, de 1 a 5):");
        int calificacionMinima = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después del número
        nuevoQuiz.establecerCalificacionMinima(calificacionMinima);

	    // Agregar actividades previas y prerequisitos si es necesario
	    nuevoQuiz.setActividadesPreviasSugeridas(new ArrayList<>());
	    nuevoQuiz.setPrerequisitos(new ArrayList<>());
	    nuevoQuiz.setActividadesSeguimientoRecomendadas(new ArrayList<>());

	 // Preguntar cuántas preguntas desea agregar
        System.out.println("¿Cuántas preguntas desea agregar al quiz?");
        int cantidadPreguntas = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después del número

        // Crear preguntas de opción múltiple
        List<PreguntaOpcionMultiple> preguntas = new ArrayList<>();
        
        for (int i = 0; i < cantidadPreguntas; i++) {
            System.out.println("Ingrese el enunciado de la pregunta " + (i + 1) + ":");
            String enunciado = scanner.nextLine();
            
            List<String> opciones = new ArrayList<>();
            // Pedir opciones
            for (int j = 0; j < 4; j++) {
                System.out.println("Ingrese la opción " + (char) ('A' + j) + " para la pregunta " + (i + 1) + ":");
                String opcion = scanner.nextLine();
                opciones.add(opcion);
            }
            
            // Crear la pregunta de opción múltiple
            PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple();
            pregunta.nuevoEnunciado(enunciado);
            pregunta.crearOpciones(opciones);

            // Pedir la respuesta correcta
            System.out.println("¿Cuál es la respuesta correcta para la pregunta " + (i + 1) + " (A/B/C/D)?");
            String respuestaCorrecta = scanner.nextLine();
            pregunta.establecerRespuestaCorrecta("Opción " + respuestaCorrecta);

            // Pedir una explicación de la respuesta
            System.out.println("Ingrese una explicación para la respuesta correcta:");
            String explicacion = scanner.nextLine();
            pregunta.crearExplicacion(explicacion);
            
            // Agregar la pregunta a la lista de preguntas
            preguntas.add(pregunta);
        }
        
        // Agregar las preguntas al quiz
        nuevoQuiz.setPreguntasMultiples(preguntas);
	    
	    imprimirDetallesQuiz(nuevoQuiz);
	    
	 // **RESPONDER EL QUIZ**
        int respuestasCorrectas = 0;
        for (int i = 0; i < preguntas.size(); i++) {
            PreguntaOpcionMultiple pregunta = preguntas.get(i);
            System.out.println(pregunta.getEnunciado());
            List<String> opciones = pregunta.getOpciones();
            for (int j = 0; j < opciones.size(); j++) {
                System.out.println((char) ('A' + j) + ") " + opciones.get(j));
            }
            
            System.out.println("Ingrese su respuesta (A/B/C/D):");
            String respuestaUsuario = scanner.nextLine().trim().toUpperCase();

            // Verificar si la respuesta es correcta
            if (pregunta.getRespuestaCorrecta().equals(respuestaUsuario)) {
                respuestasCorrectas++;
                System.out.println("¡Correcto!\n");
            } else {
                System.out.println("Incorrecto. La respuesta correcta es: " + pregunta.getRespuestaCorrecta() + "\n");
            }
        }

        // Calcular el progreso del estudiante
        System.out.println("Respondiste correctamente " + respuestasCorrectas + " de " + preguntas.size() + " preguntas.");
        
        // Determinar si el estudiante aprobó o no
        double puntuacion = (double) respuestasCorrectas / preguntas.size() * 5; // Calificación sobre 5
        System.out.println("Tu puntuación es: " + puntuacion);
        
        if (puntuacion >= calificacionMinima) {
            System.out.println("¡Felicidades! Has aprobado el quiz.");
        } else {
            System.out.println("Lo siento, no has aprobado el quiz.");
        }

        // Cerrar el scanner
        scanner.close();
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