package models;

import java.util.ArrayList;

public class Room {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private String name;

    public Room(ArrayList<String> users, String name) {
        for (String user : users) {
            this.users.add(User.getUserByNickname(user));
        }
        this.name = name;
        rooms.add(this);
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
}
