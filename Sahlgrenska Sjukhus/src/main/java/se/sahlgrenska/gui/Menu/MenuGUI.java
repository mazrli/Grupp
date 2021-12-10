package se.sahlgrenska.gui.Menu;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.main.Util;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.employee.Employee;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MenuGUI extends HelperGUI {

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel contentPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;


    private JLabel userLabel;
    private JLabel employeeIDLabel;
    private JLabel dateLabel;

    private JTextField userTextField;
    private JTextField employeeIDTextField;

    private JButton logoutButton;


    private final Employee currentUser;

    public MenuGUI(Employee currentUser) {
        this.currentUser = currentUser;

        setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.blue);

        topPanel = new JPanel();
        GridLayout topPanelLayout = new GridLayout(2, 3, 10, 10);
        topPanel.setLayout(topPanelLayout);
        topPanel.setBackground(Color.ORANGE);
        userLabel = new JLabel("Användare:");
        userLabel.setFont(Util.biggerFont);
        dateLabel = new JLabel(LocalDateTime.now().format(Util.dateFormatter));
        dateLabel.setFont(Util.biggerFont);
        employeeIDLabel = new JLabel("Arbetar-id:");
        employeeIDLabel.setFont(Util.biggerFont);
        userTextField = new JTextField(currentUser.getLoginDetails().getUsername());
        userTextField.setFont(Util.biggerFont);
        userTextField.setEditable(false);
        employeeIDTextField = new JTextField(currentUser.getId());
        employeeIDTextField.setFont(Util.biggerFont);
        employeeIDTextField.setEditable(false);
        topPanel.add(userLabel);
        topPanel.add(userTextField);
        topPanel.add(dateLabel);
        topPanel.add(employeeIDLabel);
        topPanel.add(employeeIDTextField);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        leftPanel = new JPanel();
        mainPanel.add(leftPanel, BorderLayout.WEST);
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        rightPanel = new JPanel();
        mainPanel.add(rightPanel, BorderLayout.EAST);
        rightPanel.setBackground(Color.BLUE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));


        contentPanel = new JPanel();
        contentPanel.setBackground(Color.RED);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0 ));

        mainPanel.add(contentPanel, BorderLayout.CENTER);


        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.YELLOW);
        logoutButton = new JButton("Logga ut");
        bottomPanel.add(logoutButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        logoutButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });


        List<HelperGUI> availableMenus = new ArrayList<>();
        for(HelperGUI subMenu: Driver.subMenus) {

            if(currentUser.getAccessibility() == subMenu.getAccessibility() || currentUser.getAccessibility() == Accessibility.ALL) {
                availableMenus.add(subMenu);
            }

        }

        GridLayout contentPanelLayout = new GridLayout(availableMenus.size(), 2, 10, 5);
        contentPanel.setLayout(contentPanelLayout);

        if(false) {
            int num = (int) (Math.random() * 6) + 1;

            for (int i = 0; i < num; i++) {
                JButton button = new JButton("test " + i);
                button.setPreferredSize(new Dimension(80, 30));
                button.setFont(Util.biggerFont);
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                contentPanel.add(button);
            }
        }

        for(HelperGUI helperGUI : availableMenus) {
            addButton(helperGUI);
        }

        setVisible(true);

        init(mainPanel, "Sahlgrenska sjukhus", Accessibility.ALL);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                logout();
            }
        });
    }

    /*
        Logga ut

        stäng alla fönster och öppna logga in menyn.
     */
    private void logout() {
        Driver.setCurrentUser(null);

        for(HelperGUI subMenu : Driver.subMenus)
            subMenu.setVisible(false);

        Driver.getMainMenu().setVisible(false);

        Driver.getLoginGUI().setVisible(true);
    }


    private void addButton(HelperGUI helperGUI) {
        JButton button = new JButton(helperGUI.getTitle());

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                helperGUI.setVisible(true);
            }
        });

        button.setPreferredSize(new Dimension(80, 30));
        button.setFont(Util.biggerFont);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(button);
    }
}
