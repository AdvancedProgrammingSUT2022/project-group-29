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

    public void createUser(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String nickname = matcher.group("nickname");

        if (isExistUsername(username) != null)
            System.out.println("user with username " + username + " already exists");
        else if (isExistNickname(nickname) != null)
            System.out.println("user with nickname " + nickname + " already exists");
        else {
            System.out.println("user created successfully!");
            User user = new User(username,password,nickname);
        }
    }

    public void loginUser(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        User user;
        if ((user = isExistUsername(username)) == null)
            System.out.println("Username and password didn’t match!");
        else if (user.getPassword().equals(password))
            System.out.println("Username and password didn’t match!");
        else {
            System.out.println("user logged in successfully!");
            User.setLoggedInUser(user);
        }
    }

    public Boolean enterMenu(Matcher matcher) {
        String menuName = matcher.group("menuName");
        if (User.getLoggedInUser() == null)
            System.out.println("please login first");
        else if (menuName.equals("Main")) {
            return true;
        }
        else
            System.out.println("menu navigation is not possible");
        return false;
    }

     User isExistUsername(String username) {
         ArrayList<User> users = User.getAllUsers();
         for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

     public User isExistNickname(String nickname) {
         ArrayList<User> users = User.getAllUsers();
        for (User user : users) {
            if (user.getNickname().equals(nickname))
                return user;
        }
        return null;
    }
}
