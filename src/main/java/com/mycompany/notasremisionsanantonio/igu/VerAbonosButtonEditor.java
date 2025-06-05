
package com.mycompany.notasremisionsanantonio.igu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VerAbonosButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private JFrame ventanaNotasPorCobrar;

    public VerAbonosButtonEditor(JCheckBox checkBox, JTable table, JFrame ventanaNotasPorCobrar) {
        super(checkBox);
        this.table = table;
        this.ventanaNotasPorCobrar = ventanaNotasPorCobrar;
        button = new JButton("Ver Abonos");

        button.addActionListener(e -> {
            int row = table.getSelectedRow();
            int idRemision = (int) table.getValueAt(row, 0);

            VerAbonosRemision verAbonos = new VerAbonosRemision(idRemision, ventanaNotasPorCobrar);
            verAbonos.setVisible(true);
            ventanaNotasPorCobrar.setVisible(false); // Ocultamos la ventana actual
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Ver Abonos";
    }
}
