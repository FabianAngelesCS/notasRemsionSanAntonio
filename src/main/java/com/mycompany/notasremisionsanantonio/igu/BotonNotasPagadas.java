
package com.mycompany.notasremisionsanantonio.igu;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class BotonNotasPagadas extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

    private JButton button;
    private JTable table;

    public BotonNotasPagadas(JTable table) {
        this.table = table;
        this.button = new JButton("Ver Notas");
        this.button.addActionListener(this);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Ver Notas";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.getSelectedRow();
        int idCliente = (int) table.getValueAt(row, 0);
        new NotasRemisionPagadas(idCliente).setVisible(true);
        fireEditingStopped();
    }
}
