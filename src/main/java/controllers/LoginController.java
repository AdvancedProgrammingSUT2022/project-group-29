package controllers;

import models.User;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class LoginController {
    private static LoginController instance = null;

    private LoginController() {
    }

    public static LoginController getInstance() {
        if (instance == null)
            instance = new LoginController();
        return instance;
    }

    public String createUser(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String nickname = matcher.group("nickname");

<<<<<<< HEAD
        if (isExistUsername(username) != null)
            System.out.println("user with username " + username + " already exists");
        else if (isExistNickname(nickname) != null)
            System.out.println("user with nickname " + nickname + " already exists");
        else {
            System.out.println("user created successfully!");
            User user = new User(username,password,nickname);
        }
=======
        ArrayList<User> users = User.getAllUsers();
        if (isExistUsername(users, username) != null)
            return "user with username " + username + " already exists";
        if (isExistNickname(users, nickname) != null)
            return "user with nickname " + nickname + " already exists";
        User user = new User(username, password, nickname);
        return "user created successfully!";
>>>>>>> Erfan
    }

    public String loginUser(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        User user;
<<<<<<< HEAD
        if ((user = isExistUsername(username)) == null)
            System.out.println("Username and password didn’t match!");
        else if (user.getPassword().equals(password))
            System.out.println("Username and password didn’t match!");
        else {
            System.out.println("user logged in successfully!");
            User.setLoggedInUser(user);
        }
=======
        ArrayList<User> users = User.getAllUsers();
        if ((user = isExistUsername(users, username)) == null)
            return "Username and password didn’t match!";
        if (user.getPassword().equals(password))
            return "Username and password didn’t match!";
        User.setLoggedInUser(user);
        return "user logged in successfully!";
>>>>>>> Erfan
    }

    public String enterMenu(Matcher matcher) {
        String menuName = matcher.group("menuName");
        if (User.getLoggedInUser() == null)
            return "please login first";
        if (menuName.equals("Main Menu"))
            return "";
        return "menu navigation is not possible";
    }
<<<<<<< HEAD
    //داخل کلاس User تابع مشابهی قرار دادم که برای منوی پروفایل از اونجا استفاده کنیم ، میتونی این متذ رو پاک کنی! و اون رو استفاده کنی! :))))
     private User isExistUsername(String username) {
         ArrayList<User> users = User.getAllUsers();
         for (User user : users) {
=======

    private User isExistUsername(ArrayList<User> users, String username) {
        for (User user : users) {
>>>>>>> Erfan
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

<<<<<<< HEAD
     private User isExistNickname(String nickname) {
         ArrayList<User> users = User.getAllUsers();
=======
    private User isExistNickname(ArrayList<User> users, String nickname) {
>>>>>>> Erfan
        for (User user : users) {
            if (user.getNickname().equals(nickname))
                return user;
        }
        return null;
    }
}
