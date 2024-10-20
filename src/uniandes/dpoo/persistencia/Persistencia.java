package uniandes.dpoo.persistencia;

import java.io.*;

public class Persistencia {

    public static void guardarObjeto(Object obj, String nombreArchivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(obj);
        } catch (IOException e) {
        	System.err.println("Error al guardar el objeto en "+ nombreArchivo+ ": "+e.getMessage());
        	e.printStackTrace();
        }
    }

    public static Object cargarObjeto(String nombreArchivo) throws IOException, ClassNotFoundException {
        Object obj = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            obj = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
        	System.err.println("Error al cargar el objeto desde "+nombreArchivo+": "+ e.getMessage());
        	e.printStackTrace();
        }
        return obj;
    }
}
