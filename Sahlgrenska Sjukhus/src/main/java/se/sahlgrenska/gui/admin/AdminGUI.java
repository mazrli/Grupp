package se.sahlgrenska.gui.admin;

import se.sahlgrenska.gui.util.HelperGUI;
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
    private JTextField passwordField;
    private JPanel panel2;
    private JTextField textField3;
    private JPanel panel3;
    private JTextField textField4;
    private JPanel userPanel;
    private JButton raderaButton;
    private JButton redigeraButton;
    private JPanel userButtonPanel;
    private JList userList;
    private JPanel searchPanel;
    private JPanel listPanel;
    private JButton createUserButton;
    private JButton backButton;
    private JTextField searchField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    DefaultListModel dlm = new DefaultListModel();

    private Set<Employee> users;
    private Employee selectedUser;

    public AdminGUI() {
        init(mainPanel, "Hantera Användare", new Dimension(550, 650), Accessibility.ALL);

        users = Driver.getIOManager().getAllEmployees(Driver.getCurrentUser().getLoginDetails());
        for(Employee employee : users) {
            dlm.addElement(employee);
        }
        userList.setModel(dlm);

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
                setVisible(false);
                Driver.getMainMenu().setVisible(true);
            }
        });

        userList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedUser = (Employee) userList.getSelectedValue();

                usernameField.setText(selectedUser.getLoginDetails().getUsername());
                passwordField.setText(selectedUser.getLoginDetails().getPassword());
            }
        });
    }

    private void populateList(Set<Object> items) {
        dlm.clear();

        for(Object employee : items) {
            dlm.addElement(employee);
        }

    }

}
