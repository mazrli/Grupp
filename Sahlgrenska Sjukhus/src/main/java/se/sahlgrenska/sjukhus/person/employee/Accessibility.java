package se.sahlgrenska.sjukhus.person.employee;

public enum Accessibility {
    NONE,
    DOCTOR,
    RECEPTIONIST,
    ALL;


    public String toString() {
        if(this.equals(Accessibility.ALL))
            return "ADMIN";
        else
            return this + "";
    }
}
