
package com.mycompany.notasremisionsanantonio.igu;

import com.mycompany.notasremisionsanantonio.logica.Cliente;
import com.mycompany.notasremisionsanantonio.persistencia.ClienteDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class Clientes extends javax.swing.JFrame {
    private Inicio ventanaInicio;

    public Clientes(Inicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
        initComponents();
        cargarClientes();
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        agregarCliente = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("CLIENTES");

        TablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[] {"Nombre", "Teléfono", "Dirección", "Observaciones", "Acciones"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        }
    );
    jScrollPane2.setViewportView(TablaClientes);
    if (TablaClientes.getColumnModel().getColumnCount() > 0) {
        TablaClientes.getColumnModel().getColumn(0).setHeaderValue("Nombre");
        TablaClientes.getColumnModel().getColumn(1).setHeaderValue("Teléfono");
        TablaClientes.getColumnModel().getColumn(2).setHeaderValue("Dirección");
        TablaClientes.getColumnModel().getColumn(3).setHeaderValue("Observaciones");
        TablaClientes.getColumnModel().getColumn(4).setHeaderValue("Acciones");
    }

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    agregarCliente.setBackground(new java.awt.Color(0, 204, 204));
    agregarCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    agregarCliente.setText("AGREGAR CLIENTE");
    agregarCliente.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            agregarClienteActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1)
            .addGap(226, 226, 226)
            .addComponent(agregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(agregarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addGap(18, 18, 18)))
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarClienteActionPerformed
        AgregarCliente pantallaAgregarCliente = new AgregarCliente(this);
        pantallaAgregarCliente.setVisible(true);
        pantallaAgregarCliente.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_agregarClienteActionPerformed

    public void cargarClientes() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id_cliente"); 
        modelo.addColumn("Nombre");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Dirección");
        modelo.addColumn("Observaciones");
        modelo.addColumn("Acciones");

        ClienteDAO dao = new ClienteDAO();
        List<Cliente> listaClientes = (List<Cliente>) dao.obtenerClientes();

        for (Cliente c : listaClientes) {
            modelo.addRow(new Object[] {
                c.getId_cliente(), 
                c.getNombre(),
                c.getTelefono(),
                c.getDireccion(),
                c.getObservaciones(),
                c.isEstatus() ? "Desactivar" : "Activar"
            });
        }

        TablaClientes.setModel(modelo);

        // Ocultar columna del ID visualmente
        TablaClientes.getColumnModel().getColumn(0).setMinWidth(0);
        TablaClientes.getColumnModel().getColumn(0).setMaxWidth(0);
        TablaClientes.getColumnModel().getColumn(0).setWidth(0);

        // Asignar renderer y editor al botón
        TablaClientes.getColumn("Acciones").setCellRenderer(new RenderBoton());
        TablaClientes.getColumn("Acciones").setCellEditor(new EditorBoton(TablaClientes, listaClientes, this));
    }
    
    public void actualizarEstatusEnTabla(int idCliente, boolean nuevoEstatus) {
        for (int i = 0; i < TablaClientes.getRowCount(); i++) {
            int idFila = Integer.parseInt(TablaClientes.getValueAt(i, 0).toString());
            if (idFila == idCliente) {
                // Actualiza el modelo de la tabla
                TablaClientes.setValueAt(nuevoEstatus ? "Desactivar" : "Activar", i, 5);

                // Forzar repintado de esa celda
                TablaClientes.repaint(TablaClientes.getCellRect(i, 5, true));
                break;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaClientes;
    private javax.swing.JButton agregarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
