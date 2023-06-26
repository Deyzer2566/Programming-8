package i18n;

import java.util.ListResourceBundle;

public class GuiLabels_el extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {{"NotCorrectLoginOrPassword","Λανθασμένο όνομα χρήστη ή κωδικός πρόσβασης"},
            {"auth","Πιστοποίηση ταυτότητας"},
            {"reg","Εγγραφή"},{"login","Όνομα χρήστη"},{"password","Κωδικός πρόσβασης"},
            {"ThereIsUserWithThisLogin","Υπάρχει ήδη χρήστης με αυτό το όνομα χρήστη"},
            {"PasswordsNotEqual","Οι κωδικοί πρόσβασης δεν ταιριάζουν!"},
            {"RepeatPassword","Επαναλάβετε τον κωδικό πρόσβασης"},
            {"ID","ID"},{"Name","Όνομα"},{"Coordinates","Συντεταγμένες"},
            {"CreationTime","Χρόνος δημιουργίας"},{"StudentsCount","Αριθμός μαθητών"},
            {"ExpelledStudentsCount","Αριθμός αποβλήτων μαθητών"},
            {"ShouldBeExpelledCount","Αριθμός μαθητών στο ΠΠΑ"},
            {"Semester","Εξάμηνο"},{"Admin","Διαχειριστής"},{"Table","Πίνακας"},
            {"Map","Χάρτης"},{"add","Προσθήκη"},{"addIfMax","Προσθήκη, αν είναι μέγιστο"}, {"remove","Αφαίρεση"},
            {"clear","Καθαρισμός"},{"execute","Εκτέλεση σεναρίου"},{"info","Πληροφορίες"},
            {"FillGraphs","Συμπληρώστε όλα τα υποχρεωτικά πεδία"}, {"First","Πρώτος"},
            {"Third","Τρίτος"},{"Eight","Όγδοος"}, {"Red","Κόκκινο"},{"Blue","Μπλε"},{"Brown","Καφέ"},
            {"Green","Πράσινο"},{"Orange","Πορτοκαλί"},{"EyeColor","Χρώμα ματιών"},{"HairColor","Χρώμα μαλλιών"},
            {"PersonName","Όνομα προσώπου"},{"Weight","Βάρος"},{"Nationality","Εθνικότητα"},
            {"NorthKorea","Βόρεια Κορέα"}, {"SouthKorea","Νότια Κορέα"}, {"UnitedKingdom","Ηνωμένο Βασίλειο"},
            {"NoRights","Δεν έχετε δικαίωμα να επεξεργαστείτε"},{"Update","Ενημέρωση"}, {"ChooseARow","Επιλέξτε μια σειρά!"},
            {"AreYouSure","Είστε σίγουροι;"},{"Yes","Ναι"},{"No","Όχι"},
            {"ErrorDuringExecution","Σφάλμα κατά τη διάρκεια εκτέλεσης του σεναρίου"},
            {"ChooseAGroup","Επιλέξτε μια ομάδα"},{"filter","φίλτρο"}
    };
}
