package se.sahlgrenska.gui.admin;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.gui.util.misc.SuggestionDropDownDecorator;
import se.sahlgrenska.gui.util.misc.SuggestionExampleMain;
import se.sahlgrenska.gui.util.misc.TextComponentSuggestionClient;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.main.Util;
import se.sahlgrenska.sjukhus.person.Person;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.employee.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private JTextField employeeIDField;
    private JPanel panel3;
    private JLabel employeeIDLabel;

    //ComboBoxModel comboBoxModel = new DefaultComboBoxModel(Accessibility.values());
    DefaultListModel userDefaultModel = new DefaultListModel();

    private Set<Employee> users;
    private Set<Person> persons;

    private Employee selectedUser;

    public AdminGUI() {
        users = Driver.getIOManager().getAllEmployees(Driver.getCurrentUser().getLoginDetails());
        refreshList();
        userList.setModel(userDefaultModel);

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                Set<Object> results = Util.getSearchResults(users, searchField.getText());
                populateList(results);
            }
        });

        backButton.addActionListener(e -> {
            refreshList();
            setVisible(false);
            Driver.getMainMenu().setVisible(true);
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
                employeeIDField.setText(selectedUser.getId());

            }
        });

        init(mainPanel, "Hantera Användare", new Dimension(550, 650), Accessibility.ADMIN);

        nyAnvändareButton.addActionListener(e -> {
            ManageUserGUI manageUserGUI = new ManageUserGUI("Ny användare");
        });

        hidePasswordBox.addItemListener(e -> {
            UtilGUI.toggleVisibility(passwordField);
        });

        editUserButton.addActionListener(e -> {
            if(selectedUser != null) {
                ManageUserGUI manageUserGUI = new ManageUserGUI(selectedUser);
            } else {
                UtilGUI.error("Du har inte valt en användare!");
            }
        });
        raderaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedUser != null) {
                        String confirmMessage = String.format("\n%s\nAnvändarnamn: %s\nLösenord: %s\nRoll: %s",
                                selectedUser.getFullName(),
                                selectedUser.getLoginDetails().getUsername(),
                                selectedUser.getLoginDetails().getPassword(),
                                selectedUser.getAccessibility().toString()
                        );

                    int resp = JOptionPane.showConfirmDialog(null, confirmMessage, "Radera användare", JOptionPane.OK_CANCEL_OPTION);

                    if(resp == 0) {
                        Driver.getIOManager().deleteUser(selectedUser.getId());
                        userDefaultModel.removeElement(selectedUser);
                    }

                } else {
                    UtilGUI.error("Du har inte valt en användare!");
                }
            }
        });
    }

    private void resetForm() {
        searchField.setText("");
        selectedUser = null;
        usernameField.setText("");
        passwordField.setText("");
        personNumTextField.setText("");
        employeeIDField.setText("");
    }

    public void refreshList() {
        resetForm();

        userDefaultModel.clear();

        resetForm();
        users = Driver.getIOManager().getAllEmployees(Driver.getCurrentUser().getLoginDetails());

        for(Employee employee : users) {
            userDefaultModel.addElement(employee);
        }
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
