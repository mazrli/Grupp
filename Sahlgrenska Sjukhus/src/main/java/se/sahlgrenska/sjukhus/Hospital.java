package se.sahlgrenska.sjukhus;

import se.sahlgrenska.sjukhus.item.Item;
import se.sahlgrenska.sjukhus.person.Person;

import java.util.List;
import java.util.Map;

public class Hospital {

    private String name;
    private int maxCapacity;
    private Map<Item, Integer> items;
    private List<Person> persons;
    private Archive archive;
    private float balance;
    private Address address;

    public Hospital(String name, int maxCapacity, Map<Item, Integer> items, List<Person> persons, Archive archive, float balance, Address address) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.items = items;
        this.persons = persons;
        this.archive = archive;
        this.balance = balance;
        this.address = address;
    }




}
