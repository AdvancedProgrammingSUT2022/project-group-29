package models;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private long lastWin;
    private long lastVisit;
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static HashMap<String, User> hash = new HashMap<>();
    private ArrayList<String> friends = new ArrayList<>();
    private HashMap<String, Integer> friendRequests = new HashMap<>();
    private int score;

    //private Civilization civilization = null;

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.avatar = "src/main/resources/assets/avatars/avatar" + (allUsers.size() % 4 + 1) + ".png";
        allUsers.add(this);
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }
    public String getUsername() {
        return username;
    }
    public String getNickname() {
        return nickname;
    }
    public String getPassword() {
        return password;
    }

    /*public Civilization getCivilization() {
        return civilization;
    }*/

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    /*
    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }
    */
    public static void setAllUsers(ArrayList<User> allUsers) {
        User.allUsers = allUsers;
    }


    public int getScore() {
        return score;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getLastWin() {
        return lastWin;
    }

    public void setLastWin(long lastWin) {
        this.lastWin = lastWin;
    }

    public static User getUserByNickname(String nickname) {
        for (User user : allUsers) {
            if (user.getNickname().equals(nickname))
                return user;
        }
        return null;
    }

    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.username.equals(username))
                return user;
        }
        return null;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static HashMap<String, User> getHash() {
        return hash;
    }

    public long getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(long lastVisit) {
        this.lastVisit = lastVisit;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public HashMap<String, Integer> getFriendRequests() {
        return friendRequests;
    }
}
