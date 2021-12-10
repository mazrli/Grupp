package se.sahlgrenska.sjukhus;

import se.sahlgrenska.sjukhus.person.employee.Doctor;
import se.sahlgrenska.sjukhus.person.employee.Employee;
import se.sahlgrenska.sjukhus.person.patient.Patient;

import java.time.LocalDateTime;

public class Journal {
    private Patient patient;
    private LocalDateTime time;
    private String note;
    private Doctor doctor;
    private Employee employee;

    @Override
    public String toString() {
        return "Journal{" +
                "patient=" + patient +
                ", time=" + time +
                ", note='" + note + '\'' +
                ", doctor=" + doctor +
                ", employee=" + employee +
                '}';
    }

    //Contructor for Journal.
    public Journal(Patient patient, LocalDateTime time, String note, Employee employee) {
        this.patient = patient;
        this.time = time;
        this.note = note;
        this.employee = employee;
    }
}
