package com.mycompany.notasremisionsanantonio.igu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class VerPDFButtonEditor extends DefaultCellEditor {
    private JButton button;
    private boolean isPushed;

    public VerPDFButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton("Ver PDF");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                JOptionPane.showMessageDialog(button, "Aquí iría la lógica para abrir el PDF");
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        isPushed = false;
        return "Ver PDF";
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
