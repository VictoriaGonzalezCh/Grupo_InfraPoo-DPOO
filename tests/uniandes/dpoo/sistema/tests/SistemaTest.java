package uniandes.dpoo.sistema.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.RecursoEducativo;
import uniandes.dpoo.sistema.Sistema;
import uniandes.dpoo.usuario.Estudiante;
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
        actividad1 = new RecursoEducativo(1, "Actividad1", "Description", "Objective", "30 min", null, null, "Medium", true, null, null, null);
        
        sistema.registrarUsuario(usuario1);
        sistema.registrarUsuario(usuario2);
        sistema.agregarLearningPath(learningPath1);
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
}
