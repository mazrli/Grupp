package se.sahlgrenska.gui.Journal;

import com.sun.source.doctree.ThrowsTree;
import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.Journal;
import se.sahlgrenska.sjukhus.item.Item;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.patient.Disease;
import se.sahlgrenska.sjukhus.person.patient.Patient;
import se.sahlgrenska.sjukhus.person.patient.Symptom;

import javax.swing.*;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.JComboBox;
import java.awt.*;
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
    private JTextField NamnTextField;
    private JTextField PersonNummerTextField;
    private JTextField TelefonNummerTextField;
    private JComboBox SjukdomComboBox;
    private JTextField TillståndTextField;
    private JTextField RumTextField;
    private JTextField LäkareTextField;
    private JLabel NamnLabel;
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

    List<Patient> patients;
    Journal currentJournal;
    UndoManager undoManager;
    UndoableEdit edit;
    List<Disease> diseases;

    public JournalGUI() {

        init(MainPanel, "Hantera journaler", Accessibility.DOCTOR);
        setSize(550, 600);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); <--- Använd inte setDefaultCloseOperation!

        //Gernerates a dummylist of patients to test remove button
        DefaultListModel dataList = new DefaultListModel();
        for (int i = 0; i < 5; i++) {
            JLabel label = new JLabel("Hasse" + " " + i);
            dataList.add(i, label.getText());
        }
        JournalDataList.setModel(dataList);

        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        for (int i = 0; i < 10; i++) {
            JLabel label =new JLabel("Sjukdom" + " " + i );
            comboBoxModel.addElement(label.getText());
        }
        SjukdomComboBox.setModel(comboBoxModel);

        DefaultListModel diseaseList = new DefaultListModel();
        for (int i = 0; i < 5; i++) {
            JLabel label = new JLabel("Sjukdom" + " " + i);
            diseaseList.add(i, label.getText());
        }
        DiseaseDataList.setModel(diseaseList);

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
                    diseaseList.removeElement(DiseaseDataList.getSelectedValue());
                }
            }
        });

        //Undo latest removed patient from list of patient(Incomplete).
        ÅngraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Stack<UndoManager> stack = new Stack<UndoManager>();
                if (dataList.removeElement(JournalDataList.getSelectedValue()) == true) {
                    stack.push(undoManager);
                }
            }
        });

        //Saves different types of variable into object.
        SparaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name =  NamnTextField.getText();
                Integer personN = Integer.parseInt(PersonNummerTextField.getText());
                Integer phoneN = Integer.parseInt(TelefonNummerTextField.getText());
                Object disease = SjukdomComboBox.getSelectedItem();
                String condition = TillståndTextField.getText();
                Integer room = Integer.parseInt(RumTextField.getText());
                String doctor = LäkareTextField.getText();

                Patient patient = new Patient();

                Journal journal = new Journal(patient, LocalDateTime.now(), KommentarTextArea.getText(), Driver.getCurrentUser());

                Driver.getHospital().getArchive().getJournals().get(patient).add(journal);

                System.out.println(journal.toString());
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
                System.out.println(JournalDataList.getSelectedValue().toString());
            }
        });

        SjukdomComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                try {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (!comboBoxModel.getSelectedItem().toString().equals("Choose")) {
                            JOptionPane.showMessageDialog(null, comboBoxModel.getSelectedItem().toString() + " is selected");
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
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