package se.sahlgrenska.gui.Journal;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.Address;
import se.sahlgrenska.sjukhus.Journal;
import se.sahlgrenska.sjukhus.person.Gender;
import se.sahlgrenska.sjukhus.person.Person;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.patient.BloodType;
import se.sahlgrenska.sjukhus.person.patient.Disease;
import se.sahlgrenska.sjukhus.person.patient.Patient;

import javax.swing.*;
import javax.swing.JComboBox;
import java.awt.event.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;


public class JournalGUI extends HelperGUI {


    private JPanel MainPanel;
    private JPanel TopPanel;
    private JLabel JournalTitleLabel;
    private JPanel LeftPanel;
    private JPanel DataListPanel;
    private JLabel JournalDataLabel;
    private JLabel DiseaseDataLabel;
    private JPanel ButtonPanel;
    private JButton RaderaButton;
    private JButton ÅngraButton;
    private JButton SparaButton;
    private JButton AvbrytButton;
    private JPanel CommentPanel;
    private JTextArea KommentarTextArea;
    private JLabel KommentarLabel;
    private JPanel InputPanel;
    private JTextField firstNameTextField;
    private JTextField personNumberTextField;
    private JTextField phoneNumberTextField;
    private JComboBox diseaseComboBox;
    private JTextField conditionTextField;
    private JTextField RumTextField;
    private JTextField LäkareTextField;
    private JLabel FirstNameLabel;
    private JLabel PersonNummerLabel;
    private JLabel TelefonNummerLabel;
    private JLabel SjukdomLabel;
    private JLabel TillståndLabel;
    private JLabel LäkareLabel;
    private JList JournalDataList;
    private JScrollPane JournalDataScrollPane;
    private JScrollPane DiseaseDataScrollPane;
    private JList DiseaseDataList;
    private JScrollPane CommentScrollPane;
    private JLabel GenderLabel;
    private JLabel BloodTypeLabel;
    private JComboBox genderComboBox;
    private JComboBox bloodTypeComboBox;
    private JLabel LastNameLabel;
    private JTextField lastNameTextField;
    private JLabel CrticalConditionLabel;
    private JLabel DoctorTextLabel;
    private JCheckBox criticalTrueCheckBox;

    List<Patient> patientdatalist;
    List<Patient> patients;
    List<Disease> diseases;

    DefaultListModel dataList;

    Patient selectedPatient;

    Map<Patient, List<Journal>> journals = Driver.getHospital().getArchive().getJournals();

    public JournalGUI() {

        init(MainPanel, "Hantera journaler", Accessibility.DOCTOR);
        setSize(550, 600);
        DoctorTextLabel.setText(Driver.getCurrentUser().toString());
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); <--- Använd inte setDefaultCloseOperation!

        try {


            //fix
            dataList = new DefaultListModel();

            dataList.addAll(journals.keySet());

            JournalDataList.setModel(dataList);

        } catch (Exception dLE) {
            dLE.printStackTrace();
        }

        genderComboBox.setModel(new DefaultComboBoxModel<>(Gender.values()));
        bloodTypeComboBox.setModel(new DefaultComboBoxModel<>(BloodType.values()));
        String[] diseaseSelect = {"Förkyld", "Kattmat"};
        diseaseComboBox.setModel(new DefaultComboBoxModel(diseaseSelect));

        patients = Driver.getHospital().getArchive().getPatients().get(Driver.getCurrentUser());

        //Delete data from selected List, Option: DataList or DiseaseList.
        RaderaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JournalDataList.getSelectedValue() != null) {
                    //Removes a patient.
                    dataList.removeElement(JournalDataList.getSelectedValue());
                }
                else if (DiseaseDataList.getSelectedValue() != null) {
                    //Removes a disease.
                    //diseaseList.removeElement(DiseaseDataList.getSelectedValue());
                }
            }
        });

        //Saves different types of variable into object.
        SparaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String firstName =  firstNameTextField.getText();
                    String lastName = lastNameTextField.getText();
                    String personN = personNumberTextField.getText();
                    String phoneN = phoneNumberTextField.getText();
                    String genderType = genderComboBox.getSelectedItem().toString();
                    String  bloodType = bloodTypeComboBox.getSelectedItem().toString();
                    Object disease = diseaseComboBox.getSelectedItem();
                    String condition = conditionTextField.getText();
                    boolean isCritical = criticalTrueCheckBox.isSelected();
                    String comment = KommentarTextArea.getText();
                    Gender gender = Gender.valueOf(genderType);
                    BloodType bloodType1 = BloodType.valueOf(bloodType);
                    //implement ComboBox to have select value.

                    Address address = new Address();
                    Person person = new Person(firstName, lastName, personN, gender, phoneN, address);


                    //ta patienten från listan och använd dess setters istället.

                    selectedPatient.setCondition(condition);

                    Journal journal = new Journal(selectedPatient, LocalDateTime.now(), comment, Driver.getCurrentUser());

                    Driver.getHospital().getArchive().AddJournal(journal, selectedPatient);

                } catch (NumberFormatException Ne) {
                    Ne.printStackTrace();
                } catch (Exception E) {
                    E.printStackTrace();
                }
                Driver.getHospital().getArchive().printJournals();

            }
        });

        //Cancel the window and move back to the menu.
        AvbrytButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Takes you back to main menu.
                setVisible(false);
                Driver.getMainMenu().setVisible(true);
            }
        });

        //Add function mouseclick to Journaldatalist when select item in list.
        JournalDataList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println(JournalDataList.getSelectedValue().toString());
            }
        });

        JournalDataList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedPatient = (Patient)JournalDataList.getSelectedValue();
                firstNameTextField.setText(selectedPatient.getFirstName());
                lastNameTextField.setText(selectedPatient.getLastName());
                personNumberTextField.setText(selectedPatient.getPersonNumber());
                phoneNumberTextField.setText(selectedPatient.getPhoneNumber());
                bloodTypeComboBox.setSelectedItem(selectedPatient.getBloodType());
                genderComboBox.setSelectedItem(selectedPatient.getGender());
                diseaseComboBox.setSelectedItem(selectedPatient.getDiseases());
                conditionTextField.setText(selectedPatient.getCondition());
                criticalTrueCheckBox.setSelected(selectedPatient.isCriticalCondition());

            }
        });
    }

    //Makes it possible to load the Journal-list into Journaldatalist, otherwise it was not possible.
    public void LoadJournal() {
        List<Journal> journals = new ArrayList<Journal>();
        for (Patient patient : patients) {
            journals.addAll(Driver.getHospital().getArchive().getJournals().get(patient));
            JLabel label = new JLabel(patient.getFirstName());
            JournalDataList.add(label);
        }
    }
}