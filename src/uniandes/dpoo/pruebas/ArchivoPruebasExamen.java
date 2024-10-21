package uniandes.dpoo.pruebas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpath.Examen;
import uniandes.dpoo.learningpath.PreguntaAbierta;
import uniandes.dpoo.usuario.Profesor;
import uniandes.dpoo.sistema.Sistema;

public class ArchivoPruebasExamen {

    public static void main(String[] args) {
        Profesor profesor = new Profesor(Sistema.generarIDUnicoUsuarios(), "Profesor de Prueba", "profesor@ejemplo.com");
        Examen nuevoExamen = new Examen(Sistema.generarIDUnicoActividades(), 
                                         "Examen de Java", 
                                         "Examen para evaluar conocimientos en Java.", 
                                         "Evaluar el conocimiento en programación en Java.", 
                                         "medio", 
                                         "60", 
                                         new ArrayList<>(), 
                                         "15/11/2024", 
                                         true, 
                                         profesor, 
                                         new ArrayList<>(), 
                                         "enviada", 
                                         new ArrayList<>());

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 2; i++) {
            nuevoExamen.agregarPregunta(scanner);
        }

        imprimirDetallesExamen(nuevoExamen);
        nuevoExamen.responderPreguntas(scanner);
        nuevoExamen.mostrarRespuestasEstudiante();
        nuevoExamen.calificarExamen();
        scanner.close();
    }

    public static void imprimirDetallesExamen(Examen examen) {
        System.out.println("Detalles del Examen:");
        System.out.println("Título: " + examen.getTituloActividad());
        System.out.println("Descripción: " + examen.getDescripcion());
        System.out.println("Objetivo: " + examen.getObjetivo());
        System.out.println("Nivel de Dificultad: " + examen.getNivelDificultad());
        System.out.println("Duración Esperada: " + examen.getDuracionEsperada() + " minutos");
        System.out.println("Fecha Límite: " + examen.getFechaLimite());
        System.out.println("Estado: " + examen.getEstado());
        
        System.out.println("Preguntas del examen:");
        examen.mostrarPreguntas();
    }
}
