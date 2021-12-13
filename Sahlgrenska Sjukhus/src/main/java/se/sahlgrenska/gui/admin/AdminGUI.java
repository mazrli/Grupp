package se.sahlgrenska.gui.admin;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.main.Util;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.employee.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

public class AdminGUI extends HelperGUI {
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel bottomPanel;
    private JLabel titleLabel;
    private JPanel mainPanel;
    private JPanel usernamePanel;
    private JTextField usernameField;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel userPanel;
    private JButton raderaButton;
    private JButton nyAnvändareButton;
    private JPanel userButtonPanel;
    private JList userList;
    private JPanel searchPanel;
    private JPanel listPanel;
    private JButton createUserButton;
    private JButton backButton;
    private JTextField searchField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JComboBox accessibilityBox;
    private JPasswordField passwordField;
    private JCheckBox hidePasswordBox;

    ComboBoxModel comboBoxModel = new DefaultComboBoxModel(Accessibility.values());
    DefaultListModel userDefaultModel = new DefaultListModel();

    private Set<Employee> users;
    private Employee selectedUser;

    public AdminGUI() {

        accessibilityBox.setModel(comboBoxModel);

        users = Driver.getIOManager().getAllEmployees(Driver.getCurrentUser().getLoginDetails());

        for(Employee employee : users) {
            userDefaultModel.addElement(employee);
        }
        userList.setModel(userDefaultModel);

        searchField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                Set<Object> results = Util.getSearchResults(users, searchField.getText());
                populateList(results);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
                setVisible(false);
                Driver.getMainMenu().setVisible(true);
            }
        });

        userList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                hidePasswordBox.setSelected(true);
                selectedUser = (Employee) userList.getSelectedValue();

                usernameField.setText(selectedUser.getLoginDetails().getUsername());
                passwordField.setText(selectedUser.getLoginDetails().getPassword());
                comboBoxModel.setSelectedItem(selectedUser.getAccessibility());

            }
        });

        init(mainPanel, "Hantera Användare", new Dimension(550, 650), Accessibility.ADMIN);

        nyAnvändareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
                usernameField.grabFocus();
            }
        });

        hidePasswordBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    passwordField.setEchoChar(Util.echoPWchar);
                } else {
                    passwordField.setEchoChar('\0');
                }
            }
        });

        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                Accessibility accessibility = Accessibility.valueOf(comboBoxModel.getSelectedItem().toString());

                if (username.length() == 0 || password.length == 0 || accessibility == Accessibility.NONE) {
                    UtilGUI.error("Alla fält är obligatoriska.");
                }

            }
        });
    }

    private void resetForm() {
        selectedUser = null;
        usernameField.setText("");
        passwordField.setText("");
        comboBoxModel.setSelectedItem(Accessibility.NONE);
    }

    private void populateList(Set<Object> items) {
        userDefaultModel.clear();

        for(Object employee : items) {
            userDefaultModel.addElement(employee);
        }

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
