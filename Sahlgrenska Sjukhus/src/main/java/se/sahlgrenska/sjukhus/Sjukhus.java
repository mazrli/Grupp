package se.sahlgrenska.sjukhus;

import se.sahlgrenska.sjukhus.item.Item;
import se.sahlgrenska.sjukhus.person.Person;

import java.util.List;

public class Sjukhus {
    private String name;
    private int maxCapacity;
    private List<Item> items;
    private List<Person> persons;
    private Archive archive;
    private float balance;
    private Address address;
}
