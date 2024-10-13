package uniandes.dpoo.learningpath;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.usuario.Profesor;

public class Examen extends Actividad {

	private List<PreguntaAbierta> preguntasAbiertas;
	private String estado;
	
	
	public Examen(int id, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, Profesor creador,
            List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
  super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
  this.preguntasAbiertas = new ArrayList<>();
  this.estado = "enviada";
}

	public Examen(int id, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, Profesor creador,
            List<Actividad> prerequisitos, String estado, List<Actividad> actividadesSeguimientoRecomendadas) {
  super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
  this.preguntasAbiertas = new ArrayList<>();
  this.estado = estado;
}
	
	public void agregarPregunta(Scanner scanner) {
        System.out.println("Creando una nueva pregunta...");

        // Solicitar enunciado de la pregunta
        System.out.println("Escriba el enunciado de la pregunta: ");
        String enunciado = scanner.nextLine();

        // Solicitar explicación
        System.out.println("Escriba la respuesta esperada de la respuesta correcta: ");
        String explicacion = scanner.nextLine();

        // Crear la pregunta y agregarla a la lista
        PreguntaAbierta pregunta = new PreguntaAbierta(enunciado, explicacion);
        preguntasAbiertas.add(pregunta);

        System.out.println("Pregunta agregada exitosamente.");
    }
	
	
	public void nuevoExamen(List<PreguntaAbierta> preguntasAbiertas, String estado) {
		this.preguntasAbiertas = preguntasAbiertas;
		this.setEstado(estado);
		
	}
	
	public void calificarExamen() {
		this.setEstado("calificada");
		System.out.println("El examen ha sido calificado.");
	}
	

	public void mostrarPreguntas() {
        for (PreguntaAbierta pregunta : preguntasAbiertas) {
            pregunta.mostrarPregunta();
            System.out.println();  
        }
    }
	
	public List<PreguntaAbierta> getPreguntasAbiertas() {
        return preguntasAbiertas;
    }
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
