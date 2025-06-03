
package com.mycompany.notasremisionsanantonio.igu;

import com.mycompany.notasremisionsanantonio.persistencia.Conexion;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public AbonarRemision(int idRemision) {
        this.idRemision = idRemision;
        initComponents();
        cargarTotalYRestante();
    }

    private void initComponents() {
        setTitle("Registrar Abono");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        labelRestante = new JLabel("Restante: $0.00", SwingConstants.CENTER);
        txtAbono = new JTextField();
        btnRegistrar = new JButton("Registrar Abono");

        add(new JLabel("Monto del abono:", SwingConstants.CENTER));
        add(txtAbono);
        add(labelRestante);
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> registrarAbono());
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

    private void registrarAbono() {
        try {
            double abono = Double.parseDouble(txtAbono.getText());

            if (abono <= 0) {
                JOptionPane.showMessageDialog(this, "El monto debe ser mayor a 0.");
                return;
            }

            try (Connection conn = new Conexion().conectar()) {
                String insertSQL = "INSERT INTO abono_remision (id_remision, monto, fecha) VALUES (?, ?, NOW())";
                try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                    stmt.setInt(1, idRemision);
                    stmt.setDouble(2, abono);
                    stmt.executeUpdate();
                }

                // Revisar si ya se completó el total
                double abonado = 0.0;
                String sqlAbonos = "SELECT SUM(monto) AS abonado FROM abono_remision WHERE id_remision = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sqlAbonos)) {
                    stmt.setInt(1, idRemision);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        abonado = rs.getDouble("abonado");
                    }
                }

                if (abonado >= totalRemision) {
                    String updateSQL = "UPDATE remision SET pagada = 1 WHERE id_remision = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
                        stmt.setInt(1, idRemision);
                        stmt.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(this, "Remisión pagada en su totalidad.");
                    dispose(); // Cierra esta ventana
                } else {
                    JOptionPane.showMessageDialog(this, "Abono registrado.");
                    txtAbono.setText("");
                    cargarTotalYRestante(); // refresca el monto restante
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar el abono: " + ex.getMessage());
        }
    }
}
