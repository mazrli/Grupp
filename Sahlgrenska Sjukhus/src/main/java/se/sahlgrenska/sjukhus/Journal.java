package se.sahlgrenska.sjukhus;

import se.sahlgrenska.sjukhus.person.employee.Doctor;
import se.sahlgrenska.sjukhus.person.patient.Patient;

import java.time.LocalDateTime;

public class Journal {
    private Patient patient;
    private LocalDateTime time;
    private String note;
    private Doctor doctor;
}
