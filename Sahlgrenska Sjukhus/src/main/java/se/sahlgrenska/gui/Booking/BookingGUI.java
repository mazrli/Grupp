package se.sahlgrenska.gui.Booking;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.sjukhus.Booking;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingGUI extends HelperGUI {
    private Booking booking;
    private JPanel mainPanel;
    private JPanel test;
    private JPanel testomg;
    private JLabel title;
    private LocalDateTime date;

    private int minWindowSize = 500;
    private int maxWindowSize = 500;


    public static void main(String[] args) {
        new BookingGUI().setVisible(true);
    }


    public BookingGUI() {
        init(mainPanel, "Create Booking");
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
     //   setResizable(false);


    }


}
