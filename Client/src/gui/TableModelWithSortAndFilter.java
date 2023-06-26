package gui;

import data.StudyGroup;
import i18n.LocaleManager;
import net.RemoteDatabaseWithAuthAndInfoAboutGroupsOwners;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedList;

public class TableModelWithSortAndFilter extends MyTableModel {
    public TableModelWithSortAndFilter(RemoteDatabaseWithAuthAndInfoAboutGroupsOwners db, TableViewer viewer,JPanel map) {
        super(db, viewer,map);
    }
    int columnSorted;
    String filter;
    public void sort(int column){
        Comparator<StudyGroup> lambdaSort;
        switch (column){
            case 0:
                lambdaSort = (o1, o2)->Long.compare(o1.getId(),o2.getId());
                break;
            case 1:
                lambdaSort = (o1, o2)->o1.getName().compareTo(o2.getName());
                break;
            case 3:
                lambdaSort = (o1,o2)->o1.getCreationDate().compareTo(o2.getCreationDate());
                break;
            case 4:
                lambdaSort = (o1,o2)->o1.getStudentsCount().compareTo(o2.getStudentsCount());
                break;
            case 5:
                lambdaSort = (o1,o2)->o1.getExpelledStudents().compareTo(o2.getExpelledStudents());
                break;
            case 6:
                lambdaSort = (o1,o2)->o1.getShouldBeExpelled().compareTo(o2.getShouldBeExpelled());
                break;
            case 8:
                lambdaSort=(o1,o2)->o1.getGroupAdmin().getName().compareTo(o2.getName());
                break;
            default:
                return;
        }
        columnSorted = column;
        groups = new LinkedList(groups.stream().sorted(lambdaSort).toList());
    }

    public void filter(String str){
        this.filter = str;
        if(str == null || str.equals("")){
            return;
        }
        this.groups = new LinkedList(groups.stream().filter(x->x.toString().contains(str)).toList());
    }

    @Override
    public void synchronize() {
        super.synchronize();
        sort(columnSorted);
        filter(filter);
        SwingUtilities.invokeLater(()->
        {
            fireTableDataChanged();
            map.repaint();
        });
    }
}
