package uniandes.dpoo.persistencia;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

import uniandes.dpoo.learningpath.RecursoEducativo;

public class PersistenciaRecursoEducativo extends Persistencia {
	
    private static final String ARCHIVO_RECURSO_EDUCATIVO = "data/recursoEducativo.dat";
    
    public static void guardarRecurso(List<RecursoEducativo> recursosEducativo) throws IOException {
        try {
        	guardarObjeto(recursosEducativo,ARCHIVO_RECURSO_EDUCATIVO);
          } catch (Exception e) {
        	  System.err.println("Error al guardar los progresos: "+ e.getMessage());
        	  e.printStackTrace();
          }	
    }

    @SuppressWarnings("unchecked")
    public static List<RecursoEducativo> cargarRecurso() throws IOException, ClassNotFoundException {
        List<RecursoEducativo> recursos = new ArrayList<>();
        try {
        	Object obj = cargarObjeto(ARCHIVO_RECURSO_EDUCATIVO);
        	if (obj != null) {
        		recursos = (List<RecursoEducativo>) obj;
        	}
        } catch (Exception e) {
        	System.err.println("Error al cargar los progresos: "+ e.getMessage());
        	e.printStackTrace();
        }
        return recursos;
    }
}


