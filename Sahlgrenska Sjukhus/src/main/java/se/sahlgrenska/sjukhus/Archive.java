package se.sahlgrenska.sjukhus;

import se.sahlgrenska.sjukhus.person.patient.Patient;

import java.util.List;
import java.util.Map;

public class Archive {
    private Map<Patient, List<Journal>> journals;
    private Map<Patient, List<Booking>> bookings;
}
