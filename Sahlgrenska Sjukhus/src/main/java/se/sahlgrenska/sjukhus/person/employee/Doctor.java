package se.sahlgrenska.sjukhus.person.employee;

import se.sahlgrenska.sjukhus.person.patient.Patient;

import java.util.List;

public class Doctor extends Employee {
    private List<Patient> patients;
    private Speciality speciality;
    private Accessibility accessibility;
}
