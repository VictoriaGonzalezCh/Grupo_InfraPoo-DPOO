package uniandes.dpoo.usuario;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.Feedback;
import uniandes.dpoo.learningpath.LearningPath;

public class Estudiante extends Usuario {
	
	private static List<Actividad> actividadesCompletadas; 
    private List<LearningPath> learningPathsCompletados;
    private List<LearningPath> learningPathsEnCurso;
    private static List<Actividad> actividadesEnCurso;
	
	public static Estudiante nuevoEstudiante(int id, String login, String contraseña) {
		return new Estudiante(id, login, contraseña);
		// TODO Auto-generated constructor stub
	}
	
	public Estudiante(int id, String login, String contraseña) {
		this.login = login;
		this.contraseña = contraseña;
		Estudiante.actividadesCompletadas = new ArrayList<>();
		this.learningPathsCompletados = new ArrayList<>();
		Estudiante.actividadesEnCurso = new ArrayList<>();
	}
	
	public static void registrarseLearningPath(Estudiante estudiante, LearningPath learningPath) {
		estudiante.learningPathsEnCurso.add(learningPath);
	}
	
	public void learningPathCompletada(LearningPath learningPath) {
		this.learningPathsCompletados.add(learningPath);
	}
	
	public void registrarLearningPathCompletada(LearningPath learningPath, Actividad actividad) {
		if (learningPathsEnCurso.contains(learningPath)) 
			{if (actividad.getResultado() == "exitoso" ) 
				{actividadesCompletadas.add(actividad); actividadesEnCurso.remove(actividad);}}
		else {System.out.println("La actividad no esta en curso");}
	}
	
	private static boolean verificarPrerequisitos(Actividad actividad) {
		List<Actividad> prerequisitos = actividad.getPrerequisitos();
		if (prerequisitos.contains(actividad) && actividadesCompletadas.contains(actividad)) 
			{return true;}
		return false;
	}
	
	public static void iniciarActividad(Actividad actividad) {
		if (!verificarPrerequisitos(actividad)) 
			{System.out.println("Advertencia: No se pueden iniciar actividades sin completar los prerequisitos");
			}
		
		actividadesEnCurso.add(actividad);
		
	}
	
	public void registrarActividadCompletada(Actividad actividad) {
		if (actividadesEnCurso.contains(actividad)) 
			{if (actividad.getResultado() == "exitoso" ) 
				{actividadesCompletadas.add(actividad); actividadesEnCurso.remove(actividad);}}
		else {System.out.println("La actividad no esta en curso");}
	}
	
	
	public static double establecerProgresoEstudiante(LearningPath learningPath) {
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
		Estudiante.actividadesCompletadas = actividadesCompletadas;
	}

	public List<LearningPath> getLearningPathsCompletados() {
		return learningPathsCompletados;
	}

	public void setLearningPathsCompletados(List<LearningPath> learningPathsCompletados) {
		this.learningPathsCompletados = learningPathsCompletados;
	}

	public List<Actividad> getActividadesEnCurso() {
		return actividadesEnCurso;
	}

	public void setActividadesEnCurso(List<Actividad> actividadesEnCurso) {
		Estudiante.actividadesEnCurso = actividadesEnCurso;
	}

	public static Feedback crearFeedbackEstudiante(Estudiante autor, Actividad actividad, int rating, String comentario) {
        // El profesor puede crear un feedback
        Feedback feedback = new Feedback(autor, comentario, rating, actividad);
        //System.out.println("Feedback creado por el estudiante: " + getLogin());
        actividad.agregarFeedback(feedback);
        return feedback;
    }

}
