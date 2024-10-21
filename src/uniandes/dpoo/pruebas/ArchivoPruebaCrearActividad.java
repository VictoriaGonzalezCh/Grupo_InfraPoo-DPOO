package uniandes.dpoo.pruebas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.sistema.Sistema;
import uniandes.dpoo.usuario.Profesor;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.Actividad;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArchivoPruebaCrearActividad {

    public static void main(String[] args) {
        // Crear instancia del sistema
        Sistema sistema = new Sistema();
        
        // Crear un profesor y un LearningPath para hacer la prueba
        Profesor profesor = new Profesor(0, null, null);
        profesor.setId(Sistema.generarIDUnicoUsuarios());
        profesor.setLogin("jperez");
        profesor.setContraseña("password123");

        LearningPath learningPath = new LearningPath();
        learningPath.setId(Sistema.generarIDUnicoLearningPaths());
        learningPath.setTitulo("Fundamentos de Programación");
        learningPath.setDescripcionContenido("Curso introductorio sobre los fundamentos de programación en Java");
        learningPath.setDescripcionObjetivo("Entender las bases de la programación");
        learningPath.setNivelDificultad("Fácil");
        learningPath.setRating("4.5");
        sistema.agregarLearningPath(learningPath);

        
        // Imprimir actividades para verificar
        System.out.println("Actividades en el Learning Path: ");
        for (Actividad actividad : learningPath.getActividades()) {
            System.out.println("- " + actividad.getTituloActividad());
            
        
            Actividad nuevaActividad = new Actividad();
            nuevaActividad.setId(Sistema.generarIDUnicoActividades());
            nuevaActividad.setTituloActividad("Persistencia");
            nuevaActividad.setDescripcion("Clase 8 de DPOO");
            nuevaActividad.setObjetivo("Aprender sobre persistencia");
            nuevaActividad.setDuracionEsperada("80");
            nuevaActividad.setObligatoria(true);
            nuevaActividad.setNivelDificultad("medio");
            nuevaActividad.setFechaLimite("14/11/2024");
            // Aquí puedes agregar actividades previas y prerequisitos si los tienes
            nuevaActividad.setActividadesPreviasSugeridas(new ArrayList<>());
            nuevaActividad.setPrerequisitos(new ArrayList<>());
            nuevaActividad.setActividadesSeguimientoRecomendadas(new ArrayList<>());

            learningPath.agregarActividad(nuevaActividad);
            learningPath.setDuracionMinutos();
            System.out.println("El id para la actividad es " + nuevaActividad.getId());
            
            
            
        }    
        
        
        
            
        }

    
}