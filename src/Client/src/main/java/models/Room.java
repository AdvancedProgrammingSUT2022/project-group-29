package models;

import java.util.ArrayList;

public class Room {
    private static Room publicRoom = new Room(User.getAllUsers(), "public");
    private static Room currentRoom = null;
    private static ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<User> users;
    private String name;

    public Room(ArrayList<User> users, String name) {
        this.users = users;
        this.name = name;
        if (!name.equals("public"))
            rooms.add(this);
    }

    public static Room getRoomByName(String value) {
        for (Room room : Room.rooms) {
            if (room.name.equals(value))
                return room;
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean isChat() {
        return this.users.size() == 2;
    }

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public String getName() {
        return name;
    }

    public static Room getPublicRoom() {
        return publicRoom;
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(Room currentRoom) {
        Room.currentRoom = currentRoom;
    }

    public boolean hasUser(User loggedInUser) {
        for (User user : this.users) {
            if (user.getUsername().equals(loggedInUser.getUsername()))
                return true;
        }
        return false;
    }
}
