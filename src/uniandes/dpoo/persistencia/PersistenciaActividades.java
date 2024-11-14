package uniandes.dpoo.persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import uniandes.dpoo.learningpath.*;
import org.json.JSONArray;
import org.json.JSONObject;
import uniandes.dpoo.usuario.ProfesorCreador;


public class PersistenciaActividades {

	private static final String RUTA_DIRECTORIO = "data";
    private static final String ARCHIVO_ACTIVIDADES = "actividad.json";
    private Map<String, Actividad> actividades;
    
    public PersistenciaActividades() {
    	actividades = new HashMap<>();
    	cargarActividadesArchivo();
    }
    
    public List<Actividad> cargarActividadesArchivo() {
    	try{
    		File archivo = new File(RUTA_DIRECTORIO, ARCHIVO_ACTIVIDADES);
    		if (archivo.exists()) {
    			String contenido = new String(Files.readAllBytes(Paths.get(archivo.getPath())));
    			JSONArray jsonArray = new JSONArray(contenido);
    			for (int i=0; i< jsonArray.length();i++) {
    				JSONObject jsonObj = jsonArray.getJSONObject(i);
    				Actividad Actividad = crearActividadDesdeJSON(jsonObj);
    				if (Actividad != null) {
    					actividades.put(Integer.toString(Actividad.getId()), Actividad);
    				}
    			} 
    		}
    		} catch (IOException e){
				e.printStackTrace();
    		}
		return null;
    	}
    
    public static Actividad crearActividadDesdeJSON(JSONObject actividadObj) {
    	String tipo = actividadObj.getString("tipo");
    	int id = actividadObj.getInt("id");
    	String titulo = actividadObj.getString("titulo");
    	String descripcion = actividadObj.getString("descripcion");
    	String objetivo = actividadObj.getString("nivelDificultad");
    	String duracionEsperada = actividadObj.getString("duracionEsperada");
    	String nivelDificultad = actividadObj.getString("nivelDificultad");
    	String profesor = actividadObj.getString("creador");
    	JSONArray actividadesPreviasSugeridas = actividadObj.getJSONArray("actividadesPreviasSugeridas");
    	String fechaLimite = actividadObj.getString("fechaLimite");
    	boolean obligatoria = actividadObj.getBoolean("obligatoria");
    	JSONArray prerequisitos = actividadObj.getJSONArray("Prerequisitos");
    	JSONArray actividadesSeguimientoRecomendadas = actividadObj.getJSONArray("actividadesSeguimientoRecomendadas");
    	
    	
    	Actividad actividad = null;
    	switch (tipo) {
    		case "Quiz":
    			actividad = new Quiz(id,titulo,descripcion,objetivo,duracionEsperada, actividadesPreviasSugeridas,fechaLimite,
    					obligatoria, profesor ,prerequisitos,actividadesSeguimientoRecomendadas);
    			break;
    		case "Encuesta":
    			actividad = new Encuesta(id,titulo,descripcion,objetivo,nivelDificultad, duracionEsperada,actividadesPreviasSugeridas,fechaLimite,
    					obligatoria, profesor profesor,prerequisitos,actividadesSeguimientoRecomendadas);
    			break;
    		case "Examen":
    			actividad = new Examen(id,titulo,descripcion,objetivo,duracionEsperada, actividadesPreviasSugeridas,fechaLimite,
    					obligatoria, profesor,prerequisitos,actividadesSeguimientoRecomendadas);
    			break;
    		case "RecursoEducativo":
    			actividad = new RecursoEducativo(id,titulo,descripcion,objetivo,duracionEsperada, actividadesPreviasSugeridas,fechaLimite,
    					obligatoria, profesor,prerequisitos,actividadesSeguimientoRecomendadas);
    			break;
    		case "Tarea":
    			actividad = new Tarea (id,titulo,descripcion,objetivo,duracionEsperada, actividadesPreviasSugeridas,fechaLimite,
    					obligatoria, profesor,prerequisitos,actividadesSeguimientoRecomendadas);
    			break;
    	}
    	return actividad;
    } 
    
    public void guardarActividadesEnArchivo(List<Actividad> todasLasActividades) throws Exception {
    	try {
    		JSONArray jsonArray = new JSONArray();
    		for (Actividad actividad : actividades.values()) {
    			jsonArray.put(convertirActividadJSON(actividad));
				
    		}
    		File archivo = new File(RUTA_DIRECTORIO, ARCHIVO_ACTIVIDADES);
    		try (FileWriter fileWriter = new FileWriter(archivo)){
    			fileWriter.write(jsonArray.toString());
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

	public static JSONObject convertirActividadJSON(Actividad actividad) throws Exception{
		try {
			
			if (actividad instanceof Quiz) {
				return convertirQuizJSON((Quiz) actividad);
			} else if (actividad instanceof Encuesta) {
				return convertirEncuestaJSON((Encuesta) actividad);
			} else if (actividad instanceof Examen) {
				return convertirExamenJSON((Examen) actividad);
			} else if (actividad instanceof RecursoEducativo) {
				return convertirRecursoEducativoJSON((RecursoEducativo) actividad);
			} else if (actividad instanceof Tarea) {
				return convertirTareaJSON((Tarea) actividad);
			}
			return new JSONObject();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
}

	
	private static JSONObject convertirQuizJSON(Quiz quiz) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("tipo", "Quiz");
        jsonObj.put("id", quiz.getId());
        jsonObj.put("titulo", quiz.getTituloActividad());
        jsonObj.put("descripcion", quiz.getDescripcion());
        jsonObj.put("objetivo", quiz.getObjetivo());
        jsonObj.put("nivelDificultad", quiz.getNivelDificultad());
        jsonObj.put("duracionEsperada", quiz.getDuracionEsperada());
        jsonObj.put("fechaLimite", quiz.getFechaLimite());
        jsonObj.put("obligatoria", quiz.isObligatoria());
        jsonObj.put("preguntasMultiples", new JSONArray(quiz.getPreguntasMultiples()));
        jsonObj.put("preguntasVF", new JSONArray(quiz.getPreguntasVF()));
        jsonObj.put("calificacionMinima", quiz.getCalificacionMinima());
        jsonObj.put("feedbacks", new JSONArray(quiz.getFeedbacks()));
        return jsonObj;
    }
	
	private static JSONObject convertirEncuestaJSON(Encuesta encuesta) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("tipo", "Encuesta");
        jsonObj.put("id", encuesta.getId());
        jsonObj.put("titulo", encuesta.getTituloActividad());
        jsonObj.put("descripcion", encuesta.getDescripcion());
        jsonObj.put("objetivo", encuesta.getObjetivo());
        jsonObj.put("nivelDificultad", encuesta.getNivelDificultad());
        jsonObj.put("duracionEsperada", encuesta.getDuracionEsperada());
        jsonObj.put("fechaLimite", encuesta.getFechaLimite());
        jsonObj.put("obligatoria", encuesta.isObligatoria());
        jsonObj.put("preguntasAbiertas", new JSONArray(encuesta.getPreguntasAbiertas()));
        jsonObj.put("feedbacks", new JSONArray(encuesta.getFeedbacks()));
        return jsonObj;
    }
	
	private static JSONObject convertirExamenJSON(Examen examen) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("tipo", "Examen");
        jsonObj.put("id", examen.getId());
        jsonObj.put("titulo", examen.getTituloActividad());
        jsonObj.put("descripcion", examen.getDescripcion());
        jsonObj.put("objetivo", examen.getObjetivo());
        jsonObj.put("nivelDificultad", examen.getNivelDificultad());
        jsonObj.put("duracionEsperada", examen.getDuracionEsperada());
        jsonObj.put("fechaLimite", examen.getFechaLimite());
        jsonObj.put("obligatoria", examen.isObligatoria());
        jsonObj.put("preguntasAbiertas", new JSONArray(examen.getPreguntasAbiertas()));
        return jsonObj;
	    }
	
	private static JSONObject convertirRecursoEducativoJSON(RecursoEducativo recurso) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("tipo", "RecursoEducativo");
        jsonObj.put("id", recurso.getId());
        jsonObj.put("titulo", recurso.getTituloActividad());
        jsonObj.put("descripcion", recurso.getDescripcion());
        jsonObj.put("objetivo", recurso.getObjetivo());
        jsonObj.put("nivelDificultad", recurso.getNivelDificultad());
        jsonObj.put("duracionEsperada", recurso.getDuracionEsperada());
        jsonObj.put("fechaLimite", recurso.getFechaLimite());
        jsonObj.put("obligatoria", recurso.isObligatoria());
        jsonObj.put("tipoRecurso", recurso.getTipoRecurso());
        jsonObj.put("recurso", recurso.recurso);
        return jsonObj;
    }
	 
	private static JSONObject convertirTareaJSON(Tarea tarea) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("tipo", "Tarea");
        jsonObj.put("id", tarea.getId());
        jsonObj.put("titulo", tarea.getTituloActividad());
        jsonObj.put("descripcion", tarea.getDescripcion());
        jsonObj.put("objetivo", tarea.getObjetivo());
        jsonObj.put("nivelDificultad", tarea.getNivelDificultad());
        jsonObj.put("duracionEsperada", tarea.getDuracionEsperada());
        jsonObj.put("fechaLimite", tarea.getFechaLimite());
        jsonObj.put("obligatoria", tarea.isObligatoria());
        jsonObj.put("estado", tarea.getEstado());
        jsonObj.put("medioEntrega", tarea.getMedioEntrega());
        return jsonObj;
	    }
}
