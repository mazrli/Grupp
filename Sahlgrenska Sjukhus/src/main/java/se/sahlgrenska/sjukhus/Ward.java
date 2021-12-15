package se.sahlgrenska.sjukhus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Ward {
    private String name;
    private Set<Room> rooms;

    public Ward(String name, Set<Room> rooms) { //Vi borde egentligen kolla dessa innan de tilldelas.
        this.name = name;
        this.rooms = rooms;
    }


    public void addRoom(Room room) {
        if (room == null) {
            return;
        }

        if (!rooms.contains(room)) {
            rooms.add(room);
        }
    }


    public String getName(){
        return name;
    }


    public Set<Room> getRooms(){
        return rooms;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ward ward = (Ward) o;
        return Objects.equals(name, ward.name) && Objects.equals(rooms, ward.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rooms);
    }

    public String toString(){
        return String.format(getName().replace("[","").replace("]",""));
    }



}
