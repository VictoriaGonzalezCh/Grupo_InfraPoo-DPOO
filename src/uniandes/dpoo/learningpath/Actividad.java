package uniandes.dpoo.learningpath;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

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
		//this.resultado = "N.A";
		// TODO Auto-generated constructor stub
	}
	
	public Actividad(int id, String tituloActividad, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada, String resultado, List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, ProfesorCreador creador, List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
			this.id = id;
			this.tituloActividad = tituloActividad;
			this.descripcion = descripcion;
			this.objetivo = objetivo;
			this.nivelDificultad = nivelDificultad;
			this.duracionEsperada = duracionEsperada;
			this.resultado = resultado;
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
	    // Crear un panel principal con un layout vertical
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	    // Agregar el título de la actividad
	    panel.add(new JLabel("Feedbacks para la actividad: " + tituloActividad));
	    panel.add(new JSeparator(SwingConstants.HORIZONTAL));

	    // Mostrar cada feedback
	    for (Feedback fb : feedbacks) {
	        JPanel feedbackPanel = new JPanel();
	        feedbackPanel.setLayout(new GridLayout(0, 1));
	        feedbackPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        feedbackPanel.setBackground(new Color(245, 245, 245));

	        feedbackPanel.add(new JLabel("Autor: " + fb.getAutor()));
	        feedbackPanel.add(new JLabel("Comentario: " + fb.getComentario()));
	        feedbackPanel.add(new JLabel("Rating: " + fb.getRating()));
	        feedbackPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

	        panel.add(feedbackPanel);
	    }

	    // Mostrar el panel en un cuadro de diálogo
	    JScrollPane scrollPane = new JScrollPane(panel);
	    scrollPane.setPreferredSize(new Dimension(400, 300));
	    JOptionPane.showMessageDialog(null, scrollPane, "Feedbacks", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void marcarResultado(String resultado) {
        this.resultado = resultado;
    }
	
	public void cambiarResultado(String cambioResultado) {
		String resultadoConvertido = cambioResultado.toLowerCase();

		if (resultadoConvertido.equals("exitosa") || resultadoConvertido.equals("no exitosa") || resultadoConvertido.equals("en progreso")) {
	        this.resultado = resultadoConvertido;
	        System.out.println("El estado del resultado ha sido actualizado a: " + this.resultado);
	    } else {
	        System.out.println("El estado proporcionado no es válido: " + cambioResultado);
	    }
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
	    System.out.println("El método mostrarInfoActividad() se está ejecutando.");

	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	    // Manejo de valores nulos
	    panel.add(new JLabel("Descripción: " + (descripcion != null ? descripcion : "No disponible")));
	    panel.add(new JLabel("Objetivo: " + (objetivo != null ? objetivo : "No disponible")));
	    panel.add(new JLabel("Nivel de Dificultad: " + (nivelDificultad != null ? nivelDificultad : "No especificado")));
	    panel.add(new JLabel("Duración Esperada: " + (duracionEsperada != null ? duracionEsperada : "No especificada")));
	    panel.add(new JLabel("Fecha Límite: " + (fechaLimite != null ? fechaLimite : "No especificada")));
	    panel.add(new JLabel("Obligatoria: " + (obligatoria ? "Sí" : "No")));
	    panel.add(new JLabel("Creador: " + (creador != null ? creador.getLogin() : "No disponible")));
	    panel.add(new JLabel("Resultado: " + (resultado != null ? resultado : "No disponible")));
	    panel.add(new JSeparator(SwingConstants.HORIZONTAL));

	    // Actividades previas sugeridas
	    panel.add(new JLabel("Actividades Previas Sugeridas:"));
	    if (actividadesPreviasSugeridas.isEmpty()) {
	        panel.add(new JLabel("No hay actividades previas sugeridas."));
	    } else {
	        for (Actividad act : actividadesPreviasSugeridas) {
	            panel.add(new JLabel("- " + act.getTituloActividad()));
	        }
	    }
	    panel.add(new JSeparator(SwingConstants.HORIZONTAL));

	    // Prerrequisitos
	    panel.add(new JLabel("Prerrequisitos:"));
	    if (prerequisitos.isEmpty()) {
	        panel.add(new JLabel("No hay prerrequisitos."));
	    } else {
	        for (Actividad act : prerequisitos) {
	            panel.add(new JLabel("- " + act.getTituloActividad()));
	        }
	    }
	    
	    panel.add(new JLabel("Actividades de Seguimiento:"));
	    if (actividadesSeguimientoRecomendadas == null || actividadesSeguimientoRecomendadas.isEmpty()) {
	        panel.add(new JLabel("No hay actividades de seguimiento."));
	    } else {
	        for (Actividad act : actividadesSeguimientoRecomendadas) {
	            panel.add(new JLabel("- " + act.getTituloActividad()));
	        }
	    }

	    // Mostrar el panel en un cuadro de diálogo
	    JScrollPane scrollPane = new JScrollPane(panel);
	    scrollPane.setPreferredSize(new Dimension(500, 400));
	    JOptionPane.showMessageDialog(null, scrollPane, "Información de la Actividad", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
		
    }
	
	

