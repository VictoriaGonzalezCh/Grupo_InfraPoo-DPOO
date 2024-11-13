package uniandes.dpoo.learningpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uniandes.dpoo.usuario.Estudiante;
import uniandes.dpoo.usuario.ProfesorCreador;

public class Actividad {
	
	protected int id;
	protected String descripcion;
	protected String objetivo;
	protected String nivelDificultad;
	protected String duracionEsperada;
	protected List<Actividad> actividadesPreviasSugeridas;
	protected List<Actividad> actividadesSeguimientoRecomendadas;
	protected String fechaLimite;
	protected boolean obligatoria;
	protected ProfesorCreador creador;
	protected List<Actividad> prerequisitos;
	protected String tituloActividad;
	protected List<Feedback> feedbacks;
	protected String resultado;
	
	public Actividad() {
		this.feedbacks = new ArrayList<>();
		this.resultado = "N.A";
		// TODO Auto-generated constructor stub
	}
	
	public Actividad(int id, String tituloActividad, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada, List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, ProfesorCreador creador, List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
			this.id = id;
			this.tituloActividad = tituloActividad;
			this.descripcion = descripcion;
			this.objetivo = objetivo;
			this.nivelDificultad = nivelDificultad;
			this.duracionEsperada = duracionEsperada;
			this.actividadesPreviasSugeridas = actividadesPreviasSugeridas;
			this.fechaLimite = fechaLimite;
			this.obligatoria = obligatoria;
			this.creador = creador;
			this.prerequisitos = prerequisitos;
			this.feedbacks = new ArrayList<>();
			this.actividadesSeguimientoRecomendadas = actividadesSeguimientoRecomendadas;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void agregarFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        System.out.println("Feedback agregado: " + feedback.getComentario());
    }

    public void mostrarFeedbacks() {
        System.out.println("Feedbacks para la actividad: " + tituloActividad);
        for (Feedback fb : feedbacks) {
            System.out.println("Autor: " + fb.getAutor());
            System.out.println("Comentario: " + fb.getComentario());
            System.out.println("Comentario: " + fb.getRating());
            System.out.println("-----------------------------");
        }
    }
	
	public void marcarResultado(String resultado) {
        this.resultado = resultado;
    }
	
	public void cambiarResultado(String cambioResultado) {
		cambioResultado = resultado.toLowerCase();

	    if (cambioResultado.equals("exitosa") || cambioResultado.equals("no exitosa") || cambioResultado.equals("en progreso")) {
	        this.resultado = cambioResultado;
	        System.out.println("El estado del resultado ha sido actualizado a: " + resultado);}
	    }
	
	public boolean isCompletada() {
		
	    if (resultado.equals("exitosa")) {
	        return true;}
	    
	        return false;
	    }
	 
	
    public void agregarActividadPrevia(Actividad actividad) {
        this.actividadesPreviasSugeridas.add(actividad);
    }


    public void agregarPrerequisito(Actividad prerequisito) {
        this.prerequisitos.add(prerequisito);
    }
	
    public void agregarActividadesSeguimientoRecomendadas(Actividad actividad) {
        this.actividadesSeguimientoRecomendadas.add(actividad);
    }
    

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getNivelDificultad() {
		return nivelDificultad;
	}

	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}

	public String getDuracionEsperada() {
		return duracionEsperada;
	}

	public void setDuracionEsperada(String duracionEsperada) {
		this.duracionEsperada = duracionEsperada;
	}

	public List<Actividad> getActividadesPreviasSugeridas() {
		return actividadesPreviasSugeridas;
	}

	public void setActividadesPreviasSugeridas(List<Actividad> actividadesPreviasSugeridas) {
		this.actividadesPreviasSugeridas = actividadesPreviasSugeridas;
	}

	public String getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public boolean isObligatoria() {
		return obligatoria;
	}

	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}

	public ProfesorCreador getCreador() {
		return creador;
	}

	public void setCreador(ProfesorCreador creador) {
		this.creador = creador;
	}

	public List<Actividad> getPrerequisitos() {
		return prerequisitos;
	}

	public void setPrerequisitos(List<Actividad> prerequisitos) {
		this.prerequisitos = prerequisitos;
	}

	
	public String getTituloActividad() {
		return tituloActividad;
	}

	public void setTituloActividad(String tituloActividad) {
		this.tituloActividad = tituloActividad;
	}
	

	public List<Actividad> getActividadesSeguimientoRecomendadas() {
		return actividadesSeguimientoRecomendadas;
	}

	public void setActividadesSeguimientoRecomendadas(List<Actividad> actividadesSeguimientoRecomendadas) {
		this.actividadesSeguimientoRecomendadas = actividadesSeguimientoRecomendadas;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public void mostrarInfoActividad() {
        System.out.println("Descripción: " + descripcion);
        System.out.println("Objetivo: " + objetivo);
        System.out.println("Nivel de Dificultad: " + nivelDificultad);
        System.out.println("Duración Esperada: " + duracionEsperada);
        System.out.println("Fecha Límite: " + fechaLimite);
        System.out.println("Obligatoria: " + (obligatoria ? "Sí" : "No"));
        System.out.println("Creador: " + creador.getLogin());
        System.out.println("Resultado: " + resultado);
        
        // Mostrar actividades previas sugeridas
        System.out.println("Actividades Previas Sugeridas:");
        for (Actividad act : actividadesPreviasSugeridas) {
            System.out.println("- " + act.getTituloActividad());
        }

        // Mostrar prerrequisitos
        System.out.println("Prerrequisitos:");
        for (Actividad act : prerequisitos) {
            System.out.println("- " + act.getTituloActividad());
        }
    }
	 
		
		
    }
	
	

