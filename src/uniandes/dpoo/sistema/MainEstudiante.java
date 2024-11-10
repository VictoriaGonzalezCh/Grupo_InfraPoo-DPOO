package uniandes.dpoo.sistema;

import java.io.IOException;

public class MainEstudiante {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Consola consola = new Consola();
        // Aquí se hace el login para un estudiante (puedes configurar esto si es necesario)
        consola.loginComoEstudiante();  // Implementa este método en Consola para login directo
    }
}