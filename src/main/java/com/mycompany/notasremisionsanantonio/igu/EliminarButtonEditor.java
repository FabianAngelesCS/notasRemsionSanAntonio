package com.mycompany.notasremisionsanantonio.igu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class EliminarButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private boolean isPushed;
    private int editingRow;

    public EliminarButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton("Eliminar");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        editingRow = row;
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            SwingUtilities.invokeLater(() -> {
                ((DefaultTableModel) table.getModel()).removeRow(editingRow);
            });
        }
        isPushed = false;
        return "Eliminar";
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
