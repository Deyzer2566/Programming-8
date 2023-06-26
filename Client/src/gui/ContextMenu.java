package gui;

import data.StudyGroup;
import i18n.LocaleManager;
import storage.Database;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class ContextMenu extends JPopupMenu {
    public ContextMenu(Frame parent, StudyGroup group, Database db){
        ResourceBundle rb = ResourceBundle.getBundle("i18n.GuiLabels", LocaleManager.getLocale());
        JMenuItem remove = new JMenuItem(rb.getString("remove"));
        add(remove);
        remove.addActionListener(new RemoveActionListener(parent, group, db));
    }
}
