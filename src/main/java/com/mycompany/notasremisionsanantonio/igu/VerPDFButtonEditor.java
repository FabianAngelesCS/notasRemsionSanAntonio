package com.mycompany.notasremisionsanantonio.igu;

import com.mycompany.notasremisionsanantonio.logica.GeneradorPDF;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

public class VerPDFButtonEditor extends DefaultCellEditor {
    private JButton button;
    private boolean isPushed;
    private JTable table;

    public VerPDFButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton("Ver PDF");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    try {
                        int idRemision = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                        GeneradorPDF.mostrarPDF(idRemision); 
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(button, "Error al mostrar el PDF: " + ex.getMessage());
                    }
                }
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                  boolean isSelected, int row, int column) {
        this.table = table;
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
