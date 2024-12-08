package uniandes.dpoo.sistema;

import java.io.IOException;
import javax.swing.*;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.ProgresoEstudiante;
import uniandes.dpoo.usuario.Estudiante;
import uniandes.dpoo.usuario.ProfesorCreador;
import uniandes.dpoo.usuario.ProfesorSeguimiento;
import uniandes.dpoo.usuario.Usuario;

public class Consola extends JFrame{
	
    private static Sistema sistema;
    private Usuario usuarioLogueado;

    
    public Consola() {
        sistema = new Sistema();
    }

    public void iniciar() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de Learning Path");

        while (true) {
            if (usuarioLogueado == null) {
                // Opciones para no logueados
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Registrar profesor creador");
                System.out.println("3. Registrar profesor seguimiento");
                System.out.println("4. Registrar estudiante");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir la línea

                switch (opcion) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        registrarProfesorCreador(scanner);
                        break;
                    case 3:
                        registrarProfesorSeguimiento(scanner);
                        break;
                    case 4:
                        registrarEstudiante(scanner);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                    if (usuarioLogueado instanceof ProfesorCreador) {
                        menuProfesorCreador(scanner);  // Menú para profesor
                    } else if (usuarioLogueado instanceof ProfesorSeguimiento) {
                        menuProfesorSeguimiento(scanner);  // Menú para estudiante
                    }  else if (usuarioLogueado instanceof Estudiante) {
                        menuEstudiante(scanner);  // Menú para estudiante
                    }
            	
                    }
                }
            }
    
    
        
    
    public void loginComoProfesorCreador() throws IOException, ClassNotFoundException {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de Learning Path");

        while (true) {
            if (usuarioLogueado == null) {
                // Opciones para no logueados
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Registrar profesor");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir la línea

                switch (opcion) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        registrarProfesorCreador(scanner);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {        
        System.out.println("¡Bienvenido, Profesor Creador!");
        menuProfesorCreador(scanner);}}
        
    }
    
    public void loginComoProfesorSeguimiento() throws IOException, ClassNotFoundException {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de Learning Path");

        while (true) {
            if (usuarioLogueado == null) {
                // Opciones para no logueados
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Registrar profesor seguimiento");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir la línea

                switch (opcion) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        registrarProfesorSeguimiento(scanner);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {        
        System.out.println("¡Bienvenido, Profesor Seguimiento!");
        menuProfesorSeguimiento(scanner);}}
        
    }

    public void loginComoEstudiante() throws IOException, ClassNotFoundException {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de Learning Path");

        while (true) {
            if (usuarioLogueado == null) {
                // Opciones para no logueados
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Registrar estudiante");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir la línea

                switch (opcion) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        registrarEstudiante(scanner);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {      
        System.out.println("¡Bienvenido, Estudiante!");
        menuEstudiante(scanner);}}
        
    }
    
    private void menuProfesorCreador(Scanner scanner) throws IOException {
    	boolean continuar = true;
    	while (continuar) {
        // Opciones para profesor
        System.out.println("1. Crear Learning Path");
        System.out.println("2. Editar Learning Path");
        System.out.println("3. Crear Nueva Actividad");
        System.out.println("4. Editar Actividad");
        System.out.println("5. Ver reseñas de una actividad");
        System.out.println("6. Ver información de una actividad");
        System.out.println("7. Mostrar información de un LearningPath");
        System.out.println("8. Salir");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                crearLearningPath();
                break;
            case 2:
                editarLearningPath(scanner);
                break;
            case 3:
                crearActividad();
                break;
            case 4:
                editarActividad(scanner);
                break;
            case 5:
                verReseñasActividades(scanner);
                break;
            case 6:
                mostrarInfoActividad(scanner);
                break;
            case 7:
            	mostrarInfoLearningPath(scanner);
                break;
            case 8:
                continuar = false;  // Salir del menú de profesor
                usuarioLogueado = null;
                System.out.println("Volviendo al menú principal...");
                break;    
                
            default:
                System.out.println("Opción no válida.");
            }
        }
    }
    
    private void menuProfesorSeguimiento(Scanner scanner) throws IOException {
    	boolean continuar = true;
    	while (continuar) {
        // Opciones para profesor
        System.out.println("1. Calificar actividad");
        System.out.println("2. Crear reseña");
        System.out.println("3. Ver reseñas de una actividad");
        System.out.println("4. Ver información de una actividad");
        System.out.println("5. Mostrar información de un LearningPath");
        System.out.println("6. Mostrar respuestas de un estudiante");
        System.out.println("7. Mostrar resultado de actividad por estudiante");
        System.out.println("8. Salir");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                editarResultadoActividad(scanner);
                break;
            case 2:
                crearFeedbackProfesor(scanner);
                break;
            case 3:
                verReseñasActividades(scanner);
                break;
            case 4:
                mostrarInfoActividad(scanner);
                break;
            case 5:
            	mostrarInfoLearningPath(scanner);
                break;
            case 6:
            	verRespuestasEstudiante(scanner);
                break;
            case 7:
            	verResultadoEstudiante(scanner);
                break;
            case 8:
                continuar = false;  // Salir del menú de profesor
                usuarioLogueado = null;
                System.out.println("Volviendo al menú principal...");
                break;    
                
            default:
                System.out.println("Opción no válida.");
            }
        }
    }
    
    private void menuEstudiante(Scanner scanner) {
    	boolean continuar = true;
    	while (continuar) {
        // Opciones para estudiante
        System.out.println("1. Inscribirse a un Learning Path");
        System.out.println("2. Iniciar una Actividad");
        System.out.println("3. Ver progreso Learning Path");
        System.out.println("4. Crear reseña");
        System.out.println("5. Ver reseñas de una actividad");
        System.out.println("6. Ver información de una actividad");
        System.out.println("7. Mostrar información de un LearningPath");
        System.out.println("8. Mostrar respuestas de un estudiante");
        System.out.println("9. Mostrar resultado de actividad");
        System.out.println("10. Salir");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                inscribirseLearningPath(scanner);
                break;
            case 2:
                iniciarActividad(scanner);
                break;
            case 3:
                verProgresoEstudiante(scanner);
                break;
            case 4:
                crearFeedbackEstudiante(scanner);
                break;
            case 5:
                verReseñasActividades(scanner);
                break;
            case 6:
                mostrarInfoActividad(scanner);
                break;
            case 7:
            	mostrarInfoLearningPath(scanner);
                break;
            case 8:
            	verRespuestasEstudiante(scanner);
                break;
            case 9:
            	verResultadoEstudiante(scanner);
                break;    
            case 10:
                continuar = false;  // Salir del menú de profesor
                usuarioLogueado = null;
                System.out.println("Volviendo al menú principal...");
                break; 
            
            default:
                System.out.println("Opción no válida.");
            }
        }
    }
    

    private void login(Scanner scanner) throws ClassNotFoundException, IOException {
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Contraseña: ");
        String contraseña = scanner.nextLine();
        
        // Cargar la lista de usuarios registrados desde el archivo
        // Suponiendo que en "Usuarios.dat" tienes una lista de usuarios serializados
        
        //@SuppressWarnings("unchecked")
		//List<Usuario> usuarios = (List<Usuario>) Persistencia.cargarObjeto("Usuarios");
        
        // Usar la lista cargada para intentar iniciar sesión
        usuarioLogueado = sistema.login(login, contraseña);  // Sistema que toma la lista de usuarios
        
        if (usuarioLogueado != null) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }
    }

    private void registrarProfesorCreador(Scanner scanner) throws IOException, ClassNotFoundException {
    	System.out.println("Login: ");
        String login = scanner.nextLine();
        
        while (!sistema.loginUsuariosNoRepetidos(login)) {
            System.out.println("El login ya está registrado. Por favor ingrese un nuevo login: ");
            login = scanner.nextLine();  // Pedimos otro login
        }
        
        System.out.println("Contraseña: ");
        String contraseña = scanner.nextLine();
        
        int id = Sistema.generarIDUnicoUsuarios();
        
        ProfesorCreador profesor = new ProfesorCreador(id, login, contraseña);
        sistema.registrarUsuario(profesor);
        System.out.println("Profesor registrado exitosamente.");
        System.out.println("El id del usuario es " + id );
        
        
        //Persistencia.guardarObjeto(profesor, "Profesores");
        //Persistencia.guardarObjeto(profesor, "Usuario");
    }
    
    private void registrarProfesorSeguimiento(Scanner scanner) throws IOException, ClassNotFoundException {
    	System.out.println("Login: ");
        String login = scanner.nextLine();
        
        while (!sistema.loginUsuariosNoRepetidos(login)) {
            System.out.println("El login ya está registrado. Por favor ingrese un nuevo login: ");
            login = scanner.nextLine();  // Pedimos otro login
        }
        
        System.out.println("Contraseña: ");
        String contraseña = scanner.nextLine();
        
        int id = Sistema.generarIDUnicoUsuarios();
        
        ProfesorSeguimiento profesor = new ProfesorSeguimiento(id, login, contraseña);
        sistema.registrarUsuario(profesor);
        System.out.println("Profesor registrado exitosamente.");
        System.out.println("El id del usuario es " + id );
        
        
        //Persistencia.guardarObjeto(profesor, "Profesores");
        //Persistencia.guardarObjeto(profesor, "Usuario");
    }
    

    private void registrarEstudiante(Scanner scanner) throws IOException, ClassNotFoundException {
    	System.out.println("Login: ");
        String login = scanner.nextLine();
        
        while (!sistema.loginUsuariosNoRepetidos(login)) {
            System.out.println("El login ya está registrado. Por favor ingrese un nuevo login: ");
            login = scanner.nextLine();  // Pedimos otro login
        }
        
        System.out.println("Contraseña: ");
        String contraseña = scanner.nextLine();

        int id = Sistema.generarIDUnicoUsuarios();
        
        Estudiante estudiante = new Estudiante(id, login, contraseña);
        sistema.registrarUsuario(estudiante);
        
        System.out.println("Estudiante registrado exitosamente.");
        System.out.println("El id del usuario es " + id );
        
       
        //Persistencia.guardarObjeto(estudiante, "Estudiantes");
        //Persistencia.guardarObjeto(estudiante, "Usuario");
       
    }
    
    void crearLearningPath() {        
    	String titulo = JOptionPane.showInputDialog(this, "Título del Learning Path:");
        if (titulo == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        String descripcionContenido = JOptionPane.showInputDialog(this, "Descripción del contenido:");
        if (descripcionContenido == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        String descripcionObjetivo = JOptionPane.showInputDialog(this, "Descripción del objetivo:");
        if (descripcionObjetivo == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        String nivelDificultad = JOptionPane.showInputDialog(this, "Nivel de dificultad (por ejemplo, fácil, medio, difícil):");
        if (nivelDificultad == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        String rating = JOptionPane.showInputDialog(this, "Calificación inicial (rating):");
        if (rating == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }
        
        int id = Sistema.generarIDUnicoLearningPaths();
        
        JOptionPane.showMessageDialog(this, "El Learning Path ha sido registrado exitosamente.\nID: " + id);
                
        
        sistema.crearLearningPath(id, titulo, descripcionContenido, descripcionObjetivo, nivelDificultad, rating);
    }
    
    public void crearActividad() {        
    	
    	// Obtener el ID del Learning Path
        String idLP = JOptionPane.showInputDialog(this, "Escriba el id del Learning Path dentro del cual quiere crear la actividad:");
        if (idLP == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        LearningPath learningPathEncontrado = null;
        try {
            learningPathEncontrado = sistema.buscarLearningPath(Integer.parseInt(idLP));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID no válido.");
            return; // Salir si el ID no es válido
        }
        
        if (learningPathEncontrado == null) {
            JOptionPane.showMessageDialog(this, "Learning Path no encontrado.");
            return;
        }

        // Obtener el tipo de actividad
        String tipoActividad = JOptionPane.showInputDialog(this, "Escriba el tipo de Actividad (tarea, quiz, examen, recurso educativo, encuesta):");
        if (tipoActividad == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        // Obtener el título de la actividad
        String tituloActividad = JOptionPane.showInputDialog(this, "Escriba el título de la Actividad:");
        if (tituloActividad == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return;
        }

        // Obtener la descripción de la actividad
        String descripcion = JOptionPane.showInputDialog(this, "Descripción de la actividad:");
        if (descripcion == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return;
        }

        // Obtener la descripción del objetivo
        String objetivo = JOptionPane.showInputDialog(this, "Descripción del objetivo:");
        if (objetivo == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return;
        }

        // Obtener el nivel de dificultad
        String nivelDificultad = JOptionPane.showInputDialog(this, "Nivel de dificultad (por ejemplo, fácil, medio, difícil):");
        if (nivelDificultad == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return;
        }

        // Obtener la duración esperada de la actividad
        String duracionEsperada = JOptionPane.showInputDialog(this, "Duración esperada (en minutos):");
        if (duracionEsperada == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return;
        }

        // Obtener la fecha límite de la actividad
        String fechaLimite = JOptionPane.showInputDialog(this, "Fecha límite (formato: dd/MM/yyyy):");
        if (fechaLimite == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return;
        }

        // Preguntar si es obligatoria
        String obligatoriaString = JOptionPane.showInputDialog(this, "¿Es una actividad obligatoria? (true/false):");
        if (obligatoriaString == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return;
        }
        boolean obligatoria = Boolean.parseBoolean(obligatoriaString);

        
        int id = Sistema.generarIDUnicoActividades();
        
        ProfesorCreador usuario = (ProfesorCreador) sistema.obtenerUsuarioAutenticado();
        
        // Aquí asumes que ya tienes la lista de actividades previas sugeridas
        List<Actividad> actividadesPreviasSugeridas = new ArrayList<>();  
        List<Actividad> prerequisitos = new ArrayList<>();
        List<Actividad> actividadesSeguimientoRecomendadas = new ArrayList<>();
        
        sistema.crearNuevaActividad(learningPathEncontrado, tipoActividad, id, tituloActividad, descripcion, objetivo, duracionEsperada, obligatoria, usuario, nivelDificultad, actividadesPreviasSugeridas, fechaLimite, prerequisitos, actividadesSeguimientoRecomendadas);
        //Actividad nuevaActividad = Profesor.nuevaActividad(tipoActividad, id, tituloActividad, descripcion, objetivo, duracionEsperada, obligatoria, usuario, nivelDificultad, actividadesPreviasSugeridas, fechaLimite, prerequisitos, actividadesSeguimientoRecomendadas, scanner);
        
        //learningPathEncontrado.agregarActividad(nuevaActividad);
        //learningPathEncontrado.setDuracionMinutos();
        //System.out.println("El id para la actividad es " + id );
    }
    
    
    private void editarLearningPath(Scanner scanner) {
    	
    	System.out.println("Escriba el id del Learning Path que quiere editar: ");
        String idParaEditar = scanner.nextLine();
        
        LearningPath learningPathEncontrado = sistema.buscarLearningPath(Integer.parseInt(idParaEditar));
    	
    	System.out.println("Título nuevo del Learning Path: ");
        String nuevoTitulo = scanner.nextLine();
        
        System.out.println("Descripción nueva del contenido: ");
        String nuevaDescripcionContenido = scanner.nextLine();
        
        System.out.println("Descripción nueva del objetivo: ");
        String nuevaDescripcionObjetivo = scanner.nextLine();
        
        System.out.println("Nuevo nivel de dificultad (por ejemplo, fácil, medio, difícil): ");
        String nuevoNivelDificultad = scanner.nextLine();
        
        System.out.println("Nueva calificación inicial (rating): ");
        String nuevoRating = scanner.nextLine();
        
        //Profesor.editarLearningPath(learningPathEncontrado, nuevoTitulo, nuevaDescripcionContenido, nuevaDescripcionObjetivo, nuevoNivelDificultad, nuevoRating);
        
        //System.out.println("El Learning Path ha sido editado exitosamente.");
        
        sistema.editarLearningPath(learningPathEncontrado, nuevoTitulo, nuevaDescripcionContenido, nuevaDescripcionObjetivo, nuevoNivelDificultad, nuevoRating);
    }
        
    private void editarActividad(Scanner scanner) {
        
        System.out.println("Escriba el id de la Actividad que quiere editar: ");
        String idActividadParaEditar = scanner.nextLine();
        
        Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(idActividadParaEditar));
        
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Editar la descripción de la actividad");
        System.out.println("2. Añadir una actividad de prerrequisito o seguimiento");
        int eleccion = Integer.parseInt(scanner.nextLine());

        switch (eleccion) {
        case 1:
        	System.out.println("Descripción nueva de la actividad: ");
            String nuevaDescripcion = scanner.nextLine();

            System.out.println("Descripción nueva del objetivo: ");
            String nuevoObjetivo = scanner.nextLine();

            System.out.println("Nuevo nivel de dificultad (por ejemplo, fácil, medio, difícil): ");
            String nuevoNivelDificultad = scanner.nextLine();

            System.out.println("Nueva duración esperada (en minutos): ");
            String nuevaDuracionEsperada = scanner.nextLine();
            
            System.out.println("Nueva fecha límite (formato: dd/MM/yyyy): ");
            String nuevaFechaLimite = scanner.nextLine();

            System.out.println("¿Es una actividad obligatoria? (true/false): ");
            boolean nuevaObligatoria = Boolean.parseBoolean(scanner.nextLine());
            
            List<Actividad> actividadesPreviasSugeridas = new ArrayList<>();  
            List<Actividad> prerequisitos = new ArrayList<>();
            
            sistema.editarActividad(actividadEncontrada, nuevaDescripcion, nuevoObjetivo, nuevoNivelDificultad, nuevaDuracionEsperada, actividadesPreviasSugeridas, nuevaFechaLimite, nuevaObligatoria, prerequisitos);
            //Profesor usuario = (Profesor)sistema.obtenerUsuarioAutenticado();
            
            //Profesor.editarActividad(actividadEncontrada, usuario, nuevaDescripcion, nuevoObjetivo, nuevoNivelDificultad, nuevaDuracionEsperada,
              //      actividadesPreviasSugeridas, nuevaFechaLimite, nuevaObligatoria, prerequisitos);
            
            //System.out.println("La actividad ha sido editada exitosamente.");
            break;
        
        case 2:
        	System.out.println("¿Desea añadir una actividad como prerrequisito, seguimiento o actividad previa?");
            System.out.println("1. Prerrequisito");
            System.out.println("2. Seguimiento");
            System.out.println("2. Previa ");
            int tipoActividadRelacionada = Integer.parseInt(scanner.nextLine());

            System.out.println("Escriba el ID de la actividad relacionada: ");
            String idActividadRelacionada = scanner.nextLine();
            Actividad actividadRelacionada = sistema.buscarActividadPorId(Integer.parseInt(idActividadRelacionada));

            if (actividadRelacionada == null) {
                System.out.println("No se encontró ninguna actividad con el ID proporcionado.");
                return;
            }
            
            if (tipoActividadRelacionada == 1) {
            	añadirPrerequisito(scanner);
            }
            
            else if (tipoActividadRelacionada == 2) {
            	añadirActividadesSeguimientoRecomendadas(scanner);
            }
            
            else if (tipoActividadRelacionada == 3) {
            	añadirActividadPrevia(scanner);
            }
        }
        
        
    }
        
    
    private void verRespuestasEstudiante(Scanner scanner) {
    	System.out.println("Escriba el id de la actividad de la cual quiere ver las respuestas ingresadas por el estudiante: ");
        String id = scanner.nextLine();
        
        //Actividad actividadEncontrada = sistema.buscarActividadDentroLearningPath(tituloLP, tituloActividad );
        //Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
        
        System.out.println("Escriba el id del estudiante del quiere buscar las respuestas de esa actividad: ");
        String idUsuario = scanner.nextLine();
        
        sistema.verRespuestasEstudiante(id, idUsuario);
        
        //Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
        
        //Estudiante estudianteEncontrado = sistema.stringAEstudiante((Integer.parseInt(idUsuario)));
        
        //LearningPath learningPath = sistema.buscarLearningPathPorActividad(actividadEncontrada);
        
        //estudianteEncontrado.mostrarRespuestasEstudiantes(actividadEncontrada, learningPath);
        
    }
    
    private void editarResultadoActividad(Scanner scanner) {
        System.out.println("Escriba el id de la actividad: ");
        String id = scanner.nextLine();
        Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));

        System.out.println("Escriba el id del estudiante del cual quiere buscar las respuestas de esa actividad: ");
        String idUsuario = scanner.nextLine();
        Estudiante estudianteEncontrado = sistema.stringAEstudiante(Integer.parseInt(idUsuario));

        verRespuestasEstudiante(scanner);
        
        System.out.println("¿A qué resultado quiere cambiar el resultado actual? (Para examenes tiene que ser exitoso o no exitoso, para tareas puede ser exitoso, insatisfactorio, completado): ");
        String nuevoResultado = scanner.nextLine();

        // Verifica si la actividad y el estudiante fueron encontrados
        if (actividadEncontrada != null && estudianteEncontrado != null) {
            LearningPath learningPath = sistema.buscarLearningPathPorActividad(actividadEncontrada);
            
            if (learningPath != null) {
                ProgresoEstudiante progreso = learningPath.obtenerProgresoDeEstudiante(estudianteEncontrado);
                
                if (progreso != null) {
                    // Cambiamos el resultado en el progreso del estudiante 
                    progreso.cambiarResultadoActividad(actividadEncontrada, nuevoResultado);
                    System.out.println("El resultado de la actividad ha sido cambiado a: " + nuevoResultado);
                } else {
                    System.out.println("No se encontró progreso para el estudiante en la actividad especificada.");
                }
            } else {
                System.out.println("No se encontró el Learning Path para la actividad especificada.");
            }
        } else {
            System.out.println("Actividad o estudiante no encontrados.");
        }
    }
    
    
    private void inscribirseLearningPath(Scanner scanner){
    	
    	//System.out.println("Escriba el id de su login: ");
        //String idUsuario = scanner.nextLine();
    	
    	System.out.println("Escriba el id del Learning Path al cual se quiere inscribirse: ");
        String id = scanner.nextLine();
        
        sistema.inscribirseLearningPath(id);
        
        //LearningPath learningPathEncontrado = sistema.buscarLearningPath(Integer.parseInt(id));
        
        //Estudiante estudianteEncontrado = (Estudiante)sistema.obtenerUsuarioAutenticado();
        
        //estudianteEncontrado.registrarseLearningPath(learningPathEncontrado);
        //learningPathEncontrado.asociarProgresoConEstudiante(estudianteEncontrado);
        //System.out.println("Se ha registrado al learning Path exitosamente.");
    }
    
    private void iniciarActividad(Scanner scanner){
		
    	System.out.println("Escriba el id de la actividad que quiere iniciar: ");
        String id = scanner.nextLine();
        
        sistema.iniciarActividad(id, scanner);
        
        //Estudiante estudianteEncontrado = (Estudiante)sistema.obtenerUsuarioAutenticado();
        
        //Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
        
        //estudianteEncontrado.iniciarActividad(actividadEncontrada);
        
        //sistema.realizarActividadEstudiante(actividadEncontrada, estudianteEncontrado, scanner);
        
    }
    
    private void verProgresoEstudiante(Scanner scanner) {
    	
    	System.out.println("Escriba el id del LearningPath del cual quiere ver el progreso: ");
        String id = scanner.nextLine();
    	
        sistema.verProgresoEstudiante(id);
        
    	//Estudiante estudianteEncontrado = (Estudiante)sistema.obtenerUsuarioAutenticado();
        
        //LearningPath learningPathEncontrado = sistema.buscarLearningPath(Integer.parseInt(id));
        
        //System.out.println("El progreso de este Learning Path es : " + estudianteEncontrado.establecerProgresoEstudiante(learningPathEncontrado));
        
    }
    
    
    private void crearFeedbackEstudiante(Scanner scanner) {
    	//System.out.println("Id de tu usuario: ");
        //String idUsuario = scanner.nextLine();
    	
    	System.out.println("Escriba el id de la actividad a la cual le quiere dejar una reseña: ");
    	String id = scanner.nextLine();
    	
    	System.out.println("Escriba el rating que quiere dejar de la actividad: ");
    	String rating = scanner.nextLine();
    	
    	System.out.println("Escriba el feedback que quiere dejar de la actividad: ");
    	String comentarioFeedback = scanner.nextLine();
    	
    	sistema.crearFeedbackEstudiante(id, rating, comentarioFeedback);
    	
    	//Estudiante estudianteEncontrado = (Estudiante)sistema.obtenerUsuarioAutenticado();
    	
    	//Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
    	
    	//estudianteEncontrado.crearFeedbackEstudiante(estudianteEncontrado, actividadEncontrada, Integer.parseInt(rating), comentarioFeedback); 
            
    }
    
    private void crearFeedbackProfesor(Scanner scanner) {        
    	System.out.println("Escriba el id de la actividad que quiere iniciar: ");
    	String id = scanner.nextLine();
    	
    	System.out.println("Escriba el rating que quiere dejar de la actividad: ");
    	String rating = scanner.nextLine();
    	
    	System.out.println("Escriba el feedback que quiere dejar de la actividad: ");
    	String comentarioFeedback = scanner.nextLine();
    	
    	sistema.crearFeedbackProfesor(id, rating, comentarioFeedback);
    	
    	//Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
    	
    	//Profesor profesorEncontrado = (Profesor)sistema.obtenerUsuarioAutenticado();
    	
    	//profesorEncontrado.crearFeedbackProfesor(profesorEncontrado, actividadEncontrada, Integer.parseInt(rating), comentarioFeedback); 
            
    }
    
    private void añadirActividadesSeguimientoRecomendadas(Scanner scanner) {
    	
    	System.out.println("Escriba el id de la actividad a la cual le quiere añadir una actividad de seguimientos recomendada: ");
    	String id = scanner.nextLine();
    	Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
    	
    	System.out.println("Cuantas actividades de seguimiento recomendadas desea añadir: ");
    	int numActividades = Integer.parseInt(scanner.nextLine());
    	
    	
    	int contador = 0;
    	
    	while (contador < numActividades) {
    		System.out.println("Escriba el id de la actividad quiere añadir a la lista de actividades de seguimientos recomendada: ");
        	String idActividadParaAñadir = scanner.nextLine();
        	
        	Actividad actividadEncontradaParaAñadir = sistema.buscarActividadPorId(Integer.parseInt(idActividadParaAñadir));
        	
        	actividadEncontrada.agregarActividadesSeguimientoRecomendadas(actividadEncontradaParaAñadir);
            
            contador++;
    	}
    	
    	System.out.println("Actividad de seguimiento añadida ");
        }
    
    	private void añadirActividadPrevia(Scanner scanner) {
        	
        	System.out.println("Escriba el id de la actividad a la cual le quiere añadir una actividad previa: ");
        	String id = scanner.nextLine();
        	Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
        	
        	System.out.println("Cuantas actividades previas desea añadir: ");
        	int numActividades = Integer.parseInt(scanner.nextLine());
        	
        	int contador = 0;
        	
        	while (contador < numActividades) {
        		System.out.println("Escriba el id de la actividad quiere añadir a la lista de actividades previas: ");
            	String idActividadParaAñadir = scanner.nextLine();
            	
            	Actividad actividadEncontradaParaAñadir = sistema.buscarActividadPorId(Integer.parseInt(idActividadParaAñadir));
            	
            	actividadEncontrada.agregarActividadPrevia(actividadEncontradaParaAñadir);
                
                contador++;
            }
        	System.out.println("Actividad previa añadida ");
    	}
        	
        private void añadirPrerequisito(Scanner scanner) {
            	
            	System.out.println("Escriba el id de la actividad a la cual le quiere añadir una actividad prerequisito: ");
            	String id = scanner.nextLine();
            	Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
            	
            	System.out.println("Cuantas actividades prerequisito desea añadir: ");
            	int numActividades = Integer.parseInt(scanner.nextLine());
            	
            	int contador = 0;
            	
            	while (contador < numActividades) {
            		System.out.println("Escriba el id de la actividad quiere añadir a la lista de prerequisitos: ");
                	String idActividadParaAñadir = scanner.nextLine();
                	
                	Actividad actividadEncontradaParaAñadir = sistema.buscarActividadPorId(Integer.parseInt(idActividadParaAñadir));
                	
                	actividadEncontrada.agregarPrerequisito(actividadEncontradaParaAñadir);
                    
                    contador++;
                }
            	System.out.println("Actividad prerequisito añadida ");
        	}
        
        private void verReseñasActividades(Scanner scanner) {
        	System.out.println("Escriba el id de la actividad de la cual quiere ver la reseña: ");
        	String id = scanner.nextLine();
        	Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
        	
        	actividadEncontrada.mostrarFeedbacks();
        }
        
        private void mostrarInfoActividad(Scanner scanner) {
        	System.out.println("Escriba el id de la actividad de la cual quiere ver la información: ");
        	String id = scanner.nextLine();
        	Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
        	
        	actividadEncontrada.mostrarInfoActividad();
        }
        
        private void mostrarInfoLearningPath(Scanner scanner) {
        	System.out.println("Escriba el id del LearningPath del cual quiere ver la información: ");
        	String id = scanner.nextLine();
        	LearningPath LPEncontrado = sistema.buscarLearningPath(Integer.parseInt(id));
        	
        	LPEncontrado.mostrarInfoLearningPath();
        }
    
        private void verResultadoEstudiante(Scanner scanner) {
        	System.out.println("Escriba el id de la actividad de la cual quiero ver el resultado: ");
        	String id = scanner.nextLine();
        	Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
        	
        	System.out.println("Escriba el id del estudiante del quiere buscar las respuestas de esa actividad: ");
            String idUsuario = scanner.nextLine();
            Estudiante estudianteEncontrado = sistema.stringAEstudiante((Integer.parseInt(idUsuario)));
        	
        	//System.out.println("Id de tu usuario: ");
            //String idUsuario = scanner.nextLine();
            //Estudiante estudianteEncontrado = sistema.stringAEstudiante(Integer.parseInt(idUsuario));
            
        	//Estudiante estudianteEncontrado = (Estudiante)sistema.obtenerUsuarioAutenticado();
        	
            sistema.obtenerResultadoActividadEstudiante(actividadEncontrada, estudianteEncontrado);
        }
       
}


