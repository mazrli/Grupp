package se.sahlgrenska.sjukhus.item;

public abstract class Item {
    public String name;
    public String description;
    public float price;

    public Item(String name, String description, float price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
