package uniandes.dpoo.pruebas;

import java.util.Scanner;
import uniandes.dpoo.learningpath.Feedback;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.sistema.Sistema;
import uniandes.dpoo.usuario.Estudiante;
import uniandes.dpoo.usuario.Usuario;

public class ArchivoPruebaReseña {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Estudiante estudiante1 = new Estudiante(0, null, null);
        estudiante1.setId(Sistema.generarIDUnicoUsuarios());
        estudiante1.setLogin("mgarcia");
        estudiante1.setContraseña("pass456");
        
        LearningPath learningPath1 = new LearningPath();

        // Usar setters para asignar información básica al LearningPath
        learningPath1.setId(Sistema.generarIDUnicoLearningPaths());
        learningPath1.setTitulo("Fundamentos de Programación");
        learningPath1.setDescripcionContenido("Curso introductorio sobre los fundamentos de programación en Java");
        learningPath1.setDescripcionObjetivo("Entender las bases de la programación");
        learningPath1.setNivelDificultad("Facil");
        learningPath1.setRating("4.5");
        
        Sistema sistema = new Sistema();
		sistema.agregarLearningPath(learningPath1);
        
        Actividad actividad1 = new Actividad();
        actividad1.setId(Sistema.generarIDUnicoActividades());
        actividad1.setTituloActividad("Clase 1: Introducción a Java");
        actividad1.setDescripcion("Aprender los conceptos básicos de Java");
        actividad1.setDuracionEsperada("60");
        actividad1.setObligatoria(true);
        
        System.out.println("El id para que deje la reseña es: " + actividad1.getId());
        
        // Crear una actividad para la reseña
        System.out.println("Ingrese el id de la actividad a la cual le quiere dejar una reseña:");
        String idActividad = scanner.nextLine();
        Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(idActividad));

        // Ingresar comentario para el feedback
        System.out.println("Ingrese el comentario sobre la actividad:");
        String comentario = scanner.nextLine();

        // Ingresar rating para la actividad (por ejemplo, de 1 a 5)
        System.out.println("Ingrese el rating (de 1 a 5) para la actividad:");
        int rating = scanner.nextInt();

        // Crear el objeto Feedback
        Feedback feedback = new Feedback(estudiante1, comentario, rating, actividadEncontrada);

        // Mostrar el feedback ingresado
        System.out.println("\nReseña ingresada:");
        feedback.mostrarFeedback();
        System.out.println("Rating: " + feedback.getRating());

        // Cerrar el scanner
        scanner.close();
    }
}