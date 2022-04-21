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

    public String logoutUser() {
        User.setLoggedInUser(null);
        return "user logged out successfully!";
    }

    public String enterMenu(Matcher matcher) {
        String menuName = matcher.group("menuName");
        if (menuName.equals("Profile Menu"))
            return "";
        else
            return "menu navigation is not possible";
    }
}
