package com.mycompany.notasremisionsanantonio.igu;
import com.mycompany.notasremisionsanantonio.persistencia.Conexion;
import com.toedter.calendar.JDateChooser;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AbonarRemision extends javax.swing.JFrame {

    private int idRemision;
    private double totalRemision;
    private JLabel labelRestante;
    private JTextField txtAbono;
    private JButton btnRegistrar;
    private PagoCompletoListener listener;

    private NotasPorCobrar ventanaNotasPorCobrar;

    private JDateChooser dateChooser; // NUEVO

    public AbonarRemision(int idRemision, NotasPorCobrar ventanaNotasPorCobrar) {
        this.idRemision = idRemision;
        this.ventanaNotasPorCobrar = ventanaNotasPorCobrar;
        this.listener = ventanaNotasPorCobrar; // compatibilidad
        initComponents();
        cargarTotalYRestante();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (ventanaNotasPorCobrar != null) {
                    ventanaNotasPorCobrar.actualizarTablaRemisiones();
                }
            }
        });
    }

    private void initComponents() {
        setTitle("Registrar Abono");
        setSize(350, 250); // un poco más alto para la fecha
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 1)); // 5 filas: monto, campo monto, fecha, restante, botón

        labelRestante = new JLabel("Restante: $0.00", SwingConstants.CENTER);
        txtAbono = new JTextField();
        btnRegistrar = new JButton("Registrar Abono");

        // Crear y configurar JDateChooser
        dateChooser = new JDateChooser();
        dateChooser.setDate(new Date()); // fecha por defecto hoy
        dateChooser.setDateFormatString("yyyy-MM-dd");

        add(new JLabel("Monto del abono:", SwingConstants.CENTER));
        add(txtAbono);
        add(new JLabel("Fecha del abono:", SwingConstants.CENTER)); // etiqueta para fecha
        add(dateChooser);  // agrega selector de fecha
        add(labelRestante);
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> registrarAbono());
    }

    private void registrarAbono() {
        try {
            double abono = Double.parseDouble(txtAbono.getText());

            if (abono <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser mayor a 0.");
                return;
            }

            Date fechaAbono = dateChooser.getDate();
            if (fechaAbono == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha válida para el abono.");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateada = sdf.format(fechaAbono);

            double abonado = 0.0;

            try (Connection conn = new Conexion().conectar()) {
                // Obtener total abonado actual
                String sqlAbonos = "SELECT SUM(monto) AS abonado FROM abono_remision WHERE id_remision = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sqlAbonos)) {
                    stmt.setInt(1, idRemision);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        abonado = rs.getDouble("abonado");
                    }
                }

                double restante = totalRemision - abonado;

                if (abono > restante) {
                    JOptionPane.showMessageDialog(this,
                            "El monto del abono excede lo que falta por pagar ($" + String.format("%.2f", restante) + ").\n" +
                                    "Por favor, ajuste el abono.");
                    return;
                }

                // Insertar abono con fecha seleccionada
                String insertSQL = "INSERT INTO abono_remision (id_remision, monto, fecha) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                    stmt.setInt(1, idRemision);
                    stmt.setDouble(2, abono);
                    stmt.setString(3, fechaFormateada); // se usa la fecha elegida
                    stmt.executeUpdate();
                }

                abonado += abono;

                if (abonado >= totalRemision) {
                    String updateSQL = "UPDATE remision SET pagada = 1 WHERE id_remision = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
                        stmt.setInt(1, idRemision);
                        stmt.executeUpdate();
                    }

                    if (listener != null) {
                        listener.onRemisionPagada(idRemision);
                    }

                    JOptionPane.showMessageDialog(this, "Remisión pagada en su totalidad.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Abono registrado.");
                    txtAbono.setText("");
                    cargarTotalYRestante(); // refresca monto restante
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar el abono: " + ex.getMessage());
        }
    }
    private void cargarTotalYRestante() {
        try (Connection conn = new Conexion().conectar()) {

            // Obtener el total de la remisión
            String sqlTotal = """
                SELECT SUM(cantidad * precio_unitario) AS total
                FROM detalle_remision
                WHERE id_remision = ?
            """;
            try (PreparedStatement stmt = conn.prepareStatement(sqlTotal)) {
                stmt.setInt(1, idRemision);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    totalRemision = rs.getDouble("total");
                }
            }

            // Obtener la suma de los abonos
            String sqlAbonos = """
                SELECT SUM(monto) AS abonado
                FROM abono_remision
                WHERE id_remision = ?
            """;
            double abonado = 0.0;
            try (PreparedStatement stmt = conn.prepareStatement(sqlAbonos)) {
                stmt.setInt(1, idRemision);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    abonado = rs.getDouble("abonado");
                }
            }

            double restante = totalRemision - abonado;
            labelRestante.setText("Restante: $" + String.format("%.2f", restante));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el total: " + e.getMessage());
        }
    }

}