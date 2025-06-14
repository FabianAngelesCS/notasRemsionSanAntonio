
package com.mycompany.notasremisionsanantonio.igu;

import com.mycompany.notasremisionsanantonio.persistencia.Conexion;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NotasRemisionPagadas extends javax.swing.JFrame {
    
    private JFrame ventanaClientes;

    public NotasRemisionPagadas(int idCliente, JFrame ventanaClientes) {
        
        this.ventanaClientes = ventanaClientes;

        initComponents();
        setTitle("Notas de remisión pagadas");
        setLocationRelativeTo(null);
        cargarNotasRemisionPagadas(idCliente);
        
        jTable1.getColumn("Abonos").setCellRenderer(new ButtonRendererNotasPagadas());
        jTable1.getColumn("Abonos").setCellEditor(new ButtonEditorNotasPagadas(new JCheckBox(), jTable1));
        
        this.ventanaClientes.setVisible(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                ventanaClientes.setVisible(true);
            }
        });
    }
    
    private void cargarNotasRemisionPagadas(int idCliente) {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); 

        String sql = """
            SELECT r.id_remision, r.folio, r.fecha, c.nombre,
                   SUM(dr.cantidad * dr.precio_unitario) AS total,
                   (SELECT IFNULL(SUM(a.monto), 0) FROM abono_remision a WHERE a.id_remision = r.id_remision) AS abonos
            FROM remision r
            JOIN cliente c ON r.id_cliente = c.id_cliente
            JOIN detalle_remision dr ON r.id_remision = dr.id_remision
            WHERE r.pagada = 1 AND r.id_cliente = ?
            GROUP BY r.id_remision, r.folio, r.fecha, c.nombre
        """;

        try (Connection conn = new Conexion().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_remision = rs.getInt("id_remision");
                String folio = rs.getString("folio");
                String cliente = rs.getString("nombre");
                String fecha = rs.getString("fecha");
                double total = rs.getDouble("total");
                double abonos = rs.getDouble("abonos");

                modelo.addRow(new Object[]{
                    id_remision,
                    folio,
                    cliente,
                    fecha,
                    String.format("$%.2f", total),
                    "Ver abonos"
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar notas pagadas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Notas remisión pagadas");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id remisión", "No. Folio", "Cliente", "Fecha", "Importe Total", "Abonos"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(230, 230, 230))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
            .addComponent(jSeparator1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
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
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
class ButtonRendererNotasPagadas extends JButton implements javax.swing.table.TableCellRenderer {
    public ButtonRendererNotasPagadas() {
        setText("Ver Abonos");
    }

    @Override
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                            boolean hasFocus, int row, int column) {
        return this;
    }
}
class ButtonEditorNotasPagadas extends DefaultCellEditor {
    private JButton button;
    private int currentRow;
    private JTable table;

    public ButtonEditorNotasPagadas(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton("Ver Abonos");

        button.addActionListener(e -> {
            int idRemision = (int) table.getValueAt(currentRow, 0); // Usamos la tabla pasada
            new VentanaAbonosPagados(idRemision).setVisible(true);
            fireEditingStopped();
        });
    }

    @Override
    public java.awt.Component getTableCellEditorComponent(JTable table, Object value,
                                                          boolean isSelected, int row, int column) {
        currentRow = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Ver Abonos";
    }
}

