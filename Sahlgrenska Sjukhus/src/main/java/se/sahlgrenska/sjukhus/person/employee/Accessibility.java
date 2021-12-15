package se.sahlgrenska.sjukhus.person.employee;

import java.util.stream.Stream;

public enum Accessibility {
    NONE,
    PATIENT,
    DOCTOR,
    RECEPTIONIST,
    ADMIN;

    public static Object[] getEmployeeRoles() {
        return Stream.of(Accessibility.values()).filter(v -> v != Accessibility.PATIENT).toArray();
    }
}
