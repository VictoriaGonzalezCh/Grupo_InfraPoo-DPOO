package uniandes.dpoo.usuario;

import java.util.ArrayList;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.Encuesta;
import uniandes.dpoo.learningpath.Examen;
import uniandes.dpoo.learningpath.Feedback;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.PreguntaAbierta;
import uniandes.dpoo.learningpath.PreguntaOpcionMultiple;
import uniandes.dpoo.learningpath.Quiz;
import uniandes.dpoo.learningpath.RecursoEducativo;
import uniandes.dpoo.learningpath.Tarea;


public class Estudiante extends Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Actividad> actividadesCompletadas; 
    private List<LearningPath> learningPathsCompletados;
    private List<LearningPath> learningPathsEnCurso;
    private List<Actividad> actividadesEnCurso;
	
	public static Estudiante nuevoEstudiante(int id, String login, String contraseña) {
		return new Estudiante(id, login, contraseña);
		// TODO Auto-generated constructor stub
	}
	
	public Estudiante(int id, String login, String contraseña) {
		super(id, login, contraseña);
		this.learningPathsEnCurso = new ArrayList<>();
		this.learningPathsCompletados = new ArrayList<>();
		this.actividadesEnCurso = new ArrayList<>();
		this.actividadesCompletadas = new ArrayList<>();
	}
	
	@Override
    public String toString() {
        return super.toString() + " (Estudiante)";
    }
	
	public void registrarseLearningPath(LearningPath learningPath) {
		this.learningPathsEnCurso.add(learningPath);
	}
	
	public void learningPathCompletada(LearningPath learningPath) {
		this.learningPathsCompletados.add(learningPath);
	}
	
	
	public void registrarLearningPathCompletada(LearningPath learningPath) {
	    // Verificar si el LearningPath está en curso
	    if (learningPathsEnCurso.contains(learningPath)) {
	        // Verificar si el LearningPath fue completado
	        if (verificarLearningPathCompletado(learningPath)) {
	            // Registrar el LearningPath como completado
	            learningPathCompletada(learningPath);
	            // Remover de la lista de LearningPaths en curso
	            learningPathsEnCurso.remove(learningPath);
	            System.out.println("¡Has terminado el Learning Path!");
	        }
	    }
	}
	
	private boolean verificarPrerequisitos(Actividad actividad) {
		List<Actividad> prerequisitos = actividad.getPrerequisitos();
		if (prerequisitos.contains(actividad) && actividadesCompletadas.contains(actividad)) 
			{return true;}
		return false;
	}
	
	public void iniciarActividad(Actividad actividad) {
		if (!verificarPrerequisitos(actividad)) 
			{System.out.println("Advertencia: No se pueden iniciar actividades sin completar los prerequisitos");
			}
		
		actividadesEnCurso.add(actividad);
		
	}
	
	public boolean verificarLearningPathCompletado(LearningPath learningPath) {
	    List<Actividad> actividadesObligatorias = learningPath.obtenerActividadesObligatorias();
	    for (Actividad actividad : actividadesObligatorias) {
	        if (!actividadesCompletadas.contains(actividad)) {
	            return false; // Aún no se han completado todas las actividades
	        }
	    }
	    return true; // Todas las actividades obligatorias están completadas
	}
	
	
	public void registrarActividadCompletada(Actividad actividad) {
	    if (actividadesEnCurso.contains(actividad)) {
	        if (actividad.getResultado().equals("exitoso")) {
	        	
	            actividadesCompletadas.add(actividad);
	            actividadesEnCurso.remove(actividad);

	            // Verificar si se completó el LearningPath
	            for (LearningPath lp : learningPathsEnCurso) {
	                if (lp.obtenerActividadesObligatorias().contains(actividad)) {
	                	lp.registrarActividadCompletadaPorEstudiante(this, actividad);
	                    if (verificarLearningPathCompletado(lp)) {
	                        learningPathsCompletados.add(lp);
	                        learningPathsEnCurso.remove(lp);
	                        System.out.println("LearningPath completado: " + lp.getTitulo());
	                    }
	                }
	            }
	        }
	    } else {
	        System.out.println("La actividad no está en curso.");
	    }
	}
	
	
	public double establecerProgresoEstudiante(LearningPath learningPath) {
		List<Actividad> actividadesObligatorias = learningPath.obtenerActividadesObligatorias();
		List<Actividad> actividadesObligatoriasCompletadas = new ArrayList<>();
		
		for (Actividad actividad: actividadesObligatorias) {
				if (actividadesCompletadas.contains(actividad)) 
					{actividadesObligatoriasCompletadas.add(actividad);}}
		
		int numObligatorias = actividadesObligatorias.size();
		int numObligatoriasCompletadas = actividadesObligatoriasCompletadas.size();
		
		double porcentajeProgreso = ((double)numObligatoriasCompletadas/numObligatorias) * 100;
			
		return porcentajeProgreso;
	}

	public List<Actividad> getActividadesCompletadas() {
		return actividadesCompletadas;
	}

	public void setActividadesCompletadas(List<Actividad> actividadesCompletadas) {
		this.actividadesCompletadas = actividadesCompletadas;
	}

	public List<LearningPath> getLearningPathsCompletados() {
		return learningPathsCompletados;
	}

	public void setLearningPathsCompletados(List<LearningPath> learningPathsCompletados) {
		this.learningPathsCompletados = learningPathsCompletados;
	}

	public List<LearningPath> getLearningPathsEnCurso() {
		return learningPathsEnCurso;
	}

	public void setLearningPathsEnCurso(List<LearningPath> learningPathsEnCurso) {
		this.learningPathsEnCurso = learningPathsEnCurso;
	}

	public List<Actividad> getActividadesEnCurso() {
		return actividadesEnCurso;
	}

	public void setActividadesEnCurso(List<Actividad> actividadesEnCurso) {
		this.actividadesEnCurso = actividadesEnCurso;
	}

	public Feedback crearFeedbackEstudiante(Estudiante autor, Actividad actividad, int rating, String comentario) {
        // El estudiante puede crear un feedback
        Feedback feedback = new Feedback(autor, comentario, rating, actividad);
        //System.out.println("Feedback creado por el estudiante: " + getLogin());
        actividad.agregarFeedback(feedback);
        return feedback;
    }
	
	public void realizarActividad(Actividad actividad, LearningPath lp, Scanner scanner) {
		lp.obtenerProgresoDeEstudiante(this).setFechaInicio(java.time.LocalDateTime.now().toString());
		
		if (actividad instanceof Quiz) {
	        Quiz quiz = (Quiz) actividad;  // Realizamos un cast
	        quiz.responderPreguntas(scanner);  // Invocamos el método
	        registrarActividadCompletada(actividad);
	        
	    } else if (actividad instanceof Examen) {
	        Examen examen = (Examen) actividad;
	        examen.responderPreguntas(scanner);
	        registrarActividadCompletada(actividad);
	        
	    } else if (actividad instanceof Encuesta) {
	        Encuesta encuesta = (Encuesta) actividad;
	        encuesta.responderPreguntas(scanner);
	        encuesta.setEstado("enviado");
	        registrarActividadCompletada(actividad);
	        
	    } else if (actividad instanceof Tarea) {
	        Tarea tarea = (Tarea) actividad;
	        tarea.enviarTarea();
	        System.out.println("Diga por que medio envio la tarea:");
	        String medioEntrega = scanner.nextLine();
	        tarea.establecermedioEntrega(medioEntrega); 
	        registrarActividadCompletada(actividad);
	    }
		 else if (actividad instanceof RecursoEducativo) {
			RecursoEducativo recursoEducativo = (RecursoEducativo) actividad;
			recursoEducativo.mostrarRecurso();
			recursoEducativo.recursoTerminado();
			registrarActividadCompletada(actividad);
		}
	    
	    else {
	        System.out.println("Tipo de actividad no soportada.");
	    }
	    
	    registrarActividadCompletada(actividad);
	    lp.registrarActividadCompletadaPorEstudiante(this, actividad);
	    
	}
	
	
	public void mostrarRespuestasEstudiantes(Actividad actividad, LearningPath lp) {
		
		if (actividad instanceof Quiz) {
			HashMap<PreguntaOpcionMultiple, String> respuestasQuiz = lp.obtenerRespuestaQuizDeEstudiante(this, actividad);
			
			if (respuestasQuiz != null) {
                System.out.println("Respuestas del Quiz:");
                for (PreguntaOpcionMultiple pregunta : respuestasQuiz.keySet()) {
                    System.out.println("Pregunta: " + pregunta.getEnunciado());
                    System.out.println("Respuesta: " + respuestasQuiz.get(pregunta));
                }
            } else {
                System.out.println("El estudiante no ha respondido este Quiz.");
            }
		}
			
		else if (actividad instanceof Encuesta) {
			HashMap<PreguntaAbierta, String> respuestasEncuesta = lp.obtenerRespuestaEncuestaDeEstudiante(this, actividad);
            if (respuestasEncuesta != null) {
                System.out.println("Respuestas de la Encuesta:");
                for (PreguntaAbierta pregunta : respuestasEncuesta.keySet()) {
                    System.out.println("Pregunta: " + pregunta.getEnunciado());
                    System.out.println("Respuesta: " + respuestasEncuesta.get(pregunta));
                }
            } else {
                System.out.println("El estudiante no ha respondido esta Encuesta.");
            }
		}
		
		else if (actividad instanceof Examen) {
			HashMap<PreguntaAbierta, String> respuestasExamen = lp.obtenerRespuestaExamenDeEstudiante(this, actividad);
			if (respuestasExamen != null) {
                System.out.println("Respuestas del Examen:");
                for (PreguntaAbierta pregunta : respuestasExamen.keySet()) {
                    System.out.println("Pregunta: " + pregunta.getEnunciado());
                    System.out.println("Respuesta: " + respuestasExamen.get(pregunta));
                }
            } else {
                System.out.println("El estudiante no ha respondido este Examen.");
            }
		}
		
	}
	
	public void mostrarResultadoEstudiantes(Actividad actividad, LearningPath lp) {
		
		if (actividad instanceof Quiz) {
			System.out.println("El resultado de esta actividad es: " + actividad.getResultado());
		}
			
		else if (actividad instanceof Encuesta) {
			System.out.println("El resultado de esta actividad es: " + actividad.getResultado());
		}
		
		else if (actividad instanceof Examen) {
			System.out.println("El resultado de esta actividad es: " + actividad.getResultado());
		}
		
		else if (actividad instanceof RecursoEducativo) {
			System.out.println("El resultado de esta actividad es: " + actividad.getResultado());
		}
		
		else if (actividad instanceof Tarea) {
			System.out.println("El resultado de esta actividad es: " + actividad.getResultado());
		}
	}

	
	
	
}
