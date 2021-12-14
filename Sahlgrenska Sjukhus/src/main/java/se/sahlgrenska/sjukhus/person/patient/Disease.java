package se.sahlgrenska.sjukhus.person.patient;

import java.util.List;

public class Disease {
    private List<Symptom> symptoms;
    private String name;
    private boolean isCritical;

    public Disease (String diseaseName) {
        this.name = diseaseName;
    }

    public Disease(List<Symptom> symptoms, String name, boolean isCritical) {
        this.symptoms = symptoms;
        this.name = name;
        this.isCritical = isCritical;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public String getDiseaseName() {
        return name;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }
}
