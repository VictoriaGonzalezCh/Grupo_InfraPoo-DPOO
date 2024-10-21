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
        Encuesta encuesta = new Encuesta(1, "Encuesta de Satisfacción", 
                                          "Encuesta para medir la satisfacción del estudiante.",
                                          "Medir satisfacción", "Baja", "30 minutos", 
                                          actividadesPrevias, "2024-12-31", true, 
                                          profesor, null, null);
        
        // Agregar preguntas a la encuesta
        for (int i = 0; i < 2; i++) {
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

        // Cerrar el scanner
        scanner.close();
    }
}
