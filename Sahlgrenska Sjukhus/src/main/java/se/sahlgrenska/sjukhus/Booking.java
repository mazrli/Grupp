package se.sahlgrenska.sjukhus;

import se.sahlgrenska.sjukhus.person.employee.Employee;
import se.sahlgrenska.sjukhus.person.patient.Patient;

import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    private LocalDateTime time;
    private List<Patient> patients;
    private List<Employee> employees;
    private Ward ward;
    private Room room;
    private String note;


    public Booking(LocalDateTime time, List<Patient> patients, List<Employee> employees, Ward ward, Room room, String note) {
        this.time = time;
        this.patients = patients;
        this.employees = employees;
        this.ward = ward;
        this.room = room;
        this.note = note;
    }


    public Room getRoom() {
        return room;

    }

    @Override
    public String toString() {
        return "Booking{" +
                "time=" + time +
                ", patients=" + patients +
                ", employees=" + employees +
                ", ward=" + ward +
                ", room=" + room +
                '}';
    }

    public String getNote() {
        return note;
    }
}
