package uniandes.dpoo.learningPath.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.sistema.Sistema;
import uniandes.dpoo.usuario.Estudiante;

class ReseñaTest {

	@BeforeEach
	void setUp() throws Exception {
		Estudiante estudiante1 = new Estudiante(0, null, null);
        estudiante1.setId(Sistema.generarIDUnicoUsuarios());
        estudiante1.setLogin("mgarcia");
        estudiante1.setContraseña("pass456");
        
        LearningPath learningPath1 = new LearningPath();

        // Usar setters para asignar información básica al LearningPath
        learningPath1.setId(Sistema.generarIDUnicoLearningPaths());
        learningPath1.setTitulo("Fundamentos de Programación");
        learningPath1.setDescripcionContenido("Curso introductorio sobre los fundamentos de programación en Java");
        learningPath1.setDescripcionObjetivo("Entender las bases de la programación");
        learningPath1.setNivelDificultad("Facil");
        learningPath1.setRating("4.5");
        
        Sistema sistema = new Sistema();
		sistema.agregarLearningPath(learningPath1);
        
        Actividad actividad1 = new Actividad();
        actividad1.setId(Sistema.generarIDUnicoActividades());
        actividad1.setTituloActividad("Clase 1: Introducción a Java");
        actividad1.setDescripcion("Aprender los conceptos básicos de Java");
        actividad1.setDuracionEsperada("60");
        actividad1.setObligatoria(true);
        
        
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
