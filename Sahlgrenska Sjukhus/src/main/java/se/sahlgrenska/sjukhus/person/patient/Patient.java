package se.sahlgrenska.sjukhus.person.patient;

import se.sahlgrenska.sjukhus.Journal;
import se.sahlgrenska.sjukhus.person.Person;

import javax.management.Notification;
import java.util.List;

public class Patient extends Person {
   public List<Journal> journals;
    public List<Disease> diseases;
    public List<Notification> notifications;

}
