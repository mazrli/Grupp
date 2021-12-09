package se.sahlgrenska.main;


import se.sahlgrenska.gui.Booking.BookingGUI;
import se.sahlgrenska.gui.Journal.JournalGUI;
import se.sahlgrenska.gui.LogIn.LogInGUI;
import se.sahlgrenska.gui.Menu.MenuGUI;
import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.sjukhus.Hospital;
import se.sahlgrenska.sjukhus.Journal;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.employee.Employee;
import se.sahlgrenska.sjukhus.person.employee.LoginDetails;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {

    private Employee currentUser;
    private Hospital hospital;

    private static LogInGUI logInGUI;
    private static MenuGUI mainMenu;
    public static List<HelperGUI> subMenus = new ArrayList<>();

    public static void main(String[] args) {
        logInGUI = new LogInGUI();

        LogInGUI logInGUI = new LogInGUI();

        //lägg till alla menyer i subMenus
        subMenus.add(new JournalGUI());
        subMenus.add(new BookingGUI());

        logInGUI.setVisible(true);
    }

    public static MenuGUI getMainMenu() {
        return mainMenu;
    }
    public static void setMainMenu(Employee employee) {
        mainMenu = new MenuGUI(employee);
    }

    public static LogInGUI getLoginGUI() {
        return logInGUI;
    }
}
