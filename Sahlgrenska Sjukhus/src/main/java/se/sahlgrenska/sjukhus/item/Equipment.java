package se.sahlgrenska.sjukhus.item;

import java.util.Objects;

public class Equipment extends Item {
    private boolean isReusable;

    public Equipment(String name, String description, float price, boolean isReusable) {
        super(name, description, price);
        this.isReusable = isReusable;
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
        Equipment equipment = (Equipment) o;
        return isReusable == equipment.isReusable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isReusable);
    }



}
