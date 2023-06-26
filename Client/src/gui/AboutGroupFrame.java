package gui;

import data.Country;
import data.Semester;
import data.StudyGroup;
import i18n.LocaleManager;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AboutGroupFrame extends EmptyAboutGroupFrame{
    protected JLabel idGroupLabel;
    protected JLabel idGroupField;
    protected JLabel creationTimeGroup;
    protected StudyGroup group;
    public AboutGroupFrame(StudyGroup group){
        this.group = group;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        idGroupLabel = new JLabel();
        idGroupField = new JLabel(Long.valueOf(group.getId()).toString());
        nameGroup = new JLabel();
        nameGroupField = new JTextField(10);
        nameGroupField.setText(group.getName());
        coordsGroup = new JLabel();
        xCoord = new JTextField(5);
        xCoord.setText(group.getCoordinates().getX().toString());
        yCoord = new JTextField(5);
        yCoord.setText(group.getCoordinates().getY().toString());
        creationTimeGroup = new JLabel();
        studentsCount = new JLabel();
        studentsCountField = new JTextField(5);
        studentsCountField.setText(group.getStudentsCount().toString());
        expelledCount = new JLabel();
        expelledCountField = new JTextField(5);
        expelledCountField.setText(group.getExpelledStudents().toString());
        shouldBeExpelledCount = new JLabel();
        shouldBeExpelledCountField = new JTextField(5);
        shouldBeExpelledCountField.setText(group.getShouldBeExpelled().toString());
        semester = new JLabel();
        semesterComboBox = new ComboBoxWithLocalization<>(new LabelWithData[]{
                new LabelWithData<>(new JLabel(), Semester.FIRST),
                new LabelWithData<>(new JLabel(),Semester.THIRD),
                new LabelWithData<>(new JLabel(), Semester.EIGHTH)
        },new String[]{"First","Third","Eight"});
        semesterComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            LabelWithData<Semester> selected_Sem = (LabelWithData<Semester>)value;
            return selected_Sem.getLabel();
        });
        admin = new JLabel();
        adminNameLabel = new JLabel();
        adminNameField = new JTextField(7);
        adminNameField.setText(group.getGroupAdmin().getName());
        weightLabel = new JLabel();
        weightField = new JTextField(5);
        weightField.setText(Integer.valueOf(group.getGroupAdmin().getWeight()).toString());
        eyeColorLabel = new JLabel();
        eyeColorComboBox = new ComboBoxWithLocalization<>(new LabelWithData[]{
                new LabelWithData<>(new JLabel(),data.Color.RED),
                new LabelWithData<>(new JLabel(),data.Color.BLUE),
                new LabelWithData<>(new JLabel(), data.Color.BROWN),
                new LabelWithData<>(new JLabel(),data.Color.GREEN),
                new LabelWithData<>(new JLabel(), data.Color.ORANGE)
        },new String[]{"Red","Blue","Brown","Green","Orange"});
        eyeColorComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            LabelWithData<data.Color> selected_Sem = (LabelWithData<data.Color>)value;
            return selected_Sem.getLabel();
        });
        hairColorLabel = new JLabel();
        hairColorComboBox = new ComboBoxWithLocalization<>(new LabelWithData[]{
                new LabelWithData<>(new JLabel(),data.Color.RED),
                new LabelWithData<>(new JLabel(),data.Color.BLUE),
                new LabelWithData<>(new JLabel(), data.Color.BROWN),
                new LabelWithData<>(new JLabel(),data.Color.GREEN),
                new LabelWithData<>(new JLabel(), data.Color.ORANGE)
        },new String[]{"Red","Blue","Brown","Green","Orange"});
        hairColorComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            LabelWithData<data.Color> selected_Sem = (LabelWithData<data.Color>)value;
            return selected_Sem.getLabel();
        });
        nationalityLabel = new JLabel();
        nationalityComboBox = new ComboBoxWithLocalization<>(new LabelWithData[]{
                new LabelWithData<>(new JLabel(), Country.NORTH_KOREA),
                new LabelWithData<>(new JLabel(),Country.SOUTH_KOREA),
                new LabelWithData<>(new JLabel(), Country.UNITED_KINGDOM)
        },new String[]{"NorthKorea","SouthKorea","UnitedKingdom"});
        nationalityComboBox.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            LabelWithData<Country> selected_Sem = (LabelWithData<Country>)value;
            return selected_Sem.getLabel();
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.add(createPanelWithComponents(new JComponent[]{idGroupLabel,idGroupField}));
        panel.add(createPanelWithComponents(new JComponent[]{nameGroup,nameGroupField}));
        panel.add(createPanelWithComponents(new JComponent[]{coordsGroup,new JLabel("X"),xCoord,new JLabel("Y"),yCoord}));
        panel.add(createPanelWithComponents(new JComponent[]{creationTimeGroup,}));
        panel.add(createPanelWithComponents(new JComponent[]{studentsCount,studentsCountField}));
        panel.add(createPanelWithComponents(new JComponent[]{expelledCount,expelledCountField}));
        panel.add(createPanelWithComponents(new JComponent[]{shouldBeExpelledCount,shouldBeExpelledCountField}));
        panel.add(createPanelWithComponents(new JComponent[]{semester, semesterComboBox}));
        panel.add(createPanelWithComponents(new JComponent[]{admin}));
        panel.add(createPanelWithComponents(new JComponent[]{adminNameLabel,adminNameField}));
        panel.add(createPanelWithComponents(new JComponent[]{weightLabel,weightField}));
        panel.add(createPanelWithComponents(new JComponent[]{eyeColorLabel,eyeColorComboBox}));
        panel.add(createPanelWithComponents(new JComponent[]{hairColorLabel,hairColorComboBox}));
        panel.add(createPanelWithComponents(new JComponent[]{nationalityLabel,nationalityComboBox}));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy=1;
        gbc.gridx=1;
        add(panel,gbc);
        pack();
        setVisible(true);
        LocaleManager.registerNewLocalizable(this);
        setTitle("О группе");
    }

    @Override
    public void changeLocalization(Locale newLocale) {
        super.changeLocalization(newLocale);
        idGroupLabel.setText(rb.getString("ID"));
        DateFormat f = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, newLocale);
        SimpleDateFormat sf = (SimpleDateFormat) f;
        String pattern = sf.toPattern();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern + " z", newLocale);
        creationTimeGroup.setText(rb.getString("CreationTime")+": "+
                group.getCreationDate().format(
                        DateTimeFormatter.ofPattern(sdf.toPattern(),newLocale)));
        pack();
    }
}
