package uniandes.dpoo.usuario;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.Encuesta;
import uniandes.dpoo.learningpath.Examen;
import uniandes.dpoo.learningpath.Feedback;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.Quiz;
import uniandes.dpoo.learningpath.RecursoEducativo;
import uniandes.dpoo.learningpath.Tarea;

public class ProfesorCreador extends Usuario{
		
		List<LearningPath> learningPaths;
		
		
		public static ProfesorCreador nuevoProfesorCreador(int id, String login, String contraseña) {
			return new ProfesorCreador(id, login, contraseña);
			// TODO Auto-generated constructor stub
		}
		
		public ProfesorCreador(int id, String login, String contraseña) {
			super(id, login, contraseña);
		}
		
		
		public LearningPath nuevoLearningPath(int id, String titulo, String descripcionContenido, String descripcionObjetivo, String nivelDificultad, String rating){
			LearningPath learningPath = new LearningPath(id, titulo, descripcionContenido, descripcionObjetivo, nivelDificultad, rating);
			this.learningPaths.add(learningPath);
			return learningPath;
		}
		
		public static void editarLearningPath(LearningPath learningPath,String nuevoTitulo, String nuevaDescripcionContenido, String nuevaDescripcionObjetivo, String nuevoNivelDificultad, String nuevoRating) {
			learningPath.setTitulo(nuevoTitulo);
	        learningPath.setDescripcionContenido(nuevaDescripcionContenido);
	        learningPath.setDescripcionObjetivo(nuevaDescripcionObjetivo);
	        learningPath.setNivelDificultad(nuevoNivelDificultad);
	        learningPath.setRating(nuevoRating);
	        learningPath.setFechaModificacion();
	        learningPath.setVersion();
		}
		
		public static Actividad actualizarActividad(Actividad actividad, String nuevaDescripcion, String nuevoObjetivo, String nuevoNivelDificultad, String nuevaDuracionEsperada, List<Actividad> actividadesPreviasSugeridas, String nuevaFechaLimite, boolean nuevaObligatoria, List<Actividad> prerequisitos){
			actividad.setDescripcion(nuevaDescripcion);
	        actividad.setObjetivo(nuevoObjetivo);
	        actividad.setNivelDificultad(nuevoNivelDificultad);
	        actividad.setDuracionEsperada(nuevaDuracionEsperada);
	        actividad.setFechaLimite(nuevaFechaLimite);
	        actividad.setObligatoria(nuevaObligatoria);
			return actividad;
	       	}
		
		
		public static Actividad clonarActividad(Actividad actividad) {
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
		
		public static Actividad editarActividad(Actividad actividad, ProfesorCreador usuario, String nuevaDescripcion, String nuevoObjetivo,
	            String nuevoNivelDificultad, String nuevaDuracionEsperada,
	            List<Actividad> actividadesPreviasSugeridas, String nuevaFechaLimite,
	            boolean nuevaObligatoria, List<Actividad> prerequisitos) {
			
			
			Actividad resultado;
			
			if (usuario == actividad.getCreador()) {resultado = actualizarActividad(actividad, nuevaDescripcion, nuevoObjetivo, nuevoNivelDificultad,
	                nuevaDuracionEsperada, actividadesPreviasSugeridas, nuevaFechaLimite,
	                nuevaObligatoria, prerequisitos); System.out.println("La actividad ha sido editada");}
			else {resultado = clonarActividad(actividad); System.out.println("La actividad tuvo que ser clonada para poder ser editada"); resultado = actualizarActividad(resultado, nuevaDescripcion, nuevoObjetivo, nuevoNivelDificultad,
	                nuevaDuracionEsperada, actividadesPreviasSugeridas, nuevaFechaLimite,
	                nuevaObligatoria, prerequisitos); System.out.println("La actividad clonada ha sido editada"); }
			
			return resultado;
		}
		
		public static Actividad nuevaActividad(String tipo, int id, String titulo, String descripcion, String objetivo, String duracionEsperada, boolean obligatoria, ProfesorCreador creador, String nivelDificultad, List<Actividad> actividadesPreviasSugeridas, String fechaLimite, List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas, Scanner scanner) {
		    Actividad actividad = null;
		    String resultado = "n.a";
		    
		    switch (tipo.toLowerCase()) {
		        case "recurso educativo":
		            String tipoRecurso = JOptionPane.showInputDialog(null, "Especifique el tipo de recurso para el recurso educativo:");
		            String recurso = JOptionPane.showInputDialog(null, "Escriba lo que quiere que tenga el recurso:");

		            actividad = new RecursoEducativo(id, tipoRecurso, recurso, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, resultado, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
		            break;
		        
		        case "tarea":
		            actividad = new Tarea(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, resultado, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
		            break;
		        
		        case "quiz":
		            String tipoQuiz = JOptionPane.showInputDialog(null, "Escriba el tipo de quiz (VerdaderoFalso o OpcionMultiple):");
		            String calificacionMinima = JOptionPane.showInputDialog(null, "Especifique la calificación mínima para el quiz:");
		            String numPreguntas = JOptionPane.showInputDialog(null, "Especifique cuántas preguntas quiere que tenga el quiz:");

		            Quiz actividadQuiz = new Quiz(id, Integer.parseInt(calificacionMinima), tipoQuiz, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, resultado, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);

		            // Añadir preguntas dependiendo del tipo de quiz
		            if (tipoQuiz.equalsIgnoreCase("OpcionMultiple")) {
		                for (int i = 0; i < Integer.parseInt(numPreguntas); i++) {
		                    actividadQuiz.agregarPregunta(scanner);
		                }
		            } else if (tipoQuiz.equalsIgnoreCase("VerdaderoFalso")) {
		                for (int i = 0; i < Integer.parseInt(numPreguntas); i++) {
		                    actividadQuiz.agregarPreguntaVerdaderoFalso(scanner);
		                }
		            }
		            actividad = actividadQuiz;
		            break;
		        
		        case "examen":
		            String numPreguntasExamen = JOptionPane.showInputDialog(null, "Especifique cuántas preguntas abiertas quiere que tenga el examen:");

		            Examen actividadExamen = new Examen(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, resultado, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
		            for (int i = 0; i < Integer.parseInt(numPreguntasExamen); i++) {
		                actividadExamen.agregarPregunta(scanner);
		            }
		            actividad = actividadExamen;
		            break;
		        
		        case "encuesta":
		            String numPreguntasEncuesta = JOptionPane.showInputDialog(null, "Especifique cuántas preguntas abiertas quiere que tenga la encuesta:");

		            Encuesta actividadEncuesta = new Encuesta(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, resultado, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
		            for (int i = 0; i < Integer.parseInt(numPreguntasEncuesta); i++) {
		                actividadEncuesta.agregarPregunta(scanner);
		            }
		            actividad = actividadEncuesta;
		            break;
		        
		        default:
		            JOptionPane.showMessageDialog(null, "Tipo de actividad no reconocido.");
		            break;
		    }

		    return actividad;
		}
		
		public void establecerPrerrequisitos(Actividad actividad, List<Actividad> prerrequisitos) {
	        actividad.setPrerequisitos(prerrequisitos);
	    }
			
		
		public List<LearningPath> getLearningPaths() {
			return learningPaths;
		}

		public void setLearningPaths(List<LearningPath> learningPaths) {
			this.learningPaths = learningPaths;
		}
		
		
			

}
