package com.mycompany.notasremisionsanantonio.igu;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class VerPDFButtonRenderer extends JButton implements TableCellRenderer {

    public VerPDFButtonRenderer() {
        setOpaque(true);
        setText("Ver PDF");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
