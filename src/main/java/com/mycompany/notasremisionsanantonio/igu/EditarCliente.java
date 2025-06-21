
package com.mycompany.notasremisionsanantonio.igu;
import javax.swing.JOptionPane;
import com.mycompany.notasremisionsanantonio.logica.Cliente;
import java.awt.Frame;
import javax.swing.JDialog;

public class EditarCliente extends javax.swing.JDialog {

    //private String idCliente;
    private boolean datosModificados = false;
    private Cliente clienteOriginal; // Para almacenar el cliente original y el ID

    // Modifica el constructor para que reciba un objeto Cliente
    public EditarCliente(java.awt.Frame parent, Cliente cliente) {
        super(parent, true); // Llama al constructor de JDialog, 'true' lo hace modal
        this.clienteOriginal = cliente;
        initComponents();
        
        nombreEditado.setText(cliente.getNombre());
        telefonoEditado.setText(cliente.getTelefono());
        direccEditado.setText(cliente.getDireccion());
        observEditado.setText(cliente.getObservaciones());
        estatusEditado.setSelected(cliente.isEstatus());
        
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose(); // Simplemente cierra la ventana sin guardar
            }
        });
    
    setLocationRelativeTo(parent);
    setTitle("Editando Cliente: " + cliente.getNombre());
    }
    
    // Nuevo método para obtener el objeto Cliente con los datos modificados
    public Cliente getClienteModificado() {
        Cliente clienteModificado = new Cliente();
        clienteModificado.setId_cliente(clienteOriginal.getId_cliente()); // Usa el ID original
        clienteModificado.setNombre(nombreEditado.getText().trim());
        clienteModificado.setTelefono(telefonoEditado.getText().trim());
        clienteModificado.setDireccion(direccEditado.getText().trim());
        clienteModificado.setObservaciones(observEditado.getText().trim());
        clienteModificado.setEstatus(estatusEditado.isSelected());
        return clienteModificado;
    }
    
    public boolean isDatosModificados() {
            return datosModificados;
}
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nombreEditado = new javax.swing.JTextField();
        telefonoEditado = new javax.swing.JTextField();
        direccEditado = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        observEditado = new javax.swing.JTextArea();
        estatusEditado = new javax.swing.JCheckBox();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("NOMBRE");

        jLabel2.setText("TELEFONO");

        jLabel3.setText("DIRECCION");

        jLabel4.setText("OBSERVACIONES");

        jLabel5.setText("ESTATUS");

        nombreEditado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreEditadoActionPerformed(evt);
            }
        });

        observEditado.setColumns(20);
        observEditado.setRows(5);
        jScrollPane1.setViewportView(observEditado);

        estatusEditado.setText("ACTIVO");

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
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonGuardar)
                        .addGap(75, 75, 75)
                        .addComponent(botonCancelar))
                    .addComponent(estatusEditado)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                        .addComponent(nombreEditado)
                        .addComponent(telefonoEditado)
                        .addComponent(direccEditado)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombreEditado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(telefonoEditado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(direccEditado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(estatusEditado))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGuardar)
                    .addComponent(botonCancelar))
                .addContainerGap(49, Short.MAX_VALUE))
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

    private void nombreEditadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreEditadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreEditadoActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        if(validarCampos()){
        this.datosModificados = true;
        dispose(); // Cierra la ventana después de validar
    }
        
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        datosModificados=false;
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed
     
    private boolean validarCampos() {
    if (nombreEditado.getText().trim().isEmpty()) {
        mostrarError("El nombre es obligatorio");
        return false;
    }
    
    if (telefonoEditado.getText().trim().isEmpty()) {
        mostrarError("El teléfono es obligatorio");
        return false;
    } else if (!telefonoEditado.getText().matches("\\d{8,15}")) {
        mostrarError("Teléfono debe contener solo números (8-15 dígitos)");
        return false;
    }
    
    if (direccEditado.getText().trim().isEmpty()) {
        mostrarError("La dirección es obligatoria");
        return false;
    }
    
    return true;
}

private void mostrarError(String mensaje) {
    JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JTextField direccEditado;
    private javax.swing.JCheckBox estatusEditado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreEditado;
    private javax.swing.JTextArea observEditado;
    private javax.swing.JTextField telefonoEditado;
    // End of variables declaration//GEN-END:variables
}
