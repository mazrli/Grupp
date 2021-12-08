package se.sahlgrenska.gui.Menu;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.employee.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuGUI extends HelperGUI {

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel contentPanel;

    private JScrollPane scrollPane;

    private JLabel userLabel;
    private JLabel employeeIDLabel;

    private JButton logoutButton;

    private final Employee currentUser;

    public MenuGUI(Employee currentUser) {
        this.currentUser = currentUser;

        mainPanel = new JPanel();
        BoxLayout mainPanelBoxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(mainPanelBoxLayout);
        mainPanel.setBackground(Color.blue);


        topPanel = new JPanel();
        topPanel.setBackground(Color.green);

        userLabel = new JLabel("User: " + currentUser.getLoginDetails().getUsername());
        employeeIDLabel = new JLabel("Employee id: " + currentUser.getId());
        topPanel.add(userLabel);
        topPanel.add(employeeIDLabel);


        mainPanel.add(topPanel);

        contentPanel = new JPanel();
        BoxLayout contentPanelBoxLayout = new BoxLayout(contentPanel, BoxLayout.Y_AXIS);
        contentPanel.setLayout(contentPanelBoxLayout);
        logoutButton = new JButton("Logga ut");

        contentPanel.setBackground(Color.RED);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));


        mainPanel.add(contentPanel);


        logoutButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Driver.getMainMenu().setVisible(false);
                Driver.getLoginGUI().setVisible(true);
            }
        });

        for(HelperGUI subMenu: Driver.subMenus) {
            // lägg endast till knappen om currentUser har tillåtelse.
            if(subMenu.getAccessibility() == currentUser.getAccessibility() || currentUser.getAccessibility() == Accessibility.ALL) {
                addButton(subMenu);
            }

        }

        contentPanel.add(logoutButton);

        setVisible(true);

        init(mainPanel, "Sahlgrenska sjukhus", Accessibility.ALL);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                Driver.getLoginGUI().setVisible(true);
            }
        });
    }


    private void addButton(HelperGUI helperGUI) {
        JButton button = new JButton(helperGUI.getTitle());
        System.out.println("Added menu -> " + helperGUI.getTitle());
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                helperGUI.setVisible(true);
            }
        });
        contentPanel.add(button);
    }
}
