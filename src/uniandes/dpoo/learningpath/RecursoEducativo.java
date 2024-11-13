package uniandes.dpoo.learningpath;

import java.util.List;


import uniandes.dpoo.usuario.ProfesorCreador;

public class RecursoEducativo extends Actividad {

	String tipoRecurso;
	String recurso;
	String estado;
	
	public RecursoEducativo(int id, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, ProfesorCreador creador,
            List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
  super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas);
		this.estado = "no terminada";
	}
	
	public RecursoEducativo(int id, String tipoRecurso, String recurso, String titulo, String descripcion, String objetivo, String nivelDificultad, String duracionEsperada,
            List<Actividad> actividadesPreviasSugeridas, String fechaLimite, boolean obligatoria, ProfesorCreador creador,
            List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas) {
  super(id, titulo, descripcion, objetivo, nivelDificultad, duracionEsperada, actividadesPreviasSugeridas, fechaLimite, obligatoria, creador, prerequisitos, actividadesSeguimientoRecomendadas); 
		this.tipoRecurso = tipoRecurso;
		this.recurso = recurso;
		this.estado = "no terminada";
	}
	
	public void mostrarRecurso() {
		System.out.println("Recurso: " + this.recurso);
	}
	
	public void recursoTerminado() {
		this.estado = "exitosa";
	}
	
	public boolean verficarRecursoTerminado() {
		boolean resultado = false;
		if (estado.equals("exitosa") ) {resultado = true;}
		
		return resultado;
	}	
	
	
	public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getEstado() {
        return estado;
    }
    
}
