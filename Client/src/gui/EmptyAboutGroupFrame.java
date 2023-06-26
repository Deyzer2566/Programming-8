package gui;


import data.Country;
import data.Semester;
import i18n.LocaleManager;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class EmptyAboutGroupFrame extends JFrameWithLocalization {
    protected JLabel nameGroup;
    protected JTextField nameGroupField;
    protected JLabel coordsGroup;
    protected JTextField xCoord;
    protected JTextField yCoord;
    protected JLabel studentsCount;
    protected JTextField studentsCountField;
    protected JLabel expelledCount;
    protected JTextField expelledCountField;
    protected JLabel shouldBeExpelledCount;
    protected JTextField shouldBeExpelledCountField;
    protected JLabel semester;
    protected ComboBoxWithLocalization<Semester> semesterComboBox;
    protected JLabel admin;
    protected JLabel adminNameLabel;
    protected JTextField adminNameField;
    protected JLabel weightLabel;
    protected JTextField weightField;
    protected JLabel eyeColorLabel;
    protected ComboBoxWithLocalization<data.Color> eyeColorComboBox;
    protected JLabel hairColorLabel;
    protected ComboBoxWithLocalization<data.Color> hairColorComboBox;
    protected JLabel nationalityLabel;
    protected ComboBoxWithLocalization<data.Country> nationalityComboBox;
//    public EmptyAboutGroupFrame(){
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
//        nameGroup = new JLabel();
//        nameGroupField = new JTextField(10);
//        coordsGroup = new JLabel();
//        xCoord = new JTextField(5);
//        yCoord = new JTextField(5);
//        studentsCount = new JLabel();
//        studentsCountField = new JTextField(5);
//        expelledCount = new JLabel();
//        expelledCountField = new JTextField(5);
//        shouldBeExpelledCount = new JLabel();
//        shouldBeExpelledCountField = new JTextField(5);
//        semester = new JLabel();
//        semesterComboBox = new ComboBoxWithLocalization<>(new LabelWithData[]{
//                new LabelWithData<>(new JLabel(),Semester.FIRST),
//                new LabelWithData<>(new JLabel(),Semester.THIRD),
//                new LabelWithData<>(new JLabel(), Semester.EIGHTH)
//        },new String[]{"First","Third","Eight"});
//        semesterComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
//            LabelWithData<Semester> selected_Sem = (LabelWithData<Semester>)value;
//            return selected_Sem.getLabel();
//        });
//        admin = new JLabel();
//        adminNameLabel = new JLabel();
//        adminNameField = new JTextField(7);
//        weightLabel = new JLabel();
//        weightField = new JTextField(5);
//        eyeColorLabel = new JLabel();
//        eyeColorComboBox = new ComboBoxWithLocalization<>(new LabelWithData[]{
//                new LabelWithData<>(new JLabel(),data.Color.RED),
//                new LabelWithData<>(new JLabel(),data.Color.BLUE),
//                new LabelWithData<>(new JLabel(), data.Color.BROWN),
//                new LabelWithData<>(new JLabel(),data.Color.GREEN),
//                new LabelWithData<>(new JLabel(), data.Color.ORANGE)
//        },new String[]{"Red","Blue","Brown","Green","Orange"});
//        eyeColorComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
//            LabelWithData<data.Color> selected_Sem = (LabelWithData<data.Color>)value;
//            return selected_Sem.getLabel();
//        });
//        hairColorLabel = new JLabel();
//        hairColorComboBox = new ComboBoxWithLocalization<>(new LabelWithData[]{
//                new LabelWithData<>(new JLabel(),data.Color.RED),
//                new LabelWithData<>(new JLabel(),data.Color.BLUE),
//                new LabelWithData<>(new JLabel(), data.Color.BROWN),
//                new LabelWithData<>(new JLabel(),data.Color.GREEN),
//                new LabelWithData<>(new JLabel(), data.Color.ORANGE)
//        },new String[]{"Red","Blue","Brown","Green","Orange"});
//        hairColorComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
//            LabelWithData<data.Color> selected_Sem = (LabelWithData<data.Color>)value;
//            return selected_Sem.getLabel();
//        });
//        nationalityLabel = new JLabel();
//        nationalityComboBox = new ComboBoxWithLocalization<>(new LabelWithData[]{
//                new LabelWithData<>(new JLabel(), Country.NORTH_KOREA),
//                new LabelWithData<>(new JLabel(),Country.SOUTH_KOREA),
//                new LabelWithData<>(new JLabel(), Country.UNITED_KINGDOM)
//        },new String[]{"NorthKorea","SouthKorea","UnitedKingdom"});
//        nationalityComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
//            LabelWithData<Country> selected_Sem = (LabelWithData<Country>)value;
//            return selected_Sem.getLabel();
//        });
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        panel.add(createPanelWithComponents(new JComponent[]{nameGroup,nameGroupField}));
//        panel.add(createPanelWithComponents(new JComponent[]{coordsGroup,new JLabel("X"),xCoord,new JLabel("Y"),yCoord}));
//        panel.add(createPanelWithComponents(new JComponent[]{studentsCount,studentsCountField}));
//        panel.add(createPanelWithComponents(new JComponent[]{expelledCount,expelledCountField}));
//        panel.add(createPanelWithComponents(new JComponent[]{shouldBeExpelledCount,shouldBeExpelledCountField}));
//        panel.add(createPanelWithComponents(new JComponent[]{semester, semesterComboBox}));
//        panel.add(createPanelWithComponents(new JComponent[]{admin}));
//        panel.add(createPanelWithComponents(new JComponent[]{adminNameLabel,adminNameField}));
//        panel.add(createPanelWithComponents(new JComponent[]{weightLabel,weightField}));
//        panel.add(createPanelWithComponents(new JComponent[]{eyeColorLabel,eyeColorComboBox}));
//        panel.add(createPanelWithComponents(new JComponent[]{hairColorLabel,hairColorComboBox}));
//        panel.add(createPanelWithComponents(new JComponent[]{nationalityLabel,nationalityComboBox}));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridy=1;
//        gbc.gridx=1;
//        add(panel,gbc);
//        pack();
//        setVisible(true);
//    }

    protected JPanel createPanelWithComponents(JComponent[] components){
        JPanel ret = new JPanel();
        for(JComponent component:components){
            ret.add(component);
        }
        return ret;
    }

    @Override
    public void changeLocalization(Locale newLocale) {
        super.changeLocalization(newLocale);
        this.rb = ResourceBundle.getBundle("i18n.GuiLabels",newLocale);
        nameGroup.setText(rb.getString("Name")+":");
        coordsGroup.setText(rb.getString("Coordinates")+":");
        studentsCount.setText(rb.getString("StudentsCount")+":");
        expelledCount.setText(rb.getString("ExpelledStudentsCount")+":");
        shouldBeExpelledCount.setText(rb.getString("ShouldBeExpelledCount")+":");
        semester.setText(rb.getString("Semester")+":");
        semesterComboBox.changeLocalization(newLocale);
        admin.setText(rb.getString("Admin")+":");
        adminNameLabel.setText(rb.getString("PersonName"));
        weightLabel.setText(rb.getString("Weight"));
        eyeColorLabel.setText(rb.getString("EyeColor"));
        eyeColorComboBox.changeLocalization(newLocale);
        hairColorLabel.setText(rb.getString("HairColor"));
        hairColorComboBox.changeLocalization(newLocale);
        nationalityLabel.setText(rb.getString("Nationality"));
        nationalityComboBox.changeLocalization(newLocale);
    }
}
