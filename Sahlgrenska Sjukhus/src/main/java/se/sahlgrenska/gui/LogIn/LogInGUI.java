package se.sahlgrenska.gui.LogIn;

import se.sahlgrenska.gui.Menu.MenuGUI;
import se.sahlgrenska.gui.util.HelperGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInGUI extends HelperGUI {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel passwordPanel;
    private JPanel bottomPanel;
    private JTextField textField1;
    private JPanel usernamePanel;
    private JTextField textField2;
    private JButton loginButton;
    private JButton quitButton;

    public LogInGUI() { //constructor
        init(mainPanel, "Logga in", 350, 350);

        loginButton.addActionListener(new LoginButtonActionListener());
    }


    private class LoginButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            HelperGUI.toggleMainMenu();
        }
    }


}
