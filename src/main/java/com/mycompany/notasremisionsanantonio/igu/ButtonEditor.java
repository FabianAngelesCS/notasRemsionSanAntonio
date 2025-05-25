package com.mycompany.notasremisionsanantonio.igu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private boolean isPushed;
    private int editingRow; // <- para guardar la fila que se está editando

    public ButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton("Eliminar");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped(); // Esto llama a getCellEditorValue()
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        isPushed = true;
        editingRow = row; // <- aquí guardamos la fila que se va a eliminar
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            final int rowToDelete = editingRow;
            SwingUtilities.invokeLater(() -> {
                if (rowToDelete >= 0 && rowToDelete < table.getRowCount()) {
                    ((DefaultTableModel) table.getModel()).removeRow(rowToDelete);
                }
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
