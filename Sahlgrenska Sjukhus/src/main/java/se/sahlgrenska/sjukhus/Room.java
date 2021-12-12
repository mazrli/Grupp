package se.sahlgrenska.sjukhus;

import java.util.ArrayList;

import se.sahlgrenska.sjukhus.item.Equipment;
import se.sahlgrenska.sjukhus.item.Item;

public class Room {
    private String name;
    private int size;
    private ArrayList<Item> itemsInRoom = new ArrayList<Item>();


    private void addItemsToRoom(){

    new Equipment("Defibrilator", "Starts hearts", 50.5f, true),new Equipment("MRI", "Scans body", 50.0f, true));

}





    public ArrayList GetItems() {

        return itemsInRoom;
    }


}
