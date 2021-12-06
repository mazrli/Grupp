package se.sahlgrenska.sjukhus.item;

import se.sahlgrenska.sjukhus.person.employee.Employee;

import java.util.Map;

public class Order {
    private Map<Item, Integer> shoppingCart;
    private float totalCost;
    private String notes;
    private Employee orderer;
}
