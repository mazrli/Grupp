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

import javax.management.Notification;
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
    private JTextField FirstNameTextField;
    private JTextField PersonNummerTextField;
    private JTextField TelefonNummerTextField;
    private JComboBox SjukdomComboBox;
    private JTextField TillståndTextField;
    private JTextField RumTextField;
    private JTextField LäkareTextField;
    private JLabel FirstNameLabel;
    private JLabel PersonNummerLabel;
    private JLabel TelefonNummerLabel;
    private JLabel SjukdomLabel;
    private JLabel TillståndLabel;
    private JLabel RumLabel;
    private JLabel LäkareLabel;
    private JList JournalDataList;
    private JScrollPane JournalDataScrollPane;
    private JScrollPane DiseaseDataScrollPane;
    private JList DiseaseDataList;
    private JScrollPane CommentScrollPane;
    private JLabel GenderLabel;
    private JLabel BloodTypeLabel;
    private JComboBox GenderComboBox;
    private JComboBox BloodTypeComboBox;
    private JLabel LastNameLabel;
    private JTextField LastNameTextField;

    List<Patient> patientdatalist;

    List<Patient> patients;
    List<Disease> diseases;


    Journal journalList;
    Patient patientList;

    Map<Patient, List<Journal>> journals = Driver.getHospital().getArchive().getJournals();

    public JournalGUI() {

        init(MainPanel, "Hantera journaler", Accessibility.DOCTOR);
        setSize(550, 600);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); <--- Använd inte setDefaultCloseOperation!

        //Gernerates a dummylist of patients to test remove button
        /*faultListModel dataList = new DefaultListModel();
        for (int i = 0; i < 5; i++) {
            JLabel label = new JLabel("Hasse" + " " + i);
            dataList.add(i, label.getText());
        }*/
        try {


            //fix
            DefaultListModel dataList = new DefaultListModel();

            dataList.addAll(journals.keySet());

            JournalDataList.setModel(dataList);
            
        } catch (Exception dLE) {
            dLE.printStackTrace();
        }

        // Driver.getHospital().getPatients().toArray()

        /*DefaultListModel dataPatientList = new DefaultListModel();
        dataPatientList.addElement(journals);
        JournalDataList.setModel(dataPatientList);*/

        //Closest I got to find a object to add into JournalDataList.
        //JournalDataList.setModel(new DefaultComboBoxModel(Driver.getHospital().getPatients().toArray()));

        //Calls enum values to it selected combobox.
        GenderComboBox.setModel(new DefaultComboBoxModel<>(Gender.values()));
        BloodTypeComboBox.setModel(new DefaultComboBoxModel<>(BloodType.values()));

        DefaultComboBoxModel diseaseComboBox = new DefaultComboBoxModel();

        SjukdomComboBox.setModel(new DefaultComboBoxModel());

        patients = Driver.getHospital().getArchive().getPatients().get(Driver.getCurrentUser());

        //Delete data from selected List, Option: DataList or DiseaseList.
        RaderaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JournalDataList.getSelectedValue() != null) {
                    //Removes a patient.
                    //dataList.removeElement(JournalDataList.getSelectedValue());
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
                    String firstName =  FirstNameTextField.getText();
                    String lastName = LastNameTextField.getText();
                    String personN = PersonNummerTextField.getText();
                    String phoneN = TelefonNummerTextField.getText();
                    String genderType = GenderComboBox.getSelectedItem().toString();
                    String  bloodType = BloodTypeComboBox.getSelectedItem().toString();
                    Object disease = SjukdomComboBox.getSelectedItem();
                    String condition = TillståndTextField.getText();
                    Integer room = Integer.parseInt(RumTextField.getText());
                    String doctor = LäkareTextField.getText();
                    String comment = KommentarTextArea.getText();
                    Gender gender = Gender.valueOf(genderType);
                    BloodType bloodType1 = BloodType.valueOf(bloodType);
                    //implement ComboBox to have select value.

                    Address address = new Address();
                    Person person = new Person(firstName, lastName, personN, gender, phoneN, address);

                    //ta patienten från listan och använd dess setters istället.
                    Patient patient = null; //new Patient(person, -1, new ArrayList<Disease>(), new ArrayList<Notification>(), address);
                    Journal journal = new Journal(patient, LocalDateTime.now(), KommentarTextArea.getText(), Driver.getCurrentUser());


                    Driver.getHospital().getArchive().AddJournal(journal, patient);

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

        SjukdomComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                /*try {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (!diseaseComboBox.getSelectedItem().toString().equals("Choose")) {
                            JOptionPane.showMessageDialog(null, diseaseComboBox.getSelectedItem().toString() + " is selected");
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }*/
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
    /*public void LoadPatient() {
        List<Patient> patients = new ArrayList<Patient>();
        for (Patient patient: patients) {
            patients.addAll(Driver.getHospital().getArchive().getPatients().toString());
        }
    }*/
}