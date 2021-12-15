package se.sahlgrenska.sjukhus.person;

import se.sahlgrenska.sjukhus.Address;

public class Person {
    private final String firstName;
    private final String lastName;
    private final String personNumber;
    private final String phoneNumber;
    private final Address address;
    private final Gender gender;

    public Person(String firstName, String lastName, String personNumber, Gender gender, String phoneNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personNumber = personNumber;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " - " + personNumber;
    }
}

