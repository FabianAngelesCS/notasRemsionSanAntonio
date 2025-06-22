
package com.mycompany.notasremisionsanantonio.igu;
import com.mycompany.notasremisionsanantonio.persistencia.Conexion;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mycompany.notasremisionsanantonio.igu.EstadoBoton;
import com.mycompany.notasremisionsanantonio.logica.Producto;
import com.mycompany.notasremisionsanantonio.persistencia.ProductoDAO;
//import javax.swing.table.TableCellEditor;
//import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JCheckBox;




public class Productos extends javax.swing.JFrame {
    private Inicio ventanaInicio;
    
    public Productos(Inicio ventanaInicio) {
        this.ventanaInicio = ventanaInicio;
        initComponents();
        cargarProductosDesdeBD();
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        agregarProducto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("PRODUCTOS");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Descripción", "Precio", "Eliminar", "Editar"
            }
        ));
        jScrollPane1.setViewportView(tablaProductos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        agregarProducto.setBackground(new java.awt.Color(0, 204, 204));
        agregarProducto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        agregarProducto.setText("AGREGAR PRODUCTO");
        agregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(352, 352, 352))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(agregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(agregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     
    
    private void agregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarProductoActionPerformed
        AgregarProducto pantallaAgregarProducto = new AgregarProducto(this);
        pantallaAgregarProducto.setVisible(true);
        pantallaAgregarProducto.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_agregarProductoActionPerformed

    public void cargarProductosDesdeBD() {
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id_producto, nombre, caracteristicas, precio, estatus, cantidad FROM producto")) {
 
            DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Descripción", "Precio", "Estatus", "Editar","ProductoCompleto"}, 0){
                @Override
                public boolean isCellEditable(int row, int column) {
                // Solo las columnas de botones son editables
                    return column == 4 || column == 5;
            }
              @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) return Boolean.class;
                if (columnIndex == 6) return Producto.class;

                return super.getColumnClass(columnIndex);
            }
            };

            while (rs.next()) {
                int id_producto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                String caracteristicas = rs.getString("caracteristicas");
                double precio = rs.getDouble("precio");
                boolean estatus = rs.getBoolean("estatus");
                int cantidad = rs.getInt("cantidad");
                
                Producto productoCompleto = new Producto(id_producto, nombre, precio, caracteristicas, cantidad,estatus);

                modelo.addRow(new Object[]{id_producto, nombre, caracteristicas, precio, estatus, "Editar",productoCompleto});
            }

            tablaProductos.setModel(modelo);
            
            tablaProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tablaProductos.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaProductos.getColumnModel().getColumn(0).setWidth(0);
            
            tablaProductos.getColumnModel().getColumn(6).setMinWidth(0);
            tablaProductos.getColumnModel().getColumn(6).setMaxWidth(0);
            tablaProductos.getColumnModel().getColumn(6).setWidth(0);
            
            TableColumn estadoColumn = tablaProductos.getColumnModel().getColumn(4);
            estadoColumn.setCellRenderer(new EstadoBoton.Renderer());
            estadoColumn.setCellEditor(new EstadoBoton.Editor(new JCheckBox(), "producto", "id_producto"));
            
            TableColumn editarColumn=tablaProductos.getColumnModel().getColumn(5);
            editarColumn.setCellRenderer(new EditarBoton.Renderer());
            editarColumn.setCellEditor(new EditarBoton.Editor(
                    new JCheckBox(),
                    filaSeleccionada -> {
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para editar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Obtener el objeto Producto completo de la columna oculta (índice 6)
            Producto productoAeditar = (Producto) modelo.getValueAt(filaSeleccionada, 6); // Usar filaSeleccionada aquí

            if (productoAeditar == null) {
                System.err.println("Error: El objeto Producto de la columna oculta es null en la fila " + filaSeleccionada);
                JOptionPane.showMessageDialog(this, "No se pudo obtener la información completa del producto.", "Error Interno", JOptionPane.ERROR_MESSAGE);
                return;
            }

            EditarProducto dialog = new EditarProducto(this, productoAeditar);
            dialog.setVisible(true);

            // LÓGICA DESPUÉS DE QUE EL DIÁLOGO SE CIERRA
            if (dialog.productoModificados()) {
                Producto productoModificado = dialog.getProductoModificado();
                if (productoModificado != null) {
                    try {
                        ProductoDAO productoDAO = new ProductoDAO();
                        boolean exito = productoDAO.actualizarProducto(productoModificado);

                        if (exito) {
                            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            // Actualizar la fila en la tabla visualmente
                            modelo.setValueAt(productoModificado.getNombre(), filaSeleccionada, 1);
                            modelo.setValueAt(productoModificado.getCaracteristicas(), filaSeleccionada, 2);
                            modelo.setValueAt(productoModificado.getPrecio(), filaSeleccionada, 3);
                            modelo.setValueAt(productoModificado.isEstatus(), filaSeleccionada, 4);
                            modelo.setValueAt(productoModificado, filaSeleccionada, 6); // Actualiza el objeto completo
                            
                            // Asegurarte de que la tabla se redibuje
                            modelo.fireTableRowsUpdated(filaSeleccionada, filaSeleccionada);

                        } else {
                            JOptionPane.showMessageDialog(this, "No se pudo actualizar el producto en la base de datos.", "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error inesperado al guardar los cambios del producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            } else {
                System.out.println("Edición de producto cancelada.");
            }
        } catch (ClassCastException e) {
            System.err.println("Error de casteo al obtener Producto de la tabla: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error interno al procesar el producto para edición (ClassCastException).", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.err.println("Error general al intentar abrir el diálogo de edición: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado al preparar la edición.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos: " + e.getMessage());
        }
    }
             
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}
