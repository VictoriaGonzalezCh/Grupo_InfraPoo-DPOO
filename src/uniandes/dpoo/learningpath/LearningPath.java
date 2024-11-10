package uniandes.dpoo.learningpath;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import uniandes.dpoo.usuario.Estudiante;

import java.io.Serializable;

public class LearningPath implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String titulo;
	private String descripcionContenido;
	private String descripcionObjetivo;
	private String nivelDificultad;
	private int duracionMinutos;
	private String rating;
	private List<Actividad> listaActividades;
	private HashMap<Estudiante, ProgresoEstudiante> progresoEstudiante;
	private String fechaCreacion;
	private String fechaModificacion;
	private int version;

	
	


	public LearningPath() {
		this.listaActividades = new ArrayList<>();
		this.progresoEstudiante = new HashMap<>();
		this.duracionMinutos = 0;
		this.fechaCreacion = "";
		this.fechaModificacion = "";
		this.version = 1;
		
		
	}


	public LearningPath(int id, String titulo, String descripcionContenido, String descripcionObjetivo, String nivelDificultad, String rating) {
		this.setId(id);
		this.titulo = titulo;
		this.descripcionContenido = descripcionContenido;
		this.descripcionObjetivo = descripcionObjetivo;
		this.nivelDificultad = nivelDificultad;
		this.rating = rating;
		this.listaActividades = new ArrayList<>();
		this.progresoEstudiante = new HashMap<>();
		
	}
	
	
	public ProgresoEstudiante obtenerProgresoDeEstudiante(Estudiante estudiante) {
        return progresoEstudiante.get(estudiante);
    }

	

       public void registrarActividadCompletadaPorEstudiante(Estudiante estudiante, Actividad actividad) {
    	   ProgresoEstudiante progreso = progresoEstudiante.get(estudiante);
    	   if (progreso == null) {
    	        // Si no existe, se crea un nuevo progreso y se asocia con el estudiante
    	        progreso = new ProgresoEstudiante();
    	        progresoEstudiante.put(estudiante, progreso);
    	    }
    	    
    	    // Añadir las respuestas del estudiante para la actividad completada
    	    progreso.añadirRespuestasEstudiante(estudiante, actividad);
    	    
    	    // Actualizar la fecha de finalización del progreso
    	    progreso.setFechaFinalizacion(java.time.LocalDateTime.now().toString());
    	    progreso.setTiempoDedicado(progreso.getFechaInicio(), progreso.getFechaFinalizacion());
    	}
    	   
    	
	

	
	
	public void asociarProgresoConEstudiante(Estudiante estudiante) {
		if (!progresoEstudiante.containsKey(estudiante)) {
	        // Si no tiene progreso, creamos uno nuevo y lo asociamos con el estudiante
	        ProgresoEstudiante nuevoProgreso = new ProgresoEstudiante();
	        progresoEstudiante.put(estudiante, nuevoProgreso);
	    } else {
	        // O puedes manejar el caso en que ya exista progreso, si es necesario
	        System.out.println("El estudiante ya tiene un progreso asociado.");
	    }
	}
	
	public HashMap<PreguntaAbierta, String> obtenerRespuestaEncuestaDeEstudiante(Estudiante estudiante, Actividad actividad) {
		return progresoEstudiante.get(estudiante).getRespuestasPorEstudianteEncuesta().get(actividad);
	}
	
	public HashMap<PreguntaAbierta, String> obtenerRespuestaExamenDeEstudiante(Estudiante estudiante, Actividad actividad) {
		return progresoEstudiante.get(estudiante).getRespuestasPorEstudianteExamen().get(actividad);
	}
	
	public HashMap<PreguntaOpcionMultiple, String> obtenerRespuestaQuizDeEstudiante(Estudiante estudiante, Actividad actividad) {
		return progresoEstudiante.get(estudiante).getRespuestasPorEstudianteQuiz().get(actividad);
	}
	
	public HashMap<PreguntaVerdaderoFalso, String> obtenerRespuestaQuizDeEstudianteVF(Estudiante estudiante, Actividad actividad) {
		return progresoEstudiante.get(estudiante).getRespuestasPorEstudianteQuizVF().get(actividad);
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


	public int getDuracionMinutos() {
		return duracionMinutos;
	}


	public void setDuracionMinutos() {
		int duracionTotal = 0;
	    for (Actividad actividad : listaActividades) {  
	        duracionTotal += Integer.parseInt(actividad.getDuracionEsperada());  
	    }
	    this.duracionMinutos = duracionTotal;
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

	public List<Actividad> getActividades() {
		return listaActividades;
	}
	
	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion() {
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
		String fechaComoString = fechaActual.format(formato);
		this.fechaCreacion = fechaComoString;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion() {
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
		String fechaComoString = fechaActual.format(formato);
		this.fechaModificacion = fechaComoString;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion() {
		this.version++;
	}


	public List<Estudiante> getEstudiantesInscritos() {
	    // Devuelve la lista de estudiantes inscritos basándose en las claves del HashMap
	    Set<Estudiante> estudiantesSet = progresoEstudiante.keySet(); // Obtiene el Set de estudiantes
	    return new ArrayList<>(estudiantesSet);  // Crea una lista a partir del Set
	}
	
	public HashMap<Estudiante, ProgresoEstudiante> getProgresoEstudiante() {
		return progresoEstudiante;
	}


	public void setProgresoEstudiante(HashMap<Estudiante, ProgresoEstudiante> progresoEstudiante) {
		this.progresoEstudiante = progresoEstudiante;
	}
	
}
