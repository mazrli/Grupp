package se.sahlgrenska.main;

import com.sun.java.accessibility.util.GUIInitializedListener;
import se.sahlgrenska.database.IOManager;
import se.sahlgrenska.gui.Booking.BookingGUI;
import se.sahlgrenska.gui.Journal.JournalGUI;
import se.sahlgrenska.gui.LogIn.LogInGUI;
import se.sahlgrenska.gui.Menu.MenuGUI;
import se.sahlgrenska.gui.Order.ItemStatus;
import se.sahlgrenska.gui.Order.OrderGUI;
import se.sahlgrenska.gui.admin.AdminGUI;
import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.gui.util.UtilGUI;
import se.sahlgrenska.sjukhus.Address;
import se.sahlgrenska.sjukhus.Archive;
import se.sahlgrenska.sjukhus.Hospital;
import se.sahlgrenska.sjukhus.item.Item;
import se.sahlgrenska.sjukhus.person.Person;
import se.sahlgrenska.sjukhus.person.employee.Employee;

import javax.swing.*;
import java.awt.*;
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

    private static boolean hasBeenSetup = false;

    public static void main(String[] args) {
        setupOS();
        logInGUI = new LogInGUI();
    }


    public static void setup(Employee employee) {
        currentUser = employee;
        ioManger.query(String.format("INSERT INTO online VALUES(%s) ON DUPLICATE KEY UPDATE employee_id = employee_id;", employee.getId()));

        hospital = new Hospital("Sahlgrenska sjukhuset", 200, new HashMap<Item, Integer>(), new ArrayList<Person>(), new Archive(), 500000, new Address("Göteborg", "Blå stråket 5", "413 45", "Åmål"));

        //lägg till alla menyer i subMenus
        subMenus.add(new JournalGUI());
        subMenus.add(new BookingGUI());
        subMenus.add(new OrderGUI());
        subMenus.add(new AdminGUI());
        subMenus.add(new ItemStatus());

        mainMenu = new MenuGUI(employee);
    }

    //här kan vi påverka vad som händer i programmet innan det avslutas (t.ex spara data)
    public static void quit() {
        ioManger.closeDB();

        System.exit(0);
    }

    private static void setupOS() {
        //todo: set up stuff
        //icon och look & feel
        if(Util.getOS().contains("windows")) { //inställningar för windows os
            try {
                UIManager.setLookAndFeel(UtilGUI.windowsLook);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Util.getOS().contains("mac")) { //inställningar för mac os
            Taskbar.getTaskbar().setIconImage(UtilGUI.iconImage);
        }
    }

    public static MenuGUI getMainMenu() {
        return mainMenu;
    }

    public static List<HelperGUI> getSubMenus() {
        return subMenus;
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
