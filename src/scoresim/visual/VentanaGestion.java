package scoresim.visual;

import scoresim.ConexionBD;
import scoresim.EquipoDAO;
import scoresim.ScoreSimApp;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Ventana principal de gestión de jugadores.
 * Permite filtrar, buscar, editar estadísticas y lanzar simulaciones masivas.
 */
public class VentanaGestion extends JFrame {
    // Componentes de la interfaz
    private JTable tablaJugadores;
    private DefaultTableModel modelo;
    private TableRowSorter<DefaultTableModel> sorter; // Permite ordenar y filtrar la tabla visualmente
    private JTextField txtMedia, txtDorsal;
    private JComboBox<String> comboRol;
    private JLabel lblJugadorSeleccionado;
    private JComboBox<String> comboPaises;
    private JComboBox<String> comboPosicion;

    // Almacena el ID del jugador que estamos editando actualmente
    private int idSeleccionado = -1;

    public VentanaGestion() {
        // Configuración básica de la ventana (JFrame)
        setTitle("ScoreSim 2026 - Panel de Control");
        setSize(1100, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- PANEL SUPERIOR: FILTROS Y BUSCADOR ---
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Combo de Países: Carga las nacionalidades únicas desde la DB
        panelFiltro.add(new JLabel("Nacionalidad:"));
        comboPaises = new JComboBox<>(cargarPaisesDesdeDB());
        panelFiltro.add(comboPaises);

        // Combo de Línea: Filtra por zona del campo (Ataque, Medio, etc.)
        panelFiltro.add(new JLabel(" Línea:"));
        String[] lineas = {"TODAS", "ATAQUE", "MEDIO", "DEFENSA", "PORTERO"};
        comboPosicion = new JComboBox<>(lineas);
        panelFiltro.add(comboPosicion);

        // Buscador de Texto: Filtro dinámico por nombre/apellido
        panelFiltro.add(new JLabel("   🔍 Buscar:"));
        JTextField txtBuscador = new JTextField(12);
        panelFiltro.add(txtBuscador);

        // Escuchadores de eventos para los combos (actualizan la tabla al cambiar)
        comboPaises.addActionListener(e -> filtrar());
        comboPosicion.addActionListener(e -> filtrar());

        add(panelFiltro, BorderLayout.NORTH);

        // --- PANEL CENTRAL: TABLA DE DATOS ---
        // Definición de columnas del modelo
        modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellido", "País", "OVR", "Dorsal", "Posición", "Rol"}, 0);
        tablaJugadores = new JTable(modelo);

        // Inicialización del Sorter: Permite que JTable ordene al hacer clic en cabeceras
        sorter = new TableRowSorter<>(modelo);
        tablaJugadores.setRowSorter(sorter);

        // Carga inicial de datos
        actualizarTabla("TODOS", "TODAS");
        EstiloUI.prepararTabla(tablaJugadores); // <--- Línea añadida
        add(new JScrollPane(tablaJugadores), BorderLayout.CENTER);

        // Renderizador de Celdas: Cambia el color/estilo de la fila según el ROL del jugador
        tablaJugadores.getColumnModel().getColumn(7).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if ("TITULAR".equals(value)) {
                    c.setFont(c.getFont().deriveFont(Font.BOLD)); // Negrita para titulares
                    c.setForeground(new Color(0, 102, 204));     // Azul
                } else if ("DESCONVOCADO".equals(value)) {
                    c.setForeground(Color.GRAY);                 // Gris para no convocados
                } else {
                    c.setForeground(Color.BLACK);                // Negro para el resto
                }
                return c;
            }
        });

        // Evento del Buscador: Filtra el modelo de la tabla según el texto ingresado
        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String texto = txtBuscador.getText();
                if (texto.length() == 0) {
                    sorter.setRowFilter(null); // Quita el filtro si el buscador está vacío
                } else {
                    // Filtra ignorando mayúsculas (?i) buscando en columnas 1 (Nombre) y 2 (Apellido)
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1, 2));
                }
            }
        });

        // --- PANEL INFERIOR: FORMULARIO DE EDICIÓN Y BOTONES ---
        JPanel panelInferior = new JPanel(new GridLayout(2, 1));

        // Fila 1: Campos para editar estadísticas del jugador seleccionado
        JPanel panelEdicion = new JPanel();
        lblJugadorSeleccionado = new JLabel("Selecciona un jugador: ");
        panelEdicion.add(lblJugadorSeleccionado);

        panelEdicion.add(new JLabel("OVR:"));
        txtMedia = new JTextField(3);
        panelEdicion.add(txtMedia);

        panelEdicion.add(new JLabel("Dorsal:"));
        txtDorsal = new JTextField(3);
        panelEdicion.add(txtDorsal);

        panelEdicion.add(new JLabel("Rol:"));
        String[] opcionesRol = {"TITULAR", "SUPLENTE", "RESERVA", "DESCONVOCADO"};
        comboRol = new JComboBox<>(opcionesRol);
        panelEdicion.add(comboRol);

        // Botón Guardar: Actualiza los datos en la base de datos
        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBackground(new Color(52, 152, 219));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setOpaque(true);
        btnGuardar.setContentAreaFilled(true);
        btnGuardar.setBorderPainted(false);
        panelEdicion.add(btnGuardar);

        // Fila 2: Botón de Simulación Masiva
        JPanel panelAcciones = new JPanel();
        JButton btnSimular = new JButton("🚀 LANZAR 10.000 SIMULACIONES");
        btnSimular.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSimular.setForeground(Color.WHITE);
        btnSimular.setBackground(new Color(46, 204, 113));
        btnSimular.setOpaque(true);
        btnSimular.setContentAreaFilled(true);
        btnSimular.setBorderPainted(false);
        panelAcciones.add(btnSimular);

        panelInferior.add(panelEdicion);
        panelInferior.add(panelAcciones);
        add(panelInferior, BorderLayout.SOUTH);

        // ============================================================
        // === AQUÍ VAN LOS ESTILOS DE EstiloUI ===
        // ============================================================

        // Aplicar estilo a los campos de texto
        EstiloUI.prepararCampoTexto(txtMedia);
        EstiloUI.prepararCampoTexto(txtDorsal);

        // Aplicar estilo a los botones
        EstiloUI.prepararBoton(btnGuardar, EstiloUI.AZUL_PRIMARY, EstiloUI.AZUL_HOVER);
        EstiloUI.prepararBoton(btnSimular, EstiloUI.VERDE_SUCCESS, EstiloUI.VERDE_HOVER);
        // --- GESTIÓN DE EVENTOS DE SELECCIÓN ---

        // Al hacer clic en una fila de la tabla, cargamos los datos en el formulario inferior
        tablaJugadores.getSelectionModel().addListSelectionListener(e -> {
            int filaVisual = tablaJugadores.getSelectedRow();
            if (filaVisual != -1) {
                // IMPORTANTE: convertRowIndexToModel mapea el índice visual al índice real del modelo
                // (Necesario si la tabla está ordenada o filtrada)
                int modeloFila = tablaJugadores.convertRowIndexToModel(filaVisual);
                idSeleccionado = (int) modelo.getValueAt(modeloFila, 0);
                lblJugadorSeleccionado.setText("Editando: " + modelo.getValueAt(modeloFila, 1) + " " + modelo.getValueAt(modeloFila, 2));
                txtMedia.setText(modelo.getValueAt(modeloFila, 4).toString());
                txtDorsal.setText(modelo.getValueAt(modeloFila, 5).toString());
                comboRol.setSelectedItem(modelo.getValueAt(modeloFila, 7).toString());
            }
        });

        // Acción de Guardar: Valida y envía cambios a la base de datos
        btnGuardar.addActionListener(e -> {
            if (idSeleccionado != -1) {
                actualizarJugador(idSeleccionado, txtMedia.getText(), txtDorsal.getText(), (String) comboRol.getSelectedItem());
                filtrar(); // Refresca la tabla visualmente
                EquipoDAO.limpiarCache(); // Limpia el caché para que el simulador vea los nuevos valores
            }
        });

        // Acción de Simular: Lanza el hilo de simulación masiva
        btnSimular.addActionListener(e -> {
            // Desactivamos el botón para evitar clics dobles
            btnSimular.setEnabled(false);

            new SwingWorker<Map<String, scoresim.EstadisticasEquipo>, Void>() {
                @Override
                protected Map<String, scoresim.EstadisticasEquipo> doInBackground() {
                    // Ejecuta los 10,000 mundiales (Montecarlo)
                    return scoresim.ScoreSimApp.ejecutarSimulacionDesdeUI();
                }

                @Override
                protected void done() {
                    try {
                        Map<String, scoresim.EstadisticasEquipo> historial = get();

                        // Generamos una crónica textual del Mundial #10,001 para el reporte
                        scoresim.torneo.Mundial2026 mundialLógica = new scoresim.torneo.Mundial2026();
                        List<scoresim.torneo.GrupoMundial> grupos = scoresim.torneo.Mundial2026.crearMundialOficial();

                        // Esto genera el String que espera tu VentanaReporte
                        String cronicaFinal = mundialLógica.ejecutarSegundaFase(grupos);

                        // Abrimos la ventana con: Historial, Total (10000) y la Crónica
                        new scoresim.visual.VentanaReporte(historial, 10000, cronicaFinal).setVisible(true);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        btnSimular.setEnabled(true);
                    }
                }
            }.execute();
        });

        setLocationRelativeTo(null); // Centra la ventana en pantalla
        setVisible(true);
    }

    /**
     * Consulta las nacionalidades únicas en la DB para llenar el JComboBox de países.
     */
    private String[] cargarPaisesDesdeDB() {
        List<String> paises = new ArrayList<>();
        paises.add("TODOS");
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT DISTINCT nacionalidad FROM jugadores ORDER BY nacionalidad ASC")) {
            while (rs.next()) paises.add(rs.getString("nacionalidad"));
        } catch (SQLException ex) { ex.printStackTrace(); }
        return paises.toArray(new String[0]);
    }

    /**
     * Captura los valores de los filtros visuales y llama a la actualización de datos.
     */
    private void filtrar() {
        String pais = (String) comboPaises.getSelectedItem();
        String pos = (String) comboPosicion.getSelectedItem();
        actualizarTabla(pais, pos);
    }

    /**
     * Consulta la base de datos con filtros específicos y repuebla el modelo de la tabla.
     */
    private void actualizarTabla(String filtroPais, String filtroCategoria) {
        modelo.setRowCount(0); // Limpia la tabla actual
        StringBuilder sql = new StringBuilder("SELECT id, nombre, apellido, nacionalidad, valoracion_global, dorsal, posicion, rol FROM jugadores WHERE 1=1");

        // Construcción dinámica de la consulta SQL
        if (filtroPais != null && !filtroPais.equals("TODOS")) {
            sql.append(" AND nacionalidad = '").append(filtroPais).append("'");
        }

        if (filtroCategoria != null && !filtroCategoria.equals("TODAS")) {
            switch (filtroCategoria) {
                case "ATAQUE" -> sql.append(" AND posicion IN ('DC', 'EI', 'ED', 'SD')");
                case "MEDIO" -> sql.append(" AND posicion IN ('MC', 'MCO', 'MCD', 'MI', 'MD')");
                case "DEFENSA" -> sql.append(" AND posicion IN ('DFC', 'LI', 'LD', 'CAI', 'CAD')");
                case "PORTERO" -> sql.append(" AND posicion IN ('POR', 'PO')");
            }
        }

        // Ordenamos por Nacionalidad, luego Titulares arriba y por Valoración de mayor a menor
        sql.append(" ORDER BY nacionalidad ASC, FIELD(rol, 'TITULAR', 'SUPLENTE', 'RESERVA', 'DESCONVOCADO'), valoracion_global DESC");

        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {
            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"),
                        rs.getString("nacionalidad"), rs.getInt("valoracion_global"),
                        rs.getInt("dorsal"), rs.getString("posicion"), rs.getString("rol")
                });
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    /**
     * Ejecuta un UPDATE en la base de datos para modificar las estadísticas de un jugador.
     */
    private void actualizarJugador(int id, String media, String dorsal, String rol) {
        try {
            // Conversión de tipos y validación de rango FIFA/FM
            int ovrInt = Integer.parseInt(media);
            int dorsalInt = Integer.parseInt(dorsal);

            if (ovrInt < 0 || ovrInt > 99) {
                JOptionPane.showMessageDialog(this, "El OVR debe estar entre 0 y 99");
                return;
            }

            String sql = "UPDATE jugadores SET valoracion_global = ?, dorsal = ?, rol = ? WHERE id = ?";
            try (Connection conn = ConexionBD.conectar();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, ovrInt);
                pstmt.setInt(2, dorsalInt);
                pstmt.setString(3, rol);
                pstmt.setInt(4, id);
                pstmt.executeUpdate();
                System.out.println("✅ Jugador " + id + " actualizado en DB.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "OVR y Dorsal deben ser números válidos.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}