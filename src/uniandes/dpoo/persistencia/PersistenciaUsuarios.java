package uniandes.dpoo.persistencia;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import uniandes.dpoo.usuario.Usuario;

public class PersistenciaUsuarios extends Persistencia {

    private static final String ARCHIVO_USUARIOS = "data/usuarios.dat";

    public static void guardarUsuarios(List<Usuario> usuarios) throws IOException {
        try {
        	guardarObjeto(usuarios, ARCHIVO_USUARIOS);
        } catch (Exception e) {
        	System.err.println("Error al guardar usauarios: "+ e.getMessage());
        	e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Usuario> cargarUsuarios() throws IOException, ClassNotFoundException {
    	List<Usuario> usuarios = new ArrayList<>();
    	try {
    		Object obj = cargarObjeto(ARCHIVO_USUARIOS);
    		if (obj != null) {
    			usuarios = (List<Usuario>) obj;
    		}
        } catch (Exception e) {
        	System.err.println("Error al cargar usuarios: "+ e.getMessage());
        	e.printStackTrace();
        }
        return usuarios;
    }
}
