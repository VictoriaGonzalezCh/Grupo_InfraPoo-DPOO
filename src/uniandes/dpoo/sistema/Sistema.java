package uniandes.dpoo.sistema;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.usuario.Estudiante;
import uniandes.dpoo.usuario.Profesor;
import uniandes.dpoo.usuario.Usuario;

public class Sistema {
	
	private static Set<Integer> idsGeneradosUsuarios = new HashSet<>();
	private static Set<Integer> idsGeneradosLearningPaths = new HashSet<>();   
    private static Set<Integer> idsGeneradosActividades = new HashSet<>();
    private static final Random random = new Random();

    
	private List<Usuario> usuarios;
    private List<LearningPath> learningPaths;

    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.learningPaths = new ArrayList<>();
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario login(String login, String contraseña) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.validarContraseña(contraseña)) {
                return usuario;
            }
        }
        return null;
    }

    public void agregarLearningPath(LearningPath learningPath) {
        learningPaths.add(learningPath);
    }
    
    
    
    public LearningPath buscarLearningPath(int id) {
    	LearningPath resultado = null;
    	
    	for (LearningPath learningPath: learningPaths) {
    		{if (learningPath.getId() == id)
    			{resultado = learningPath;}}}
    		
		return resultado;
		}
    
    public Actividad buscarActividadDentroLearningPath(String tituloLearningPath, String tituloActividad) {
    	Actividad resultado = null;
    	List<Actividad> listaActividades = null;
    	
    	for (LearningPath learningPath: learningPaths)
    		{if (learningPath.getTitulo().equals(tituloLearningPath))
    			{listaActividades = learningPath.obtenerListaActividades();}}
    		
    	for (Actividad actividad: listaActividades)
    			{if (actividad.getTituloActividad().equals(tituloActividad)) 
    				{resultado = actividad;}}
    		
    	return resultado;
    	}
    
    
    public Actividad buscarActividadPorId(int id) {
    	Actividad resultado = null;
    	
        for (LearningPath learningPath : learningPaths) {
            for (Actividad actividad : learningPath.obtenerListaActividades()) {
                if (actividad.getId() == id) {resultado = actividad;
                }
            }
        }
        return resultado; 
    }
    
    
    public Estudiante stringAEstudiante(String login) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario instanceof Estudiante) {
                return (Estudiante) usuario;  // Hacemos un cast a Estudiante
            }
        }
        return null;  // Si no se encuentra, retorna null
    }
    
    public Profesor stringAProfesor(String login) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario instanceof Profesor) {
                return (Profesor) usuario; 
            }
        }
        return null;  
    }
    
    public static int generarIDUnicoUsuarios() {
        int nuevoID;

        do {
            nuevoID = random.nextInt(1000000); // Genera un número aleatorio entre 0 y 999,999
        } while (idsGeneradosUsuarios.contains(nuevoID)); // Asegúrate de que el ID no se repita

        idsGeneradosUsuarios.add(nuevoID); // Guarda el nuevo ID para evitar repeticiones
        return nuevoID;
    }
    
    public static int generarIDUnicoLearningPaths() {
        int nuevoID;

        do {
            nuevoID = random.nextInt(1000000); // Genera un número aleatorio entre 0 y 999,999
        } while (idsGeneradosLearningPaths.contains(nuevoID)); // Asegúrate de que el ID no se repita

        idsGeneradosLearningPaths.add(nuevoID); // Guarda el nuevo ID para evitar repeticiones
        return nuevoID;
    }
    
    public static int generarIDUnicoActividades() {
        int nuevoID;

        do {
            nuevoID = random.nextInt(1000000); // Genera un número aleatorio entre 0 y 999,999
        } while (idsGeneradosActividades.contains(nuevoID)); // Asegúrate de que el ID no se repita
        
        idsGeneradosActividades.add(nuevoID); // Guarda el nuevo ID para evitar repeticiones
        return nuevoID;
    }
    
    
    public boolean loginUsuariosNoRepetidos(String loginNuevo) {
    	for (Usuario usuario : usuarios) {
    		if (usuario.getLogin().equals(loginNuevo)) {
                return false; 
            }
        }
		return true;
    }
    
    
    
}

	
