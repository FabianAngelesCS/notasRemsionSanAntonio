/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.notasremisionsanantonio.igu;

import javax.swing.*;
//import javax.swing.table.DefaultCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class EditarBoton {
    public static class Renderer extends JButton implements javax.swing.table.TableCellRenderer{
         
        public Renderer() {
            setOpaque(true);
            setBackground(new Color(70, 130, 180)); // Azul acero
            setForeground(Color.WHITE);
            setText("Editar");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
            return this;
        }
    }

    public static class Editor extends DefaultCellEditor {
        private JButton button;
        private Object idRegistro;
        private JTable tabla;
        private int filaActual;
        private Consumer<Object> accionEditar;

        public Editor(JCheckBox checkBox, Consumer<Object> accionEditar) {
            super(checkBox);
            this.accionEditar = accionEditar;
            
            button = new JButton();
            button.setOpaque(true);
            button.setBackground(new Color(70, 130, 180));
            button.setForeground(Color.WHITE);
            button.setText("Editar");
            button.addActionListener(this::accionBoton);
        }

        private void accionBoton(ActionEvent e) {
            fireEditingStopped();
            if (accionEditar != null) {
                accionEditar.accept(idRegistro);
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
            this.tabla = table;
            this.filaActual = row;
            this.idRegistro = table.getValueAt(row, 0); // Asume que el ID está en la columna 0
            
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "Editar";
        }
    }
}
