package uniandes.dpoo.learningpath;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import uniandes.dpoo.usuario.Estudiante;

public class ProgresoEstudiante {

	private HashMap<Actividad, HashMap<PreguntaOpcionMultiple, String>> respuestasPorEstudianteQuiz;
	private HashMap<Actividad, HashMap<PreguntaAbierta, String>> respuestasPorEstudianteExamen;
	private HashMap<Actividad, HashMap<PreguntaAbierta, String>> respuestasPorEstudianteEncuesta;
	private HashMap<Actividad, HashMap<PreguntaVerdaderoFalso, String>> respuestasPorEstudianteQuizVF;
	
	private Estudiante estudiante;
	private Actividad actividad;
	private String fechaInicio;
	private String fechaFinalizacion;
	private int tiempoDedicado;
	private int tasaExitoFracaso;
	
	
	public ProgresoEstudiante() {
		this.respuestasPorEstudianteQuiz = new HashMap<>();
		this.respuestasPorEstudianteExamen = new HashMap<>();
		this.respuestasPorEstudianteEncuesta = new HashMap<>();
		this.respuestasPorEstudianteQuizVF = new HashMap<>();
	}
		// TODO Auto-generated constructor stub
	public ProgresoEstudiante(Estudiante estudiante, Actividad actividad, String fechaInicio, String fechaFinalizacion, int tiempoDedicado, int tasaExitoFracaso) {
		this.estudiante = estudiante;
        this.actividad = actividad;
		this.fechaInicio = fechaInicio;
		this.fechaFinalizacion = fechaFinalizacion;
		this.tiempoDedicado = tiempoDedicado;
		this.respuestasPorEstudianteQuiz = new HashMap<>();
		this.respuestasPorEstudianteExamen = new HashMap<>();
		this.respuestasPorEstudianteEncuesta = new HashMap<>();
		this.respuestasPorEstudianteQuizVF = new HashMap<>();
	}	
	
	public void añadirRespuestasEstudiante(Estudiante estudiante, Actividad actividad) {
		
		if (actividad instanceof Quiz) {
			Quiz quiz = (Quiz) actividad;  // Realizamos un cast
	        
			// Manejar respuestas de preguntas de opción múltiple
            if (quiz.tienePreguntasOpcionMultiple()) {
                respuestasPorEstudianteQuiz.put(actividad, quiz.getRespuestasEstudiante());
            }

            // Manejar respuestas de preguntas de verdadero/falso
            if (quiz.tienePreguntasVerdaderoFalso()) {
                respuestasPorEstudianteQuizVF.put(actividad, quiz.getRespuestasEstudianteVF());
            }
			
	        
	    } else if (actividad instanceof Examen) {
	        Examen examen = (Examen) actividad;
	        respuestasPorEstudianteExamen.put(actividad, examen.getRespuestasEstudiante() );
	        
	    } else if (actividad instanceof Encuesta) {
	        Encuesta encuesta = (Encuesta) actividad;
	        respuestasPorEstudianteEncuesta.put(actividad, encuesta.getRespuestasEstudiante() );    
	    
	        
	    }
		estudiante.getActividadesCompletadas().add(actividad);
	}
	
	
	public void cambiarResultadoActividad(Actividad actividad, String nuevoResultado) {
		if (actividad instanceof Tarea) {
            Tarea tarea = (Tarea) actividad;
            tarea.calificarTarea(nuevoResultado);
            
        } else if (actividad instanceof Examen) {
            Examen examen = (Examen) actividad;
            examen.mostrarRespuestasEstudiante();
            examen.calificarExamen();
        }
		
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
	
	public HashMap<Actividad, HashMap<PreguntaVerdaderoFalso, String>> getRespuestasPorEstudianteQuizVF() {
		return respuestasPorEstudianteQuizVF;
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
	public int getTiempoDedicado() {
		return tiempoDedicado;
	}
	public void setTiempoDedicado(String tiempoInicial, String tiempoFinal) {
		this.tiempoDedicado = calculartiempoDedicado(tiempoInicial, tiempoFinal);
	}
	public int getTasaExitoFracaso() {
		return tasaExitoFracaso;
	}
	public void setTasaExitoFracaso(int tasaExitoFracaso) {
		this.tasaExitoFracaso = tasaExitoFracaso;
	}
	
	
	public void setRespuestasPorEstudianteQuizVF(
			HashMap<Actividad, HashMap<PreguntaVerdaderoFalso, String>> respuestasPorEstudianteQuizVF) {
		this.respuestasPorEstudianteQuizVF = respuestasPorEstudianteQuizVF;
	}
	public int calculartiempoDedicado(String tiempoInicial, String tiempoFinal) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		LocalDateTime fechaInicio = LocalDateTime.parse(tiempoInicial, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(tiempoFinal, formatter);
        
        // Calcular la duración entre las dos fechas
        Duration duracion = Duration.between(fechaInicio, fechaFin);
        
        // Retornar el tiempo dedicado en minutos
        return (int) duracion.toMinutes(); 
	}
	
	
		
}
