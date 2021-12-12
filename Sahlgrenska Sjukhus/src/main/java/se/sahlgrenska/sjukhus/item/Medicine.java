package se.sahlgrenska.sjukhus.item;

import java.time.LocalDate;
import java.util.Objects;

public class Medicine extends Item {
    LocalDate expirationDate;

    public Medicine(String name, String description, float price, LocalDate expirationDate) {
        super(name, description, price);
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Medicine medicine = (Medicine) o;
        return Objects.equals(expirationDate, medicine.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), expirationDate);
    }


}
