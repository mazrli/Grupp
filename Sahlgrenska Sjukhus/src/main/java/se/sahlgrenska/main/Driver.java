package se.sahlgrenska.main;

import se.sahlgrenska.database.IOManager;

import se.sahlgrenska.gui.Booking.BookingGUI;
import se.sahlgrenska.gui.Journal.JournalGUI;
import se.sahlgrenska.gui.LogIn.LogInGUI;
import se.sahlgrenska.gui.Menu.MenuGUI;
import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.sjukhus.Address;
import se.sahlgrenska.sjukhus.Archive;
import se.sahlgrenska.sjukhus.Hospital;
import se.sahlgrenska.sjukhus.item.Item;
import se.sahlgrenska.sjukhus.person.Person;
import se.sahlgrenska.sjukhus.person.employee.Employee;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Driver {

    private static Employee currentUser;

    private static Hospital hospital;

    private static LogInGUI logInGUI;
    private static MenuGUI mainMenu;

    private static final IOManager ioManger = new IOManager();
    public static final List<HelperGUI> subMenus = new ArrayList<>();

    public static void main(String[] args) {

        hospital = new Hospital("Sahlgrenska sjukhuset", 200, new HashMap<Item, Integer>(), new ArrayList<Person>(), new Archive(), 500000, new Address("Blå stråket 5", "413 45", "Göteborg"));

        setupOS();
        logInGUI = new LogInGUI();

        LogInGUI logInGUI = new LogInGUI();

        //lägg till alla menyer i subMenus
        subMenus.add(new JournalGUI());
        subMenus.add(new BookingGUI());

        logInGUI.setVisible(true);

    }

    //här kan vi påverka vad som händer i programmet innan det avslutas (t.ex spara data)
    public static void quit() {
        ioManger.closeDB();

        System.exit(0);
    }

    private static void setupOS() {
        //todo: set up stuff
    }

    public static MenuGUI getMainMenu() {
        return mainMenu;
    }

    public static void setMainMenu(Employee employee) {
        currentUser = employee;
        mainMenu = new MenuGUI(employee);
    }

    public static LogInGUI getLoginGUI() {
        return logInGUI;
    }

    public static IOManager getIOManager() {
        return ioManger;
    }

    public static Employee getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Employee user) {
        currentUser = user;
    }

    public static Hospital getHospital() {
        return hospital;
    }

}
