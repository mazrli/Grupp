package se.sahlgrenska.sjukhus.item;

import java.util.Objects;

public abstract class Item implements Comparable<Item> {
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
    public int compareTo(Item i) {
        int comp = name.hashCode() - i.getName().hashCode();
        System.out.println("INNUTI compareTo "+comp);

        if (comp != 0) {
            return comp;
        } else if (description.hashCode() - i.getDescription().hashCode() != 0) {
            return comp;
        } else {
            if (price - i.getPrice() == 0) {
                comp = 0;
            } else if (price - i.getPrice() > 0) {
                comp = 1;
            } else {
                comp = -1;
            }
            return comp;
        }

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

    public String[] toTable() {
        return new String[]{name, "1", String.valueOf(price), description};
    }

    @Override
    public String toString() {
        return name;
    }

}
