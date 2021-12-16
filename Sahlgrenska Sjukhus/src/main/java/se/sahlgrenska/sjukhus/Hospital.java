package se.sahlgrenska.sjukhus;

import se.sahlgrenska.sjukhus.item.Equipment;
import se.sahlgrenska.sjukhus.item.Item;
import se.sahlgrenska.sjukhus.item.Medicine;
import se.sahlgrenska.sjukhus.person.Person;
import se.sahlgrenska.sjukhus.person.patient.Patient;

import java.time.LocalDate;
import java.util.*;

public class Hospital {

    private String name;
    private int maxCapacity;
    private Map<Item, Integer> items;
    private Set<Person> persons;
    private Archive archive;
    private float balance;
    private Address address;
    private List<Ward> wards;
    private int id;

    @Deprecated
    public Hospital(String name, int maxCapacity, Map<Item, Integer> items, Set<Person> persons, Archive archive, float balance, Address address) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.items = items;
        this.persons = persons;
        this.archive = archive;
        this.balance = balance;
        this.address = address;
        this.wards = new ArrayList<>();
        // fillArrayListTempUntilDatabaseConnection();
    }

    public Hospital(String name, int maxCapacity, float balance, Map<Item, Integer> storage, Address address, List<Ward> wards, int id) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.balance = balance;
        this.address = address;
        this.wards = wards;
        this.items = storage;
        this.id = id;
    }

    public Hospital(String Namn, String Beskrivning, String Mängd, String Pris) {

    }


    private void fillArrayListTempUntilDatabaseConnection() {

        Item item1 = new Equipment("Defibrillator", "Starts hearts", 2500.5f, true);
        Item item2 = new Equipment("MRI", "Scans body", 5000.0f, true);
        Item item3 = new Medicine("Panodil", "Pain relief", 15.0f, LocalDate.now());
        Item item4 = new Medicine("Alvedon", "Pain relief", 12.5f, LocalDate.now());

        Room roomArr[] = {new Room("Room 1", 20, item1, 2), new Room("Room2", 30, item1, 4)};
        HashSet<Room> rooms = new HashSet<>(Arrays.asList(roomArr));
        addWard(new Ward("Ward1", rooms));

        HashMap<Item, Integer> itemsInRoom = new HashMap<Item, Integer>();
        itemsInRoom.put(item2, 1);
        itemsInRoom.put(item3, 10);
        Room roomArr1[] = {new Room("Room 3", 15, item4, 45), new Room("Room6", 40, itemsInRoom)};
        HashSet<Room> room1 = new HashSet<>(Arrays.asList(roomArr1));
        addWard(new Ward("Ward2", room1));


        fillItemsStorageForHospital();
        System.out.println("Fake data added");
    }

    public Patient getPatient(String personnumber) {
        for (Patient patient : archive.getBookings().keySet())
            if (patient.getPersonNumber().equals(personnumber))
                return patient;
        return null;
    }


    private void fillItemsStorageForHospital() {
        Item item1 = new Equipment("Defibrilator", "Starts hearts", 2500.5f, true);
        Item item2 = new Equipment("MRI", "Scans body", 5000.0f, true);
        Item item5 = new Equipment("Stethoscope", "Heartbeats", 5000.0f, true);
        Item item3 = new Medicine("Panodil", "Pain relief", 15.0f, LocalDate.now());
        Item item4 = new Medicine("Alvedon", "Pain relief", 12.5f, LocalDate.now());
        Item item6 = new Medicine("SARS-vaccine", "Covid", 105.2f, LocalDate.now());

        items.put(item1, 25);
        items.put(item2, 10);
        items.put(item3, 15);
        items.put(item4, 50);
        items.put(item5, 30);
        items.put(item6, 44);

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

    public Map<Item, Integer> getHospitalsStoredItems() {
        return items;
    }


    public int getItemStorageQuantity(Item item) {
        if (items.containsKey(item)) {
            return items.get(item);
        }
        return -1;
    }

    public void addItem(Item item, int quantity) {

        if (item != null || quantity > 0) {
            items.put(item, quantity);
            System.out.println("Added to hospital "+ item);
        }

    }

    public void removeItem(Item item, int quantity) {

        if (items.containsKey(item)) {
            int maxQuantity = items.get(item);
            int availableQuantity = maxQuantity - quantity;

            if (availableQuantity > 0) {
                items.put(item, availableQuantity);
                maxQuantity = availableQuantity;
            } else {
                items.remove(item);
            }
        }
    }


    public List<Ward> getWards() {
        return wards;
    }


    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public int getId() {
        return id;
    }
}
