package se.sahlgrenska.main;

import se.sahlgrenska.gui.Booking.BookingGUI;
import se.sahlgrenska.gui.LogIn.LogInGUI;
import se.sahlgrenska.sjukhus.Hospital;
import se.sahlgrenska.sjukhus.person.employee.Employee;

public class Driver {

    private Employee currentUser;
    private Hospital hospital;

    public static void main(String[] args) {

        LogInGUI logInGUI = new LogInGUI();
        logInGUI.setVisible(false);

        new BookingGUI().setVisible(true);

    }

}
