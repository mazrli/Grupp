package se.sahlgrenska.sjukhus.item;

public class Equipment extends Item {
    private boolean isReusable;

    public Equipment(String name, String description, float price, boolean isReusable) {
        super(name, description, price);
        this.isReusable = isReusable;
    }
}
