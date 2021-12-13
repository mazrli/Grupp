package se.sahlgrenska.sjukhus.person.employee;

public class Admin extends Employee {
    Accessibility accessibility;

    public Admin(String id, float salary, float workingHours, LoginDetails loginDetails) {
        super(id, salary, workingHours, Accessibility.ADMIN, loginDetails);
    }
}
