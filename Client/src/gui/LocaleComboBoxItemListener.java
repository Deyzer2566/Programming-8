package gui;

import i18n.LocaleManager;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LocaleComboBoxItemListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
        LocaleManager.changeLocalization(((LocaleWithIcon)e.getItem()).getLocale());
    }
}
