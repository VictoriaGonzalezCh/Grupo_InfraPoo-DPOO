package uniandes.dpoo.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.Encuesta;
import uniandes.dpoo.learningpath.Examen;
import uniandes.dpoo.learningpath.Feedback;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.ProgresoEstudiante;
import uniandes.dpoo.learningpath.Quiz;
import uniandes.dpoo.learningpath.RecursoEducativo;
import uniandes.dpoo.learningpath.Tarea;

public class Profesor extends Usuario {

	List<LearningPath> learningPaths;
	
	
	public static Profesor nuevoProfesor(int id, String login, String contraseña) {
		return new Profesor(id, login, contraseña);
		// TODO Auto-generated constructor stub
	}
	
	public Profesor(int id, String login, String contraseña) {
		this.id = id;
		this.login = login;
		this.contraseña = contraseña;
		this.learningPaths = new ArrayList<>();
	}
	
	
	public LearningPath nuevoLearningPath(int id, String titulo, String descripcionContenido, String descripcionObjetivo, String nivelDificultad, String duracionMinutos, String rating){
		LearningPath learningPath = new LearningPath(id, titulo, descripcionContenido, descripcionObjetivo, nivelDificultad, duracionMinutos, rating);
		this.learningPaths.add(learningPath);
		return learningPath;
	}
	
	public static void editarLearningPath(LearningPath learningPath,String nuevoTitulo, String nuevaDescripcionContenido, String nuevaDescripcionObjetivo, String nuevoNivelDificultad, String nuevaDuracionMinutos, String nuevoRating) {
		learningPath.setTitulo(nuevoTitulo);
        learningPath.setDescripcionContenido(nuevaDescripcionContenido);
        learningPath.setDescripcionObjetivo(nuevaDescripcionObjetivo);
        learningPath.setNivelDificultad(nuevoNivelDificultad);
        learningPath.setDuracionMinutos(nuevaDuracionMinutos);
        learningPath.setRating(nuevoRating);	
	}
	
	private static Actividad actualizarActividad(Actividad actividad, String nuevaDescripcion, String nuevoObjetivo, String nuevoNivelDificultad, String nuevaDuracionEsperada, List<Actividad> actividadesPreviasSugeridas, String nuevaFechaLimite, boolean nuevaObligatoria, List<Actividad> prerequisitos){
		actividad.setDescripcion(nuevaDescripcion);
        actividad.setObjetivo(nuevoObjetivo);
        actividad.setNivelDificultad(nuevoNivelDificultad);
        actividad.setDuracionEsperada(nuevaDuracionEsperada);
        actividad.setFechaLimite(nuevaFechaLimite);
        actividad.setObligatoria(nuevaObligatoria);
		return actividad;
       	}
	
	
	private static Actividad clonarActividad(Actividad actividad) {
        // Crear una nueva instancia de Actividad con los mismos atributos
        Actividad actividadClonada = new Actividad();
        actividadClonada.setTituloActividad(actividad.getTituloActividad()+ " - clonada");
        actividadClonada.setDescripcion(actividad.getDescripcion());
        actividadClonada.setObjetivo(actividad.getObjetivo());
        actividadClonada.setNivelDificultad(actividad.getNivelDificultad());
        actividadClonada.setDuracionEsperada(actividad.getDuracionEsperada());
        actividadClonada.setObligatoria(actividad.isObligatoria());
        actividadClonada.setFechaLimite(actividad.getFechaLimite());
        actividadClonada.setActividadesPreviasSugeridas(actividad.getActividadesPreviasSugeridas());
        actividadClonada.setPrerequisitos(actividad.getPrerequisitos());
        actividadClonada.setCreador(actividad.getCreador());
        
        return actividadClonada; // Retornar la actividad clonada
    }
	
	public static Actividad editarActividad(Actividad actividad, int id, String nuevaDescripcion, String nuevoObjetivo,
            String nuevoNivelDificultad, String nuevaDuracionEsperada,
            List<Actividad> actividadesPreviasSugeridas, String nuevaFechaLimite,
            boolean nuevaObligatoria, List<Actividad> prerequisitos) {
		
		
		Actividad resultado;
		
		if (id == actividad.getCreador()) {resultado = actualizarActividad(actividad, nuevaDescripcion, nuevoObjetivo, nuevoNivelDificultad,
                nuevaDuracionEsperada, actividadesPreviasSugeridas, nuevaFechaLimite,
                nuevaObligatoria, prerequisitos); System.out.println("La actividad ha sido editada");}
		else {resultado = clonarActividad(actividad); System.out.println("La actividad tuvo que ser clonada para poder ser editada"); resultado = actualizarActividad(resultado, nuevaDescripcion, nuevoObjetivo, nuevoNivelDificultad,
                nuevaDuracionEsperada, actividadesPreviasSugeridas, nuevaFechaLimite,
                nuevaObligatoria, prerequisitos); System.out.println("La actividad clonada ha sido editada"); }
		
		return resultado;
	}
	
	public static Actividad nuevaActividad(String tipo, int id, String titulo, String descripcion, String objetivo, String duracionEsperada, boolean obligatoria, int idCreador, String nivelDificultad, List<Actividad> actividadesPreviasSugeridas, String fechaLimite, List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas, Scanner scanner) {
	    Actividad actividad = null;
	    
	    switch (tipo.toLowerCase()) {
	        case "recurso educativo":
	        	System.out.println("Especifique el tipo de recurso para el recurso educativo: ");
	             String tipoRecurso = scanner.nextLine();
	             System.out.println("Escriba lo que quiere que tenga el recurso: ");
	             String recurso = scanner.nextLine();
	             
	            actividad = new RecursoEducativo(id, tipoRecurso, recurso, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, idCreador, prerequisitos, actividadesSeguimientoRecomendadas);
	            
	            break;
	            
	        case "tarea":
	            actividad = new Tarea(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, idCreador, prerequisitos, actividadesSeguimientoRecomendadas);
	            break;
	            
	        case "quiz":
	        	System.out.println("Especifique la calificacion minima para el quiz: ");
	             String calificacionMinima = scanner.nextLine();
	             System.out.println("Especifique cuentas preguntas de opcion multiple quiere que tenga el quiz: ");
	             String numPreguntas = scanner.nextLine();
	    
	            Quiz actividadQuiz = new Quiz(id, Integer.parseInt(calificacionMinima), titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, idCreador, prerequisitos, actividadesSeguimientoRecomendadas);
	            
	            for (int i = 0; i < Integer.parseInt(numPreguntas); i++) {
	                actividadQuiz.agregarPregunta(scanner);
	            }
	            actividad = actividadQuiz;
	            break;
	            
	        case "examen":
	        	System.out.println("Especifique cuantas preguntas de opcion multiple quiere que tenga el quiz: ");
	            String numPreguntasExamen = scanner.nextLine();
	        	
	            Examen actividadExamen = new Examen(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, idCreador, prerequisitos, actividadesSeguimientoRecomendadas);
	            for (int i = 0; i < Integer.parseInt(numPreguntasExamen); i++) {
	                actividadExamen.agregarPregunta(scanner);
	            }
	            actividad = actividadExamen;
	            
	            break;  
	        case "encuesta":
	        	System.out.println("Especifique cuantas preguntas de opcion multiple quiere que tenga el quiz: ");
	            String numPreguntasEncuesta = scanner.nextLine();
	        	
	            Encuesta actividadEncuesta = new Encuesta(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, idCreador, prerequisitos, actividadesSeguimientoRecomendadas);
	            for (int i = 0; i < Integer.parseInt(numPreguntasEncuesta); i++) {
	            	actividadEncuesta.agregarPregunta(scanner);
	            }
	            actividad = actividadEncuesta;
	            
	            break;   
	        
	            
	        default:
	            System.out.println("Tipo de actividad no reconocido.");
	            break;
	    }
	    
	    return actividad;
	}
	
	public void establecerPrerrequisitos(Actividad actividad, List<Actividad> prerrequisitos) {
        actividad.setPrerequisitos(prerrequisitos);
    }
	
	public void calificarTareasOExamenes(Actividad actividad, Estudiante estudiante, boolean esExito) {
        if (actividad instanceof Tarea) {
            Tarea tarea = (Tarea) actividad;
            tarea.calificarTarea(esExito);
            
        } else if (actividad instanceof Examen) {
            Examen examen = (Examen) actividad;
            examen.mostrarRespuestasEstudiante();
            examen.calificarExamen();
        }
    }
	
	
	
	public void editarResultado(Actividad actividad, String cambioResultado) {
        actividad.cambiarResultado(cambioResultado);
        
    }
	
	public List<LearningPath> getLearningPaths() {
		return learningPaths;
	}

	public void setLearningPaths(List<LearningPath> learningPaths) {
		this.learningPaths = learningPaths;
	}
	
	
	public Feedback crearFeedbackProfesor(Profesor autor, Actividad actividad, int rating, String comentario) {
        // El profesor puede crear un feedback
        Feedback feedback = new Feedback(autor, comentario, rating, actividad);
        
        actividad.agregarFeedback(feedback);
        //System.out.println("Feedback creado por el estudiante: " + getLogin());
        return feedback;
    }
	
	
	
}
