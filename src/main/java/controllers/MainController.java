package controllers;

import models.User;

import java.util.regex.Matcher;

public class MainController {
    private static MainController instance = null;

    private MainController(){
    }

    public static MainController getInstance() {
        if (instance == null)
            instance = new MainController();
        return instance;
    }

    public void logoutUser() {
        System.out.println("user logged out successfully!");
        User.setLoggedInUser(null);
    }

    public Boolean enterMenu(Matcher matcher) {
        String menuName = matcher.group("menuName");
        if (menuName.equals("Profile Menu"))
            return true;
        else
            System.out.println("menu navigation is not possible");
        return false;
    }
}
