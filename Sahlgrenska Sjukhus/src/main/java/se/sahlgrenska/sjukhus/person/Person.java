package se.sahlgrenska.sjukhus.person;

import se.sahlgrenska.sjukhus.Address;

public class Person {
    private String name;
    private String personNumber;
    private String phoneNumber;
    private Address address;

    public Person(String name, String personNumber, String phoneNumber, Address address) {
        this.name = name;
        this.personNumber = personNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Person() {

    }
}
