package com.mycompany.notasremisionsanantonio.igu;
import com.mycompany.notasremisionsanantonio.persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

import java.text.DecimalFormat;


public class CreditoTotal extends javax.swing.JFrame {
    private Inicio ventanaInicio;
    public CreditoTotal(Inicio ventanaInicio ) {
        this.ventanaInicio = ventanaInicio;
        initComponents();
        
        // Ocultar columna ID (columna 0)
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setWidth(0);

        // Asignar botones a las columnas correctas
        jTable1.getColumnModel().getColumn(2).setCellRenderer(new BotonNotasPagadas(jTable1));
        jTable1.getColumnModel().getColumn(2).setCellEditor(new BotonNotasPagadas(jTable1));

        jTable1.getColumnModel().getColumn(3).setCellRenderer(new BotonVerCredito(jTable1));
        jTable1.getColumnModel().getColumn(3).setCellEditor(new BotonVerCredito(jTable1));
        
        lblTotalCredito.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        lblTotalCredito.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalCredito.setText("Total crédito: 0.00");

        cargarCreditoPorCliente();
        configurarCierreVentana();
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
    public void cargarCreditoPorCliente() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpiar tabla

        Conexion conexion = new Conexion();
        Connection conn = conexion.conectar();

        double totalCredito = 0.0;
        DecimalFormat formatoMoneda = new DecimalFormat("#,##0.00");

        if (conn != null) {
            String sql = """
                SELECT
                    c.id_cliente,
                    c.nombre AS cliente,
                    IFNULL(rem.total_remisiones, 0) AS total_remisiones,
                    IFNULL(abonos.total_abonos, 0) AS total_abonos,
                    IFNULL(rem.total_remisiones, 0) - IFNULL(abonos.total_abonos, 0) AS credito
                FROM cliente c
                LEFT JOIN (
                    SELECT r.id_cliente, SUM(dr.cantidad * dr.precio_unitario) AS total_remisiones
                    FROM remision r
                    JOIN detalle_remision dr ON dr.id_remision = r.id_remision
                    WHERE r.pagada = 0
                    GROUP BY r.id_cliente
                ) rem ON rem.id_cliente = c.id_cliente
                LEFT JOIN (
                    SELECT r.id_cliente, SUM(ar.monto) AS total_abonos
                    FROM remision r
                    JOIN abono_remision ar ON ar.id_remision = r.id_remision
                    WHERE r.pagada = 0
                    GROUP BY r.id_cliente
                ) abonos ON abonos.id_cliente = c.id_cliente
                WHERE c.estatus = 1
                GROUP BY c.id_cliente;
            """;

            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int idCliente = rs.getInt("id_cliente");
                    String cliente = rs.getString("cliente");
                    double credito = rs.getDouble("credito");

                    totalCredito += credito;

                    model.addRow(new Object[]{
                        idCliente,
                        cliente,
                        null,
                        null,
                        formatoMoneda.format(credito)
                    });
                }

                // Mostrar total afuera
                lblTotalCredito.setText("Total crédito: " + formatoMoneda.format(totalCredito));

            } catch (Exception e) {
                System.out.println("Error al cargar créditos: " + e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblTotalCredito = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("CRÉDITO TOTAL Y POR CLIENTE");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID_CLIENTE", "Cliente", "Remisiones pagadas", "Remisiones pendientes", "Crédito"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        lblTotalCredito.setText("Saldo Total: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTotalCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTotalCredito;
    // End of variables declaration//GEN-END:variables
}
