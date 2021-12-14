package se.sahlgrenska.gui.LogIn;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.misc.SuggestionDropDownDecorator;
import se.sahlgrenska.gui.util.misc.TextComponentSuggestionClient;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

import javax.swing.*;
import java.awt.*;
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

    }






}
