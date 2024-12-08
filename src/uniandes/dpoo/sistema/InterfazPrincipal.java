package uniandes.dpoo.sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InterfazPrincipal extends JFrame {

    private Sistema sistema; // Referencia al sistema principal

    public InterfazPrincipal(Sistema sistema) {
        this.sistema = sistema;

        // Configuración de la ventana principal
        setTitle("Sistema de Learning Path");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el panel principal con opciones
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10)); // Cambié el GridLayout para ajustarlo mejor al diseño

        // Título en la parte superior
        JLabel lblTitulo = new JLabel("LearningPaths", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(lblTitulo);

        // Botones principales
        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnRegistrar = new JButton("Registrar Usuario");

        // Estilo de los botones (opcional, para que se vean más estilizados)
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 14));
        btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 14));

        // Cambiar el tamaño de los botones para que sean más pequeños
        btnLogin.setPreferredSize(new Dimension(200, 30)); // Botón más pequeño
        btnRegistrar.setPreferredSize(new Dimension(200, 30)); // Botón más pequeño

        // Agregar listeners a los botones
        btnLogin.addActionListener(e -> mostrarLogin());
        btnRegistrar.addActionListener(e -> mostrarFormularioRegistro());

        // Agregar botones al panel
        mainPanel.add(btnLogin);
        mainPanel.add(btnRegistrar);

        // Agregar el panel principal a la ventana
        add(mainPanel);

        setVisible(true); // Mostrar ventana
    }

    // Método para mostrar el formulario de login
    private void mostrarLogin() {
        new InterfazLogin(sistema, this);
        dispose(); // Cerrar la ventana actual
    }

    // Método para mostrar el formulario de registro
    private void mostrarFormularioRegistro() {
        new InterfazRegistroUsuarios(sistema, this);
        dispose(); // Cerrar la ventana actual
    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema(); // Instancia del sistema
        new InterfazPrincipal(sistema); // Crear y mostrar la ventana principal
    }
}