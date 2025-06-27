
package com.mycompany.notasremisionsanantonio.igu;

import com.mycompany.notasremisionsanantonio.persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NotasPorCobrar extends javax.swing.JFrame implements PagoCompletoListener{
    
    private int idCliente;
    private JFrame ventanaClientes;
    
    public NotasPorCobrar(int idCliente, String nombre, JFrame ventanaClientes) {
        initComponents();

        this.idCliente = idCliente;
        this.ventanaClientes = ventanaClientes;

        nombreCliente.setText(nombre);

        cargarNotasRemision(idCliente);

        this.ventanaClientes.setVisible(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                ventanaClientes.setVisible(true);
            }
        });
    }

    @Override
    public void onRemisionPagada(int idRemision) {
        cargarNotasRemision(idCliente); 
    }

    private void cargarNotasRemision(int idCliente) {
        DefaultTableModel modelo = (DefaultTableModel) remisionesPendientes.getModel();
        modelo.setRowCount(0);

        String sql = """
            SELECT r.id_remision, r.folio, r.fecha, c.nombre,
                   SUM(dr.cantidad * dr.precio_unitario) AS total
            FROM remision r
            JOIN cliente c ON r.id_cliente = c.id_cliente
            JOIN detalle_remision dr ON r.id_remision = dr.id_remision
            WHERE r.pagada = 0 AND r.id_cliente = ?
            GROUP BY r.id_remision, r.folio, r.fecha, c.nombre
        """;

        try (Connection conn = new Conexion().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente); 

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String folio = rs.getString("folio");
                String cliente = rs.getString("nombre");
                String fecha = rs.getString("fecha");
                double total = rs.getDouble("total");
                int id_remision = rs.getInt("id_remision");

                modelo.addRow(new Object[]{id_remision, folio, cliente, fecha, total, "Eliminar", "Abonar"});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar remisiones no impresas.");
        }

        configurarBotonesTabla();
    }

    private void configurarBotonesTabla() {
        remisionesPendientes.getColumn("Eliminar").setCellRenderer(new EliminarButtonRenderer());
        remisionesPendientes.getColumn("Eliminar").setCellEditor(new EliminarButtonEditor(new JCheckBox(), remisionesPendientes));
        
        remisionesPendientes.getColumn("Abonar").setCellRenderer(new AbonarButtonRenderer());
        remisionesPendientes.getColumn("Abonar").setCellEditor(new AbonarButtonEditor(new JCheckBox(), remisionesPendientes));
        
        remisionesPendientes.getColumn("Ver Abonos").setCellRenderer(new VerAbonosButtonRenderer());
        remisionesPendientes.getColumn("Ver Abonos").setCellEditor(new VerAbonosButtonEditor(new JCheckBox(), remisionesPendientes, this));

        remisionesPendientes.getColumnModel().getColumn(0).setMinWidth(0);
        remisionesPendientes.getColumnModel().getColumn(0).setMaxWidth(0);
        remisionesPendientes.getColumnModel().getColumn(0).setWidth(0);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombreCliente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        remisionesPendientes = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("PENDIENTES POR PAGAR DE:");

        nombreCliente.setBackground(new java.awt.Color(153, 255, 255));
        nombreCliente.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        nombreCliente.setForeground(new java.awt.Color(0, 153, 153));

        remisionesPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Remision", "No. Folio", "Cliente", "Fecha", "Importe Total", "Eliminar", "Abonar", "Ver Abonos"
            }
        ));
        jScrollPane1.setViewportView(remisionesPendientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(200, 200, 200))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
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
    private javax.swing.JLabel nombreCliente;
    private javax.swing.JTable remisionesPendientes;
    // End of variables declaration//GEN-END:variables
}
