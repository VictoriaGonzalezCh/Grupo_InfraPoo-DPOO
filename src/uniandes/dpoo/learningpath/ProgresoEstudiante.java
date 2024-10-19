package uniandes.dpoo.learningpath;

import java.util.HashMap;
import java.util.List;

import uniandes.dpoo.usuario.Estudiante;

public class ProgresoEstudiante {

	private HashMap<Actividad, HashMap<PreguntaOpcionMultiple, String>> respuestasPorEstudianteQuiz;
	private HashMap<Actividad, HashMap<PreguntaAbierta, String>> respuestasPorEstudianteExamen;
	private HashMap<Actividad, HashMap<PreguntaAbierta, String>> respuestasPorEstudianteEncuesta;
	
	private Estudiante estudiante;
	private Actividad actividad;
	private String fechaInicio;
	private String fechaFinalizacion;
	private String tiempoDedicado;
	private int tasaExitoFracaso;
	
	
	public ProgresoEstudiante() {
		this.respuestasPorEstudianteQuiz = new HashMap<>();
		this.respuestasPorEstudianteExamen = new HashMap<>();
		this.respuestasPorEstudianteEncuesta = new HashMap<>();
	}
		// TODO Auto-generated constructor stub
	public ProgresoEstudiante(Estudiante estudiante, Actividad actividad, String fechaInicio, String fechaFinalizacion, String tiempoDedicado, int tasaExitoFracaso) {
		this.estudiante = estudiante;
        this.actividad = actividad;
		this.fechaInicio = fechaInicio;
		this.fechaFinalizacion = fechaFinalizacion;
		this.tiempoDedicado = tiempoDedicado;
		this.respuestasPorEstudianteQuiz = new HashMap<>();
		this.respuestasPorEstudianteExamen = new HashMap<>();
		this.respuestasPorEstudianteEncuesta = new HashMap<>();
	}	
	
	public void añadirRespuestasEstudiante(Estudiante estudiante, Actividad actividad) {
		
		if (actividad instanceof Quiz) {
	        Quiz quiz = (Quiz) actividad;  // Realizamos un cast
	        respuestasPorEstudianteQuiz.put(actividad, quiz.getRespuestasEstudiante());
	        
	    } else if (actividad instanceof Examen) {
	        Examen examen = (Examen) actividad;
	        respuestasPorEstudianteExamen.put(actividad, examen.getRespuestasEstudiante() );
	        
	    } else if (actividad instanceof Encuesta) {
	        Encuesta encuesta = (Encuesta) actividad;
	        respuestasPorEstudianteEncuesta.put(actividad, encuesta.getRespuestasEstudiante() );
	        
	    } else if (actividad instanceof Tarea) {
	        Tarea tarea = (Tarea) actividad;
	        
	    }}
	
	
	
	public void cambiarResultadoActividad(Actividad actividad, String nuevoResultado) {
        actividad.cambiarResultado(nuevoResultado);  // Suponiendo que Actividad tiene un método para cambiar el resultado
    }
	
	
	public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
	
	public HashMap<Actividad, HashMap<PreguntaOpcionMultiple, String>> getRespuestasPorEstudianteQuiz() {
		return respuestasPorEstudianteQuiz;
	}
	public void setRespuestasPorEstudianteQuiz(
			HashMap<Actividad, HashMap<PreguntaOpcionMultiple, String>> respuestasPorEstudianteQuiz) {
		this.respuestasPorEstudianteQuiz = respuestasPorEstudianteQuiz;
	}
	public HashMap<Actividad, HashMap<PreguntaAbierta, String>> getRespuestasPorEstudianteExamen() {
		return respuestasPorEstudianteExamen;
	}
	public void setRespuestasPorEstudianteExamen(
			HashMap<Actividad, HashMap<PreguntaAbierta, String>> respuestasPorEstudianteExamen) {
		this.respuestasPorEstudianteExamen = respuestasPorEstudianteExamen;
	}
	public HashMap<Actividad, HashMap<PreguntaAbierta, String>> getRespuestasPorEstudianteEncuesta() {
		return respuestasPorEstudianteEncuesta;
	}
	public void setRespuestasPorEstudianteEncuesta(
			HashMap<Actividad, HashMap<PreguntaAbierta, String>> respuestasPorEstudianteEncuesta) {
		this.respuestasPorEstudianteEncuesta = respuestasPorEstudianteEncuesta;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(String fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	public String getTiempoDedicado() {
		return tiempoDedicado;
	}
	public void setTiempoDedicado(String tiempoDedicado) {
		this.tiempoDedicado = tiempoDedicado;
	}
	public int getTasaExitoFracaso() {
		return tasaExitoFracaso;
	}
	public void setTasaExitoFracaso(int tasaExitoFracaso) {
		this.tasaExitoFracaso = tasaExitoFracaso;
	}
	
	
	
		
	
}
