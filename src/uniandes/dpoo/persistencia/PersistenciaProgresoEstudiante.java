package uniandes.dpoo.persistencia;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

import uniandes.dpoo.learningpath.ProgresoEstudiante;

public class PersistenciaProgresoEstudiante extends Persistencia {

    private static final String ARCHIVO_PROGRESO_ESTUDIANTE = "data/progresoEstudiante.dat";
    
    public static void guardarProgresos(List<ProgresoEstudiante> progresosEstudiantes) throws IOException {
        try {
        	guardarObjeto(progresosEstudiantes,ARCHIVO_PROGRESO_ESTUDIANTE);
          } catch (Exception e) {
        	  System.err.println("Error al guardar los progresos: "+ e.getMessage());
        	  e.printStackTrace();
          }	
    }

    @SuppressWarnings("unchecked")
    public static List<ProgresoEstudiante> cargarProgresos() throws IOException, ClassNotFoundException {
        List<ProgresoEstudiante> progresos = new ArrayList<>();
        try {
        	Object obj = cargarObjeto(ARCHIVO_PROGRESO_ESTUDIANTE);
        	if (obj != null) {
        		progresos = (List<ProgresoEstudiante>) obj;
        	}
        } catch (Exception e) {
        	System.err.println("Error al cargar los progresos: "+ e.getMessage());
        	e.printStackTrace();
        }
        return progresos;
    }
}

