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
    private JPanel userPanel;
    private JButton raderaButton;
    private JButton nyAnvändareButton;
    private JPanel userButtonPanel;
    private JList userList;
    private JPanel searchPanel;
    private JPanel listPanel;
    private JButton editUserButton;
    private JButton backButton;
    private JTextField searchField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JComboBox accessibilityBox;
    private JPasswordField passwordField;
    private JCheckBox hidePasswordBox;
    private JPanel personNumPanel;
    private JTextField personNumTextField;
    private JLabel personNumLabel;
    private JLabel accessibilityLabel;
    private JPanel panel2;
    private JTextField accessibilityTextField;

    //ComboBoxModel comboBoxModel = new DefaultComboBoxModel(Accessibility.values());
    DefaultListModel userDefaultModel = new DefaultListModel();

    private Set<Employee> users;
    private Employee selectedUser;

    public AdminGUI() {

        //accessibilityBox.setModel(comboBoxModel);

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
                searchField.setText("");
                populateList(users);
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
                personNumTextField.setText(selectedUser.getPersonNumber());
                accessibilityTextField.setText(selectedUser.getAccessibility().toString());
                //comboBoxModel.setSelectedItem(selectedUser.getAccessibility());

            }
        });

        init(mainPanel, "Hantera Användare", new Dimension(550, 650), Accessibility.ADMIN);

        nyAnvändareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageUserGUI manageUserGUI = new ManageUserGUI("Ny användare");
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

        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedUser != null) {
                    ManageUserGUI manageUserGUI = new ManageUserGUI(selectedUser);
                } else {
                    UtilGUI.error("Du har inte valt en användare!");
                }
            }
        });
    }

    private void resetForm() {
        selectedUser = null;
        usernameField.setText("");
        passwordField.setText("");
        personNumTextField.setText("");
        //comboBoxModel.setSelectedItem(Accessibility.NONE);
    }

    private void populateList(Set<? extends Object> items) {
        userDefaultModel.clear();

        for(Object employee : items) {
            userDefaultModel.addElement(employee);
        }

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
