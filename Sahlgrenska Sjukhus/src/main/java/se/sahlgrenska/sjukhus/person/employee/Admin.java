package se.sahlgrenska.sjukhus.person.employee;

public class Admin extends Employee {
    Accessibility accessibility;

    public Admin(String id, float salary, float workingHours, Accessibility accessibility, LoginDetails loginDetails) {
        super(id, salary, workingHours, accessibility, loginDetails);
    }
}
