package se.sahlgrenska.sjukhus.person.employee;

import se.sahlgrenska.sjukhus.person.Person;

public abstract class Employee extends Person {
    private String id;
    private float salary;
    private float workingHours;
    private Accessibility accessibility;
    private LoginDetails loginDetails;

    public Employee(String id, float salary, float workingHours, Accessibility accessibility, LoginDetails loginDetails) {
        this.id = id;
        this.salary = salary;
        this.workingHours = workingHours;
        this.accessibility = accessibility;
        this.loginDetails = loginDetails;
    }

    public Accessibility getAccessibility() {
        return accessibility;
    }

    public LoginDetails getLoginDetails() {
        return loginDetails;
    }

    public String getId() {
        return id;
    }
}
