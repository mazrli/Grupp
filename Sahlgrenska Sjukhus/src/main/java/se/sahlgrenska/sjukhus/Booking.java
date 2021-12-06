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
}
