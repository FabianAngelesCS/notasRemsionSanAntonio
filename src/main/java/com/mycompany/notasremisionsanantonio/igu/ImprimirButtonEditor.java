package com.mycompany.notasremisionsanantonio.igu;
import com.mycompany.notasremisionsanantonio.logica.GeneradorPDF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.table.TableCellEditor;

public class ImprimirButtonEditor extends DefaultCellEditor {
    private JButton button;
    private JTable table;
    private boolean isPushed;

    public ImprimirButtonEditor(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;
        button = new JButton("Imprimir");
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            isPushed = false;
            int idRemision = (int) table.getValueAt(table.getSelectedRow(), 0);
            try {
                String ruta = GeneradorPDF.generarPDF(idRemision); // Cambia tu método para que retorne la ruta
                Desktop.getDesktop().print(new File(ruta));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(button, "No se pudo imprimir el PDF: " + ex.getMessage());
            }
        }
        return "️Imprimir";
    }
}
