package se.sahlgrenska.sjukhus;

public class Address {
    private String country;
    private String city;
    private String street;
    private String zip;


    public Address(String country, String street, String zip, String city) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zip = zip;
    }
}
