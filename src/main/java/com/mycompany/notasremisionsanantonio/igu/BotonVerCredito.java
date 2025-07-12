package com.mycompany.notasremisionsanantonio.igu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;

public class BotonVerCredito extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

    private JButton button;
    private int idCliente;
    private String nombreCliente;
    private JTable table;

    public BotonVerCredito(JTable table) {
        this.table = table;
        button = new JButton("Ver Crédito");
        button.addActionListener(this);
    }

    @Override
    public Object getCellEditorValue() {
        return "Ver Crédito";
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        idCliente = Integer.parseInt(table.getValueAt(row, 0).toString());
        nombreCliente = table.getValueAt(row, 1).toString();
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtén la ventana padre real, debe ser CreditoTotal
        Component c = SwingUtilities.getWindowAncestor(table);
        if (!(c instanceof CreditoTotal)) {
            JOptionPane.showMessageDialog(null, "Error: Ventana padre no es CreditoTotal");
            return;
        }
        CreditoTotal ventanaCredito = (CreditoTotal) c;
        NotasPorCobrar ventana = new NotasPorCobrar(idCliente, nombreCliente, ventanaCredito);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        fireEditingStopped();
    }
}
