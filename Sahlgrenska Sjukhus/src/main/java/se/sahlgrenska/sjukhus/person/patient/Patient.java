package se.sahlgrenska.sjukhus.person.patient;

import se.sahlgrenska.sjukhus.Address;
import se.sahlgrenska.sjukhus.Journal;
import se.sahlgrenska.sjukhus.person.Gender;
import se.sahlgrenska.sjukhus.person.Person;

import javax.management.Notification;
import java.util.List;

public class Patient extends Person {

    public List<Journal> journals;
    private int patientID;
    private List<Disease> diseases;
    private List<Notification> notifications;
    private String condition;
    private boolean criticalCondition;
    private BloodType bloodType;

    @Deprecated
    public Patient(String firstName, String lastName, String personNumber, String phoneNumber, Address address) {
        super(firstName, lastName, personNumber, Gender.FEMALE, phoneNumber, address);
    }

    @Deprecated
    public Patient() {
        super("Johan", "Andersson", "w324", Gender.MALE, "sdf", null);
    }

    public Patient(Person person, int patientID, List<Disease> diseases, List<Notification> notifications, String condition, boolean criticalCondition, BloodType bloodType) {
        super(person.getFirstName(), person.getLastName(), person.getPersonNumber(), person.getGender(), person.getPhoneNumber(), person.getAddress());
        this.patientID = patientID;
        this.diseases = diseases;
        this.notifications = notifications;
        this.condition = condition;
        this.criticalCondition = criticalCondition;
        this.bloodType = bloodType;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public String getCondition() {
        return condition;
    }

    public boolean isCriticalCondition() {
        return criticalCondition;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public int getPatientID() {
        return patientID;
    }
}
