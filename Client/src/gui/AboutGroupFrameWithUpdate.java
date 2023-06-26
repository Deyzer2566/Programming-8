package gui;

import data.*;
import i18n.LocaleManager;
import storage.Database;
import storage.GroupDidNotFound;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class AboutGroupFrameWithUpdate extends EmptyAboutGroupFrame{
    protected JLabel idGroupLabel;
    protected JLabel idGroupField;
    protected JLabel creationTimeGroup;
    protected StudyGroup group;
    protected JLabel error;
    JButton update;
    enum ErrorTypes{
        None, NO_RIGHTS, FILL_GRAPH;
        public String getErrorMessage(ResourceBundle rb){
            if(this.equals(NO_RIGHTS)){
                return rb.getString("NoRights");
            }
            else if(this.equals(FILL_GRAPH)){
                return rb.getString("FillGraphs");
            }
            return "";
        }
    }
    ErrorTypes errorType;
    public AboutGroupFrameWithUpdate(StudyGroup group, Database db,JPanel map){
        this.group = group;
        errorType = ErrorTypes.None;
        error = new JLabel();
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
        update = new JButton();
        update.addActionListener(l -> {
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
            StudyGroup newGroup = new StudyGroup(group.getId(),nameOfGroup, coordinates, group.getCreationDate(),
                    studentsCount, expelledCount, shouldBeExpelled,semester, admin);
            new Thread(()-> {
                final int countOfSteps = 10;
                Coordinates step = new Coordinates((coordinates.getX()-group.getCoordinates().getX())/countOfSteps,
                        (coordinates.getY()-group.getCoordinates().getY())/countOfSteps);
                for(int i = 1;i<=10;i++){
                    StudyGroup newGroup1 = new StudyGroup(newGroup.getId(),newGroup.getName(),
                            new Coordinates(group.getCoordinates().getX()+step.getX()*i,group.getCoordinates().getY()+step.getY()*i),
                            newGroup.getCreationDate(),newGroup.getStudentsCount(),newGroup.getExpelledStudents(),
                            newGroup.getShouldBeExpelled(),newGroup.getSemesterEnum(),newGroup.getGroupAdmin());
                    synchronized (db) {
                        db.update(group.getId(), newGroup1);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
            LocaleManager.dropLocalizable(this);
            dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
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
        panel.add(createPanelWithComponents(new JComponent[]{update}));
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
        update.setText(rb.getString("Update"));
        idGroupLabel.setText(rb.getString("ID"));
        DateFormat f = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, newLocale);
        error.setText(errorType.getErrorMessage(rb));
        SimpleDateFormat sf = (SimpleDateFormat) f;
        String pattern = sf.toPattern();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern + " z", newLocale);
        creationTimeGroup.setText(rb.getString("CreationTime")+": "+
                group.getCreationDate().format(
                        DateTimeFormatter.ofPattern(sdf.toPattern(),newLocale)));
        pack();
    }
}
