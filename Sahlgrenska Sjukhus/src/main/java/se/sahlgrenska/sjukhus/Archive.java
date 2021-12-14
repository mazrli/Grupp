package se.sahlgrenska.sjukhus;

import se.sahlgrenska.sjukhus.person.employee.Employee;
import se.sahlgrenska.sjukhus.person.patient.Patient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Archive {
    private Map<Patient, List<Journal>> journals;
    private Map<Patient, List<Booking>> bookings;

    private Map<Employee, List<Patient>> patients;

    public Archive() {
        journals = new HashMap<>();
        bookings = new HashMap<>();
        patients = new HashMap<>();
    }

    public Archive(Map<Patient, List<Journal>> journals, Map<Patient, List<Booking>> bookings, Map<Employee, List<Patient>> patients) {
        this.journals = journals;
        this.bookings = bookings;
        this.patients = patients;
    }

    public Map<Employee, List<Patient>> getPatients() {
        return patients;
    }
    public void setPatients(Map<Employee, List<Patient>> patients) {
        this.patients = patients;
    }

    public Map<Patient, List<Journal>> getJournals() {
        return journals;
    }

    public void setJournals(Map<Patient, List<Journal>> journals) {
        this.journals = journals;
    }

    public Map<Patient, List<Booking>> getBookings() {
        return bookings;
    }

    public void setBookings(Map<Patient, List<Booking>> bookings) {
        this.bookings = bookings;
    }
}
