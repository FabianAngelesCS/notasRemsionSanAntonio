package com.mycompany.notasremisionsanantonio.igu;

import com.mycompany.notasremisionsanantonio.persistencia.Conexion;
import com.toedter.calendar.JDateChooser;

import java.awt.GridLayout;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class AbonarRemision extends javax.swing.JFrame {

    private int idRemision;
    private double totalRemision;
    private JLabel labelRestante;
    private JTextField txtAbono;
    private JButton btnRegistrar;
    private PagoCompletoListener listener;
    private NotasPorCobrar ventanaNotasPorCobrar;
    private JDateChooser dateChooser;

    // NUEVOS COMPONENTES
    private JComboBox<String> comboConcepto;
    private JTextField txtOtroConcepto;

    public AbonarRemision(int idRemision, NotasPorCobrar ventanaNotasPorCobrar) {
        this.idRemision = idRemision;
        this.ventanaNotasPorCobrar = ventanaNotasPorCobrar;
        this.listener = ventanaNotasPorCobrar;
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
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(9, 1));

        labelRestante = new JLabel("Restante: $0.00", SwingConstants.CENTER);
        txtAbono = new JTextField();
        btnRegistrar = new JButton("Registrar Abono");

        dateChooser = new JDateChooser();
        dateChooser.setDate(new Date());
        dateChooser.setDateFormatString("yyyy-MM-dd");

        // Componentes nuevos para concepto
        comboConcepto = new JComboBox<>(new String[]{"Abono efectivo", "Otro"});
        txtOtroConcepto = new JTextField();
        txtOtroConcepto.setEnabled(false);

        comboConcepto.addItemListener(e -> {
            if ("Otro".equals(comboConcepto.getSelectedItem())) {
                txtOtroConcepto.setEnabled(true);
            } else {
                txtOtroConcepto.setText("");
                txtOtroConcepto.setEnabled(false);
            }
        });

        add(new JLabel("Monto del abono:", SwingConstants.CENTER));
        add(txtAbono);
        add(new JLabel("Fecha del abono:", SwingConstants.CENTER));
        add(dateChooser);
        add(new JLabel("Concepto del abono:", SwingConstants.CENTER));
        add(comboConcepto);
        add(txtOtroConcepto); 
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

            String concepto;
            if ("Otro".equals(comboConcepto.getSelectedItem())) {
                concepto = txtOtroConcepto.getText().trim();
                if (concepto.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un concepto.");
                    return;
                }
            } else {
                concepto = "Abono efectivo";
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateada = sdf.format(fechaAbono);

            double abonado = 0.0;

            try (Connection conn = new Conexion().conectar()) {
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
                            "El monto del abono excede lo que falta por pagar ($" + String.format("%.2f", restante) + ").");
                    return;
                }

                // Insertar con observaciones
                String insertSQL = "INSERT INTO abono_remision (id_remision, monto, fecha, observaciones) VALUES (?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                    stmt.setInt(1, idRemision);
                    stmt.setDouble(2, abono);
                    stmt.setString(3, fechaFormateada);
                    stmt.setString(4, concepto);
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
                    txtOtroConcepto.setText("");
                    comboConcepto.setSelectedIndex(0);
                    cargarTotalYRestante();
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
