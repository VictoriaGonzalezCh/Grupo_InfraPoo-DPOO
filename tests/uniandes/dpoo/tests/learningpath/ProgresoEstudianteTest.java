package uniandes.dpoo.tests.learningpath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.Examen;
import uniandes.dpoo.learningpath.PreguntaOpcionMultiple;
import uniandes.dpoo.learningpath.ProgresoEstudiante;
import uniandes.dpoo.learningpath.Quiz;
import uniandes.dpoo.learningpath.Tarea;
import uniandes.dpoo.usuario.Estudiante;

class ProgresoEstudianteTest {

    private ProgresoEstudiante progreso;
    private Estudiante estudiante;
    private Actividad actividadQuiz;
    private Actividad actividadExamen;

    @BeforeEach
    public void setUp() {
        estudiante = new Estudiante(0, "123", "Juan Pérez");
        actividadQuiz = new Quiz(0, 0, "Quiz de Matemáticas", null, null, null, null, null, null, null, false, null, null, null);
        actividadExamen = new Examen(0, "Examen de Física", null, null, null, null, null, null, false, null, null, null);

        progreso = new ProgresoEstudiante(estudiante, actividadQuiz, "2024-11-01T10:00:00", "2024-11-01T12:00:00", 0, 0);
    }

    @Test
    public void testCalcularTiempoDedicado() {
        int tiempoDedicado = progreso.calculartiempoDedicado("2024-11-01T10:00:00", "2024-11-01T12:00:00");
        assertEquals(120, tiempoDedicado, "El tiempo dedicado debe ser de 120 minutos");
    }

    @Test
    public void testAñadirRespuestasEstudianteQuiz() {
        Quiz quiz = (Quiz) actividadQuiz;
        HashMap<PreguntaOpcionMultiple, String> respuestas = new HashMap<>();
        PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple("¿Cuánto es 2 + 2?", null, null, null);
        respuestas.put(pregunta, "4");

        //quiz.setRespuestasEstudiante(respuestas);
        progreso.añadirRespuestasEstudiante(estudiante, quiz);

        assertTrue(progreso.getRespuestasPorEstudianteQuiz().containsKey(quiz));
        assertEquals("4", progreso.getRespuestasPorEstudianteQuiz().get(quiz).get(pregunta));
    }

    @Test
    public void testCambiarResultadoActividadTarea() {
        Actividad tarea = new Tarea(0, "Tarea de Programación", null, null, null, null, null, null, false, null, null, null);
        progreso.cambiarResultadoActividad(tarea, "Exitosa");
        assertEquals("Exitosa", ((Tarea) tarea).getEstado(), "El estado de la tarea debe cambiar a 'Exitosa'");
    }

}
