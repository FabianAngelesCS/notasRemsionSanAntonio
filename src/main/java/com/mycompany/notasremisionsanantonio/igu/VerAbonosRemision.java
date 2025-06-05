package com.mycompany.notasremisionsanantonio.igu;

import com.mycompany.notasremisionsanantonio.persistencia.Conexion;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VerAbonosRemision extends javax.swing.JFrame {
    
    private int idRemision;
    private JFrame ventanaNotasPorCobrar;
    
    public VerAbonosRemision(int idRemision, JFrame ventanaNotasPorCobrar) {
        this.idRemision = idRemision;
        this.ventanaNotasPorCobrar = ventanaNotasPorCobrar;

        setTitle("Abonos realizados");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTable tabla = new JTable();
        JScrollPane scroll = new JScrollPane(tabla);
        getContentPane().add(scroll); // Añadir correctamente al layout

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Fecha");
        modelo.addColumn("Monto");

        tabla.setModel(modelo);

        try (Connection conn = new Conexion().conectar()) {
            String sql = "SELECT fecha, monto FROM abono_remision WHERE id_remision = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idRemision);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String fecha = rs.getString("fecha");
                double monto = rs.getDouble("monto");
                modelo.addRow(new Object[]{fecha, String.format("$%.2f", monto)});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar abonos: " + e.getMessage());
        }

        // Mostrar ventana anterior al cerrar
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                ventanaNotasPorCobrar.setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
