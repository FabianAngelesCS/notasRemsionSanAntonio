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
    private final JButton button;
    private String currentId;
    private final Consumer<String> action;

    public Editor(JCheckBox checkBox, Consumer<String> action) {
        super(checkBox);
        this.action = action;
        button = createButton();
    }

    private JButton createButton() {
        JButton btn = new JButton("Editar");
        btn.setBackground(new Color(70, 130, 180));
        btn.setForeground(Color.WHITE);
        btn.addActionListener(e -> {
            fireEditingStopped();
            if (action != null && currentId != null) {
                action.accept(currentId);
            }
        });
        return btn;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, 
                                              boolean isSelected, int row, int column) {
        currentId = table.getValueAt(row, 0).toString(); // Obtiene el ID de la columna 0
        return button;
    }
}
}
