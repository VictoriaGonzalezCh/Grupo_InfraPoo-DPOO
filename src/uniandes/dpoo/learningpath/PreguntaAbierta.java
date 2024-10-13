package uniandes.dpoo.learningpath;

public class PreguntaAbierta {

	String enunciado;
	String respuesta;
	
	public PreguntaAbierta() {}
	
	public PreguntaAbierta(String enunciado, String respuesta) {
		this.enunciado = enunciado;
		this.respuesta = respuesta;
	}
	
	
	public void nuevoEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public void nuevaRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	public String getEnunciado() {
		return enunciado;
	}
	
	public String getRespuesta() {
		return respuesta;
	}
	
	public void mostrarPregunta() {
        System.out.println("Enunciado: " + enunciado);
    }
	

}
