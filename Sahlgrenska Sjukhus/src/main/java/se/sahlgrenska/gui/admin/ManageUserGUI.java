package se.sahlgrenska.gui.admin;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.employee.Employee;

import javax.swing.*;
import java.awt.*;

public class ManageUserGUI extends HelperGUI {
    private JTextField personNumField;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel bottomPanel;
    private JLabel titleLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JComboBox accessibilityComboBox;
    private JLabel personNumLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel repeatPasswordLabel;
    private JLabel accessibilityLabel;
    private JPanel repeatPasswordPanel;
    private JPanel personNumPanel;
    private JPanel passwordPanel;
    private JPanel accessibilityPanel;

    ComboBoxModel accessibilityModelBox = new DefaultComboBoxModel(Accessibility.values());


    public ManageUserGUI(String title) {
        this("Ny användare", false);
    }

    public ManageUserGUI(Employee employee) {
        this("Redigera användare", true);
        usernameField.grabFocus();

        usernameField.setText(employee.getLoginDetails().getUsername());
        passwordField.setText(employee.getLoginDetails().getPassword());
        accessibilityModelBox.setSelectedItem(employee.getAccessibility());
        repeatPasswordPanel.setVisible(false);
        personNumPanel.setVisible(false);
    }

    private ManageUserGUI(String title, boolean b) {
        accessibilityComboBox.setModel(accessibilityModelBox);


        titleLabel.setText(title);
        init(mainPanel, title, Accessibility.ADMIN);
        setVisible(true);
    }
}
