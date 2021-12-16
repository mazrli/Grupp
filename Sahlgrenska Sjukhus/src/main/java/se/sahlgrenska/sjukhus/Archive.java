package se.sahlgrenska.sjukhus;

import se.sahlgrenska.sjukhus.person.employee.Employee;
import se.sahlgrenska.sjukhus.person.patient.Disease;
import se.sahlgrenska.sjukhus.person.patient.Patient;

import java.util.*;

public class Archive {
    private Map<Patient, List<Journal>> journals;
    private Map<Patient, List<Booking>> bookings;
    private Set<Disease> diseases;

    private Map<Employee, List<Patient>> patients;

    public Set<Disease> getDiseases() {
        return diseases;
    }

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

    public void AddJournal(Journal journal, Patient patient) {
        List<Journal> journalList;
        if (journals.containsKey(patient)) {
            journalList = journals.get(patient);


        } else {
            journalList = new ArrayList<Journal>();

        }
        journalList.add(journal);
        journals.put(patient, journalList);
    }

    public void printJournals() {
        for (Map.Entry<Patient, List<Journal>> patJourList : journals.entrySet()) {

            List<Journal> journalList = patJourList.getValue();
            System.out.println("Patient: " + patJourList.getKey() + " has ");
            for (Journal jour : journalList) {
                System.out.println(jour.toString());
            }
        }
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
