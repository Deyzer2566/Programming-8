package gui;

import data.*;
import i18n.LocaleManager;
import storage.Database;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class NewGroupFrame extends EmptyAboutGroupFrame{
    JLabel error;
    enum ErrorTypes {
        None, FILL_GRAPH;
        public String getErrorMessage(ResourceBundle rb){
            if(this.equals(FILL_GRAPH)){
                return rb.getString("FillGraphs");
            }
            return "";
        }
    }
    ErrorTypes errorType;
    JButton add;
    public NewGroupFrame(Database db){
        errorType = ErrorTypes.None;
        error = new JLabel();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        nameGroup = new JLabel();
        nameGroupField = new JTextField(10);
        coordsGroup = new JLabel();
        xCoord = new JTextField(5);
        yCoord = new JTextField(5);
        studentsCount = new JLabel();
        studentsCountField = new JTextField(5);
        expelledCount = new JLabel();
        expelledCountField = new JTextField(5);
        shouldBeExpelledCount = new JLabel();
        shouldBeExpelledCountField = new JTextField(5);
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
        weightLabel = new JLabel();
        weightField = new JTextField(5);
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
        add = new JButton();
        add.addActionListener(l -> {
            Float x = null;
            try{
                x=Float.valueOf(xCoord.getText());
                xCoord.setBorder(new LineBorder(Color.black,2));
            } catch (NumberFormatException e){
                error.setText(rb.getString("FillGraphs"));
                errorType = ErrorTypes.FILL_GRAPH;
                error.setVisible(true);
                xCoord.setBorder(new LineBorder(Color.red,2));
                pack();
                return;
            }

            Double y = null;
            try{
                y = Double.valueOf(yCoord.getText());
                yCoord.setBorder(new LineBorder(Color.black,2));
            } catch (NumberFormatException e){
                error.setText(rb.getString("FillGraphs"));
                errorType = ErrorTypes.FILL_GRAPH;
                error.setVisible(true);
                yCoord.setBorder(new LineBorder(Color.red,2));
                pack();
                return;
            }
            Coordinates coordinates = new Coordinates(x,y);

            String adminName = adminNameField.getText();
            if(adminName.isEmpty()){
                error.setText(rb.getString("FillGraphs"));
                errorType = ErrorTypes.FILL_GRAPH;
                error.setVisible(true);
                adminNameField.setBorder(new LineBorder(Color.red,2));
                pack();
                return;
            }
            adminNameField.setBorder(new LineBorder(Color.black,2));
            int weight = 0;
            try{
                weight = Integer.parseInt(weightField.getText());
            } catch (NumberFormatException e){
                error.setText(rb.getString("FillGraphs"));
                errorType = ErrorTypes.FILL_GRAPH;
                error.setVisible(true);
                weightField.setBorder(new LineBorder(Color.red,2));
                pack();
                return;
            }
            weightField.setBorder(new LineBorder(Color.black,2));
            data.Color eyeColor = ((LabelWithData<data.Color>)(eyeColorComboBox.getSelectedItem())).getObj();
            data.Color hairColor = ((LabelWithData<data.Color>)(hairColorComboBox.getSelectedItem())).getObj();
            data.Country nationality = ((LabelWithData<data.Country>)(nationalityComboBox.getSelectedItem())).getObj();
            Person admin = new Person(adminName,weight,eyeColor,hairColor,nationality);

            String nameOfGroup = nameGroupField.getText();
            if(nameOfGroup.isEmpty()){
                error.setText(rb.getString("FillGraphs"));
                errorType = ErrorTypes.FILL_GRAPH;
                error.setVisible(true);
                nameGroupField.setBorder(new LineBorder(Color.red,2));
                pack();
                return;
            }
            nameGroupField.setBorder(new LineBorder(Color.black,2));
            Long studentsCount = null;
            try{
                studentsCount = Long.parseLong(studentsCountField.getText());
                studentsCountField.setBorder(new LineBorder(Color.black,2));
            } catch (NumberFormatException e){
                error.setText(rb.getString("FillGraphs"));
                errorType = ErrorTypes.FILL_GRAPH;
                error.setVisible(true);
                studentsCountField.setBorder(new LineBorder(Color.red,2));
                pack();
                return;
            }
            Long expelledCount = null;
            try{
                expelledCount = Long.parseLong(expelledCountField.getText());
                expelledCountField.setBorder(new LineBorder(Color.black,2));
            } catch (NumberFormatException e){
                error.setText(rb.getString("FillGraphs"));
                errorType = ErrorTypes.FILL_GRAPH;
                error.setVisible(true);
                expelledCountField.setBorder(new LineBorder(Color.red,2));
                pack();
                return;
            }
            Integer shouldBeExpelled = null;
            try{
                shouldBeExpelled = Integer.parseInt(shouldBeExpelledCountField.getText());
                shouldBeExpelledCountField.setBorder(new LineBorder(Color.black,2));
                error.setVisible(false);
            } catch (NumberFormatException e){
                error.setText(rb.getString("FillGraphs"));
                errorType = ErrorTypes.FILL_GRAPH;
                error.setVisible(true);
                shouldBeExpelledCountField.setBorder(new LineBorder(Color.red,2));
                pack();
                return;
            }
            data.Semester semester = ((LabelWithData<data.Semester>)(semesterComboBox.getSelectedItem())).getObj();
            StudyGroup newGroup = new StudyGroup(0,nameOfGroup, coordinates, ZonedDateTime.now(),
                    studentsCount, expelledCount, shouldBeExpelled,semester, admin);
            db.add(newGroup);
            LocaleManager.dropLocalizable(this);
            dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.add(createPanelWithComponents(new JComponent[]{nameGroup,nameGroupField}));
        panel.add(createPanelWithComponents(new JComponent[]{coordsGroup,new JLabel("X"),xCoord,new JLabel("Y"),yCoord}));
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
        panel.add(createPanelWithComponents(new JComponent[]{add}));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy=1;
        gbc.gridx=1;
        add(panel,gbc);
        LocaleManager.registerNewLocalizable(this);
        pack();
        setVisible(true);
        setTitle("Добавить группу");
    }

    @Override
    public void changeLocalization(Locale newLocale) {
        super.changeLocalization(newLocale);
        add.setText(rb.getString("add"));
        error.setText(errorType.getErrorMessage(rb));
        pack();
    }
}
