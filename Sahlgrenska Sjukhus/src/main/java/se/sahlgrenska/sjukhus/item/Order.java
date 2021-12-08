package se.sahlgrenska.sjukhus.item;

import se.sahlgrenska.sjukhus.person.employee.Employee;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<Item, Integer> shoppingCart;
    private float totalCost;
    private String notes;
    private Employee orderer;

    public Order(float totalCost, String notes, Employee orderer) {
        this.shoppingCart = new HashMap<Item, Integer>();
        this.totalCost = totalCost;
        this.notes = notes;
        this.orderer = orderer;
    }
}
