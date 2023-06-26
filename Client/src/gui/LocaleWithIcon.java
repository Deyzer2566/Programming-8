package gui;

import javax.swing.*;
import java.util.Locale;

public class LocaleWithIcon {
    private Locale locale;
    private ImageIcon imageIcon;
    public LocaleWithIcon(Locale locale, ImageIcon imageIcon){
        this.locale = locale;
        this.imageIcon = imageIcon;
    }

    public ImageIcon getImageIcon(){
        return imageIcon;
    }

    public Locale getLocale(){
        return locale;
    }
}
