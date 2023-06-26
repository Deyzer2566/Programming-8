package gui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class TableFilter extends RowFilter {
    protected TableFilter(AbstractTableModel model) {
        super();
    }

    @Override
    public boolean include(Entry entry) {
        return false;
    }
}
