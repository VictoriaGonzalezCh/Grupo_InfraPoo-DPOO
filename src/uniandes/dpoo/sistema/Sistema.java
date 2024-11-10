package uniandes.dpoo.sistema;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.persistencia.Persistencia;
import uniandes.dpoo.usuario.Estudiante;
import uniandes.dpoo.usuario.Profesor;
import uniandes.dpoo.usuario.Usuario;

public class Sistema {
	
	private static Set<Integer> idsGeneradosUsuarios = new HashSet<>();
	private static Set<Integer> idsGeneradosLearningPaths = new HashSet<>();   
    private static Set<Integer> idsGeneradosActividades = new HashSet<>();
    private static final Random random = new Random();

    
	private List<Usuario> usuarios;
	private Usuario usuarioAutenticado;
    private List<LearningPath> learningPaths;

    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.usuarioAutenticado = null;
        this.learningPaths = new ArrayList<>();
    }

    public List<Integer> mostrarlistaUsuarios() {
        List<Integer> ids = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            ids.add(usuario.getId());
        }
        return ids; // Devolver la lista de IDs
    } 
    
    public List<Usuario> mostrarlistaUsuarios1() {
        return usuarios; // Esto devuelve la lista completa de usuarios
    }
    
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario login(String login, String contraseña) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login) && usuario.validarContraseña(contraseña)) {
            	this.usuarioAutenticado = usuario;
                return usuario;
            }
        }
        return null;
    }
    
    public void logout() {
        this.usuarioAutenticado = null; 
    }

    public Usuario obtenerUsuarioAutenticado() {
        return this.usuarioAutenticado; 
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
    
    public LearningPath buscarLearningPathPorActividad(Actividad actividadEncontrada) {
        // Recorrer todos los LearningPaths disponibles
        for (LearningPath lp : learningPaths) {
            // Recorrer las actividades de cada LearningPath
            for (Actividad actividad : lp.obtenerListaActividades()) {
                // Si la actividad coincide, retornar el LearningPath correspondiente
                if (actividad.equals(actividadEncontrada)) {
                    return lp;
                }
            }
        }
        // Si no se encuentra ningún LearningPath que contenga la actividad, retornar null
        return null;
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
    
    
    public Estudiante stringAEstudiante(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == (id) && usuario instanceof Estudiante) {
              return (Estudiante) usuario;  // Hacemos un cast a Estudiante
            }
        }
        return null;  // Si no se encuentra, retorna null
    }
    
    public Estudiante stringAEstudiante1(int id) {
        for (Usuario usuario : usuarios) {
            System.out.println("Verificando usuario: " + usuario.getId()); // Mensaje de depuración
            if (usuario.getId() == id && usuario instanceof Estudiante) {
                System.out.println("Usuario encontrado con ID: " + id); // Mensaje de depuración
                    return (Estudiante) usuario;  // Hacemos un cast a Estudiante
          
            }
        }
        System.out.println("No se encontró un Estudiante con el ID: " + id); // Mensaje de depuración
        return null;  // Si no se encuentra, retorna null
    }
    
    public Profesor stringAProfesor(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == (id) && usuario instanceof Profesor) {
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
    
    public boolean loginTituloLPNoRepetidos(String titulo) {
    	for (LearningPath learningPaths : learningPaths) {
    		if (learningPaths.getTitulo().equals(titulo)) {
                return false; 
            }
        }
		return true;
    }
    
    public boolean loginTituloActividadNoRepetidos(String titulo) {
        for (LearningPath learningPath : learningPaths) {
            for (Actividad actividad : learningPath.obtenerListaActividades()) { // Recorre las actividades dentro de cada Learning Path
                if (actividad.getTituloActividad().equalsIgnoreCase(titulo)) {  // Verifica si el título de la actividad se repite
                    return false;  // Si encuentra una actividad con el mismo título, retorna false
                }
            }
        }
        return true;  // Si no se encuentra ninguna actividad con el mismo título, retorna true
    }
    
    public void realizarActividadEstudiante(Actividad actividadEncontrada, Estudiante estudianteEncontrado, Scanner scanner) {
    	
    	LearningPath learningPath = buscarLearningPathPorActividad(actividadEncontrada);
    	    	
        estudianteEncontrado.realizarActividad(actividadEncontrada, learningPath, scanner);
        
        estudianteEncontrado.registrarLearningPathCompletada(learningPath);
        
    }
    
    public void obtenerResultadoActividadEstudiante(Actividad actividadEncontrada, Estudiante estudianteEncontrado) {
    	
    	LearningPath learningPath = buscarLearningPathPorActividad(actividadEncontrada);
    	
        estudianteEncontrado.mostrarResultadoEstudiantes(actividadEncontrada, learningPath);
    }

	public List<LearningPath> getLearningPaths() {
		return learningPaths;
	}

	public List<LearningPath> obtenerTodosLosLearningPaths() {
        return new ArrayList<>(learningPaths);  // Devuelve una copia de la lista de LearningPaths
    }

	public List<Actividad> obtenerTodasLasActividades() {
	    List<Actividad> todasLasActividades = new ArrayList<>();

	    // Iterar sobre cada LearningPath y obtener sus actividades
	    for (LearningPath lp : learningPaths) {
	        todasLasActividades.addAll(lp.getActividades());
	    }

	    return todasLasActividades;
	}

	public void agregarActividad(LearningPath learningPath1, Actividad actividad) {
	    // Buscar el Learning Path por su ID
	    
	    if (learningPath1 != null) {
	        // Si se encontró el Learning Path, agregar la actividad
	        learningPath1.agregarActividad(actividad);
	        System.out.println("Actividad agregada exitosamente al Learning Path con ID " + learningPath1);
	    } else {
	        System.out.println("Learning Path con ID " + learningPath1 + " no encontrado.");
	    }
	}

	public void agregarEstudiante(Estudiante estudiante) {
        // Verificar si el estudiante ya existe (comparamos por ID)
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Estudiante && usuario.getId() == estudiante.getId()) {
                System.out.println("El estudiante con ID " + estudiante.getId() + " ya existe.");
                return; // Salir si ya existe
            }
        }

        // Agregar el estudiante a la lista de usuarios
        usuarios.add(estudiante);
        System.out.println("Estudiante " + estudiante.getLogin() + " agregado con éxito.");
    }
	
	public void crearLearningPath(int id, String titulo, String descripcionContenido, String descripcionObjetivo, String nivelDificultad, String rating) {
		LearningPath nuevoLearningPath = new LearningPath(id, titulo, descripcionContenido, descripcionObjetivo, nivelDificultad, rating);
        
        agregarLearningPath(nuevoLearningPath);
        nuevoLearningPath.setFechaCreacion();
        System.out.println("El id para el Learning Path es " + id );
        
        //Persistencia.guardarObjeto(nuevoLearningPath, "LearningPaths");
	}
	
	public void crearNuevaActividad(LearningPath learningPathEncontrado, String tipoActividad, int id, String tituloActividad, String descripcion, String objetivo, String duracionEsperada, boolean obligatoria, Profesor usuario, String nivelDificultad, List<Actividad>  actividadesPreviasSugeridas, String fechaLimite, List<Actividad> prerequisitos, List<Actividad> actividadesSeguimientoRecomendadas, Scanner scanner){
		Actividad nuevaActividad = Profesor.nuevaActividad(tipoActividad, id, tituloActividad, descripcion, objetivo, duracionEsperada, obligatoria, usuario, nivelDificultad, actividadesPreviasSugeridas, fechaLimite, prerequisitos, actividadesSeguimientoRecomendadas, scanner);
        
        learningPathEncontrado.agregarActividad(nuevaActividad);
        learningPathEncontrado.setDuracionMinutos();
        System.out.println("El id para la actividad es " + id );
	}
	
	public void editarLearningPath(LearningPath learningPathEncontrado, String nuevoTitulo, String nuevaDescripcionContenido, String nuevaDescripcionObjetivo, String nuevoNivelDificultad, String nuevoRating) {
		Profesor.editarLearningPath(learningPathEncontrado, nuevoTitulo, nuevaDescripcionContenido, nuevaDescripcionObjetivo, nuevoNivelDificultad, nuevoRating);
        
        System.out.println("El Learning Path ha sido editado exitosamente.");
	}
	
	public void editarActividad(Actividad actividadEncontrada, String nuevaDescripcion, String nuevoObjetivo, String nuevoNivelDificultad, String nuevaDuracionEsperada, List<Actividad> actividadesPreviasSugeridas, String nuevaFechaLimite, boolean nuevaObligatoria, List<Actividad> prerequisitos) {

        Profesor usuario = (Profesor)obtenerUsuarioAutenticado();
        
        Profesor.editarActividad(actividadEncontrada, usuario, nuevaDescripcion, nuevoObjetivo, nuevoNivelDificultad, nuevaDuracionEsperada,
                actividadesPreviasSugeridas, nuevaFechaLimite, nuevaObligatoria, prerequisitos);
        
        System.out.println("La actividad ha sido editada exitosamente.");
	}
	
	public void verRespuestasEstudiante(String id, String idUsuario) {
		
		 	Actividad actividadEncontrada = buscarActividadPorId(Integer.parseInt(id));
	        
	        Estudiante estudianteEncontrado = stringAEstudiante((Integer.parseInt(idUsuario)));
	        
	        LearningPath learningPath = buscarLearningPathPorActividad(actividadEncontrada);
	        
	        estudianteEncontrado.mostrarRespuestasEstudiantes(actividadEncontrada, learningPath);
	}
	
	public void inscribirseLearningPath(String id) {
		LearningPath learningPathEncontrado = buscarLearningPath(Integer.parseInt(id));
        //Estudiante estudianteEncontrado = sistema.stringAEstudiante((Integer.parseInt(idUsuario)));
        
        Estudiante estudianteEncontrado = (Estudiante)obtenerUsuarioAutenticado();
        
        estudianteEncontrado.registrarseLearningPath(learningPathEncontrado);
        learningPathEncontrado.asociarProgresoConEstudiante(estudianteEncontrado);
        System.out.println("Se ha registrado al learning Path exitosamente.");
	}
	
	public void iniciarActividad(String id, Scanner scanner) {
		Estudiante estudianteEncontrado = (Estudiante)obtenerUsuarioAutenticado();
        
        Actividad actividadEncontrada = buscarActividadPorId(Integer.parseInt(id));
        
        estudianteEncontrado.iniciarActividad(actividadEncontrada);
        
        realizarActividadEstudiante(actividadEncontrada, estudianteEncontrado, scanner);
	}
	
	public void verProgresoEstudiante(String id) {
		Estudiante estudianteEncontrado = (Estudiante)obtenerUsuarioAutenticado();
        
        LearningPath learningPathEncontrado = buscarLearningPath(Integer.parseInt(id));
        
        System.out.println("El progreso de este Learning Path es : " + estudianteEncontrado.establecerProgresoEstudiante(learningPathEncontrado));
	}
	
	public void crearFeedbackEstudiante(String id, String rating, String comentarioFeedback) {
		Estudiante estudianteEncontrado = (Estudiante)obtenerUsuarioAutenticado();
    	
    	Actividad actividadEncontrada = buscarActividadPorId(Integer.parseInt(id));
    	
    	estudianteEncontrado.crearFeedbackEstudiante(estudianteEncontrado, actividadEncontrada, Integer.parseInt(rating), comentarioFeedback); 
	}
	
	public void crearFeedbackProfesor(String id, String rating, String comentarioFeedback) {
		Actividad actividadEncontrada = buscarActividadPorId(Integer.parseInt(id));
    	
    	Profesor profesorEncontrado = (Profesor)obtenerUsuarioAutenticado();
    	
    	profesorEncontrado.crearFeedbackProfesor(profesorEncontrado, actividadEncontrada, Integer.parseInt(rating), comentarioFeedback); 
	}
	
	 
	
	
}

	
