package gui;

import data.StudyGroup;
import i18n.LocaleManager;
import i18n.Localizable;
import net.RemoteDatabaseWithAuth;
import net.RemoteDatabaseWithAuthAndInfoAboutGroupsOwners;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MyTableModel extends AbstractTableModel implements Localizable {

    protected RemoteDatabaseWithAuthAndInfoAboutGroupsOwners db;
    protected LinkedList<StudyGroup> groups;

    protected String [] columns;
    protected TableViewer viewer;
    protected HashMap<Integer,LinkedList<Long>> owners;
    protected ResourceBundle rb;
    protected JPanel map;
    public MyTableModel(RemoteDatabaseWithAuthAndInfoAboutGroupsOwners db, TableViewer viewer, JPanel map){
        this.db = db;
        this.viewer=viewer;
        this.groups = new LinkedList<>(db.showAllGroups());
        this.owners = db.getOwnersOfGroups();
        this.map = map;
        rb = ResourceBundle.getBundle("i18n.GuiLabels",LocaleManager.getLocale());
        columns = new String[]{
                rb.getString("ID"),
                rb.getString("Name"),
                rb.getString("Coordinates"),
                rb.getString("CreationTime"),
                rb.getString("StudentsCount"),
                rb.getString("ExpelledStudentsCount"),
                rb.getString("ShouldBeExpelledCount"),
                rb.getString("Semester"),
                rb.getString("Admin")
        };
        new Thread(()->
        {
            while(!Thread.currentThread().isInterrupted()) {
                synchronize();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public int getRowCount() {
        return this.groups.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StudyGroup group =  groups.get(rowIndex);
        switch (columnIndex){
            case 0:
                return group.getId();
            case 1:
                return group.getName();
            case 2:
                return group.getCoordinates();
            case 3:
                ZonedDateTime zonedDateTime = group.getCreationDate();
                DateFormat f = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, LocaleManager.getLocale());
                SimpleDateFormat sf = (SimpleDateFormat) f;
                String pattern = sf.toPattern();
                SimpleDateFormat sdf = new SimpleDateFormat(pattern + " z", LocaleManager.getLocale());
                return zonedDateTime.format(
                                DateTimeFormatter.ofPattern(sdf.toPattern(),LocaleManager.getLocale()));
            case 4:
                return group.getStudentsCount();
            case 5:
                return group.getExpelledStudents();
            case 6:
                return group.getShouldBeExpelled();
            case 7:
                switch(group.getSemesterEnum()){
                    case FIRST:
                        return rb.getString("First");
                    case THIRD:
                        return rb.getString("Third");
                    case EIGHTH:
                        return rb.getString("Eight");
                }
            case 8:
                return group.getGroupAdmin();
        }
        return null;
    }

    public int getOwnerOfGroup(StudyGroup group){
        try{
            return owners.entrySet().stream().filter(x->x.getValue().contains(group.getId())).map(x->x.getKey()).toList().get(0);
        }catch(IndexOutOfBoundsException e){
            return -1;
        }
    }

    public StudyGroup getRow(int index){
        return groups.get(index);
    }

    @Override
    public String getColumnName(int column){
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (groups.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public void changeLocalization(Locale locale) {
        this.rb = ResourceBundle.getBundle("i18n.GuiLabels",locale);
        columns = new String[]{
                rb.getString("ID"),
                rb.getString("Name"),
                rb.getString("Coordinates"),
                rb.getString("CreationTime"),
                rb.getString("StudentsCount"),
                rb.getString("ExpelledStudentsCount"),
                rb.getString("ShouldBeExpelledCount"),
                rb.getString("Semester"),
                rb.getString("Admin")
        };
        SwingUtilities.invokeLater(this::fireTableDataChanged);
    }
    public void synchronize() {
        synchronized (db) {
            this.owners = db.getOwnersOfGroups();
            LinkedList<StudyGroup> newGroups = new LinkedList<StudyGroup>(db.showAllGroups());
            if(newGroups.equals(groups))
                return;
            groups=newGroups;
        }
        SwingUtilities.invokeLater(()->{
            viewer.saveState();
            fireTableDataChanged();
            viewer.recoveryState();
            map.repaint();
        });
    }

    public LinkedList<StudyGroup> getGroups(){
        return groups;
    }
}
