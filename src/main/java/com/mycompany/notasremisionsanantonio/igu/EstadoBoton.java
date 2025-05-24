/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.notasremisionsanantonio.igu;
import com.mycompany.notasremisionsanantonio.persistencia.Conexion;

import java.awt.Component;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;
//import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;



public class EstadoBoton {
    // Clase para el renderizador del botón
       public static class Renderer extends JButton implements javax.swing.table.TableCellRenderer {
        public Renderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
            if (value instanceof Boolean) {
                boolean estado = (Boolean) value;
                setText(estado ? "Desactivar" : "Activar");
                setBackground(estado ? new Color(144, 238, 144) : new Color(255, 182, 193));
                setForeground(Color.BLACK);
            }
            return this;
        }
    }

    public static class Editor extends DefaultCellEditor {
        private JButton button;
        private boolean estadoActual;
        private String idRegistro;
        private JTable tabla;
        private String nombreTabla; // "producto", "cliente", etc.
        private String nombreCampoID; // "nombre", "id_cliente", etc.

        public Editor(JCheckBox checkBox, String nombreTabla, String nombreCampoID) {
            super(checkBox);
            this.nombreTabla = nombreTabla;
            this.nombreCampoID = nombreCampoID;
            
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(this::accionBoton);
        }

        private void accionBoton(ActionEvent e) {
            fireEditingStopped();
            cambiarEstadoRegistro();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
            estadoActual = (Boolean) value;
            idRegistro = (String) table.getValueAt(row, 0); // Asume que el ID está en la columna 0
            tabla = table;

            button.setText(estadoActual ? "Desactivar" : "Activar");
            button.setBackground(estadoActual ? new Color(144, 238, 144) : new Color(255, 182, 193));
            
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return !estadoActual;
        }

        private void cambiarEstadoRegistro() {
            boolean nuevoEstado = !estadoActual;
            
            try {Conexion conexion = new Conexion();
                Connection conn = conexion.conectar();
                java.sql.PreparedStatement ps = conn.prepareStatement(
                    "UPDATE " + nombreTabla + " SET estatus = ? WHERE " + nombreCampoID + " = ?");
                ps.setBoolean(1, nuevoEstado);
                ps.setString(2, idRegistro);
                ps.executeUpdate();
                conn.close();
                
                // Actualizar el modelo de la tabla
                ((javax.swing.table.DefaultTableModel)tabla.getModel()).setValueAt(nuevoEstado, tabla.getSelectedRow(), 3);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(button, "Error al actualizar estado: " + e.getMessage());
            }
        }
    }
}