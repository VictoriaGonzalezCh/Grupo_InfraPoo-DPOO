package uniandes.dpoo.sistema;

import javax.swing.*;
import uniandes.dpoo.usuario.Estudiante;
import uniandes.dpoo.usuario.ProfesorCreador;
import uniandes.dpoo.usuario.ProfesorSeguimiento;
import uniandes.dpoo.usuario.Usuario;

class InterfazLogin extends JFrame {
    private Usuario usuarioLogueado; // Variable para almacenar el usuario logueado

    public InterfazLogin(Sistema sistema, JFrame ventanaAnterior) {
        setTitle("Iniciar Sesión");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField txtUsuario = new JTextField(20);
        JPasswordField txtContrasena = new JPasswordField(20);

        panel.add(new JLabel("Usuario:"));
        panel.add(txtUsuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContrasena);

        JButton btnLogin = new JButton("Iniciar Sesión");
        JButton btnVolver = new JButton("Volver");

        panel.add(Box.createVerticalStrut(10)); // Espaciado
        panel.add(btnLogin);
        panel.add(Box.createVerticalStrut(5)); // Espaciado
        panel.add(btnVolver);

        // Listener para el botón de login
        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String contrasena = new String(txtContrasena.getPassword());

            usuarioLogueado = sistema.login(usuario, contrasena);
            if (usuarioLogueado != null) {
                JOptionPane.showMessageDialog(null, "Bienvenido, " + usuarioLogueado.getLogin());
                // Abrir menú correspondiente
                if (usuarioLogueado instanceof ProfesorCreador) {
                    new InterfazProfesorCreador((ProfesorCreador) usuarioLogueado, sistema);
                } else if (usuarioLogueado instanceof ProfesorSeguimiento) {
                    // new InterfazProfesorSeguimiento((ProfesorSeguimiento) usuarioLogueado, sistema);
                } else if (usuarioLogueado instanceof Estudiante) {
                    // new InterfazEstudiante((Estudiante) usuarioLogueado, sistema);
                }
                dispose(); // Cerrar ventana actual
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", 
                                              "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Listener para el botón "Volver"
        btnVolver.addActionListener(e -> {
            if (ventanaAnterior != null) {
                ventanaAnterior.setVisible(true); // Mostrar la ventana anterior
            }
            dispose(); // Cerrar esta ventana
        });

        add(panel);
        setVisible(true);
    }
}