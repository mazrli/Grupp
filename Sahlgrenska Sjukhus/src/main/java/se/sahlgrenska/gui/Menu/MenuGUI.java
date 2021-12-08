package se.sahlgrenska.gui.Menu;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuGUI extends HelperGUI {

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JPanel userPanel;
    private JPanel employeeIDPanel;
    private JLabel userLabel;
    private JLabel employeeIDLabel;
    private JTextPane EmployeeIDTextfield;
    private JTextPane employeeIDTextField;

    private JButton logOutButton = new JButton("Logga ut");

    private final Map<String, JButton> menus = new HashMap<>();

    public MenuGUI(Accessibility accessibility) {
        init(mainPanel, "Sahlgrenska sjukhus");
    }

    private void createUIComponents() {

        System.out.println("casdlöfjasjlödk");

        logOutButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Logged out!");
                HelperGUI.toggleMainMenu();
            }
        });

        contentPanel.add(logOutButton);


    }

    public void addButton(JFrame jFrame) {
        if(!jFrame.getTitle().equals("Sahlgrenska sjukhus")) {
            JButton button = new JButton(jFrame.getTitle());
            button.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                }
            });
            menus.put(jFrame.getTitle(), button);
        }
    }

    private Map<String, JButton> getMenus() {
        return menus;
    }
}
