package uniandes.dpoo.usuario.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.Feedback;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.usuario.ProfesorSeguimiento;

class ProfesorSeguimientoTest {

	private ProfesorSeguimiento profesor;
    private Actividad actividad;

    @BeforeEach
    public void setUp() {
        profesor = new ProfesorSeguimiento(1, "testLogin", "testPassword");
        actividad = new Actividad(0, "Actividad de prueba", null, null, null, null, "en progreso", null, null, false, null, null, null);  // Se asume que Actividad tiene un constructor con nombre
    }

    @Test
    public void testNuevoProfesor() {
        ProfesorSeguimiento nuevoProfesor = ProfesorSeguimiento.nuevoProfesor(2, "nuevoLogin", "nuevaContraseña");
        assertNotNull(nuevoProfesor);
        assertEquals(2, nuevoProfesor.getId());
        assertEquals("nuevoLogin", nuevoProfesor.getLogin());
        assertEquals("nuevaContraseña", nuevoProfesor.getContraseña());
    }

    @Test
    public void testEditarResultado() {
        String nuevoResultado = "exitosa";
        profesor.editarResultado(actividad, nuevoResultado);
        assertEquals(nuevoResultado, actividad.getResultado());  
    }

    @Test
    public void testCrearFeedbackProfesor() {
        int rating = 4;
        String comentario = "Buen trabajo";

        Feedback feedback = profesor.crearFeedbackProfesor(profesor, actividad, rating, comentario);

        assertNotNull(feedback);
        assertEquals(profesor, feedback.getAutor());
        assertEquals(actividad, feedback.getActividad());
        assertEquals(rating, feedback.getRating());
        assertEquals(comentario, feedback.getComentario());

        List<Feedback> feedbacks = actividad.getFeedbacks();  // Se asume que Actividad tiene un método para obtener la lista de Feedbacks
        assertTrue(feedbacks.contains(feedback));
    }

   

}
