package uniandes.dpoo.sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uniandes.dpoo.learningpath.Actividad;
import uniandes.dpoo.learningpath.LearningPath;
import uniandes.dpoo.learningpath.ProgresoEstudiante;
import uniandes.dpoo.usuario.Estudiante;
import uniandes.dpoo.usuario.ProfesorCreador;
import uniandes.dpoo.usuario.ProfesorSeguimiento;
import uniandes.dpoo.usuario.Usuario;
import uniandes.dpoo.sistema.Sistema;




@SuppressWarnings("serial")
public class InterfazProfesorCreador extends JFrame {
	
	Sistema sistema = new Sistema();
	private ProfesorCreador profesor;

	public InterfazProfesorCreador(ProfesorCreador profesor, Sistema sistema) {
        this.profesor = profesor; // Guardar la referencia del profesor
		
        // Configurar la ventana principal
        setTitle("Menú Profesor Creador");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear el panel izquierdo (Perfil del profesor)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaciado interno

        // Imagen del profesor
        JLabel lblImagenProfesor = new JLabel();
        lblImagenProfesor.setIcon(new ImageIcon("ruta_a_imagen.png")); // Cambiar a la ruta de tu imagen
        lblImagenProfesor.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(lblImagenProfesor);

        // Nombre del profesor
        String nombre = profesor.getLogin();
        JLabel lblNombreProfesor = new JLabel("Profesor: " + nombre);
        lblNombreProfesor.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(lblNombreProfesor);

        // Desplegable de Learning Paths
        
        List<LearningPath> learningpaths = profesor.getLearningPaths();

        JComboBox<String> comboLearningPaths = new JComboBox<>();
        if (learningpaths == null || learningpaths.isEmpty()) {
            // Agregar mensaje al JComboBox si no hay LearningPaths
            comboLearningPaths.addItem("Aún no hay LearningPaths creados.");
        } else {
            // Llenar el JComboBox con los nombres de los LearningPaths
            for (LearningPath lp : learningpaths) {
                comboLearningPaths.addItem(lp.getTitulo());
            }
        }

        // Configurar dimensiones y agregar al panel izquierdo
        comboLearningPaths.setMaximumSize(new Dimension(200, 30)); // Ancho fijo
        leftPanel.add(Box.createVerticalStrut(10)); // Espaciado
        leftPanel.add(new JLabel("Learning Paths creados:"));
        leftPanel.add(comboLearningPaths);
       
        comboLearningPaths.addActionListener(e -> {
            String selectedPath = (String) comboLearningPaths.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Seleccionaste: " + selectedPath);
        });

        // Crear el panel derecho (Botones del menú)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(8, 1, 10, 10)); // 8 filas, 1 columna, espaciado de 10px

        // Crear los botones
        JButton btnCrearLearningPath = new JButton("Crear Learning Path");
        JButton btnEditarLearningPath = new JButton("Editar Learning Path");
        JButton btnCrearActividad = new JButton("Crear Nueva Actividad");
        JButton btnEditarActividad = new JButton("Editar Actividad");
        JButton btnVerReseñas = new JButton("Ver Reseñas de una Actividad");
        JButton btnVerInfoActividad = new JButton("Ver Información de una Actividad");
        JButton btnVerInfoLearningPath = new JButton("Mostrar Información de un Learning Path");
        JButton btnSalir = new JButton("Salir");

        // Agregar listeners a los botones
        btnCrearLearningPath.addActionListener(e -> crearLearningPath());
        btnEditarLearningPath.addActionListener(e -> editarLearningPath());
        btnCrearActividad.addActionListener(e -> crearActividad());
        btnEditarActividad.addActionListener(e -> editarActividad());
        btnVerReseñas.addActionListener(e -> verReseñasActividades());
        btnVerInfoActividad.addActionListener(e -> mostrarInfoActividad());
        btnVerInfoLearningPath.addActionListener(e -> mostrarInfoLearningPath());
        btnSalir.addActionListener(e -> salir());

        // Agregar botones al panel derecho
        rightPanel.add(btnCrearLearningPath);
        rightPanel.add(btnEditarLearningPath);
        rightPanel.add(btnCrearActividad);
        rightPanel.add(btnEditarActividad);
        rightPanel.add(btnVerReseñas);
        rightPanel.add(btnVerInfoActividad);
        rightPanel.add(btnVerInfoLearningPath);
        rightPanel.add(btnSalir);

        // Dividir en dos paneles con JSplitPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(200); // Configurar ancho del panel izquierdo

        // Agregar el SplitPane al marco principal
        add(splitPane);

        setVisible(true); // Hacer visible la ventana
    }
    // Métodos que se llaman al presionar los botones (puedes implementar la lógica aquí)
    private void crearActividad() {
        // Paso 1: Buscar el Learning Path por ID
    	String idLP = JOptionPane.showInputDialog(
    	        this, 
    	        "Escriba el ID del Learning Path dentro del cual quiere crear la actividad:"
    	);
    	LearningPath learningPathEncontrado = null;

    	while (learningPathEncontrado == null) {
    	    // Verificar si el usuario presionó "Cancelar" o cerró el diálogo
    	    if (idLP == null) {
    	        JOptionPane.showMessageDialog(this, "Operación cancelada.");
    	        return; // Salir de toda la operación
    	    }

    	    try {
    	        learningPathEncontrado = sistema.buscarLearningPath(Integer.parseInt(idLP));
    	        if (learningPathEncontrado == null) {
    	            idLP = JOptionPane.showInputDialog(
    	                    this, 
    	                    "ID no encontrado. Intente de nuevo:"
    	            );
    	        }
    	    } catch (NumberFormatException e) {
    	        idLP = JOptionPane.showInputDialog(
    	                this, 
    	                "Por favor, escriba un número válido:"
    	        );
    	    }
    	}

        // Paso 2: Pedir detalles de la actividad
        String[] tiposActividades = {"tarea", "quiz", "examen", "recurso educativo", "encuesta"};
        String tipoActividad = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el tipo de actividad:",
                "Tipo de Actividad",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tiposActividades,
                tiposActividades[0]
        );

        String tituloActividad = JOptionPane.showInputDialog(this, "Escriba el título de la actividad:");
        String descripcion = JOptionPane.showInputDialog(this, "Descripción de la actividad:");
        String objetivo = JOptionPane.showInputDialog(this, "Descripción del objetivo:");
        String nivelDificultad = JOptionPane.showInputDialog(this, "Nivel de dificultad (fácil, medio, difícil):");
        String duracionEsperada = JOptionPane.showInputDialog(this, "Duración esperada (en minutos):");
        String fechaLimite = JOptionPane.showInputDialog(this, "Fecha límite (formato: dd/MM/yyyy):");
        boolean obligatoria = JOptionPane.showConfirmDialog(
                this,
                "¿Es una actividad obligatoria?",
                "Obligatoriedad",
                JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION;

        // Generar ID único para la actividad
        int id = Sistema.generarIDUnicoActividades();

        // Obtener el usuario autenticado (ProfesorCreador)
        ProfesorCreador usuario = (ProfesorCreador) sistema.obtenerUsuarioAutenticado();

        // Crear listas vacías para actividades previas sugeridas y otras dependencias
        List<Actividad> actividadesPreviasSugeridas = new ArrayList<>();
        List<Actividad> prerequisitos = new ArrayList<>();
        List<Actividad> actividadesSeguimientoRecomendadas = new ArrayList<>();

		// Paso 3: Crear la nueva actividad en el sistema
        sistema.crearNuevaActividad(learningPathEncontrado, tipoActividad, id, tituloActividad, descripcion, objetivo, duracionEsperada, obligatoria, usuario,
				nivelDificultad, actividadesPreviasSugeridas, fechaLimite, prerequisitos, actividadesSeguimientoRecomendadas, null // No necesitas scanner aquí
);

        JOptionPane.showMessageDialog(this, "Actividad creada exitosamente con ID: " + id);
    }

    public void editarLearningPath() {
        String idParaEditar = JOptionPane.showInputDialog(this, "Escriba el ID del Learning Path que quiere editar:");
        if (idParaEditar == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        LearningPath learningPathEncontrado;
        try {
            learningPathEncontrado = sistema.buscarLearningPath(Integer.parseInt(idParaEditar));
            if (learningPathEncontrado == null) {
                JOptionPane.showMessageDialog(this, "No se encontró un Learning Path con el ID proporcionado.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Solicitar nuevos valores para los campos
        String nuevoTitulo = JOptionPane.showInputDialog(this, "Título nuevo del Learning Path:", learningPathEncontrado.getTitulo());
        if (nuevoTitulo == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        String nuevaDescripcionContenido = JOptionPane.showInputDialog(this, "Descripción nueva del contenido:", learningPathEncontrado.getDescripcionContenido());
        if (nuevaDescripcionContenido == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        String nuevaDescripcionObjetivo = JOptionPane.showInputDialog(this, "Descripción nueva del objetivo:", learningPathEncontrado.getDescripcionObjetivo());
        if (nuevaDescripcionObjetivo == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        String nuevoNivelDificultad = JOptionPane.showInputDialog(this, "Nuevo nivel de dificultad (por ejemplo, fácil, medio, difícil):", learningPathEncontrado.getNivelDificultad());
        if (nuevoNivelDificultad == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        String nuevoRating = JOptionPane.showInputDialog(this, "Nueva calificación inicial (rating):", learningPathEncontrado.getRating());
        if (nuevoRating == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        try {
            sistema.editarLearningPath(
                learningPathEncontrado, 
                nuevoTitulo, 
                nuevaDescripcionContenido, 
                nuevaDescripcionObjetivo, 
                nuevoNivelDificultad, 
                nuevoRating
            );
            JOptionPane.showMessageDialog(this, "El Learning Path ha sido editado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al editar el Learning Path: " + e.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void crearLearningPath() {
    	
        String titulo = JOptionPane.showInputDialog(this, "Título del Learning Path:");
        if (titulo == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        String descripcionContenido = JOptionPane.showInputDialog(this, "Descripción del contenido:");
        if (descripcionContenido == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        String descripcionObjetivo = JOptionPane.showInputDialog(this, "Descripción del objetivo:");
        if (descripcionObjetivo == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        String nivelDificultad = JOptionPane.showInputDialog(this, "Nivel de dificultad (por ejemplo, fácil, medio, difícil):");
        if (nivelDificultad == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        String rating = JOptionPane.showInputDialog(this, "Calificación inicial (rating):");
        if (rating == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        try {
            int id = Sistema.generarIDUnicoLearningPaths();
            sistema.crearLearningPath(id, titulo, descripcionContenido, descripcionObjetivo, nivelDificultad, rating);

            JOptionPane.showMessageDialog(this, "El Learning Path ha sido registrado exitosamente.\nID: " + id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al crear el Learning Path: " + e.getMessage(), 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editarActividad() {
        String idActividadParaEditar = JOptionPane.showInputDialog(this, "Escriba el ID de la Actividad que quiere editar:");
        if (idActividadParaEditar == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return; // Salir si el usuario cancela
        }

        Actividad actividadEncontrada;
        try {
            actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(idActividadParaEditar));
            if (actividadEncontrada == null) {
                JOptionPane.showMessageDialog(this, "No se encontró una actividad con el ID proporcionado.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] opciones = {
            "Editar la descripción de la actividad",
            "Añadir una actividad de prerrequisito o seguimiento",
            "Cancelar"
        };
        int eleccion = JOptionPane.showOptionDialog(
            this, 
            "¿Qué desea hacer?", 
            "Editar Actividad", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opciones, 
            opciones[0]
        );

        if (eleccion == 0) {
            editarDescripcionActividad(actividadEncontrada);
        } else if (eleccion == 1) {
            añadirActividadRelacionada(actividadEncontrada);
        } else {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
        }
    }

    private void editarDescripcionActividad(Actividad actividadEncontrada) {
        String nuevaDescripcion = JOptionPane.showInputDialog(this, "Descripción nueva de la actividad:", actividadEncontrada.getDescripcion());
        if (nuevaDescripcion == null) return;

        String nuevoObjetivo = JOptionPane.showInputDialog(this, "Descripción nueva del objetivo:", actividadEncontrada.getObjetivo());
        if (nuevoObjetivo == null) return;

        String nuevoNivelDificultad = JOptionPane.showInputDialog(this, "Nuevo nivel de dificultad (fácil, medio, difícil):", actividadEncontrada.getNivelDificultad());
        if (nuevoNivelDificultad == null) return;

        String nuevaDuracionEsperada = JOptionPane.showInputDialog(this, "Nueva duración esperada (en minutos):", String.valueOf(actividadEncontrada.getDuracionEsperada()));
        if (nuevaDuracionEsperada == null) return;

        String nuevaFechaLimite = JOptionPane.showInputDialog(this, "Nueva fecha límite (formato: dd/MM/yyyy):", actividadEncontrada.getFechaLimite());
        if (nuevaFechaLimite == null) return;

        int confirmarObligatoria = JOptionPane.showConfirmDialog(this, "¿Es una actividad obligatoria?", "Obligatoriedad", JOptionPane.YES_NO_OPTION);
        boolean nuevaObligatoria = (confirmarObligatoria == JOptionPane.YES_OPTION);

        try {
            List<Actividad> actividadesPreviasSugeridas = new ArrayList<>(); // Puedes adaptar esto si es necesario
            List<Actividad> prerequisitos = new ArrayList<>(); // Puedes adaptar esto si es necesario

            sistema.editarActividad(
                actividadEncontrada,
                nuevaDescripcion,
                nuevoObjetivo,
                nuevoNivelDificultad,
                nuevaDuracionEsperada,
                actividadesPreviasSugeridas,
                nuevaFechaLimite,
                nuevaObligatoria,
                prerequisitos
            );
            JOptionPane.showMessageDialog(this, "La actividad ha sido editada exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al editar la actividad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void añadirActividadRelacionada(Actividad actividadEncontrada) {
        String[] opciones = {
            "Añadir como Prerrequisito",
            "Añadir como Seguimiento",
            "Añadir como Actividad Previa",
            "Cancelar"
        };
        int tipoActividadRelacionada = JOptionPane.showOptionDialog(
            this, 
            "¿Cómo desea añadir la actividad relacionada?", 
            "Añadir Actividad Relacionada", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opciones, 
            opciones[0]
        );

        if (tipoActividadRelacionada == 3 || tipoActividadRelacionada == -1) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return;
        }

        String idActividadRelacionada = JOptionPane.showInputDialog(this, "Escriba el ID de la actividad relacionada:");
        if (idActividadRelacionada == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return;
        }

        Actividad actividadRelacionada;
        try {
            actividadRelacionada = sistema.buscarActividadPorId(Integer.parseInt(idActividadRelacionada));
            if (actividadRelacionada == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna actividad con el ID proporcionado.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (tipoActividadRelacionada) {
            case 0: // Prerrequisito
                añadirPrerequisito(actividadEncontrada, actividadRelacionada);
                break;
            case 1: // Seguimiento
                añadirActividadesSeguimiento(actividadEncontrada, actividadRelacionada);
                break;
            case 2: // Actividad previa
                añadirActividadPrevia(actividadEncontrada, actividadRelacionada);
                break;
        }
    }

    private void añadirPrerequisito(Actividad actividadEncontrada, Actividad actividadRelacionada) {
        try {
            sistema.añadirPrerequisito(actividadEncontrada, actividadRelacionada);
            JOptionPane.showMessageDialog(this, "Prerrequisito añadido exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al añadir el prerrequisito: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void añadirActividadesSeguimiento(Actividad actividadEncontrada, Actividad actividadRelacionada) {
        try {
            Consola.añadirSeguimiento(actividadEncontrada, actividadRelacionada);
            JOptionPane.showMessageDialog(this, "Seguimiento añadido exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al añadir el seguimiento: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void añadirActividadPrevia(Actividad actividadEncontrada, Actividad actividadRelacionada) {
        try {
            sistema.añadirActividadPrevia(actividadEncontrada, actividadRelacionada);
            JOptionPane.showMessageDialog(this, "Actividad previa añadida exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al añadir la actividad previa: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verReseñasActividades() {
        String id = JOptionPane.showInputDialog(this, "Escriba el ID de la actividad de la cual quiere ver la reseña:");
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return;
        }

        try {
            Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
            if (actividadEncontrada == null) {
                JOptionPane.showMessageDialog(this, "No se encontró una actividad con el ID proporcionado.");
                return;
            }

           actividadEncontrada.mostrarFeedbacks();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarInfoActividad() {
        String id = JOptionPane.showInputDialog(this, "Escriba el ID de la actividad de la cual quiere ver la información:");
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return;
        }

        try {
            Actividad actividadEncontrada = sistema.buscarActividadPorId(Integer.parseInt(id));
            if (actividadEncontrada == null) {
                JOptionPane.showMessageDialog(this, "No se encontró una actividad con el ID proporcionado.");
                return;
            }

           actividadEncontrada.mostrarInfoActividad();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarInfoLearningPath() {
        String id = JOptionPane.showInputDialog(this, "Escriba el ID del Learning Path del cual quiere ver la información:");
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
            return;
        }

        try {
            LearningPath LPEncontrado = sistema.buscarLearningPath(Integer.parseInt(id));
            if (LPEncontrado == null) {
                JOptionPane.showMessageDialog(this, "No se encontró un Learning Path con el ID proporcionado.");
                return;
            }

         // Muestra la información directamente con JOptionPane, usando el método void
            LPEncontrado.mostrarInfoLearningPath();  // Esto ahora debería mostrar la información de manera adecuada
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void salir() {
        JOptionPane.showMessageDialog(this, "Saliendo...");
        dispose(); // Cierra la ventana
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazProfesorCreador::new);
    }
}