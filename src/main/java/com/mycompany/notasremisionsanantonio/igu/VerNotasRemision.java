package com.mycompany.notasremisionsanantonio.igu;
import com.mycompany.notasremisionsanantonio.persistencia.Conexion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class VerNotasRemision extends javax.swing.JFrame {
    
    private Inicio ventanaInicio;

    public VerNotasRemision(Inicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
        
        initComponents();
        configurarCierreVentana();
        cargarNotasRemision();
       
    }

    private void configurarCierreVentana() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                ventanaInicio.setVisible(true);
            }
        });
    }
    
    private void cargarNotasRemision() {
        DefaultTableModel modelo = (DefaultTableModel) notasPendientes.getModel();
        modelo.setRowCount(0); 

        String sql = """
            SELECT r.id_remision, r.folio, r.fecha, c.nombre,
                   SUM(dr.cantidad * dr.precio_unitario) AS total
            FROM remision r
            JOIN cliente c ON r.id_cliente = c.id_cliente
            JOIN detalle_remision dr ON r.id_remision = dr.id_remision
            WHERE r.impresa = 0
            GROUP BY r.id_remision, r.folio, r.fecha, c.nombre
            """;

        try (Connection conn = new Conexion().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String folio = rs.getString("folio");
                String cliente = rs.getString("nombre");
                String fecha = rs.getString("fecha");
                double total = rs.getDouble("total");
                int id_remision = rs.getInt("id_remision");

                modelo.addRow(new Object[]{id_remision, folio, cliente, fecha, total, "Eliminar", "Ver PDF"});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar remisiones no impresas.");
        }

        configurarBotonesTabla();
    }

    private void configurarBotonesTabla() {
        notasPendientes.getColumn("Eliminar").setCellRenderer(new EliminarButtonRenderer());
        notasPendientes.getColumn("Eliminar").setCellEditor(new EliminarButtonEditor(new JCheckBox(), notasPendientes));

        notasPendientes.getColumn("Ver PDF").setCellRenderer(new VerPDFButtonRenderer());
        notasPendientes.getColumn("Ver PDF").setCellEditor(new VerPDFButtonEditor(new JCheckBox()));
        
        notasPendientes.getColumnModel().getColumn(0).setMinWidth(0);
        notasPendientes.getColumnModel().getColumn(0).setMaxWidth(0);
        notasPendientes.getColumnModel().getColumn(0).setWidth(0);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        notasPendientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("IMPRIMIR NOTAS DE REMISIÓN");

        notasPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No. Folio", "Cliente", "Fecha", "Importe", "Eliminar", "Ver PDF"
            }
        ));
        jScrollPane1.setViewportView(notasPendientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(175, 175, 175))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable notasPendientes;
    // End of variables declaration//GEN-END:variables
}
