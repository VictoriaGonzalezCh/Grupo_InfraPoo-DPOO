package uniandes.dpoo.pruebas;


import java.util.Scanner;
import uniandes.dpoo.usuario.Profesor;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.sistema.Sistema;

public class ArchivoPruebaEdicionLearningPath {

    private static Sistema sistema = new Sistema();

    public static void main(String[] args) {
    	System.out.println("Los ids de los learning paths que puede editar son:  ");
    	
        // Cargar LearningPaths existentes en el sistema
        cargarLearningPathsDePrueba();

        // Crear un objeto Scanner para capturar entrada desde consola
        Scanner scanner = new Scanner(System.in);

        // Invocar el método para editar un Learning Path
        editarLearningPath(scanner);
    }

    private static void editarLearningPath(Scanner scanner) {
        // Pedir ID del Learning Path a editar
        System.out.println("Escriba el id del Learning Path que quiere editar: ");
        String idParaEditar = scanner.nextLine();

        // Buscar el Learning Path en el sistema
        LearningPath learningPathEncontrado = sistema.buscarLearningPath(Integer.parseInt(idParaEditar));

        if (learningPathEncontrado != null) {
            // Pedir nueva información para editar el Learning Path
            System.out.println("Título nuevo del Learning Path: ");
            String nuevoTitulo = scanner.nextLine();

            System.out.println("Descripción nueva del contenido: ");
            String nuevaDescripcionContenido = scanner.nextLine();

            System.out.println("Descripción nueva del objetivo: ");
            String nuevaDescripcionObjetivo = scanner.nextLine();

            System.out.println("Nuevo nivel de dificultad (por ejemplo, fácil, medio, difícil): ");
            String nuevoNivelDificultad = scanner.nextLine();

            System.out.println("Nueva calificación inicial (rating): ");
            String nuevoRating = scanner.nextLine();

            // Llamar al método de edición en la clase Profesor
            Profesor.editarLearningPath(learningPathEncontrado, nuevoTitulo, nuevaDescripcionContenido, nuevaDescripcionObjetivo, nuevoNivelDificultad, nuevoRating);

            System.out.println("El Learning Path ha sido editado exitosamente.");
        } else {
            System.out.println("Learning Path con id " + idParaEditar + " no encontrado.");
        }
    }

    // Método para cargar datos de prueba de LearningPaths en el sistema
    private static void cargarLearningPathsDePrueba() {
        // Crear algunos LearningPaths de prueba y agregarlos al sistema
        sistema.agregarLearningPath(new LearningPath(Sistema.generarIDUnicoLearningPaths(), "Introducción a Java", "Contenido sobre Java", "Aprender los fundamentos de Java", "Fácil", "4.5"));
        sistema.agregarLearningPath(new LearningPath(Sistema.generarIDUnicoLearningPaths(), "Estructuras de Datos", "Listas, Pilas, Colas", "Comprender las estructuras de datos", "Medio", "4.0"));
        sistema.agregarLearningPath(new LearningPath(Sistema.generarIDUnicoLearningPaths(), "Algoritmos Avanzados", "Algoritmos Greedy, DP", "Aprender algoritmos avanzados", "Difícil", "4.8"));
    }
}