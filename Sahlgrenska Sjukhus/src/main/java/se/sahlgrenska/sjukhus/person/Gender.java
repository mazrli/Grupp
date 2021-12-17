package se.sahlgrenska.sjukhus.person;

public enum Gender {
    MALE,
    FEMALE,
    APACHE_HELICOPTER;

    @Override
    public String toString() {
        return super.toString().replace("_", " ");
    }
}
