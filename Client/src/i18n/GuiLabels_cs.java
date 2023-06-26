package i18n;

import java.util.ListResourceBundle;

public class GuiLabels_cs extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {{"NotCorrectLoginOrPassword","Nesprávné přihlašovací údaje"},
            {"auth","Autorizace"},
            {"reg","Registrace"},{"login","Přihlašovací jméno"},{"password","Heslo"},
            {"ThereIsUserWithThisLogin","Uživatel s tímto přihlašovacím jménem již existuje"},
            {"PasswordsNotEqual","Hesla se neshodují!"},
            {"RepeatPassword","Zopakujte heslo"},
            {"ID","ID"},{"Name","Název"},{"Coordinates","Souřadnice"},
            {"CreationTime","Čas vytvoření"},{"StudentsCount","Počet studentů"},
            {"ExpelledStudentsCount","Počet vyloučených studentů"},
            {"ShouldBeExpelledCount","Počet studentů na PPA"},
            {"Semester","Semestr"},{"Admin","Admin"},{"Table","Tabulka"},
            {"Map","Mapa"},{"add","Přidat"},{"addIfMax","Přidat, pokud je maximální"}, {"remove","Odstranit"},
            {"clear","Vyčistit"},{"execute","Vykonat skript"},{"info","Informace"},
            {"FillGraphs","Vyplňte všechna povinná pole"}, {"First","První"},
            {"Third","Třetí"},{"Eight","Osmý"}, {"Red","Červený"},{"Blue","Modrý"},{"Brown","Hnědý"},
            {"Green","Zelený"},{"Orange","Oranžový"},{"EyeColor","Barva očí"},{"HairColor","Barva vlasů"},
            {"PersonName","Jméno"},{"Weight","Váha"},{"Nationality","Národnost"},
            {"NorthKorea","Severní Korea"}, {"SouthKorea","Jižní Korea"}, {"UnitedKingdom","Spojené království"},
            {"NoRights","Nemáte oprávnění k úpravám"},{"Update","Aktualizovat"}, {"ChooseARow","Vyberte řádek!"},
            {"AreYouSure","Jste si jisti?"},{"Yes","Ano"},{"No","Ne"},
            {"ErrorDuringExecution","Chyba při provádění skriptu"},
            {"ChooseAGroup","Vyberte skupinu"}, {"filter","čistič"}
    };
}