package uniandes.dpoo.usuario.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.Feedback;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.RecursoEducativo;
import uniandes.dpoo.usuario.Profesor;

class ProfesorTest {
    private Profesor profesor;

    @Before
    public void setUp() {
        profesor = new Profesor(1, "login", "contraseña");
        profesor.setLearningPaths(new ArrayList<>());
    }

    @Test
    public void testNuevoProfesor() {
        Profesor nuevo = Profesor.nuevoProfesor(2, "newLogin", "newPassword");
        assertNotNull(nuevo);
        assertEquals(2, nuevo.getId());
        assertEquals("newLogin", nuevo.getLogin());
    }

    @Test
    public void testNuevoLearningPath() {
        LearningPath lp = profesor.nuevoLearningPath(1, "Titulo", "Descripcion", "Objetivo", "Bajo", "5");
        assertNotNull(lp);
        assertEquals(1, lp.getId());
        assertEquals("Titulo", lp.getTitulo());
        assertEquals(1, profesor.getLearningPaths().size());
    }

    @Test
    public void testEditarLearningPath() {
        LearningPath lp = new LearningPath(1, "Titulo", "Descripcion", "Objetivo", "Bajo", "5");
        Profesor.editarLearningPath(lp, "Nuevo Titulo", "Nueva Descripcion", "Nuevo Objetivo", "Medio", "4");
        assertEquals("Nuevo Titulo", lp.getTitulo());
        assertEquals("Nueva Descripcion", lp.getDescripcionContenido());
    }

    @Test
    public void testActualizarActividad() {
        Actividad actividad = new Actividad();
        List<Actividad> previas = new ArrayList<>();
        Actividad resultado = Profesor.actualizarActividad(actividad, "Nueva Desc", "Nuevo Obj", "Medio", "5h", previas, "2024-12-31", true, previas);
        assertEquals("Nueva Desc", resultado.getDescripcion());
        assertTrue(resultado.isObligatoria());
    }

    @Test
    public void testClonarActividad() {
        Actividad actividad = new Actividad();
        actividad.setTituloActividad("Titulo Original");
        Actividad clon = Profesor.clonarActividad(actividad);
        assertNotNull(clon);
        assertEquals("Titulo Original - clonada", clon.getTituloActividad());
    }

    @Test
    public void testEditarActividad() {
        Actividad actividad = new Actividad();
        actividad.setCreador(profesor);
        List<Actividad> previas = new ArrayList<>();
        Actividad resultado = Profesor.editarActividad(actividad, profesor, "Nueva Desc", "Nuevo Obj", "Medio", "5h", previas, "2024-12-31", true, previas);
        assertEquals("Nueva Desc", resultado.getDescripcion());
    }

    @Test
    public void testNuevaActividad() {
        Scanner scanner = new Scanner("Educativo\nTipo de Recurso\nDescripcion\n");
        Actividad actividad = Profesor.nuevaActividad("recurso educativo", 1, "Titulo", "Desc", "Obj", "5h", true, profesor, "Bajo", new ArrayList<>(), "2024-12-31", new ArrayList<>(), new ArrayList<>(), scanner);
        assertNotNull(actividad);
        assertTrue(actividad instanceof RecursoEducativo);
    }

    @Test
    public void testEstablecerPrerrequisitos() {
        Actividad actividad = new Actividad();
        List<Actividad> prerequisitos = new ArrayList<>();
        profesor.establecerPrerrequisitos(actividad, prerequisitos);
        assertEquals(prerequisitos, actividad.getPrerequisitos());
    }

    @Test
    public void testEditarResultado() {
        Actividad actividad = new Actividad();
        profesor.editarResultado(actividad, "Nuevo Resultado");
        assertEquals("Nuevo Resultado", actividad.getResultado());
    }

    @Test
    public void testCrearFeedbackProfesor() {
        Actividad actividad = new Actividad();
        Feedback feedback = profesor.crearFeedbackProfesor(profesor, actividad, 5, "Buen trabajo");
        assertNotNull(feedback);
        assertEquals("Buen trabajo", feedback.getComentario());
    }

}
