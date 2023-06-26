package i18n;

import java.util.LinkedList;
import java.util.Locale;

public class LocaleManager {
    private static Locale locale;
    private static LinkedList<Localizable> localizableObjects;

    public LocaleManager(){
        locale = Locale.getDefault();
        localizableObjects=new LinkedList<>();
    }
    public static void changeLocalization(Locale newLocale){
        locale = newLocale;
        for (Localizable localizable: localizableObjects){
            localizable.changeLocalization(newLocale);
        }
    }
    public static Locale getLocale(){
        return locale;
    }
    public static void registerNewLocalizable(Localizable newLocalizable){
        localizableObjects.add(newLocalizable);
        newLocalizable.changeLocalization(locale);
    }

    public static void dropLocalizable(Localizable localizable){
        localizableObjects.remove(localizable);
    }

}
