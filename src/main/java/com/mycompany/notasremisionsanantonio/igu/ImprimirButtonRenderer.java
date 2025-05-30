package com.mycompany.notasremisionsanantonio.igu;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ImprimirButtonRenderer extends JButton implements TableCellRenderer {

    public ImprimirButtonRenderer() {
        setText("️Imprimir");
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
