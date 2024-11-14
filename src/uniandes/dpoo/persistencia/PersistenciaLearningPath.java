package uniandes.dpoo.persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.persistencia.*;
import uniandes.dpoo.learningpath.*;
import uniandes.dpoo.usuario.Estudiante;

import org.json.JSONArray;
import org.json.JSONObject;

public class PersistenciaLearningPath {
	private static final String RUTA_DIRECTORIO = "data";
	private static final String ARCHIVO_LEARNING_PATHS = "learning_paths.json";
	private Map<String, LearningPath> learningPaths;
	
	public PersistenciaLearningPath() {
		learningPaths = new HashMap<>();
		cargarLearningPathsDesdeArchivo();
	}

	private void cargarLearningPathsDesdeArchivo() {
		try {
			File archivo = new File(RUTA_DIRECTORIO,ARCHIVO_LEARNING_PATHS);
			if (archivo.exists()) {
				String contenido = new String(Files.readAllBytes(Paths.get(archivo.getPath())));
				JSONArray jsonArray = new JSONArray(contenido);
				for (int i=0; i < jsonArray.length(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					LearningPath learningPath = convertirDesdeJSON(jsonObj);
					if (learningPath != null) {
						learningPaths.put(String.valueOf(learningPath.getId()), learningPath);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private LearningPath convertirDesdeJSON(JSONObject jsonObj) {
		int id = jsonObj.getInt("id");
		String titulo = jsonObj.getString("titulo");
		String descripcionContenido = jsonObj.getString("descripcionContenido");
		String descripcionObjetivo = jsonObj.getString("descripcionObjetivo");
		String nivelDificultad = jsonObj.getString("nivelDificultad");
		Double duracionMinutos = jsonObj.getDouble("duracionMinutos");
		String rating = jsonObj.getString("rating");
		int version = jsonObj.getInt("version");		
			
		LearningPath learningPath = new LearningPath(id, titulo, descripcionContenido,descripcionObjetivo,nivelDificultad,rating);
		learningPath.setDuracionMinutos();
		learningPath.setFechaCreacion();
		learningPath.setFechaModificacion();
		learningPath.setVersion();
		
		JSONArray actividadesArray = jsonObj.getJSONArray("listaActividades");
		for (int j=0; j < actividadesArray.length(); j++) {
			JSONObject actividadObj = actividadesArray.getJSONObject(j);
			Actividad actividad = null;
			try {
				actividad = PersistenciaActividades.crearActividadDesdeJSON(actividadObj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (actividad != null) {
				learningPath.agregarActividad(actividad);
			}
		}
		
		return learningPath;		
	}
	
	private JSONObject convertirAJSON(LearningPath learningPath) throws Exception {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", learningPath.getId());
		jsonObj.put("titulo", learningPath.getTitulo());
		jsonObj.put("descripcionContenido", learningPath.getDescripcionContenido());
		jsonObj.put("descripcionObjetivo", learningPath.getDescripcionObjetivo());
		jsonObj.put("nivelDificultad", learningPath.getNivelDificultad());
		jsonObj.put("duracionMinutos", learningPath.getDuracionMinutos());
		jsonObj.put("rating", learningPath.getRating());
		jsonObj.put("fechaCreacion", learningPath.getFechaCreacion());
		jsonObj.put("fechaModificacion", learningPath.getFechaModificacion());
		jsonObj.put("version", learningPath.getVersion());
		
		JSONArray actividadesArray = new JSONArray();
		for (Actividad actividad : learningPath.getActividades()) {
			actividadesArray.put(PersistenciaActividades.convertirActividadJSON(actividad));
		}
		jsonObj.put("actividades", actividadesArray);
		
		return jsonObj;
	}
	
}
