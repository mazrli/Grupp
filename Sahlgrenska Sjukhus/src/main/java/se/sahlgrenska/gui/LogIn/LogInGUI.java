package se.sahlgrenska.gui.LogIn;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.person.employee.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                    }

                    setVisible(false);
                    Driver.setMainMenu(employee);

                } else {
                    JOptionPane.showMessageDialog(null, "Fel uppgifter.", "Warning", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Alla fält är obligatoriska.", "Warning", JOptionPane.ERROR_MESSAGE);
            }
            clearFields();
        }
    }

    private void delay() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            Thread.sleep(900);
        } catch (Exception exception) {}

        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private boolean isFilled() {
        return usernameField.getText().length() > 0 && passwordField.getPassword().length > 0;
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    private class QuitButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }


}
