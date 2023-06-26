package gui;

import javax.swing.*;
import java.awt.*;

public class LocaleWithIconComboBoxRenderer extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        LocaleWithIcon locale = (LocaleWithIcon) value;
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        ImageIcon icon = locale.getImageIcon();
        setIcon(icon);
        return this;
    }
}
