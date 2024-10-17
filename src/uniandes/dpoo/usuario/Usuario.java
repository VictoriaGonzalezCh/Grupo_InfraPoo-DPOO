package uniandes.dpoo.usuario;

public class Usuario {

	String login;
	String contraseña;
	int id;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(int id, String login, String contraseña) {
		this.id = id;
		this.login = login;
		this.contraseña = contraseña;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public boolean validarContraseña(String contraseña) {
        return this.contraseña.equals(contraseña);
    }
}
