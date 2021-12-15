package se.sahlgrenska.gui.Menu;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.main.Util;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.employee.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.Timer;

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
    private JLabel onlineLabel = new JLabel("Online: -");

    private JButton logoutButton;

    private JLabel imageLabel;

    private Timer timer = new Timer("Sahlgrenska Timer");


    private final Employee currentUser;

    private final Color testColor = new Color(219, 255, 253);
    private final Color testColor2 = new Color(206, 248, 245);

    private final Color topPanelColor = testColor;//;Color.ORANGE;
    private final Color mainPanelColor = testColor; //Color.BLUE;
    private final Color contentPanelColor = testColor; //Color.red;
    private final Color leftPanelColor = testColor2; //Color.GREEN;
    private final Color rightPanelColor = testColor2; //Color.BLUE;
    private final Color bottomPanelColor = testColor; //Color.YELLOW;

    private int vSize = 160;

    public MenuGUI(Employee currentUser) {
        this.currentUser = currentUser;

        setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(mainPanelColor);

        topPanel = new JPanel();
        GridLayout topPanelLayout = new GridLayout(2, 3, 10, 10);
        topPanel.setLayout(topPanelLayout);
        //topPanel.setBackground(topPanelColor);

        userLabel = new JLabel(Driver.getCurrentUser().getFirstName() + " " + Driver.getCurrentUser().getLastName());
        userLabel.setFont(Util.biggerFont);

        dateLabel = new JLabel(LocalDateTime.now().format(Util.dateFormatter));
        /*
        dateLabel.setFont(Util.biggerFont);
         */

        employeeIDLabel = new JLabel(currentUser.getAccessibility().toString());
        employeeIDLabel.setFont(Util.biggerFont);

        onlineLabel.setFont(Util.biggerFont);


        topPanel.add(userLabel);
        //topPanel.add(dateLabel);
        topPanel.add(onlineLabel);
        topPanel.add(employeeIDLabel);

        List<HelperGUI> availableMenus = new ArrayList<>();
        for(HelperGUI subMenu: Driver.subMenus)
            if(currentUser.getAccessibility() == subMenu.getAccessibility() || currentUser.getAccessibility() == Accessibility.ADMIN)
                availableMenus.add(subMenu);


        vSize -= (availableMenus.size() * 20);

        System.out.println(vSize);

        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        leftPanel = new JPanel();
        mainPanel.add(leftPanel, BorderLayout.WEST);
        //leftPanel.setBackground(leftPanelColor);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        rightPanel = new JPanel();
        mainPanel.add(rightPanel, BorderLayout.EAST);
        //rightPanel.setBackground(rightPanelColor);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));


        contentPanel = new JPanel();
        //contentPanel.setBackground(contentPanelColor);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(vSize, 20, vSize, 20));
        mainPanel.add(contentPanel, BorderLayout.CENTER);


        bottomPanel = new JPanel();
        //bottomPanel.setBackground(bottomPanelColor);
        logoutButton = new JButton("Logga ut");
        bottomPanel.add(logoutButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        logoutButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });



        GridLayout contentPanelLayout = new GridLayout(availableMenus.size(), 2, 10, 5);
        contentPanel.setLayout(contentPanelLayout);



        for(HelperGUI helperGUI : availableMenus) {
            addButton(helperGUI);
        }

        setVisible(true);

        init(mainPanel, "Sahlgrenska sjukhus", Accessibility.ADMIN);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                logout();
            }
        });


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dateLabel.setText(LocalDateTime.now().format(Util.dateFormatter));
                Set<Employee> online = Driver.getIOManager().getOnline();
                onlineLabel.setText("Online: " + online.size());
                onlineLabel.setToolTipText(online.toString());
            }
        }, 0, 1000 * 1); // execute every 15 sec

    }



    /*
        Logga ut

        stäng alla fönster och öppna logga in menyn.
     */
    private void logout() {
        timer.cancel();

        Driver.getIOManager().query(String.format("DELETE FROM online WHERE employee_id = %s;", currentUser.getId()));

        //remove current user
        Driver.setCurrentUser(null);

        //hide all submenus
        for(HelperGUI subMenu : Driver.getSubMenus())
            subMenu.setVisible(false);

        //remove submenus form the list
        Driver.getSubMenus().clear();

        //hide main menu
        setVisible(false);

        //show login menu
        Driver.getLoginGUI().logout();
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
