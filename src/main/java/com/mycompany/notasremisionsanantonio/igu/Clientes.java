
package com.mycompany.notasremisionsanantonio.igu;

///import com.mycompany.notasremisionsanantonio.logica.Cliente;
//import com.mycompany.notasremisionsanantonio.persistencia.ClienteDAO;
//import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.mycompany.notasremisionsanantonio.igu.EstadoBoton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mycompany.notasremisionsanantonio.persistencia.Conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JCheckBox;
import com.mycompany.notasremisionsanantonio.igu.EditarCliente;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import com.mycompany.notasremisionsanantonio.logica.Cliente; // Importar la clase Cliente
import com.mycompany.notasremisionsanantonio.persistencia.ClienteDAO;


public class Clientes extends javax.swing.JFrame {
    private Inicio ventanaInicio;
    private ClienteDAO clienteDAO;

    public Clientes(Inicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
        this.clienteDAO = new ClienteDAO(); // Inicializa ClienteDAO
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
        Conexion conexion = new Conexion();
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID","Nombre", "Telefono", "Direccion", "Observaciones", "Estatus", "Editar"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 6; // Estatus y Editar son editables
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) return Boolean.class;
                return super.getColumnClass(columnIndex);
            }
        };
        
        try (Connection conn = conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id_cliente, nombre, telefono, direccion, observaciones, estatus FROM cliente")) {
            
            while (rs.next()) {
                String id = rs.getString("id_cliente");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String observaciones = rs.getString("observaciones");
                boolean estatus = rs.getBoolean("estatus");

                modelo.addRow(new Object[]{id, nombre, telefono, direccion, observaciones, estatus, "Editar"});
            }

            TablaClientes.setModel(modelo);
            
            // Ocultar columna ID
            TablaClientes.getColumnModel().getColumn(0).setMinWidth(0);
            TablaClientes.getColumnModel().getColumn(0).setMaxWidth(0);
            TablaClientes.getColumnModel().getColumn(0).setWidth(0);
            
            // Configurar columna de estado
            TableColumn estadoColumn = TablaClientes.getColumnModel().getColumn(5);
            estadoColumn.setCellRenderer(new EstadoBoton.Renderer());
            estadoColumn.setCellEditor(new EstadoBoton.Editor(new JCheckBox(), "cliente", "id_cliente"));
            
            // Configurar columna de edición
            TableColumn editarColumn = TablaClientes.getColumnModel().getColumn(6);
            editarColumn.setCellRenderer(new EditarBoton.Renderer());
            editarColumn.setCellEditor(new EditarBoton.Editor(
                new JCheckBox(), 
                // Lambda para la acción del botón de edición
                id -> { 
                    int fila = TablaClientes.getSelectedRow();
                    if (fila < 0) {
                        JOptionPane.showMessageDialog(this, "Seleccione un cliente para editar", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    // Obtener los datos del cliente de la fila seleccionada
                    int idCliente = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                    String nombre = modelo.getValueAt(fila, 1).toString();
                    String telefono = modelo.getValueAt(fila, 2).toString();
                    String direccion = modelo.getValueAt(fila, 3).toString();
                    String observaciones = modelo.getValueAt(fila, 4).toString();
                    boolean estatus = (Boolean) modelo.getValueAt(fila, 5);
                    
                    // Crear un objeto Cliente con los datos actuales de la tabla
                    Cliente clienteAEditar = new Cliente(idCliente, nombre, telefono, direccion, observaciones, estatus);

                    // Abrir la ventana de edición pasando el objeto Cliente
                    EditarCliente dialog = new EditarCliente(this, clienteAEditar);
                    dialog.setVisible(true); // Se bloquea hasta que el dialog se cierra
                    
                    if (dialog.isDatosModificados()) {
                        // Obtener el objeto Cliente con los datos modificados de la ventana de edición
                        Cliente clienteActualizado = dialog.getClienteModificado();
                        
                        // Llamar al método de ClienteDAO para actualizar en la BD
                        boolean exito = clienteDAO.actualizarCliente(clienteActualizado);
                        
                        if (exito) {
                            JOptionPane.showMessageDialog(this, "¡Cliente actualizado correctamente!");
                            cargarClientes(); // Recargar la tabla para ver los cambios
                        } else {
                            JOptionPane.showMessageDialog(this, "Error al actualizar el cliente en la base de datos.", 
                                                          "Error de Actualización", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            ));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes: " + e.getMessage());
            e.printStackTrace();
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
