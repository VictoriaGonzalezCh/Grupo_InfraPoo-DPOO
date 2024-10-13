package uniandes.dpoo.learningpath;


import uniandes.dpoo.usuario.Usuario;

public class Feedback {
    
    private Usuario autor;  
    private String comentario; 
    private int rating;
    private Actividad actividad;


    public Feedback(Usuario autor, String comentario, int rating, Actividad actividad) {
        this.autor = autor;
        this.comentario = comentario;
        this.rating = rating;
        this.actividad = actividad;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
    

    public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void mostrarFeedback() {
        System.out.println("Autor: " + autor.getLogin());
        System.out.println("Actividad: " + actividad.getTituloActividad());
        System.out.println("Comentario: " + comentario);
    }
}
