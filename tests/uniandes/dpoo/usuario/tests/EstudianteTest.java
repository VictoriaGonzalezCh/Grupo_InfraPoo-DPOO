package uniandes.dpoo.usuario.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.Feedback;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.Quiz;
import uniandes.dpoo.learningpath.Tarea;
import uniandes.dpoo.usuario.Estudiante;

class EstudianteTest {
	private Estudiante estudiante;
    private LearningPath lp;
    private Actividad actividad1;
    private Actividad actividad2;

    @BeforeEach
    public void setUp() {
        estudiante = new Estudiante(1, "usuario1", "contraseña123");
        lp = new LearningPath(0, "Learning Path 1", null, null, null, null);
        actividad1 = new Quiz(0, 0, "Quiz 1", null, null, null, null, null, null, null, false, null, null, null);
        actividad2 = new Tarea(0, "Tarea 1", null, null, null, null, null, null, false, null, null, null);

        lp.agregarActividad(actividad1);
        lp.agregarActividad(actividad2);
    }

    @Test
    public void testRegistrarseLearningPath() {
        estudiante.registrarseLearningPath(lp);
        assertTrue(estudiante.getLearningPathsCompletados().contains(lp), "El Learning Path debería estar registrado en curso.");
    }

    @Test
    public void testRegistrarActividadCompletada() {
        estudiante.registrarseLearningPath(lp);
        estudiante.iniciarActividad(actividad1);
        estudiante.registrarActividadCompletada(actividad1);
        assertTrue(estudiante.getActividadesCompletadas().contains(actividad1), "La actividad debería estar registrada como completada.");
    }

    @Test
    public void testRegistrarLearningPathCompletada() {
        estudiante.registrarseLearningPath(lp);
        estudiante.registrarLearningPathCompletada(lp);
        assertTrue(estudiante.getLearningPathsCompletados().contains(lp), "El Learning Path debería estar registrado como completado.");
    }

    @Test
    public void testEstablecerProgresoEstudiante() {
        estudiante.registrarseLearningPath(lp);
        estudiante.registrarActividadCompletada(actividad1);
        double progreso = estudiante.establecerProgresoEstudiante(lp);
        assertEquals(50.0, progreso, "El progreso debería ser del 50%.");
    }

    @Test
    public void testCrearFeedbackEstudiante() {
        Feedback feedback = estudiante.crearFeedbackEstudiante(estudiante, actividad1, 5, "Buen quiz.");
        assertNotNull(feedback, "El feedback no debería ser nulo.");
        assertEquals(5, feedback.getRating(), "La calificación del feedback debería ser 5.");
        assertEquals("Buen quiz.", feedback.getComentario(), "El comentario del feedback debería coincidir.");
    }

    // El método realizarActividad probablemente requeriría una prueba manual debido a la interacción del usuario.
    // Podrías considerar refactorizarlo para que sea más fácil de probar, por ejemplo, pasando entradas como parámetros.

    @Test
    public void testMostrarRespuestasEstudiantes() {
        // Dependiendo de la implementación de este método, puede requerir un "mock" o ser considerado para pruebas manuales.
        // Aquí solo incluimos un ejemplo para verificar si se llama correctamente.
        assertDoesNotThrow(() -> estudiante.mostrarRespuestasEstudiantes(actividad1, lp), "El método debería ejecutarse sin lanzar excepciones.");
    }

    @Test
    public void testMostrarResultadoEstudiantes() {
        // Dependiendo de la implementación, esta prueba puede necesitar ajustes.
        assertDoesNotThrow(() -> estudiante.mostrarResultadoEstudiantes(actividad1, lp), "El método debería ejecutarse sin lanzar excepciones.");
    }

}
