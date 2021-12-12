package se.sahlgrenska.sjukhus.item;

import java.util.Objects;

public abstract class Item {
    public String name;
    public String description;
    public float price;


    public Item(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other instanceof Item) {
            Item o = (Item) other;
            return getName().equals(o.getName()) && getPrice() == o.getPrice() && description.equals(o.getDescription());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getPrice());
    }

    @Override
    public String toString(){
        return getName()+" "+getDescription()+" "+getPrice();
    }

}
