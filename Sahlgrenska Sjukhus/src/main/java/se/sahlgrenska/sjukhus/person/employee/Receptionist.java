package se.sahlgrenska.sjukhus.person.employee;

import se.sahlgrenska.sjukhus.item.Order;

import java.util.List;

public class Receptionist extends Employee {
    private List<Order> orders;
    Accessibility accessibility;

    public Receptionist(String id, float salary, float workingHours, LoginDetails loginDetails) {
        super(id, salary, workingHours, Accessibility.RECEPTIONIST, loginDetails);
    }
}
