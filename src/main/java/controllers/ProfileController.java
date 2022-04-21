package controllers;

import models.User;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class ProfileController {
    private static ProfileController instance = null;

    private ProfileController() {
    }

    public static ProfileController getInstance() {
        if (instance == null)
            instance = new ProfileController();
        return instance;
    }

    public void changeProfile(Matcher matcher) {
//        String nickname = matcher.group("nickname");
//
//        ArrayList<User> users = User.getAllUsers();
//        if (LoginController.getInstance().isExistNickname(users,nickname) != null)
//            System.out.println("user with nickname " + nickname + " already exists");
    }

}
