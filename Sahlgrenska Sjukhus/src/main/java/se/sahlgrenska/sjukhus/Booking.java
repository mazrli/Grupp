package se.sahlgrenska.sjukhus;

import se.sahlgrenska.gui.Booking.BookingGUI;
import se.sahlgrenska.sjukhus.person.employee.Employee;
import se.sahlgrenska.sjukhus.person.patient.Patient;
import se.sahlgrenska.sjukhus.item.Item;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Booking {
    private LocalDateTime time;
    private List<Patient> patients;
    private List<Employee> employees;
    private Ward ward;
    private Room room;


    public Booking(LocalDateTime time, List<Patient> patients, List<Employee> employees, Ward ward, Room room) {
        this.time = time;
        this.patients = patients;
        this.employees = employees;
        this.ward = ward;
        this.room = room;
    }


    public Room getRoom() {
        return room;

    }


}
