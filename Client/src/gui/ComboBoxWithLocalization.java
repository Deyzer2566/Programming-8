package gui;

import i18n.Localizable;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class ComboBoxWithLocalization<E> extends JComboBox implements Localizable {
    LabelWithData<E>[] elements;
    String [] keys;
    public ComboBoxWithLocalization(LabelWithData<E>[] elements, String [] keys){
        super(elements);
        this.elements = elements;
        this.keys = keys;
    }

    @Override
    public void changeLocalization(Locale locale) {
        ResourceBundle rb = ResourceBundle.getBundle("i18n.GuiLabels",locale);
        for(int i = 0; i< keys.length; i++) {
            elements[i].getLabel().setText(rb.getString(keys[i]));
        }
        updateUI();
    }
}
