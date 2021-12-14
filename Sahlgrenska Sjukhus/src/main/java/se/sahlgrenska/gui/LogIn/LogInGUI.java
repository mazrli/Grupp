package se.sahlgrenska.gui.LogIn;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.gui.util.misc.SuggestionDropDownDecorator;
import se.sahlgrenska.gui.util.misc.TextComponentSuggestionClient;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.person.Person;
import se.sahlgrenska.sjukhus.person.employee.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogInGUI extends HelperGUI {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel passwordPanel;
    private JPanel bottomPanel;
    private JPanel usernamePanel;
    private JTextField usernameField;
    private JButton loginButton;
    private JButton quitButton;
    private JPasswordField passwordField;
    private JPanel fieldPanel;
    private JCheckBox rememberMeCheckBox;
    private JCheckBox visaLösenordCheckBox;
    private JLabel forgotPasswordJLabel;
    private JLabel patientLabel;

    public LogInGUI() { //constructor
        init(mainPanel, "Sahlgrenska sjukhus", new Dimension(380, 420), Accessibility.ADMIN);

        loginButton.addActionListener(new LoginButtonActionListener());
        quitButton.addActionListener(new QuitButtonActionListener());

        forgotPasswordJLabel.setText("<HTML><U>Glömt lösenord?</U></HTML>");
        forgotPasswordJLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        patientLabel.setText("<HTML><U>Jag är patient</U></HTML>");
        patientLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));



        setDefaultCloseOperation(EXIT_ON_CLOSE);

        LoginDetails loginDetails = Driver.getIOManager().getRemember();

        if(loginDetails != null) {
            usernameField.setText(loginDetails.getUsername());
            passwordField.setText(loginDetails.getPassword());
            rememberMeCheckBox.setSelected(true);
        }

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Driver.quit();
            }
        });

        setVisible(true);


        visaLösenordCheckBox.addItemListener(e -> {
            UtilGUI.toggleVisibility(passwordField);
        });

        forgotPasswordJLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UtilGUI.error("Det var ju synd.", "");
            }
        });

        patientLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                LoginPatientGUI patientGUI = new LoginPatientGUI();
            }
        });
    }

    private void createUIComponents() {

    }

    private class LoginButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(isFilled()) {

                LoginDetails loginDetails = new LoginDetails(usernameField.getText(), passwordField.getText());
                Employee employee = Driver.getIOManager().getEmployee(loginDetails);

                if (employee != null) {

                    if(rememberMeCheckBox.isSelected()) {
                        Driver.getIOManager().remember(loginDetails);
                    } else {
                        clearFields();
                    }
                    login(employee);
                } else {
                    UtilGUI.error("Uppgifterna är ogiltiga");
                    passwordField.setText("");
                }
            } else {
                UtilGUI.error("Alla fält är obligatoriska");
            }

        }
    }

    private void login(Employee employee) {
        loginButton.setEnabled(false);
        quitButton.setEnabled(false);
        Driver.setup(employee);
    }

    public void logout() {
        setVisible(true);
        loginButton.setEnabled(true);
        quitButton.setEnabled(true);
    }

    private void delay() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            Thread.sleep((int) (Math.random() * 1200));
        } catch (Exception exception) {}

        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private boolean isFilled() {
        return usernameField.getText().length() > 0 && passwordField.getPassword().length > 0;
    }

    private void clearFields() {
        rememberMeCheckBox.setSelected(false);
        usernameField.setText("");
        passwordField.setText("");
    }

    private class QuitButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkSave();
            Driver.quit();
        }
    }

    private void checkSave() {
        if(!rememberMeCheckBox.isSelected()) {
            Driver.getIOManager().remember(null);
        }
    }

}
