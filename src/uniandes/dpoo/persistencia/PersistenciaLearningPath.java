package uniandes.dpoo.persistencia;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import uniandes.dpoo.learningpath.LearningPath;

public class PersistenciaLearningPath extends Persistencia {

    private static final String ARCHIVO_LEARNING_PATHS = "data/learningPaths.dat";

    public static void guardarLearningPaths(List<LearningPath> learningPaths) throws IOException {
        try {
        	guardarObjeto(learningPaths, ARCHIVO_LEARNING_PATHS);
        } catch (Exception e) {
        	System.err.println("Error al guardar los learning paths: "+ e.getMessage());
        	e.printStackTrace();
        }
    }
  

    @SuppressWarnings("unchecked")
    public static List<LearningPath> cargarLearningPaths() throws IOException, ClassNotFoundException {
        List<LearningPath> learningPaths = new ArrayList<>();
        try {
	      	Object obj = cargarObjeto(ARCHIVO_LEARNING_PATHS);
	        if (obj != null) {
	            learningPaths =  (List<LearningPath>) obj;
	        }
        } catch (Exception e) {
        	System.err.println("Error al cargar los Learning Paths: "+e.getMessage());
        	e.printStackTrace();
        }
        return learningPaths;
    }
}

