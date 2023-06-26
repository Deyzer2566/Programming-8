package gui;

import i18n.Localizable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class JFrameWithLocalization extends JFrame implements Localizable {
    protected ResourceBundle rb;
    protected JComboBox<LocaleWithIcon> locales;
    protected static LocaleWithIcon localesArray[] = {
            new LocaleWithIcon(new Locale("ru"), new ImageIcon(
                    new ImageIcon("icons/ru.jpg")
                            .getImage()
                            .getScaledInstance(38, 25, java.awt.Image.SCALE_SMOOTH))),
            new LocaleWithIcon(Locale.US, new ImageIcon(
                    new ImageIcon("icons/usa.png")
                            .getImage()
                            .getScaledInstance(38, 25, java.awt.Image.SCALE_SMOOTH))),
            new LocaleWithIcon(new java.util.Locale("el"), new ImageIcon(
                    new ImageIcon("icons/gr.png")
                            .getImage()
                            .getScaledInstance(38, 25, java.awt.Image.SCALE_SMOOTH))),
            new LocaleWithIcon(new Locale("cs"), new ImageIcon(
                    new ImageIcon("icons/cze.png")
                            .getImage()
                            .getScaledInstance(38, 25, java.awt.Image.SCALE_SMOOTH)))
    };
    public JFrameWithLocalization() {
        setContentPane(new JPanel(new GridBagLayout()));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.gridx = 2;
        locales = new JComboBox<>(localesArray);
        locales.setRenderer(new LocaleWithIconComboBoxRenderer());
        locales.addItemListener(new LocaleComboBoxItemListener());
        add(locales,gbc);
        setTitle("8 лабораторная");
    }

    @Override
    public void changeLocalization(Locale locale){
        List<LocaleWithIcon> selectedLocale = Arrays.stream(localesArray).filter(x->x.getLocale()== locale).toList();
        ItemListener[] listeners = locales.getItemListeners();
        Arrays.stream(listeners).forEach(x->locales.removeItemListener(x));
        if(selectedLocale.size() == 1)
            locales.setSelectedItem(selectedLocale.get(0));
        Arrays.stream(listeners).forEach(x->locales.addItemListener(x));
    }
}