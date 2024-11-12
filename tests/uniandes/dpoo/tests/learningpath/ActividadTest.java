package uniandes.dpoo.learningpath.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.usuario.Profesor;
import java.util.ArrayList;
import java.util.List;

public class ActividadTest {

    private Actividad actividad;
    private Profesor creador;
    private List<Actividad> actividadesPreviasSugeridas;
    private List<Actividad> actividadesSeguimientoRecomendadas;
    private List<Actividad> prerequisitos;

    @BeforeEach
    void setUp() throws Exception {
        creador = new Profesor(123287, "profesorjuan", "12345");
        actividad = new Actividad(1, "Actividad de Ejemplo", "Descripción de ejemplo", 
                                  "Objetivo de ejemplo", "Media", "2 horas", 
                                  new ArrayList<>(), "2024-12-31", true, creador, 
                                  new ArrayList<>(), new ArrayList<>());
        
        actividadesPreviasSugeridas = new ArrayList<>();
        actividadesSeguimientoRecomendadas = new ArrayList<>();
        prerequisitos = new ArrayList<>();
    }

    @Test
    void testAgregarActividadPrevia() {
        Actividad actividadPrevia = new Actividad();
        actividad.agregarActividadPrevia(actividadPrevia);
        assertTrue(actividad.getActividadesPreviasSugeridas().contains(actividadPrevia), 
                   "La actividad previa no fue agregada correctamente.");
    }

    @Test
    void testAgregarPrerequisito() {
        Actividad prerequisito = new Actividad();
        actividad.agregarPrerequisito(prerequisito);
        assertTrue(actividad.getPrerequisitos().contains(prerequisito), 
                   "El prerequisito no fue agregado correctamente.");
    }

    @Test
    void testAgregarActividadesSeguimientoRecomendadas() {
        Actividad actividadSeguimiento = new Actividad();
        actividad.agregarActividadesSeguimientoRecomendadas(actividadSeguimiento);
        assertTrue(actividad.getActividadesSeguimientoRecomendadas().contains(actividadSeguimiento), 
                   "La actividad de seguimiento recomendada no fue agregada correctamente.");
    }

    @Test
    void testMarcarResultado() {
        actividad.marcarResultado("exitosa");
        assertEquals("exitosa", actividad.getResultado(), 
                     "El resultado de la actividad no fue marcado correctamente.");
    }

    @Test
    void testCambiarResultado() {
        actividad.cambiarResultado("en progreso");
        assertEquals("en progreso", actividad.getResultado(), 
                     "El estado de la actividad no fue actualizado correctamente.");
    }

    @Test
    void testIsCompletada() {
        actividad.marcarResultado("exitosa");
        assertTrue(actividad.isCompletada(), "La actividad no fue marcada como completada correctamente.");
    }

    @Test
    void testMostrarInfoActividad() {
        actividad.mostrarInfoActividad(); // Esto solo imprime información, por lo que no hay aserciones aquí.
        // La prueba consiste en verificar que la información se imprima correctamente en la salida estándar.
    }
}
