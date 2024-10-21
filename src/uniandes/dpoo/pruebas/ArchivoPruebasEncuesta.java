package uniandes.dpoo.pruebas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.Encuesta;
import uniandes.dpoo.sistema.*;
import uniandes.dpoo.usuario.Profesor;

public class ArchivoPruebasEncuesta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Crear un profesor (puedes modificar la clase Profesor según tu implementación)
        Profesor profesor = new Profesor(Sistema.generarIDUnicoUsuarios() , "Juan", "12345");

        // Crear una lista de actividades previas sugeridas
        List<Actividad> actividadesPrevias = new ArrayList<>();
        
        // Crear una nueva encuesta
        Encuesta encuesta = new Encuesta(Sistema.generarIDUnicoActividades(), "Encuesta de Satisfacción", 
                                          "Encuesta para medir la satisfacción del estudiante.",
                                          "Medir satisfacción", "Baja", "30 minutos", 
                                          actividadesPrevias, "2024-12-31", true, 
                                          profesor, null, null);
        
        System.out.println("¿Cuántas preguntas desea agregar a la encuesta?");
        int cantidadPreguntas = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después del número

        // Agregar preguntas a la encuesta según lo indicado por el usuario
        for (int i = 0; i < cantidadPreguntas; i++) {
            encuesta.agregarPregunta(scanner);
        }

        // Mostrar las preguntas agregadas
        System.out.println("Preguntas de la encuesta:");
        encuesta.mostrarPreguntas();

        // Responder preguntas
        encuesta.responderPreguntas(scanner);

        // Mostrar respuestas del estudiante
        encuesta.mostrarRespuestasEstudiante();

        // Cambiar el estado de la encuesta
        encuesta.cambiarEstadoEncuesta();
        
     // Mostrar la actividad completa
        System.out.println("Detalles de la actividad:");
        System.out.println("Título: " + encuesta.getTituloActividad());
        System.out.println("Descripción: " + encuesta.getDescripcion());
        System.out.println("Objetivo: " + encuesta.getObjetivo());
        System.out.println("Nivel de dificultad: " + encuesta.getNivelDificultad());
        System.out.println("Duración esperada: " + encuesta.getDuracionEsperada());
        System.out.println("Fecha límite: " + encuesta.getFechaLimite());
        System.out.println("Estado: " + encuesta.getEstado());
        

        // Cerrar el scanner
        scanner.close();
    }

}
