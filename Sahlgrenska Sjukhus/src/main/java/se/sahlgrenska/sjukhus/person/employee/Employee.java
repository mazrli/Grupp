package se.sahlgrenska.sjukhus.person.employee;

import se.sahlgrenska.sjukhus.person.Person;

public abstract class Employee extends Person {
    private String id;
    private float salary;
    private float workingHours;
    private Accessibility accessability;
    private LoginDetails loginDetails;
}
