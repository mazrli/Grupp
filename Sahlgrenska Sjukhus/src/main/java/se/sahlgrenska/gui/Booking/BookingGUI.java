package se.sahlgrenska.gui.Booking;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.sjukhus.Booking;

import javax.swing.*;

public class BookingGUI extends HelperGUI {
    private Booking booking;
    private JPanel mainPanel;
    private JPanel test;
    private JPanel testomg;
    private JLabel title;


    public static void main(String[] args) {
        new BookingGUI().setVisible(true);
    }


    public BookingGUI() {
        init(mainPanel, "Create Booking");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }


}
