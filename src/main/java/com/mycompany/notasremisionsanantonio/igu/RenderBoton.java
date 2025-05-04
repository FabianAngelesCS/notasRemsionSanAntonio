
package com.mycompany.notasremisionsanantonio.igu;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RenderBoton extends JButton implements TableCellRenderer {

    public RenderBoton() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        setText((value == null) ? "Acción" : value.toString());
        return this;
    }
}