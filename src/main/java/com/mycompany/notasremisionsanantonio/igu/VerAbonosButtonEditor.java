
package com.mycompany.notasremisionsanantonio.igu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VerAbonosButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;

    public VerAbonosButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton("Ver Abonos");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = table.getSelectedRow();
                int idRemision = (int) table.getValueAt(fila, 0);
                new VerAbonosRemision(idRemision).setVisible(true);
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                 int row, int column) {
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Ver Abonos";
    }
}
