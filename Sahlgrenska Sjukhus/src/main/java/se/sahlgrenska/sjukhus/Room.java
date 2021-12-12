package se.sahlgrenska.sjukhus;

import java.time.LocalDateTime;
import java.util.ArrayList;

import se.sahlgrenska.sjukhus.item.Equipment;
import se.sahlgrenska.sjukhus.item.Item;
import se.sahlgrenska.sjukhus.item.Medicine;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private String name;
    private int size;
    private HashMap<Item, Integer> itemsInRoom = new HashMap<Item, Integer>();


    public Room(){
        addItemsToRoomTest();
    }


    private void addItemsToRoomTest(){
        Item item1 = new Equipment("Defibrilator", "Starts hearts", 2500.5f, true);
        Item item2 = new Equipment("MRI", "Scans body", 5000.0f, true);
        Item item3 = new Medicine("Panodil", "Pain relief", 15.0f, LocalDate.now());

        itemsInRoom.put(item1,2);
        itemsInRoom.put(item1,4);
        itemsInRoom.put(item2,1);
        itemsInRoom.put(item3,10);
    }



    public void printRoomItems(){
        for (Map.Entry<Item, Integer> roomItems :
                itemsInRoom.entrySet()) {

            // Printing all elements of a Map
            Util.print(roomItems.getKey() + " = "
                    + roomItems.getValue());
        }

    }

    public HashMap<Item, Integer> getItems() {
        return itemsInRoom;
    }


}
