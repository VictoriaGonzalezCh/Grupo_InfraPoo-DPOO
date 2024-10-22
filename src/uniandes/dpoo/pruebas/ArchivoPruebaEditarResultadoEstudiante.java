package uniandes.dpoo.pruebas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.ProgresoEstudiante;
import uniandes.dpoo.sistema.Sistema;
import uniandes.dpoo.usuario.Estudiante;

public class ArchivoPruebaEditarResultadoEstudiante {
    private static Sistema sistema = new Sistema();

    public static void main(String[] args) {
        // Cargar datos de prueba en el sistema
        cargarDatosDePrueba();

        // Crear un objeto Scanner para capturar entrada desde consola
        Scanner scanner = new Scanner(System.in);

        // Invocar el método para editar el resultado de la actividad
        editarResultadoActividad(scanner);
    }

    private static void editarResultadoActividad(Scanner scanner) {
        System.out.println("Escriba el id de la actividad: ");
        String id = scanner.nextLine();
        Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));

        System.out.println("Escriba el id del estudiante del cual quiere buscar las respuestas de esa actividad: ");
        String idUsuario = scanner.nextLine();
        Estudiante estudianteEncontrado = sistema.stringAEstudiante(Integer.parseInt(idUsuario));

        // Simular la visualización de respuestas del estudiante
        verRespuestasEstudiante(estudianteEncontrado, actividadEncontrada);

        System.out.println("¿A qué resultado quiere cambiar el resultado actual? (Para examenes tiene que ser exitoso o no exitoso, para tareas puede ser exitoso, insatisfactorio, completado): ");
        String nuevoResultado = scanner.nextLine();

        // Verifica si la actividad y el estudiante fueron encontrados
        if (actividadEncontrada != null && estudianteEncontrado != null) {
            LearningPath learningPath = sistema.buscarLearningPathPorActividad(actividadEncontrada);
            
            if (learningPath != null) {
                ProgresoEstudiante progreso = learningPath.obtenerProgresoDeEstudiante(estudianteEncontrado);
                
                if (progreso != null) {
                    // Cambiamos el resultado en el progreso del estudiante 
                    progreso.cambiarResultadoActividad(actividadEncontrada, nuevoResultado);
                    System.out.println("El resultado de la actividad ha sido cambiado a: " + nuevoResultado);
                } else {
                    System.out.println("No se encontró progreso para el estudiante en la actividad especificada.");
                }
            } else {
                System.out.println("No se encontró el Learning Path para la actividad especificada.");
            }
        } else {
            System.out.println("Actividad o estudiante no encontrados.");
        }
    }

    private static void verRespuestasEstudiante(Estudiante estudiante, Actividad actividad) {
        // Simulamos la visualización de las respuestas del estudiante
        System.out.println("Respuestas del estudiante " + estudiante.getLogin() + " para la actividad " + actividad.getTituloActividad() + ":");
        // Aquí podrías listar las respuestas guardadas del estudiante para esa actividad
    }

    private static void cargarDatosDePrueba() {
        // Crear un LearningPath
        LearningPath learningPath1 = new LearningPath();
        learningPath1.setId(Sistema.generarIDUnicoLearningPaths());
        learningPath1.setTitulo("Curso de Programación");
        learningPath1.setDescripcionContenido("Curso sobre conceptos básicos de programación.");
        sistema.agregarLearningPath(learningPath1);

        // Crear algunas actividades
        Actividad actividad1 = new Actividad();
        actividad1.setId(Sistema.generarIDUnicoActividades());
        actividad1.setTituloActividad("Tarea 1");
        actividad1.setDescripcion("Resolver ejercicios de programación.");
        sistema.agregarActividad(learningPath1, actividad1);

        Actividad actividad2 = new Actividad();
        actividad2.setId(Sistema.generarIDUnicoActividades());
        actividad2.setTituloActividad("Examen 1");
        actividad2.setDescripcion("Examen sobre fundamentos de programación.");
        sistema.agregarActividad(learningPath1, actividad2);

        // Crear estudiantes
     // Crear estudiantes vacíos y usar setters para asignar sus atributos
        Estudiante estudiante1 = new Estudiante(0, null, null);
        estudiante1.setId(Sistema.generarIDUnicoUsuarios());
        estudiante1.setLogin("mgarcia");
        estudiante1.setContraseña("pass456");

        Estudiante estudiante2 = new Estudiante(0, null, null);
        estudiante2.setId(Sistema.generarIDUnicoUsuarios());
        estudiante2.setLogin("clopez");
        estudiante2.setContraseña("pass789");

        sistema.agregarEstudiante(estudiante1);
        sistema.agregarEstudiante(estudiante2);

        // Simular progreso del estudiante
        ProgresoEstudiante progreso1 = new ProgresoEstudiante(estudiante1, actividad2, null, null, 0, 0);
        progreso1.cambiarResultadoActividad(actividad1, "Completado");
        progreso1.cambiarResultadoActividad(actividad2, "Exitoso");
        learningPath1.setProgresoEstudiante(progreso1);
        
        ProgresoEstudiante progreso2 = new ProgresoEstudiante(estudiante2, actividad2, null, null, 0, 0);
        progreso2.cambiarResultadoActividad(actividad1, "Insatisfactorio");
        progreso2.cambiarResultadoActividad(actividad2, "No exitoso");
        learningPath1.setProgresoEstudiante(progreso2);
    }
}
