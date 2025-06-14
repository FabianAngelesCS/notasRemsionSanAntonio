
package com.mycompany.notasremisionsanantonio.igu;

import com.mycompany.notasremisionsanantonio.persistencia.Conexion;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class VentanaAbonosPagados extends JFrame {

    private JTable tablaAbonos;
    private DefaultTableModel modelo;

    public VentanaAbonosPagados(int idRemision) {
        setTitle("Abonos de remisión #" + idRemision);
        setSize(400, 300);
        setLocationRelativeTo(null);

        modelo = new DefaultTableModel(new Object[]{"Fecha", "Monto"}, 0);
        tablaAbonos = new JTable(modelo);
        add(new JScrollPane(tablaAbonos));

        cargarAbonos(idRemision);
    }

    private void cargarAbonos(int idRemision) {
        String sql = "SELECT fecha, monto FROM abono_remision WHERE id_remision = ? ORDER BY fecha ASC";

        try (Connection conn = new Conexion().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRemision);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String fecha = rs.getString("fecha");
                double monto = rs.getDouble("monto");
                modelo.addRow(new Object[]{fecha, String.format("$%.2f", monto)});
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar abonos: " + e.getMessage());
        }
    }
}
