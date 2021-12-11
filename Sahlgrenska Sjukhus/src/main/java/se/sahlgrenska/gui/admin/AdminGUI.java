package se.sahlgrenska.gui.admin;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.main.Util;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.employee.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
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
    private JTextField textField1;
    private JPanel panel1;
    private JTextField textField2;
    private JPanel panel2;
    private JTextField textField3;
    private JPanel panel3;
    private JTextField textField4;
    private JPanel userPanel;
    private JButton raderaButton;
    private JButton redigeraButton;
    private JPanel userButtonPanel;
    private JList list1;
    private JPanel searchPanel;
    private JPanel listPanel;
    private JButton createUserButton;
    private JButton backButton;
    private JTextField searchField;
    DefaultListModel dlm = new DefaultListModel();

    List<String> all = new ArrayList<>();

    Set<Employee> users;

    public AdminGUI() {
        init(mainPanel, "Hantera Användare", new Dimension(550, 650),Accessibility.ALL);

        users = Driver.getIOManager().getAllEmployees(Driver.getCurrentUser().getLoginDetails());

        for(Employee employee : users)
            System.out.println("d: " + employee.getFirstName());

        if(false)
        for(int i = 0; i < 50; i++) {
            String d = "" + Math.random() * 50000;

            all.add(d);
            dlm.add(i, d);
        }

        list1.setModel(dlm);

        searchField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {

                System.out.println(searchField.getText());

                List<String> results = Util.getSearchResults(all, searchField.getText());

                populateList(results);
            }
        });
    }

    private void populateList(List<String> items) {
        dlm.clear();

        for(int i = 0; i < items.size(); i++) {
            dlm.add(i, items.get(i));
        }


    }
}
