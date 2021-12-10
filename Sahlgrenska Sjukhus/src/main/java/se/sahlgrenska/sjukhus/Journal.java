package se.sahlgrenska.sjukhus;

import se.sahlgrenska.sjukhus.person.employee.Doctor;
import se.sahlgrenska.sjukhus.person.patient.Patient;

import java.time.LocalDateTime;

public class Journal {
    private Patient patient;
    private LocalDateTime time;
    private String note;
    private Doctor doctor;

    public Journal(Patient patient, LocalDateTime time, String note, Doctor doctor) {
        this.patient = patient;
        this.time = time;
        this.note = note;
        this.doctor = doctor;
    }
}
