package i18n;

import java.util.ListResourceBundle;

public class GuiLabels_en_US extends ListResourceBundle {
    @Override
    protected Object [][] getContents(){
        return contents;
    }

    private Object [][] contents={{"NotCorrectLoginOrPassword","Not correct login or password"},
            {"auth","Authorization"},
            {"reg","Registration"},{"login","Login"},{"password","Password"},
            {"NotCorrectLoginOrPassword","Not correct login or password"},
            {"ThereIsUserWithThisLogin","There is user with this login"},
            {"PasswordsNotEqual","Passwords is not equal"},
            {"RepeatPassword","Repeat password"},
            {"ID","ID"},{"Name","Name"},{"Coordinates","Coordinates"},
            {"CreationTime","Creation time"},{"StudentsCount","Students count"},
            {"ExpelledStudentsCount","Count of expelled students"},
            {"ShouldBeExpelledCount","Should be expelled count"},
            {"Semester","Term"},{"Admin","Admin"},{"Table","Table"},
            {"Map","Map"},{"add","Add"},{"addIfMax","Add, if max"}, {"remove","Remove"},
            {"clear","Clear"},{"execute","Execute script"},{"info","Information"},
            {"FillGraphs","Fill all gaps"}, {"First","First"},
            {"Third","Third"},{"Eight","Eight"},{"Red","Red"},{"Blue","Blue"},{"Brown","Brown"},
            {"Green","Green"},{"Orange","Orange"},{"EyeColor","Eye color"},{"HairColor","Hair color"},
            {"PersonName","Name of person"},{"Weight","Weight"},{"Nationality","Nationality"},
            {"NorthKorea","North Korea"}, {"SouthKorea","South Korea"}, {"UnitedKingdom","United Kingdom"},
            {"NoRights","You haven't rights to change this!"},{"Update","Update"}, {"ChooseARow","Choose a row!"},
            {"AreYouSure","Are you sure?"},{"Yes","Yes"},{"No","No"},
            {"ErrorDuringExecution","Error during script execution"},{"Info","Information"},
            {"ChooseAGroup","Select a group"}, {"filter","filter"}
    };
}
