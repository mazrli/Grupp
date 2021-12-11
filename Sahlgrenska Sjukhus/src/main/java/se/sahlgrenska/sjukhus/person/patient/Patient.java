package se.sahlgrenska.sjukhus.person.patient;

import se.sahlgrenska.sjukhus.Address;
import se.sahlgrenska.sjukhus.Journal;
import se.sahlgrenska.sjukhus.person.Gender;
import se.sahlgrenska.sjukhus.person.Person;

import javax.management.Notification;
import java.util.List;

public class Patient extends Person {
   public List<Journal> journals;
    public List<Disease> diseases;
    public List<Notification> notifications;

    @Deprecated
    public Patient(String firstName, String lastName, String personNumber, String phoneNumber, Address address) {
        super(firstName, lastName, personNumber, Gender.FEMALE, phoneNumber, address);
    }

    @Deprecated
    public Patient() {
        super("Johan", "Andersson", "w324", Gender.MALE, "sdf", null);
    }

    public Patient(Person person, List<Journal> journals, List<Disease> diseases, List<Notification> notifications) {
        super(person.getFirstName(), person.getLastName(), person.getPersonNumber(), person.getGender(), person.getPhoneNumber(), person.getAddress());
        this.journals = journals;
        this.diseases = diseases;
        this.notifications = notifications;
    }
}
