package uniandes.dpoo.sistema;

import javax.swing.*;
import uniandes.dpoo.usuario.Estudiante;
import uniandes.dpoo.usuario.ProfesorCreador;
import uniandes.dpoo.usuario.ProfesorSeguimiento;

import java.awt.*;
import java.awt.event.ActionEvent;

public class InterfazRegistroUsuarios extends JFrame {

    private Sistema sistema;
    private JFrame ventanaPrincipal; // Referencia a la ventana principal

    public InterfazRegistroUsuarios(Sistema sistema, JFrame ventanaPrincipal) {
        this.sistema = sistema;
        this.ventanaPrincipal = ventanaPrincipal; // Guardamos la referencia de la ventana principal
        
        setTitle("Registro de Usuarios");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1)); // Diseño de la ventana (4 filas)

        // Botones para registrar diferentes tipos de usuarios
        JButton btnRegistrarProfesorCreador = new JButton("Registrar Profesor Creador");
        JButton btnRegistrarProfesorSeguimiento = new JButton("Registrar Profesor Seguimiento");
        JButton btnRegistrarEstudiante = new JButton("Registrar Estudiante");
        JButton btnVolver = new JButton("Volver a la Ventana Principal");
        
        // Añadimos botones a la ventana
        add(btnRegistrarProfesorCreador);
        add(btnRegistrarProfesorSeguimiento);
        add(btnRegistrarEstudiante);
        add(btnVolver);
        
        // Acción para registrar un Profesor Creador
        btnRegistrarProfesorCreador.addActionListener((ActionEvent e) -> {
            mostrarFormularioRegistro("ProfesorCreador");
        });

        // Acción para registrar un Profesor de Seguimiento
        btnRegistrarProfesorSeguimiento.addActionListener((ActionEvent e) -> {
            mostrarFormularioRegistro("ProfesorSeguimiento");
        });

        // Acción para registrar un Estudiante
        btnRegistrarEstudiante.addActionListener((ActionEvent e) -> {
            mostrarFormularioRegistro("Estudiante");
        });

        // Acción para volver a la ventana principal
        btnVolver.addActionListener((ActionEvent e) -> {
            ventanaPrincipal.setVisible(true); // Volver a mostrar la ventana principal
            dispose(); // Cerrar la ventana de registro
        });
        
        setVisible(true);
    }

    // Método para mostrar el formulario de registro
    private void mostrarFormularioRegistro(String tipoUsuario) {
        JFrame formulario = new JFrame("Registrar " + tipoUsuario);
        formulario.setSize(400, 300);
        formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formulario.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Campos para login y contraseña
        JTextField txtLogin = new JTextField(20);
        JPasswordField txtContraseña = new JPasswordField(20);

        // Etiquetas y campos
        panel.add(new JLabel("Login:"));
        panel.add(txtLogin);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContraseña);

        JButton btnRegistrar = new JButton("Registrar");
        panel.add(Box.createVerticalStrut(10)); // Espaciado
        panel.add(btnRegistrar);
        
        // Botón para volver a la Interfaz Principal
        JButton btnVolver = new JButton("Volver a la Interfaz Principal");
        panel.add(Box.createVerticalStrut(10)); // Espaciado
        panel.add(btnVolver);

        // Acción al pulsar el botón "Registrar"
        btnRegistrar.addActionListener((ActionEvent e) -> {
            String login = txtLogin.getText();
            String contraseña = new String(txtContraseña.getPassword());

            // Validación de campos
            if (login.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(formulario, "Todos los campos son obligatorios.", 
                                              "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar login único
            if (!sistema.loginUsuariosNoRepetidos(login)) {
                JOptionPane.showMessageDialog(formulario, "El login ya está registrado.", 
                                              "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Registrar según el tipo de usuario
            int id = Sistema.generarIDUnicoUsuarios();
            switch (tipoUsuario) {
                case "ProfesorCreador":
                    ProfesorCreador profesorCreador = new ProfesorCreador(id, login, contraseña);
                    sistema.registrarUsuario(profesorCreador);
                    break;
                case "ProfesorSeguimiento":
                    ProfesorSeguimiento profesorSeguimiento = new ProfesorSeguimiento(id, login, contraseña);
                    sistema.registrarUsuario(profesorSeguimiento);
                    break;
                case "Estudiante":
                    Estudiante estudiante = new Estudiante(id, login, contraseña);
                    sistema.registrarUsuario(estudiante);
                    break;
            }

            // Mensaje de éxito
            JOptionPane.showMessageDialog(formulario, tipoUsuario + " registrado exitosamente.\nID del usuario: " + id);
            formulario.dispose(); // Cerrar formulario de registro
        });
        
        // Acción al pulsar el botón "Volver a la Interfaz Principal"
        btnVolver.addActionListener((ActionEvent e) -> {
            ventanaPrincipal.setVisible(true); // Mostrar la ventana principal
            formulario.dispose(); // Cerrar el formulario actual
        });

        formulario.add(panel);
        formulario.setVisible(true);
    }

}