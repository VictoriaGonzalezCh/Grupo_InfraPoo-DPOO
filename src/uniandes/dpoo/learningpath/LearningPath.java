package uniandes.dpoo.learningpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uniandes.dpoo.usuario.Estudiante;

public class LearningPath {
	
	private int id;
	private String titulo;
	private String descripcionContenido;
	private String descripcionObjetivo;
	private String nivelDificultad;
	private String duracionMinutos;
	private String rating;
	private List<Actividad> listaActividades;
	private HashMap<Estudiante, ProgresoEstudiante> progresoEstudiantes;
	
	public LearningPath() {
		this.listaActividades = new ArrayList<>();
	}

	public LearningPath(int id, String titulo, String descripcionContenido, String descripcionObjetivo, String nivelDificultad, String duracionMinutos, String rating) {
		this.setId(id);
		this.titulo = titulo;
		this.descripcionContenido = descripcionContenido;
		this.descripcionObjetivo = descripcionObjetivo;
		this.nivelDificultad = nivelDificultad;
		this.duracionMinutos = duracionMinutos;
		this.rating = rating;
		this.listaActividades = new ArrayList<>();
		this.progresoEstudiantes = new HashMap<>();
	}
	
	
	public ProgresoEstudiante obtenerProgresoDeEstudiante(Estudiante estudiante) {
        return progresoEstudiantes.get(estudiante);
    }

    public void registrarProgresoEstudiante(Estudiante estudiante, ProgresoEstudiante progreso) {
        progresoEstudiantes.put(estudiante, progreso);
    }
    
	public void registrarActividadCompletadaPorEstudiante(Estudiante estudiante, Actividad actividad) {
        // Obtener o crear el progreso del estudiante
        ProgresoEstudiante progreso = progresoEstudiantes.get(estudiante);
        if (progreso == null) {
            progreso = new ProgresoEstudiante();
            progresoEstudiantes.put(estudiante, progreso);
        }
        
        // Añadir las respuestas para la actividad completada
        progreso.añadirRespuestasEstudiante(actividad);
        
        // Actualiza otras métricas como la fecha de finalización
        progreso.setFechaFinalizacion("Ahora");
        //progreso.setTasaExitoFracaso(calcularTasaExito(actividad));  // Calcula la tasa de éxito
    }
    
	
	public void agregarActividad(Actividad actividad) {
		this.listaActividades.add(actividad);
	}
	
	public List<Actividad> obtenerListaActividades() {
		return listaActividades;
	}
	
	
	public List<Actividad> obtenerActividadesObligatorias() {
        List<Actividad> actividadesObligatorias = new ArrayList<>();
        for (Actividad actividad : listaActividades) {
            if (actividad.isObligatoria()) {
                actividadesObligatorias.add(actividad);
            }
        }
        return actividadesObligatorias;
    }
	
	public boolean fueCompletadoExitosamente() {
        List<Actividad> actividadesObligatorias = obtenerActividadesObligatorias();
        for (Actividad actividad : actividadesObligatorias) {
            if (!actividad.isCompletada()) { // Asegúrate de que esta verificación esté disponible
                return false; // Si alguna actividad obligatoria no está completada, retorna false
            }
        }
        return true; // Si todas las actividades obligatorias están completadas, retorna true
    }
	
	 public void mostrarInfoLearningPath() {
	        System.out.println("Título: " + titulo);
	        System.out.println("Descripción del Contenido: " + descripcionContenido);
	        System.out.println("Descripción del Objetivo: " + descripcionObjetivo);
	        System.out.println("Nivel de Dificultad: " + nivelDificultad);
	        System.out.println("Duración Total: " + duracionMinutos);
	        System.out.println("Rating: " + rating);
	        
	        // Mostrar actividades
	        System.out.println("Lista de Actividades:");
	        for (Actividad actividad : listaActividades) {
	            actividad.mostrarInfoActividad();  // Llama al método para mostrar información de cada actividad
	        }
	    }
	
	

	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescripcionContenido() {
		return descripcionContenido;
	}


	public void setDescripcionContenido(String descripcionContenido) {
		this.descripcionContenido = descripcionContenido;
	}


	public String getDescripcionObjetivo() {
		return descripcionObjetivo;
	}


	public void setDescripcionObjetivo(String descripcionObjetivo) {
		this.descripcionObjetivo = descripcionObjetivo;
	}


	public String getNivelDificultad() {
		return nivelDificultad;
	}


	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}


	public String getDuracionMinutos() {
		return duracionMinutos;
	}


	public void setDuracionMinutos(String duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
