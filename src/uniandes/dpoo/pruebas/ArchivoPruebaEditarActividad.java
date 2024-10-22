package uniandes.dpoo.pruebas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.sistema.Sistema;
import uniandes.dpoo.usuario.Profesor;

public class ArchivoPruebaEditarActividad {

    private static Sistema sistema = new Sistema();

    public static void main(String[] args) {
        // Cargar actividades de prueba en el sistema
        cargarActividadesDePrueba();

        // Crear un objeto Scanner para capturar entrada desde consola
        Scanner scanner = new Scanner(System.in);

        // Invocar el método para editar una actividad
        editarActividad(scanner);
    }

    private static void editarActividad(Scanner scanner) {
        // Mostrar todas las actividades con sus IDs antes de que el usuario seleccione una
        System.out.println("Lista de actividades disponibles para editar:");

        // Iterar sobre todas las actividades de todos los Learning Paths y mostrarlas
        List<LearningPath> learningPaths = sistema.obtenerTodosLosLearningPaths(); // Asumiendo que tienes este método
        for (LearningPath lp : learningPaths) {
            for (Actividad actividad : lp.getActividades()) {
                System.out.println("ID: " + actividad.getId() + " | Título: " + actividad.getTituloActividad());
            }
        }

        System.out.println("Escriba el ID de la actividad que quiere editar: ");
        String idActividadParaEditar = scanner.nextLine();
        
        // Buscar la actividad por ID
        Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(idActividadParaEditar));

        System.out.println("Actividad antes de editar: ");
        mostrarActividad(actividadEncontrada);
        
        if (actividadEncontrada == null) {
            System.out.println("No se encontró ninguna actividad con el ID proporcionado.");
            return;
        }

        // Mostrar las opciones de edición
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Editar la descripción de la actividad");
        System.out.println("2. Añadir una actividad de prerrequisito o seguimiento");
        int eleccion = Integer.parseInt(scanner.nextLine());

        switch (eleccion) {
            case 1:
                // Editar la descripción de la actividad
                System.out.println("Descripción nueva de la actividad: ");
                String nuevaDescripcion = scanner.nextLine();

                System.out.println("Descripción nueva del objetivo: ");
                String nuevoObjetivo = scanner.nextLine();

                System.out.println("Nuevo nivel de dificultad (por ejemplo, fácil, medio, difícil): ");
                String nuevoNivelDificultad = scanner.nextLine();

                System.out.println("Nueva duración esperada (en minutos): ");
                String nuevaDuracionEsperada = scanner.nextLine();

                System.out.println("Nueva fecha límite (formato: dd/MM/yyyy): ");
                String nuevaFechaLimite = scanner.nextLine();

                System.out.println("¿Es una actividad obligatoria? (true/false): ");
                boolean nuevaObligatoria = Boolean.parseBoolean(scanner.nextLine());

                // Lista vacía de actividades previas sugeridas y prerrequisitos (modifícalo según tu lógica)
                List<Actividad> actividadesPreviasSugeridas = new ArrayList<>();
                List<Actividad> prerequisitos = new ArrayList<>();

                // Obtener el profesor autenticado (simulado aquí)
                Profesor usuario = (Profesor) sistema.obtenerUsuarioAutenticado();

                // Llamar al método para editar la actividad
                Profesor.editarActividad(actividadEncontrada, usuario, nuevaDescripcion, nuevoObjetivo, nuevoNivelDificultad,
                        nuevaDuracionEsperada, actividadesPreviasSugeridas, nuevaFechaLimite, nuevaObligatoria, prerequisitos);

                System.out.println("Actividad después de editar: ");
                mostrarActividad(actividadEncontrada);
                
                System.out.println("La actividad ha sido editada exitosamente.");
                break;

            case 2:
                // Añadir una actividad de prerrequisito o seguimiento
                System.out.println("¿Desea añadir una actividad como prerrequisito, seguimiento o actividad previa?");
                System.out.println("1. Prerrequisito");
                System.out.println("2. Seguimiento");
                System.out.println("3. Previa");
                int tipoActividadRelacionada = Integer.parseInt(scanner.nextLine());

                System.out.println("Escriba el ID de la actividad relacionada: ");
                String idActividadRelacionada = scanner.nextLine();
                Actividad actividadRelacionada = sistema.buscarActividadPorId(Integer.parseInt(idActividadRelacionada));

                if (actividadRelacionada == null) {
                    System.out.println("No se encontró ninguna actividad con el ID proporcionado.");
                    return;
                }

                if (tipoActividadRelacionada == 1) {
                    añadirPrerequisito(scanner);
                } else if (tipoActividadRelacionada == 2) {
                    añadirActividadesSeguimientoRecomendadas(scanner);
                } else if (tipoActividadRelacionada == 3) {
                    añadirActividadPrevia(scanner);
                }
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }

    private static void mostrarActividad(Actividad actividad) {
        System.out.println("ID: " + actividad.getId());
        System.out.println("Título: " + actividad.getTituloActividad());
        System.out.println("Descripción: " + actividad.getDescripcion());
        System.out.println("Duración Esperada: " + actividad.getDuracionEsperada() + " minutos");
        System.out.println("Obligatoria: " + actividad.isObligatoria());
        System.out.println("Fecha Límite: " + actividad.getFechaLimite());
    }


	// Métodos para añadir prerrequisitos, actividades de seguimiento y previas (simulados aquí)
    private static void añadirPrerequisito(Scanner scanner) {
        System.out.println("Añadiendo prerrequisito...");
        // Lógica para añadir un prerrequisito
    }

    private static void añadirActividadesSeguimientoRecomendadas(Scanner scanner) {
        System.out.println("Añadiendo actividades de seguimiento recomendadas...");
        // Lógica para añadir actividades de seguimiento
    }

    private static void añadirActividadPrevia(Scanner scanner) {
        System.out.println("Añadiendo actividad previa...");
        // Lógica para añadir una actividad previa
    }

    // Método para cargar actividades de prueba en el sistema
    private static void cargarActividadesDePrueba() {
        LearningPath learningPath1 = new LearningPath();

        // Usar setters para asignar información básica al LearningPath
        learningPath1.setId(Sistema.generarIDUnicoLearningPaths());
        learningPath1.setTitulo("Fundamentos de Programación");
        learningPath1.setDescripcionContenido("Curso introductorio sobre los fundamentos de programación en Java");
        learningPath1.setDescripcionObjetivo("Entender las bases de la programación");
        learningPath1.setNivelDificultad("Facil");
        learningPath1.setRating("4.5");

        sistema.agregarLearningPath(learningPath1);

        Actividad actividad1 = new Actividad();
        actividad1.setId(Sistema.generarIDUnicoActividades());
        actividad1.setTituloActividad("Clase 1: Introducción a Java");
        actividad1.setDescripcion("Aprender los conceptos básicos de Java");
        actividad1.setDuracionEsperada("60");
        actividad1.setObligatoria(true);

        Actividad actividad2 = new Actividad();
        actividad2.setId(Sistema.generarIDUnicoActividades());
        actividad2.setTituloActividad("Tarea 1: Variables y Tipos de Datos");
        actividad2.setDescripcion("Resolver ejercicios sobre variables y tipos de datos");
        actividad2.setDuracionEsperada("120");
        actividad2.setObligatoria(false);

        Actividad actividad3 = new Actividad();
        actividad3.setId(Sistema.generarIDUnicoActividades());
        actividad3.setTituloActividad("Quiz 1: Estructuras de Control");
        actividad3.setDescripcion("Evaluar los conceptos de estructuras de control en Java");
        actividad3.setDuracionEsperada("45");
        actividad3.setObligatoria(true);

        sistema.agregarActividad(learningPath1, actividad1);
        sistema.agregarActividad(learningPath1, actividad2);
        sistema.agregarActividad(learningPath1, actividad3);
    }
}
