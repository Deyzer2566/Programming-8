package gui;

import data.StudyGroup;
import i18n.LocaleManager;
import i18n.Localizable;
import storage.Database;
import storage.GroupDidNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class RemoveActionListener extends JDialog implements ActionListener {
    StudyGroup selectedGroup;
    Database db;
    public RemoveActionListener(StudyGroup selectedGroup, Database db) {
        this.selectedGroup=selectedGroup;
        this.db=db;
    }

    public RemoveActionListener(Frame owner,StudyGroup selectedGroup,Database db) {
        super(owner);
        this.selectedGroup=selectedGroup;
        this.db=db;
    }

    public RemoveActionListener(Frame owner, boolean modal,StudyGroup selectedGroup,Database db) {
        super(owner, modal);
        this.selectedGroup=selectedGroup;
        this.db=db;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ResourceBundle rb = ResourceBundle.getBundle("i18n.GuiLabels",LocaleManager.getLocale());
        setTitle(rb.getString("AreYouSure"));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JButton yes = new JButton(rb.getString("Yes"));
        StudyGroup finalSelectedGroup = selectedGroup;
        yes.addActionListener(ex->{
            try{
                db.remove(finalSelectedGroup.getId());
            } catch (GroupDidNotFound exception){
                JOptionPane.showConfirmDialog(this,rb.getString("NoRights"));
            }
            dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
        });
        JButton no = new JButton(rb.getString("No"));
        no.addActionListener(ex->{
            dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
        });
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        add(yes);
        add(no);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }
}
