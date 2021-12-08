package se.sahlgrenska.gui.LogIn;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.person.employee.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    public LogInGUI() { //constructor
        init(mainPanel, "Sahlgrenska sjukhus", new Dimension(350, 350), Accessibility.ALL);

        loginButton.addActionListener(new LoginButtonActionListener());
        quitButton.addActionListener(new QuitButtonActionListener());

    }

        private  class LoginButtonActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usernameField.getText().length() > 0 && passwordField.getPassword().length > 0) {
                    //skapar lite dummy objects för att testa
                    LoginDetails loginDetails = new LoginDetails(usernameField.getText(), passwordField.getText());
                    Employee employee = new Doctor("44", 2000, 4.4f, loginDetails);

                    if (true) {
                        setVisible(false);
                        Driver.setMainMenu(employee);
                    }
                } else {
                    JOptionPane.showMessageDialog( null, "You must provide field values.", "Invalid input.", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    private  class QuitButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }




}
