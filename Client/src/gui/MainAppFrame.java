package gui;

import command.ExecuteScriptCommand;
import command.InvalidCommandArgumentException;
import command.RemoveById;
import data.Coordinates;
import data.Semester;
import data.StudyGroup;
import i18n.LocaleManager;
import io.ConsoleIO;
import io.Writer;
import net.RemoteDatabaseWithAuth;
import net.RemoteDatabaseWithAuthAndInfoAboutGroupsOwners;
import storage.GroupDidNotFound;

import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;

public class MainAppFrame extends JFrameWithLocalization{
    JTabbedPane tp;
    ResourceBundle rb;
    JTable jTable;
    TableModelWithSortAndFilter table;
    JPanel mapPanel;
    JPanel buttonsPanel;
    JButton add;
    JButton addIfMax;
    JButton remove;
    JButton clear;
    JButton execute;
    JButton info;
//    HashMap<Integer,BufferedImage> colorsOfOwners;
    HashMap<Integer,Color> colorsOfOwners;
    JButton filter;
    JTextField filterValue;
    public MainAppFrame(RemoteDatabaseWithAuthAndInfoAboutGroupsOwners db) throws IOException {
        super();
        this.colorsOfOwners = new HashMap<>();
        tp  = new JTabbedPane();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=1;
        gbc.gridy=1;
        add(tp,gbc);
        jTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(jTable);
        final BufferedImage img = ImageIO.read(new File("itmo.png"));
        mapPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(img.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH),0,0,null);

                for(int i = 0;i<table.getRowCount();i++) {
                    Integer idOwner = table.getOwnerOfGroup(table.getRow(i));
                    if (!colorsOfOwners.containsKey(idOwner)) {
                        Random random = new Random();
                        Color color = new Color(random.nextInt() % 16777216);
                        colorsOfOwners.put(idOwner, color);
                    }
                    Coordinates coords = (Coordinates) table.getValueAt(i, 2);
                    g.setColor(colorsOfOwners.get(idOwner));
                    long size = (Long)table.getValueAt(i,4) - (Long)table.getValueAt(i,5) + 7;
                    if (size > 0) {
                        g.fillOval((int) (coords.getX().floatValue()), (int) (coords.getY().floatValue() / 38.0 * this.getHeight()), (int)size, (int)size);
                    }
                }
            }
        };
        table = new TableModelWithSortAndFilter(db,new TableViewer(jTable,scrollPane),mapPanel);
        jTable.setModel(table);
        jTable.setMaximumSize(new Dimension(1000,jTable.getHeight()));
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable jtable =(JTable) e.getSource();
                Point point = e.getPoint();
                int row = jtable.rowAtPoint(point);
                if (e.getClickCount() == 2) {
                    StudyGroup selectedGroup = null;
                    try{
                        selectedGroup = table.getRow(row);
                    } catch (IndexOutOfBoundsException | NullPointerException ex){
                        JOptionPane.showMessageDialog(MainAppFrame.this,rb.getString("ChooseARow"));
                        return;
                    }
                    try {
                        db.update(selectedGroup.getId(), selectedGroup);
                        new AboutGroupFrameWithUpdate(selectedGroup,db,mapPanel);
                    } catch (GroupDidNotFound ex){
                        new AboutGroupFrame(selectedGroup);
                    }

                }
            }
        });
        jTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = jTable.convertColumnIndexToModel(jTable.columnAtPoint(e.getPoint()));
                table.sort(index);
            }
        });
        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                StudyGroup group = null;
                Point p = e.getPoint();
                try {
                    group = (table.getGroups().stream().filter(g ->
                            p.distance(new Point(g.getCoordinates().getX().intValue() + 10, (int) (g.getCoordinates().getY() / 38.0 * mapPanel.getHeight() + 10.0))) < 10
                    ).toList().get(0));
                    if (e.getButton() == 1) {
                        if (e.getClickCount() != 2)
                            return;
                        try {
                            db.update(group.getId(), group);
                            new AboutGroupFrameWithUpdate(group, db, mapPanel);
                        } catch (GroupDidNotFound ex) {
                            new AboutGroupFrame(group);
                        }
                    } else if (e.getButton() == 3) {
                        new ContextMenu(MainAppFrame.this,group,db).show(mapPanel,p.x,p.y);
                    }
                } catch (IndexOutOfBoundsException ex){
                }
            }
        });
        tp.addTab("",scrollPane);
        tp.addTab("",mapPanel);
        gbc.gridy=0;
        add(new JLabel(db.getLogin()),gbc);
        setJMenuBar(new JMenuBar());
        gbc.gridy=2;
        filterValue = new JTextField(10);
        filter = new JButton();
        filter.addActionListener(l->{
            table.filter(filterValue.getText());
        });
        add(filterValue,gbc);
        gbc.gridy=3;
        add(filter,gbc);
        gbc.gridy=4;
        buttonsPanel = new JPanel(new GridLayout(2,3));
        add(buttonsPanel,gbc);
        add = new JButton();
        add.addActionListener(e->{
            new NewGroupFrame(db);
        });
        addIfMax = new JButton();
        addIfMax.addActionListener(e -> {
            new NewGroupIfMaxFrame(db);
        });
        remove= new JButton();
        remove.addActionListener(e->{
            StudyGroup selectedGroup = null;
            try{
                int row = jTable.convertRowIndexToModel(jTable.getSelectedRow());
                selectedGroup = table.getRow(row);
            } catch (IndexOutOfBoundsException | NullPointerException ex){
                JOptionPane.showMessageDialog(MainAppFrame.this,rb.getString("ChooseARow"));
                return;
            }
            new RemoveActionListener(selectedGroup, db).actionPerformed(e);
        });
        clear = new JButton();
        clear.addActionListener(e->{
            JDialog dialog = new JDialog(this,false);
            dialog.setTitle(rb.getString("AreYouSure"));
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JButton yes = new JButton(rb.getString("Yes"));
            yes.addActionListener(ex->{
                db.clear();
                dialog.dispatchEvent(new WindowEvent(dialog,WindowEvent.WINDOW_CLOSING));
            });
            JButton no = new JButton(rb.getString("No"));
            no.addActionListener(ex->{
                dialog.dispatchEvent(new WindowEvent(dialog,WindowEvent.WINDOW_CLOSING));
            });
            dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.X_AXIS));
            dialog.add(yes);
            dialog.add(no);
            dialog.setVisible(true);
            dialog.setLocationRelativeTo(null);
            dialog.pack();
        });
        execute =new JButton();
        execute.addActionListener(l->{
            JFileChooser chooser = new JFileChooser(".");
            chooser.showOpenDialog(this);
            try {
                new ExecuteScriptCommand(new Writer() {
                    @Override
                    public void write(String str) {

                    }

                    @Override
                    public void writeln(String str) {

                    }

                    @Override
                    public void writeError(String str) {
                        JOptionPane.showMessageDialog(MainAppFrame.this,rb.getString("ErrorDuringExecution"));
                    }

                    @Override
                    public void writeObject(Object obj) {
                    }
                }, db).execute(new String[]{chooser.getSelectedFile().getAbsolutePath()});
            } catch (InvalidCommandArgumentException e) {
                JOptionPane.showMessageDialog(this,rb.getString("ErrorDuringExecution"));
            }
        });
        info= new JButton();
        info.addActionListener(l->{
            JDialog dialog = new JDialog();
            dialog.setTitle(rb.getString("info"));
            dialog.setLayout(new BoxLayout(dialog.getContentPane(),BoxLayout.Y_AXIS));
            synchronized (db) {
                for (String str : db.getInfo().split("\n"))
                    dialog.add(new JLabel(str));
            }
            dialog.setVisible(true);
            dialog.pack();
        });
        buttonsPanel.add(add);
        buttonsPanel.add(addIfMax);
        buttonsPanel.add(remove);
        buttonsPanel.add(clear);
        buttonsPanel.add(execute);
        buttonsPanel.add(info);
    }

    @Override
    public void changeLocalization(Locale locale) {
        super.changeLocalization(locale);
        rb = ResourceBundle.getBundle("i18n.GuiLabels",locale);
        filter.setText(rb.getString("filter"));
        tp.setTitleAt(0,rb.getString("Table"));
        tp.setTitleAt(1,rb.getString("Map"));
        table.changeLocalization(locale);
        for(int i = 0;i<9;i++)
            jTable.getColumnModel().getColumn(i).setHeaderValue(table.getColumnName(i));
        add.setText(rb.getString("add"));
        addIfMax.setText(rb.getString("addIfMax"));
        remove.setText(rb.getString("remove"));
        clear.setText(rb.getString("clear"));
        execute.setText(rb.getString("execute"));
        info.setText(rb.getString("info"));
        pack();
        setLocationRelativeTo(null);
    }
}
