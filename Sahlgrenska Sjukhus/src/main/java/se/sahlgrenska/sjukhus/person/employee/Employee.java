package se.sahlgrenska.sjukhus.person.employee;

import se.sahlgrenska.sjukhus.Address;
import se.sahlgrenska.sjukhus.person.Person;

public class Employee extends Person {
    private String id;
    private float salary;
    private float workingHours;
    private Accessibility accessibility;
    private LoginDetails loginDetails;

    public Employee(Person person, String id, float salary, float workingHours, Accessibility accessibility, LoginDetails loginDetails) {
        super(person.getFirstName(), person.getLastName(), person.getPersonNumber(), person.getPhoneNumber(), person.getAddress());
        this.id = id;
        this.salary = salary;
        this.workingHours = workingHours;
        this.accessibility = accessibility;
        this.loginDetails = loginDetails;
    }

    @Deprecated
    public Employee(String id, float salary, float workingHours, Accessibility accessibility, LoginDetails loginDetails) {
        super("Olof", "Andersson", "12312312", "2234", null);
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

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", salary=" + salary +
                ", workingHours=" + workingHours +
                ", accessibility=" + accessibility +
                ", loginDetails=" + loginDetails +
                '}';
    }
}
