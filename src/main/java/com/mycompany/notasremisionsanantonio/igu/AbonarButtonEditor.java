
package com.mycompany.notasremisionsanantonio.igu;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class AbonarButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;

    public AbonarButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton("Abonar");
        button.addActionListener(e -> {
            int row = table.getSelectedRow();
            int idRemision = (int) table.getValueAt(row, 0);
           new AbonarRemision(idRemision, (PagoCompletoListener) SwingUtilities.getWindowAncestor(table)).setVisible(true);
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Abonar";
    }
}
