/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.notasremisionsanantonio.igu;
import com.mycompany.notasremisionsanantonio.logica.Producto;
import javax.swing.JOptionPane;

/**
 *
 * @author Sebaz
 */
public class EditarProducto extends javax.swing.JDialog {
    
    private boolean productoModificados=false;
    private Producto productoOriginal;

    /**
     * Creates new form EditarProducto
     */
    public EditarProducto(java.awt.Frame parent, Producto producto) {
        super(parent, true);
        this.productoOriginal= producto;
        initComponents();
        
        productoEdit.setText(producto.getNombre().toUpperCase());
        caracteristicasEdit.setText(producto.getCaracteristicas().toUpperCase());
        precioEdit.setText(String.valueOf(producto.getPrecio()));
        estatusEdit.setSelected(producto.isEstatus());
        cantidadEdit.setText(String.valueOf(producto.getCantidad()));
        
        
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose(); // Simplemente cierra la ventana sin guardar
            }
        });
        
        setLocationRelativeTo(parent);
        setTitle("Editando Producto: " + producto.getNombre());
    }
    
    
        public Producto getProductoModificado(){
            Producto productoModificado = new Producto();
            try {
            productoModificado.setId_producto(productoOriginal.getId_producto());
            productoModificado.setNombre(productoEdit.getText().trim().toUpperCase());
            productoModificado.setPrecio(Double.parseDouble(precioEdit.getText().trim()));
            productoModificado.setCaracteristicas(caracteristicasEdit.getText().trim().toUpperCase());
            productoModificado.setEstatus(estatusEdit.isSelected());
            productoModificado.setCantidad(Integer.parseInt(cantidadEdit.getText().trim()));
            }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error de formato en Precio o Cantidad. Por favor, ingrese valores numéricos válidos.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            // Si hay un error de formato, no podemos construir el objeto correctamente.
            // Una opción es relanzar la excepción, o devolver null, o simplemente
            // dejar que 'validarCampos()' lo maneje si lo extiendes.
            return null; // O devolver el productoOriginal sin cambios si prefieres
        }
            
            return productoModificado;
        }
        
        public boolean productoModificados(){
            return productoModificados;
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        productoEdit = new javax.swing.JTextField();
        caracteristicasEdit = new javax.swing.JTextField();
        precioEdit = new javax.swing.JTextField();
        cantidadEdit = new javax.swing.JTextField();
        estatusEdit = new javax.swing.JCheckBox();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("NOMBRE");

        jLabel2.setText("CARACTERISTICAS");

        jLabel3.setText("PRECIO");

        jLabel4.setText("CANTIDAD");

        jLabel5.setText("ESTATUS");

        productoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productoEditActionPerformed(evt);
            }
        });

        caracteristicasEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caracteristicasEditActionPerformed(evt);
            }
        });

        precioEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioEditActionPerformed(evt);
            }
        });

        cantidadEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadEditActionPerformed(evt);
            }
        });

        estatusEdit.setText("ACTIVO");
        estatusEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estatusEditActionPerformed(evt);
            }
        });

        botonGuardar.setText("GUARDAR");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonCancelar.setText("CANCELAR");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(botonGuardar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estatusEdit)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(botonCancelar)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(productoEdit)
                            .addComponent(caracteristicasEdit)
                            .addComponent(precioEdit)
                            .addComponent(cantidadEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))))
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(productoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(caracteristicasEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(precioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cantidadEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(estatusEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGuardar)
                    .addComponent(botonCancelar))
                .addGap(49, 49, 49))
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

    private void productoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productoEditActionPerformed

    private void caracteristicasEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caracteristicasEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caracteristicasEditActionPerformed

    private void precioEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioEditActionPerformed

    private void cantidadEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadEditActionPerformed

    private void estatusEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estatusEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estatusEditActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
       if (validarCampos()){
           Producto productoConCambios = getProductoModificado();
           if (productoConCambios != null) {
           this.productoModificados =true;
           dispose();}
       }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        productoModificados=false;
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed
    
    private boolean validarCampos() {
    if (productoEdit.getText().trim().isEmpty()) {
        mostrarError("El nombre es obligatorio");
        return false;
    }
    
    if (caracteristicasEdit.getText().trim().isEmpty()) {
        mostrarError("Caracteristicas es obligatorio");
        return false;
    } 
    
    if (precioEdit.getText().trim().isEmpty()) {
        mostrarError("Precio es obligatorio");
        return false;
    }
        return true;
    }
    private void mostrarError(String mensaje) {
    JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JTextField cantidadEdit;
    private javax.swing.JTextField caracteristicasEdit;
    private javax.swing.JCheckBox estatusEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField precioEdit;
    private javax.swing.JTextField productoEdit;
    // End of variables declaration//GEN-END:variables
}
