package uniandes.dpoo.sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.ProgresoEstudiante;
import uniandes.dpoo.usuario.Estudiante;
import uniandes.dpoo.usuario.Profesor;
import uniandes.dpoo.usuario.Usuario;

public class Consola {
	
    private static Sistema sistema;
    private Usuario usuarioLogueado;

    
    public Consola() {
        sistema = new Sistema();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de Learning Path");

        while (true) {
            if (usuarioLogueado == null) {
                // Opciones para no logueados
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Registrar profesor");
                System.out.println("3. Registrar estudiante");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir la línea

                switch (opcion) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        registrarProfesor(scanner);
                        break;
                    case 3:
                        registrarEstudiante(scanner);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                // Verificar si es un profesor o estudiante
                if (usuarioLogueado instanceof Profesor) {
                	boolean continuar = true;
                	while (continuar) {
                    // Opciones para profesor
                    System.out.println("1. Crear Learning Path");
                    System.out.println("2. Editar Learning Path");
                    System.out.println("3. Crear Nueva Actividad");
                    System.out.println("4. Editar Actividad");
                    System.out.println("5. Calificar actividad");
                    System.out.println("6. Crear reseña");
                    System.out.println("7. Ver reseñas de una actividad");
                    System.out.println("8. Ver información de una actividad");
                    System.out.println("9. Mostrar información de un LearningPath");
                    System.out.println("10. Mostrar respuestas de un estudiante");
                    System.out.println("11. Mostrar resultado de actividad por estudiante");
                    System.out.println("12. Salir");
                    int opcion = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcion) {
                        case 1:
                            crearLearningPath(scanner);
                            break;
                        case 2:
                            editarLearningPath(scanner);
                            break;
                        case 3:
                            crearActividad(scanner);
                            break;
                        case 4:
                            editarActividad(scanner);
                            break;
                        case 5:
                            editarResultadoActividad(scanner);
                            break;
                        case 6:
                            crearFeedbackProfesor(scanner);
                            break;
                        case 7:
                            verReseñasActividades(scanner);
                            break;
                        case 8:
                            mostrarInfoActividad(scanner);
                            break;
                        case 9:
                        	mostrarInfoLearningPath(scanner);
                            break;
                        case 10:
                        	verRespuestasEstudiante(scanner);
                            break;
                        case 11:
                        	verResultadoEstudiante(scanner);
                            break;
                        case 12:
                            continuar = false;  // Salir del menú de profesor
                            usuarioLogueado = null;
                            System.out.println("Volviendo al menú principal...");
                            break;    
                            
                        default:
                            System.out.println("Opción no válida.");
                    }}
                } else if (usuarioLogueado instanceof Estudiante) {
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
        }}
    }

    private void login(Scanner scanner) {
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Contraseña: ");
        String contraseña = scanner.nextLine();
        
        usuarioLogueado = sistema.login(login, contraseña);
        if (usuarioLogueado != null) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }
    }

    private void registrarProfesor(Scanner scanner) {
    	System.out.println("Login: ");
        String login = scanner.nextLine();
        
        while (!sistema.loginUsuariosNoRepetidos(login)) {
            System.out.println("El login ya está registrado. Por favor ingrese un nuevo login: ");
            login = scanner.nextLine();  // Pedimos otro login
        }
        
        System.out.println("Contraseña: ");
        String contraseña = scanner.nextLine();
        
        int id = Sistema.generarIDUnicoUsuarios();
        
        Profesor profesor = new Profesor(id, login, contraseña);
        sistema.registrarUsuario(profesor);
        System.out.println("Profesor registrado exitosamente.");
        System.out.println("El id del usuario es " + id );
        
        
    }

    private void registrarEstudiante(Scanner scanner) {
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
        //System.out.println("Usuarios registrados: ");
        //for (Usuario usuario : sistema.mostrarlistaUsuarios1()) {
        //    System.out.println(usuario); // Esto mostrará ID y Login
        //}
        System.out.println("Estudiante registrado exitosamente.");
        System.out.println("El id del usuario es " + id );
    }
    
    private void crearLearningPath(Scanner scanner) {        
        System.out.println("Título del Learning Path: ");
        String titulo = scanner.nextLine();
        
        System.out.println("Descripción del contenido: ");
        String descripcionContenido = scanner.nextLine();
        
        System.out.println("Descripción del objetivo: ");
        String descripcionObjetivo = scanner.nextLine();
        
        System.out.println("Nivel de dificultad (por ejemplo, fácil, medio, difícil): ");
        String nivelDificultad = scanner.nextLine();
        
        System.out.println("Calificación inicial (rating): ");
        String rating = scanner.nextLine();
        
        int id = Sistema.generarIDUnicoLearningPaths();
        
        System.out.println("El Learning Path ha sido registrado exitosamente.");        
        
        LearningPath nuevoLearningPath = new LearningPath(id, titulo, descripcionContenido, descripcionObjetivo, nivelDificultad, rating);
        
        sistema.agregarLearningPath(nuevoLearningPath);
        nuevoLearningPath.setFechaCreacion();
        System.out.println("El id para el Learning Path es " + id );
    }
    
    public static void crearActividad(Scanner scanner) {        
    	
    	LearningPath learningPathEncontrado = null;
    	
    	while (learningPathEncontrado == null){
    	System.out.println("Escriba el id del Learning Path dentro del cual quiere crear la actividad: ");
        String idLP = scanner.nextLine();
        learningPathEncontrado = sistema.buscarLearningPath(Integer.parseInt(idLP));}
        
        System.out.println("Escriba el tipo de Actividad (tarea, quiz, examen, recurso educativo, encuesta): ");
        String tipoActividad = scanner.nextLine();
        
        System.out.println("Escriba el título de la Actividad: ");
        String tituloActividad = scanner.nextLine();
    	
        System.out.println("Id de tu usuario: ");
        String idUsuario = scanner.nextLine();
        
        System.out.println("Descripción de la actividad: ");
        String descripcion = scanner.nextLine();

        System.out.println("Descripción del objetivo: ");
        String objetivo = scanner.nextLine();

        System.out.println("Nivel de dificultad (por ejemplo, fácil, medio, difícil): ");
        String nivelDificultad = scanner.nextLine();

        System.out.println("Duración esperada (en minutos): ");
        String duracionEsperada = scanner.nextLine();
        
        System.out.println("Fecha límite (formato: dd/MM/yyyy): ");
        String fechaLimite = scanner.nextLine();

        System.out.println("¿Es una actividad obligatoria? (true/false): ");
        boolean obligatoria = Boolean.parseBoolean(scanner.nextLine());
        
        int id = Sistema.generarIDUnicoActividades();
        
        Profesor usuario = (Profesor) sistema.obtenerUsuarioAutenticado();
        
        // Aquí asumes que ya tienes la lista de actividades previas sugeridas
        List<Actividad> actividadesPreviasSugeridas = new ArrayList<>();  // Puedes adaptarlo según cómo obtengas las actividades
        List<Actividad> prerequisitos = new ArrayList<>();
        List<Actividad> actividadesSeguimientoRecomendadas = new ArrayList<>();
        
        //Profesor profesorEncontrado = sistema.stringAProfesor(Integer.parseInt(idUsuario));
        
        Actividad nuevaActividad = Profesor.nuevaActividad(tipoActividad, id, tituloActividad, descripcion, objetivo, duracionEsperada, obligatoria, usuario, nivelDificultad, actividadesPreviasSugeridas, fechaLimite, prerequisitos, actividadesSeguimientoRecomendadas, scanner);
        
        learningPathEncontrado.agregarActividad(nuevaActividad);
        learningPathEncontrado.setDuracionMinutos();
        System.out.println("El id para la actividad es " + id );
    }
    
    
    private void editarLearningPath(Scanner scanner) {
    	
    	System.out.println("Escriba el titulo del Learning Path que quiere editar: ");
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
        
        
        Profesor.editarLearningPath(learningPathEncontrado, nuevoTitulo, nuevaDescripcionContenido, nuevaDescripcionObjetivo, nuevoNivelDificultad, nuevoRating);
        
        System.out.println("El Learning Path ha sido editado exitosamente.");
    }
        
    private void editarActividad(Scanner scanner) {
    	
    	//System.out.println("Escriba el título del Learning Path dentro del cual esta la actividad que quiere editar: ");
        //String tituloParaEditar = scanner.nextLine();
        
        System.out.println("Escriba el id de la Actividad que quiere editar: ");
        String idActividadParaEditar = scanner.nextLine();
        
        //Actividad actividadEncontrada = sistema.buscarActividadDentroLearningPath(tituloParaEditar, tituloActividadParaEditar );
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
            
            // Aquí asumes que ya tienes la lista de actividades previas sugeridas
            List<Actividad> actividadesPreviasSugeridas = new ArrayList<>();  // Puedes adaptarlo según cómo obtengas las actividades
            List<Actividad> prerequisitos = new ArrayList<>();
            
            Profesor usuario = (Profesor)sistema.obtenerUsuarioAutenticado();
            
            Profesor.editarActividad(actividadEncontrada, usuario, nuevaDescripcion, nuevoObjetivo, nuevoNivelDificultad, nuevaDuracionEsperada,
                    actividadesPreviasSugeridas, nuevaFechaLimite, nuevaObligatoria, prerequisitos);
            
            System.out.println("La actividad ha sido editada exitosamente.");
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
        Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
        
        System.out.println("Escriba el id del estudiante del quiere buscar las respuestas de esa actividad: ");
        String idUsuario = scanner.nextLine();
        Estudiante estudianteEncontrado = sistema.stringAEstudiante((Integer.parseInt(idUsuario)));
        
        LearningPath learningPath = sistema.buscarLearningPathPorActividad(actividadEncontrada);
        
        estudianteEncontrado.mostrarRespuestasEstudiantes(actividadEncontrada, learningPath);
        
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
        
        LearningPath learningPathEncontrado = sistema.buscarLearningPath(Integer.parseInt(id));
        //Estudiante estudianteEncontrado = sistema.stringAEstudiante((Integer.parseInt(idUsuario)));
        
        Estudiante estudianteEncontrado = (Estudiante)sistema.obtenerUsuarioAutenticado();
        
        estudianteEncontrado.registrarseLearningPath(learningPathEncontrado);
        learningPathEncontrado.asociarProgresoConEstudiante(estudianteEncontrado);
        System.out.println("Se ha registrado al learning Path exitosamente.");
    }
    
    private void iniciarActividad(Scanner scanner){
		//System.out.println("Escriba el id de su login: ");
		//String idUsuario = scanner.nextLine();
		
    	//System.out.println("Escriba el título del LearningPath al cual pertence la actividad: ");
        //String tituloLP = scanner.nextLine();
    	System.out.println("Escriba el id de la actividad que quiere iniciar: ");
        String id = scanner.nextLine();
        
        Estudiante estudianteEncontrado = (Estudiante)sistema.obtenerUsuarioAutenticado();
        
        //Estudiante estudianteEncontrado = sistema.stringAEstudiante((Integer.parseInt(idUsuario)));
        Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
        
        estudianteEncontrado.iniciarActividad(actividadEncontrada);
        
        sistema.realizarActividadEstudiante(actividadEncontrada, estudianteEncontrado, scanner);
        
    }
    
    private void verProgresoEstudiante(Scanner scanner) {
    	//System.out.println("Escriba el id de su login: ");
		//String idUsuario = scanner.nextLine();
		//Estudiante estudianteEncontrado = sistema.stringAEstudiante((Integer.parseInt(idUsuario)));
    	Estudiante estudianteEncontrado = (Estudiante)sistema.obtenerUsuarioAutenticado();
    	
    	System.out.println("Escriba el id del LearningPath del cual quiere ver el progreso: ");
        String id = scanner.nextLine();
        
        LearningPath learningPathEncontrado = sistema.buscarLearningPath(Integer.parseInt(id));
        
        System.out.println("El progreso de este Learning Path es : " + estudianteEncontrado.establecerProgresoEstudiante(learningPathEncontrado));
        
    }
    
    
    private void crearFeedbackEstudiante(Scanner scanner) {
    	//System.out.println("Id de tu usuario: ");
        //String idUsuario = scanner.nextLine();
    	Estudiante estudianteEncontrado = (Estudiante)sistema.obtenerUsuarioAutenticado();
    	
    	System.out.println("Escriba el id de la actividad a la cual le quiere dejar una reseña: ");
    	String id = scanner.nextLine();
    	
    	System.out.println("Escriba el rating que quiere dejar de la actividad: ");
    	String rating = scanner.nextLine();
    	
    	System.out.println("Escriba el feedback que quiere dejar de la actividad: ");
    	String comentarioFeedback = scanner.nextLine();
    	
    	Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
    	//Estudiante estudianteEncontrado = sistema.stringAEstudiante(Integer.parseInt(idUsuario));
    	
    	estudianteEncontrado.crearFeedbackEstudiante(estudianteEncontrado, actividadEncontrada, Integer.parseInt(rating), comentarioFeedback); 
            
    }
    
    private void crearFeedbackProfesor(Scanner scanner) {
    	//System.out.println("Id de tu usuario: ");
        //String idUsuario = scanner.nextLine();
        
    	System.out.println("Escriba el id de la actividad que quiere iniciar: ");
    	String id = scanner.nextLine();
    	
    	System.out.println("Escriba el rating que quiere dejar de la actividad: ");
    	String rating = scanner.nextLine();
    	
    	System.out.println("Escriba el feedback que quiere dejar de la actividad: ");
    	String comentarioFeedback = scanner.nextLine();
    	
    	Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
    	//Profesor profesorEncontrado = sistema.stringAProfesor(Integer.parseInt(idUsuario));
    	
    	Profesor profesorEncontrado = (Profesor)sistema.obtenerUsuarioAutenticado();
    	
    	profesorEncontrado.crearFeedbackProfesor(profesorEncontrado, actividadEncontrada, Integer.parseInt(rating), comentarioFeedback); 
            
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
        	
        	//System.out.println("Id de tu usuario: ");
            //String idUsuario = scanner.nextLine();
            //Estudiante estudianteEncontrado = sistema.stringAEstudiante(Integer.parseInt(idUsuario));
            
        	Estudiante estudianteEncontrado = (Estudiante)sistema.obtenerUsuarioAutenticado();
        	
            sistema.obtenerResultadoActividadEstudiante(actividadEncontrada, estudianteEncontrado);
        }
       
}


