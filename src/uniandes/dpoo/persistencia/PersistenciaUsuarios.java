package uniandes.dpoo.persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.usuario.Usuario;
import uniandes.dpoo.usuario.ProfesorCreador;
import uniandes.dpoo.usuario.ProfesorSeguimiento;
import uniandes.dpoo.usuario.Estudiante;

import org.json.JSONArray;
import org.json.JSONObject;

public class PersistenciaUsuarios {
	
	private static final String RUTA_DIRECTORIO = "data";
    private static final String ARCHIVO_USUARIOS = "usuarios.json";
    private static Map<String, Usuario> usuarios;
    
    
    public PersistenciaUsuarios() {
    	usuarios = new HashMap<>();
    	cargarUsuariosDesdeArchivo();
    }
    
    private void cargarUsuariosDesdeArchivo() {
    	try {
    		File archivo = new File(RUTA_DIRECTORIO, ARCHIVO_USUARIOS);
    		if (archivo.exists()) {
    			String contenido = new String(Files.readAllBytes(Paths.get(archivo.getPath())));
    			JSONArray jsonArray = new JSONArray(contenido);
    			for (int i = 0; i < jsonArray.length(); i++) {
    				JSONObject jsonObj = jsonArray.getJSONObject(i);
    				String tipoUsuario = jsonObj.getString("tipo");
    				Usuario usuario;
    				
    				if (tipoUsuario.equals("Estudiante")) {
                        usuario = new Estudiante(
                            jsonObj.getInt("id"),
                            jsonObj.getString("login"),
                            jsonObj.getString("contraseña")
                        );
                        
    				} else if (tipoUsuario.equals("Profesor Creador")){
    					usuario = new ProfesorCreador(
                                jsonObj.getInt("id"),
                                jsonObj.getString("login"),
                                jsonObj.getString("contraseña")
                            );
                    } else if (tipoUsuario.equals("Profesor Seguimiento")){
    					usuario = new ProfesorSeguimiento(
                                jsonObj.getInt("id"),
                                jsonObj.getString("login"),
                                jsonObj.getString("contraseña")
                            );
                    }
    				
    				else {
                            continue;
    				}
    				usuarios.put(usuario.getLogin(), usuario);
    			}
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    public void guardarUsuarios(Usuario usuario) {
    	try {
			usuarios.put(usuario.getLogin(),usuario);
			guardarUsuariosEnArchivo();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public boolean existeUsuario(String login) {
    	return usuarios.containsKey(login);
    }
    
    public Usuario cargarUsuario(String login) {
    	return usuarios.get(login);
    }
    
    private static void guardarUsuariosEnArchivo() {
		try {
			File directorio = new File(RUTA_DIRECTORIO);
			if (!directorio.exists()) {
				directorio.mkdirs();
			}
			JSONArray jsonArray = new JSONArray();
			for (Usuario usuario : usuarios.values()) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", usuario.getId());
	            jsonObj.put("login", usuario.getLogin());
	            jsonObj.put("contraseña", usuario.getContraseña());
	            
	            if (usuario instanceof Estudiante) {
	            	jsonObj.put("tipo", "Estudiante");
	            } else if (usuario instanceof ProfesorCreador) { 
	            	jsonObj.put("tipo", "Profesor Creador");}
	            	else if (usuario instanceof ProfesorSeguimiento) { 
		            	jsonObj.put("tipo", "Profesor Seguimiento");
	            }
	            
	            jsonArray.put(jsonObj);
			}            
			try (FileWriter file = new FileWriter(new File(RUTA_DIRECTORIO, ARCHIVO_USUARIOS))) {
				file.write(jsonArray.toString());
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}

  	public Map<String,Usuario> getUsuarios() {
		return usuarios;
	}

}
