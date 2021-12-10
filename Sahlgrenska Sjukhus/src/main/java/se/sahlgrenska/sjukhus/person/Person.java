package se.sahlgrenska.sjukhus.person;

import se.sahlgrenska.sjukhus.Address;

public class Person {
    private String firstName;
    private String lastName;
    private String personNumber;
    private String phoneNumber;
    private Address address;

    public Person(String firstName, String lastName, String personNumber, String phoneNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personNumber = personNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Person() {

    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
