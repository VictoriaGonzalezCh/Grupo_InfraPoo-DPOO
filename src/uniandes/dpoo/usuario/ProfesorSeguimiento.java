package uniandes.dpoo.usuario;

import java.util.ArrayList;

import java.io.Serializable;

import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.Encuesta;
import uniandes.dpoo.learningpath.Examen;
import uniandes.dpoo.learningpath.Feedback;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.Quiz;
import uniandes.dpoo.learningpath.RecursoEducativo;
import uniandes.dpoo.learningpath.Tarea;
import java.io.Serializable;

public class ProfesorSeguimiento extends Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	List<LearningPath> learningPaths;
	
	
	public static ProfesorSeguimiento nuevoProfesor(int id, String login, String contraseña) {
		return new ProfesorSeguimiento(id, login, contraseña);
		// TODO Auto-generated constructor stub
	}
	
	public ProfesorSeguimiento(int id, String login, String contraseña) {
		super(id, login, contraseña);
	}
	
	
	
	public void editarResultado(Actividad actividad, String cambioResultado) {
        actividad.cambiarResultado(cambioResultado);
        
    }
		
	
	public Feedback crearFeedbackProfesor(ProfesorSeguimiento autor, Actividad actividad, int rating, String comentario) {
        // El profesor puede crear un feedback
        Feedback feedback = new Feedback(autor, comentario, rating, actividad);
        
        actividad.agregarFeedback(feedback);
        //System.out.println("Feedback creado por el estudiante: " + getLogin());
        return feedback;
    }
	
	
	
}
