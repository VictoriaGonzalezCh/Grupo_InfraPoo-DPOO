package uniandes.dpoo.persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.learningpath.Actividad;

public class PersistenciaActividades extends Persistencia {

    private static final String ARCHIVO_ACTIVIDADES = "data/actividad.dat";

    public static void guardarActividad(List<Actividad> actividades) throws IOException {
        try {
        	guardarObjeto(actividades,ARCHIVO_ACTIVIDADES);
          } catch (Exception e) {
        	  System.err.println("Error al guardar actividades: "+ e.getMessage());
        	  e.printStackTrace();
          }	
    }

    @SuppressWarnings("unchecked")
    public static List<Actividad> cargarActividad() throws IOException, ClassNotFoundException {
        List<Actividad> actividades = new ArrayList<>();
        try {
        	Object obj = cargarObjeto(ARCHIVO_ACTIVIDADES);
        	if (obj != null) {
        		actividades = (List<Actividad>) obj;
        	}
        } catch (Exception e) {
        	System.err.println("Error al cargar actividades: "+ e.getMessage());
        	e.printStackTrace();
        }
        return actividades;
    }
    
}

