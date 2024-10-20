package uniandes.dpoo.learningpath;

import java.util.List;

import uniandes.dpoo.usuario.Profesor;

public class Tarea extends Actividad {

	String estado;
	String medioEntrega;
	
	public Tarea(int id, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, Profesor creador,
            List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
  super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
		this.estado = "no enviada";
		this.medioEntrega = "definido por el estudiante";
	}
	
	public void establecermedioEntrega(String medioEntrega) {
		this.medioEntrega = medioEntrega;
	}
	
	public void enviarTarea() {
		this.estado = "enviada";
	}
	
	public void calificarTarea(String esExitosa) {
		this.estado = esExitosa;
	}
	
	public String obtenerEstado() {
        return estado;
    }

	
    public String getMedioEntrega() {
        return medioEntrega;
    }

    public void setMedioEntrega(String medioEntrega) {
        this.medioEntrega = medioEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

	

}
