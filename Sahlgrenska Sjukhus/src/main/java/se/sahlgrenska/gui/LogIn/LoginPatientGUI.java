package se.sahlgrenska.gui.LogIn;

import se.sahlgrenska.database.IOManager;
import se.sahlgrenska.gui.patient.PatientGUI;
import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.gui.util.components.JTextFieldPlaceholder;
import se.sahlgrenska.gui.util.misc.SuggestionDropDownDecorator;
import se.sahlgrenska.gui.util.misc.TextComponentSuggestionClient;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.Hospital;
import se.sahlgrenska.sjukhus.person.Person;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.patient.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginPatientGUI extends HelperGUI {
    private JPanel middlePanel;
    private JPanel fieldPanel;
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton okButton;
    private LogInGUI logInGUI;

    public LoginPatientGUI() {
        init(mainPanel, "Patient", new Dimension(250, 150), Accessibility.PATIENT);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                Driver.getLoginGUI().setVisible(true);
            }
        });

        okButton.addActionListener(e -> {

            String personNumber = textField1.getText();
            if(personNumber.length() == 12) {
                Patient patient = Driver.getIOManager().getPatient(personNumber);

                if(patient != null) {

                    Driver.initHospital(this);
                    PatientGUI patientGUI = new PatientGUI(patient);

                } else {
                    UtilGUI.error(String.format("Vi hittade ingen registrerad patient\n med personnumret \"%s\"", personNumber));
                }
            } else {
                UtilGUI.error("Ange ett personnummer.");
            }

        });
    }


    private void createUIComponents() {
        textField1 = new JTextFieldPlaceholder("YYYYMMDDXXXX");
    }
}
