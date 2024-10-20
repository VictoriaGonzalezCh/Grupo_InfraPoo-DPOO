package uniandes.dpoo.persistencia;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import uniandes.dpoo.learningpath.Feedback;

public class PersistenciaFeedback extends Persistencia {

    private static final String ARCHIVO_FEEDBACK = "data/feedback.dat";

    public static void guardarFeedback(List<Feedback> feedbacks) throws IOException {
        try {
        	guardarObjeto(feedbacks, ARCHIVO_FEEDBACK);
        } catch (Exception e) {
        	System.err.println("Error al guardar el feedback: "+ e.getMessage());
        	e.printStackTrace();
        }
    }
        

    @SuppressWarnings("unchecked")
    public static List<Feedback> cargarFeedback() throws IOException, ClassNotFoundException {
        List<Feedback> feedbacks = new ArrayList<>();
        try {
	    	Object obj = cargarObjeto(ARCHIVO_FEEDBACK);
	        if (obj != null) {
	            feedbacks = (List<Feedback>) obj;
	        }
        } catch (Exception e) {
        	System.err.println("Error al cargar feedback: "+e.getMessage());
        	e.printStackTrace();
        }
        return feedbacks;
    }
}


