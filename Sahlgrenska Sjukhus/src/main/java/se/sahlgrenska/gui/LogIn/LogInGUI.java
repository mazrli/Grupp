package se.sahlgrenska.gui.LogIn;

import se.sahlgrenska.gui.Menu.MenuGUI;
import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

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
    private JTextField textField1;
    private JPanel usernamePanel;
    private JTextField textField2;
    private JButton loginButton;
    private JButton quitButton;

    public LogInGUI() { //constructor
        init(mainPanel, "Sahlgrenska sjukhus", new Dimension(350, 350));
        setVisible(true);

        loginButton.addActionListener(new LoginButtonActionListener());
    }


    private class LoginButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Accessibility accessibility = Accessibility.DOCTOR;

            if(true) {
                setVisible(false);

                HelperGUI.setMainMenu(accessibility);
                HelperGUI.toggleMainMenu();
            }
        }
    }


}
