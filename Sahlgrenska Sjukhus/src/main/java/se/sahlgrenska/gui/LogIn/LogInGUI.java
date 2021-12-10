package se.sahlgrenska.gui.LogIn;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.person.employee.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    public LogInGUI() { //constructor
        init(mainPanel, "Sahlgrenska sjukhus", new Dimension(380, 400), Accessibility.ALL);

        loginButton.addActionListener(new LoginButtonActionListener());
        quitButton.addActionListener(new QuitButtonActionListener());

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


    }

    private class LoginButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(isFilled()) {
                delay();

                LoginDetails loginDetails = new LoginDetails(usernameField.getText(), passwordField.getText());
                Employee employee = Driver.getIOManager().getEmployee(loginDetails);

                if (employee != null) {

                    if(rememberMeCheckBox.isSelected()) {
                        Driver.getIOManager().remember(loginDetails);
                    } else {
                        clearFields();
                    }

                    setVisible(false);
                    Driver.setMainMenu(employee);

                } else {
                    UtilGUI.error("Uppgifterna är ogiltiga");
                    clearFields();
                }
            } else {
                UtilGUI.error("Alla fält är obligatoriska");
            }

        }
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
