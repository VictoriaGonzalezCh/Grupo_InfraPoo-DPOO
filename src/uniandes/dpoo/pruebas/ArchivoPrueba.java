package uniandes.dpoo.pruebas;

import uniandes.dpoo.learningpath.*; // Asegúrate de importar correctamente tus clases
import uniandes.dpoo.sistema.Sistema;
import uniandes.dpoo.usuario.Estudiante;
import uniandes.dpoo.usuario.Profesor;

public class ArchivoPrueba {

    public static void main(String[] args) {
        // Crear instancias del sistema y los usuarios
        Sistema sistema = new Sistema();

        Profesor profesor = new Profesor(0, null, null);
        profesor.setId(Sistema.generarIDUnicoUsuarios());
        profesor.setLogin("jperez");
        profesor.setContraseña("password123");

        // Crear estudiantes vacíos y usar setters para asignar sus atributos
        Estudiante estudiante1 = new Estudiante(0, null, null);
        estudiante1.setId(Sistema.generarIDUnicoUsuarios());
        estudiante1.setLogin("mgarcia");
        estudiante1.setContraseña("pass456");

        Estudiante estudiante2 = new Estudiante(0, null, null);
        estudiante2.setId(Sistema.generarIDUnicoUsuarios());
        estudiante2.setLogin("clopez");
        estudiante2.setContraseña("pass789");

        // Registrar los usuarios en el sistema usando los setters
        sistema.registrarUsuario(profesor);
        sistema.registrarUsuario(estudiante1);
        sistema.registrarUsuario(estudiante2);

        // Crear Learning Paths
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
        actividad1.setId(Sistema.generarIDUnicoActividades());
        actividad2.setTituloActividad("Tarea 1: Variables y Tipos de Datos");
        actividad2.setDescripcion("Resolver ejercicios sobre variables y tipos de datos");
        actividad2.setDuracionEsperada("120");
        actividad2.setObligatoria(false);

        Actividad actividad3 = new Actividad();
        actividad1.setId(Sistema.generarIDUnicoActividades());
        actividad3.setTituloActividad("Quiz 1: Estructuras de Control");
        actividad3.setDescripcion("Evaluar los conceptos de estructuras de control en Java");
        actividad3.setDuracionEsperada("45");
        actividad3.setObligatoria(true);

        // Agregar actividades al LearningPath
        learningPath1.agregarActividad(actividad1);
        learningPath1.agregarActividad(actividad2);
        learningPath1.agregarActividad(actividad3);
        

        // Asignar estudiantes a los Learning Paths
        estudiante1.registrarseLearningPath(learningPath1);
        
        estudiante2.registrarseLearningPath(learningPath1);
        
     // Imprimir detalles del Learning Path
        imprimirDetallesLearningPath(learningPath1);
    }

    public static void imprimirDetallesLearningPath(LearningPath lp) {
        System.out.println("Learning Path: " + lp.getTitulo());
        System.out.println("Descripción: " + lp.getDescripcionContenido());
        System.out.println("Objetivo: " + lp.getDescripcionObjetivo());
        System.out.println("Nivel de Dificultad: " + lp.getNivelDificultad());
        System.out.println("Rating: " + lp.getRating());
        System.out.println("Actividades:");

        for (Actividad actividad : lp.getActividades()) {
            System.out.println("  - " + actividad.getTituloActividad() + " (" + actividad.getDuracionEsperada() + " minutos)");
            System.out.println("    Descripción: " + actividad.getDescripcion());
            System.out.println("    Obligatoria: " + (actividad.isObligatoria() ? "Sí" : "No"));
        }

        // Imprimir estudiantes inscritos en el Learning Path
        System.out.println("Estudiantes inscritos:");
        for (Estudiante estudiante : lp.getEstudiantesInscritos()) {
            System.out.println("  - " + estudiante.getLogin());
        }
    }
}
