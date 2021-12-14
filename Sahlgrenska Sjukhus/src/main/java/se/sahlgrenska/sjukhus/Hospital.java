package se.sahlgrenska.sjukhus;

import se.sahlgrenska.sjukhus.item.Item;
import se.sahlgrenska.sjukhus.person.Person;

import java.util.*;

public class Hospital {

    private String name;
    private int maxCapacity;
    private Map<Item, Integer> items;

    private final Set<Person> persons;

    private Archive archive;
    private float balance;
    private Address address;
    private ArrayList<Ward> wards;

    public Hospital(String name, int maxCapacity, Map<Item, Integer> items, Set<Person> persons, Archive archive, float balance, Address address) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.items = items;
        this.persons = persons;
        this.archive = archive;
        this.balance = balance;
        this.address = address;
        this.wards = new ArrayList<Ward>();
        fillArrayListTempUntilDatabaseConnection();
    }

    private void fillArrayListTempUntilDatabaseConnection() {
        Room roomArr[] = {new Room("Room 1", 20), new Room("Room2", 30)};
        HashSet<Room> rooms = new HashSet<>(Arrays.asList(roomArr));
        addWard(new Ward("Ward1", rooms));
    }


    public void addWard(Ward ward) {
        if (ward == null) {
            System.out.println("cant add null-value to hospital ward-list");
            return;
        }

        if (!wards.contains(ward)) {
            wards.add(ward);
        }
    }

    public ArrayList<Ward> getWards() {
        return wards;
    }


    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }


    public Set<Person> getPersons() {
        return persons;
    }
}
