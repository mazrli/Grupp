package se.sahlgrenska.sjukhus.person.employee;

import se.sahlgrenska.sjukhus.person.patient.Patient;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Employee {
    private List<Patient> patients = new ArrayList<>();
    private Speciality speciality;

    public Doctor(String id, float salary, float workingHours, LoginDetails loginDetails) {
        super(id, salary, workingHours, Accessibility.DOCTOR, loginDetails);
    }
}
