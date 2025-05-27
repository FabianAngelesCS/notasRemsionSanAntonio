package com.mycompany.notasremisionsanantonio.igu;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class EliminarButtonRenderer extends JButton implements TableCellRenderer {

    public EliminarButtonRenderer() {
        setOpaque(true);
        setText("Eliminar");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
