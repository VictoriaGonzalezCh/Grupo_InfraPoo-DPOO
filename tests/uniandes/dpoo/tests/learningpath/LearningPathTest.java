package uniandes.dpoo.tests.learningpath;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.ProgresoEstudiante;
import uniandes.dpoo.usuario.Estudiante;
import uniandes.dpoo.usuario.ProfesorCreador;

public class LearningPathTest {
    private LearningPath learningPath;
    private Estudiante estudiante1;
    private Estudiante estudiante2;
    private Actividad actividad1;
    private Actividad actividad2;
    private ProfesorCreador creador;

    @BeforeEach
    public void setUp() {
        learningPath = new LearningPath(1, "Título de Prueba", "Descripción de Contenido", "Descripción de Objetivo", "Intermedio", "4 estrellas");
        estudiante1 = new Estudiante(12334, "Juan", "Perez");
        estudiante2 = new Estudiante(34565, "Maria", "Lopez");
        creador = new ProfesorCreador(123287, "profesorjuan", "12345");

        // Supongamos que Actividad tiene un constructor adecuado (debes definir la actividad según tu implementación real)
        actividad1 = new Actividad(1, "Actividad 1", "Descripción de ejemplo", 
                "Objetivo de ejemplo", "Media", "30", "exitoso", 
                new ArrayList<>(), "2024-12-31", true, creador, 
                new ArrayList<>(), new ArrayList<>());// Duración en minutos como string
        actividad2 = new Actividad(0, "Actividad 2", "45", null, null, "45", null, null, null, false, null, null, null);
    }

    @Test
    public void testAgregarActividad() {
        learningPath.agregarActividad(actividad1);
        learningPath.agregarActividad(actividad2);

        List<Actividad> actividades = learningPath.obtenerListaActividades();
        assertEquals(2, actividades.size());
        assertEquals("Actividad 1", actividades.get(0).getTituloActividad());
        assertEquals("Actividad 2", actividades.get(1).getTituloActividad());
    }

    @Test
    public void testRegistrarActividadCompletadaPorEstudiante() {
        learningPath.registrarActividadCompletadaPorEstudiante(estudiante1, actividad1);
        assertTrue(estudiante1.getActividadesCompletadas().contains(actividad1));
    }

    @Test
    public void testAsociarProgresoConEstudiante() {
        learningPath.asociarProgresoConEstudiante(estudiante1);
        learningPath.asociarProgresoConEstudiante(estudiante2);

        HashMap<Estudiante, ProgresoEstudiante> progresoMap = learningPath.getProgresoEstudiante();
        assertEquals(2, progresoMap.size());
        assertTrue(progresoMap.containsKey(estudiante1));
        assertTrue(progresoMap.containsKey(estudiante2));
    }

    @Test
    public void testSetDuracionMinutos() {
        learningPath.agregarActividad(actividad1);
        learningPath.agregarActividad(actividad2);

        learningPath.setDuracionMinutos();
        assertEquals(75, learningPath.getDuracionMinutos());
    }

    @Test
    public void testObtenerActividadesObligatorias() {
        actividad1.setObligatoria(true);
        actividad2.setObligatoria(false);
        
        learningPath.agregarActividad(actividad1);
        learningPath.agregarActividad(actividad2);

        List<Actividad> obligatorias = learningPath.obtenerActividadesObligatorias();
        assertEquals(1, obligatorias.size());
        assertEquals("Actividad 1", obligatorias.get(0).getTituloActividad());
    }
}
