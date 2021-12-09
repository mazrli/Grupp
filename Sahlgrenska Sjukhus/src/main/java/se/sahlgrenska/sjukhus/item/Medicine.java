package se.sahlgrenska.sjukhus.item;

import java.time.LocalDate;

public class Medicine extends Item {
    LocalDate expirationDate;

    public Medicine(String name, String description, float price, LocalDate expirationDate) {
        super(name, description, price);
        this.expirationDate = expirationDate;
    }
}
