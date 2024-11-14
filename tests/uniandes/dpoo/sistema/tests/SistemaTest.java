package uniandes.dpoo.sistema.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.RecursoEducativo;
import uniandes.dpoo.sistema.Sistema;
import uniandes.dpoo.usuario.Estudiante;
import uniandes.dpoo.usuario.ProfesorCreador;
import uniandes.dpoo.usuario.Usuario;

class SistemaTest {
	private Sistema sistema;
    private Usuario usuario1;
    private Usuario usuario2;
    private LearningPath learningPath1;
    private Actividad actividad1;

    @BeforeEach
    public void setUp() {
        sistema = new Sistema();
        usuario1 = new Usuario(1, "user1", "password1"); // Dependiendo de cómo esté implementada la clase Usuario
        usuario2 = new Usuario(2, "user2", "password2");
        learningPath1 = new LearningPath(1, "LP1", "Description", "Objective", "Easy", "5 stars");
        actividad1 = new RecursoEducativo(1, "Actividad1", "Description", "Objective", "30", "30", null, null, "Medium", true, null, new ArrayList<>(), new ArrayList<>());
        
        sistema.registrarUsuario(usuario1);
        sistema.registrarUsuario(usuario2);
        sistema.agregarLearningPath(learningPath1);
        sistema.agregarActividad(learningPath1, actividad1);
    }

    @Test
    public void testRegistrarUsuario() {
        Usuario usuario3 = new Usuario(3, "user3", "password3");
        sistema.registrarUsuario(usuario3);
        assertTrue(sistema.mostrarlistaUsuarios().contains(usuario3.getId()));
    }

    @Test
    public void testLoginSuccess() {
        Usuario result = sistema.login("user1", "password1");
        assertNotNull(result);
        assertEquals(usuario1, result);
        assertEquals(usuario1, sistema.obtenerUsuarioAutenticado());
    }

    @Test
    public void testLoginFailure() {
        Usuario result = sistema.login("user1", "wrongpassword");
        assertNull(result);
        assertNull(sistema.obtenerUsuarioAutenticado());
    }

    @Test
    public void testLogout() {
        sistema.login("user1", "password1");
        sistema.logout();
        assertNull(sistema.obtenerUsuarioAutenticado());
    }

    @Test
    public void testAgregarLearningPath() {
        LearningPath newLP = new LearningPath(2, "LP2", "Description", "Objective", "Medium", "4 stars");
        sistema.agregarLearningPath(newLP);
        assertEquals(newLP, sistema.buscarLearningPath(2));
    }

    @Test
    public void testBuscarLearningPath() {
        LearningPath result = sistema.buscarLearningPath(1);
        assertNotNull(result);
        assertEquals(learningPath1, result);
    }

    @Test
    public void testBuscarLearningPathNotFound() {
        LearningPath result = sistema.buscarLearningPath(999);
        assertNull(result);
    }

    @Test
    public void testBuscarActividadPorId() {
        learningPath1.agregarActividad(actividad1);
        Actividad result = sistema.buscarActividadPorId(1);
        assertNotNull(result);
        assertEquals(actividad1, result);
    }

    @Test
    public void testBuscarActividadPorIdNotFound() {
        Actividad result = sistema.buscarActividadPorId(999);
        assertNull(result);
    }

    @Test
    public void testStringAEstudiante() {
        Estudiante estudiante = new Estudiante(3, "student", "password");
        sistema.registrarUsuario(estudiante);
        Estudiante result = sistema.stringAEstudiante(3);
        assertNotNull(result);
        assertEquals(estudiante, result);
    }

    @Test
    public void testStringAEstudianteNotFound() {
        Estudiante result = sistema.stringAEstudiante(999);
        assertNull(result);
    }

    @Test
    public void testGenerarIDUnicoUsuarios() {
        int id1 = Sistema.generarIDUnicoUsuarios();
        int id2 = Sistema.generarIDUnicoUsuarios();
        assertNotEquals(id1, id2);
    }

    @Test
    public void testLoginUsuariosNoRepetidos() {
        assertTrue(sistema.loginUsuariosNoRepetidos("newUser"));
        assertFalse(sistema.loginUsuariosNoRepetidos("user1"));
    }

    @Test
    public void testAgregarActividad() {
        sistema.agregarActividad(learningPath1, actividad1);
        assertTrue(learningPath1.obtenerListaActividades().contains(actividad1));
    }
    
    @Test
    public void testCrearLearningPath() {

        List<LearningPath> learningPaths = sistema.getLearningPaths();
        assertEquals(1, learningPaths.size());
        
        LearningPath learningPath = learningPaths.get(0);
        assertEquals(1, learningPath.getId());
        assertEquals("LP1", learningPath.getTitulo());
        
        System.out.println("El id para el Learning Path es " + 1);
    }
    
    
    
    @Test
    public void testVerRespuestasEstudiante() {
        // Preparar datos
        Estudiante estudiante = new Estudiante(1, "student1", "password");
        Actividad actividad = new Actividad(1, "Actividad 1", "Descripción", "Objetivo", "30 min", null, null, null, "Medium", true, null, null, null);
        LearningPath learningPath = new LearningPath(1, "LP 1", "Descripción", "Objetivo", "Easy", "5 stars");

        sistema.registrarUsuario(estudiante);
        sistema.agregarLearningPath(learningPath);
        learningPath.agregarActividad(actividad);
        estudiante.registrarseLearningPath(learningPath);

        // Registrar respuestas (esto debe hacerlo según tu lógica interna, simula según el comportamiento esperado)
        estudiante.mostrarRespuestasEstudiantes(actividad, learningPath); // Dependiendo de la implementación, podrías verificar un resultado esperado
    }
    
    @Test
    public void testInscribirseLearningPath() {
        // Preparar datos
        Estudiante estudiante = new Estudiante(1, "student1", "password");
        LearningPath learningPath = new LearningPath(1, "LP 1", "Descripción", "Objetivo", "Easy", "5 stars");

        sistema.registrarUsuario(estudiante);
        sistema.login("student1", "password");
        sistema.agregarLearningPath(learningPath);

        // Inscribir al estudiante
        sistema.inscribirseLearningPath("1");

        // Verificar si el estudiante está inscrito
        assertTrue(estudiante.getLearningPathsEnCurso().contains(learningPath), "El estudiante debería estar inscrito en el LearningPath.");
    }
    
    @Test
    public void testIniciarActividad() {
        
        Estudiante estudiante = new Estudiante(1, "student1", "password");
       
        sistema.registrarUsuario(estudiante);
        sistema.login("student1", "password");
        sistema.agregarLearningPath(learningPath1);
        
        learningPath1.agregarActividad(actividad1);
        estudiante.registrarseLearningPath(learningPath1);

        estudiante.iniciarActividad(actividad1);
        
        assertTrue(estudiante.getActividadesEnCurso().contains(actividad1), "La actividad debería estar en curso para el estudiante.");
        
    }
    
    @Test
    public void testVerProgresoEstudiante() {
        // Preparar datos
        Estudiante estudiante = new Estudiante(1, "student1", "password");
        LearningPath learningPath = new LearningPath(1, "LP 1", "Descripción", "Objetivo", "Easy", "5 stars");
        
        sistema.registrarUsuario(estudiante);
        sistema.login("student1", "password");
        sistema.agregarLearningPath(learningPath);
        estudiante.registrarseLearningPath(learningPath);

        // Obtener progreso del estudiante
        sistema.verProgresoEstudiante("1");

        // Verificar el resultado esperado, dependiendo de la lógica interna, el progreso debería estar disponible
        // Podrías verificar algún estado interno o salida de consola
    }
    
    @Test
    public void testCrearFeedbackEstudiante() {
        // Preparar datos
        Estudiante estudiante = new Estudiante(1, "student1", "password");
        Actividad actividad = new Actividad(1, "Actividad 1", "Descripción", "Objetivo", "30 min", null, null, null, "Medium", true, null, null, null);
        
        sistema.registrarUsuario(estudiante);
        sistema.login("student1", "password");
        sistema.agregarActividad(learningPath1, actividad);

        // Crear feedback
        sistema.crearFeedbackEstudiante("1", "5", "Excelente actividad");

        // Verificar que el feedback fue creado (dependiendo de la lógica interna, puedes verificar el estado del feedback)
    }
    
    @Test
    public void testCrearNuevaActividad() {
        List<Actividad> actividades = learningPath1.obtenerListaActividades();
        assertEquals(1, actividades.size());

        Actividad actividad = actividades.get(0);
        assertEquals(1, actividad.getId());
        assertEquals("Actividad1", actividad.getTituloActividad());
        assertEquals("30", actividad.getDuracionEsperada());
        
    }
    
    @Test
    public void testEditarActividad() {
        //ProfesorCreador profesor = new ProfesorCreador(123, "profesor", "1234");
    	//Actividad actividad = new Actividad(1, "Actividad de Ejemplo", "Descripción de ejemplo", "Objetivo de ejemplo", "Media", "260", "exitoso", new ArrayList<>(), "2024-12-31", true, profesor , new ArrayList<>(), new ArrayList<>());

        sistema.editarActividad(actividad1, "Nueva descripción", "Nuevo objetivo", "Alta", "60", new ArrayList<>(), "2024-12-31", false, new ArrayList<>());

        assertEquals("Nueva descripción", actividad1.getDescripcion());
        assertEquals("Nuevo objetivo", actividad1.getObjetivo());
        assertEquals("Alta", actividad1.getNivelDificultad());
        assertEquals("60", actividad1.getDuracionEsperada());
        assertFalse(actividad1.isObligatoria());
        System.out.println("La actividad ha sido editada exitosamente.");
    }
    
    @Test
    public void testEditarLearningPath() {
        LearningPath learningPath = new LearningPath(1, "Título original", "Contenido original", "Objetivo original", "Básico", "3 estrellas");
        sistema.agregarLearningPath(learningPath);

        sistema.editarLearningPath(learningPath, "Nuevo título", "Nuevo contenido", "Nuevo objetivo", "Avanzado", "5 estrellas");

        assertEquals("Nuevo título", learningPath.getTitulo());
        assertEquals("Nuevo contenido", learningPath.getDescripcionContenido());
        assertEquals("Nuevo objetivo", learningPath.getDescripcionObjetivo());
        assertEquals("Avanzado", learningPath.getNivelDificultad());
        assertEquals("5 estrellas", learningPath.getRating());
        System.out.println("El Learning Path ha sido editado exitosamente.");
    }
    
    
}
