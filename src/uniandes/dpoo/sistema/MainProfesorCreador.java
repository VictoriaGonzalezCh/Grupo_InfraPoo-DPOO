package uniandes.dpoo.sistema;

import java.io.IOException;

public class MainProfesorCreador {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Consola consola = new Consola();
        // Aquí se hace el login para un profesor (puedes configurar esto si es necesario)
        consola.loginComoProfesorCreador();  // Implementa este método en Consola para login directo
    }
}