package uniandes.dpoo.usuario.tests;

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
import uniandes.dpoo.usuario.ProfesorCreador;

class ProfesorCreadorTest {

    private ProfesorCreador profesor;
    private List<Actividad> actividadesPrevias;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        profesor = new ProfesorCreador(1, "profesor", "contraseña");
        actividadesPrevias = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    @Test
    public void testNuevoLearningPath() {
        LearningPath lp = profesor.nuevoLearningPath(1, "Título", "Contenido", "Objetivo", "Medio", "5 estrellas");
        assertNotNull(lp, "El LearningPath no debería ser nulo");
        assertEquals("Título", lp.getTitulo(), "El título del LearningPath no coincide");
        assertEquals(1, profesor.getLearningPaths().size(), "La lista de LearningPaths debería tener un elemento");
    }

    @Test
    public void testEditarLearningPath() {
        LearningPath lp = profesor.nuevoLearningPath(1, "Título", "Contenido", "Objetivo", "Medio", "5 estrellas");
        ProfesorCreador.editarLearningPath(lp, "Nuevo Título", "Nuevo Contenido", "Nuevo Objetivo", "Alto", "4 estrellas");

        assertEquals("Nuevo Título", lp.getTitulo(), "El título del LearningPath no fue actualizado");
        assertEquals("Alto", lp.getNivelDificultad(), "El nivel de dificultad no fue actualizado");
    }

    @Test
    public void testActualizarActividad() {
        Actividad actividad = new Actividad();
        actividad.setDescripcion("Antigua Descripción");

        Actividad actualizada = ProfesorCreador.actualizarActividad(
            actividad, "Nueva Descripción", "Nuevo Objetivo", "Medio", "2 horas",
            actividadesPrevias, "2024-12-12", true, actividadesPrevias
        );

        assertNotNull(actualizada, "La actividad actualizada no debería ser nula");
        assertEquals("Nueva Descripción", actualizada.getDescripcion(), "La descripción no fue actualizada correctamente");
    }

    @Test
    public void testClonarActividad() {
        Actividad actividad = new Actividad();
        actividad.setTituloActividad("Original");
        Actividad clonada = ProfesorCreador.clonarActividad(actividad);

        assertNotNull(clonada, "La actividad clonada no debería ser nula");
        assertEquals("Original - clonada", clonada.getTituloActividad(), "El título no fue clonado correctamente");
    }

    @Test
    public void testEditarActividad() {
        Actividad actividad = new Actividad();
        actividad.setCreador(profesor);
        Actividad resultado = ProfesorCreador.editarActividad(
            actividad, profesor, "Nueva Desc", "Nuevo Objetivo", "Alta",
            "3 horas", actividadesPrevias, "2024-12-15", true, actividadesPrevias
        );

        assertEquals("Nueva Desc", resultado.getDescripcion(), "La descripción no fue editada correctamente");
    }

    @Test
    public void testNuevaActividad() {
        Actividad actividad = ProfesorCreador.nuevaActividad(
            "recurso educativo", 1, "Título", "Descripción", "Objetivo", "4 horas", true,
            profesor, "Medio", actividadesPrevias, "2024-11-30", actividadesPrevias, new ArrayList<>(), scanner
        );

        assertNotNull(actividad, "La actividad no debería ser nula");
        assertTrue(actividad instanceof RecursoEducativo, "La actividad no es un Recurso Educativo");
    }


}
