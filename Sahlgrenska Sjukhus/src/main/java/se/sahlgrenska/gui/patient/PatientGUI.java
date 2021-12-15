package se.sahlgrenska.gui.patient;

import se.sahlgrenska.gui.util.HelperGUI;
import se.sahlgrenska.main.Driver;
import se.sahlgrenska.sjukhus.Booking;
import se.sahlgrenska.sjukhus.person.employee.Accessibility;
import se.sahlgrenska.sjukhus.person.patient.Patient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class PatientGUI extends HelperGUI {
    private JPanel topPanel;
    private JPanel topContentPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;
    private JPanel contentPanel;
    private JPanel buttonPanel;
    private JButton inspekteraButton;
    private JButton loggaUtButton;
    private JList list1;
    private JPanel mainPanel;
    private JLabel nameLabel;

    public PatientGUI(Patient patient) {
        init(mainPanel, "Mina bokningar", Accessibility.PATIENT);


        nameLabel.setText(patient.getFullName());

        List<Booking> bookingList = Driver.getHospital().getArchive().getBookings().get(patient);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                Driver.getLoginGUI().logout();
            }
        });

        setVisible(true);
        loggaUtButton.addActionListener(e -> {
            setVisible(false);
            Driver.getLoginGUI().logout();
        });
    }
}
