
package com.mycompany.notasremisionsanantonio.igu;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class AbonarButtonRenderer extends JButton implements TableCellRenderer {

    public AbonarButtonRenderer() {
        setText("Abonar");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        return this;
    }
}
