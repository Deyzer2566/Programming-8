package gui;

import javax.swing.*;
import java.awt.*;

public class TableViewer {
    JTable table;
    JScrollPane scrollPane;
    int scrollValue;
    int selectedRow;
    int selectedColumn;
    public TableViewer(JTable table, JScrollPane scrollPane){
        this.table = table;
        this.scrollPane=scrollPane;
    }

    public void saveState(){
        scrollValue = scrollPane.getVerticalScrollBar().getValue();
        selectedRow = table.getSelectedRow();
        selectedColumn = table.getSelectedColumn();
    }

    public void recoveryState(){
        scrollPane.getVerticalScrollBar().setValue(scrollValue);
        table.changeSelection(selectedRow,selectedColumn,false,false);
    }

}
