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

    public String changeNickname(Matcher matcher) {
        String nickname = matcher.group("nickname");
        if (isExistNickname(nickname) != null)
            return "user with nickname " + nickname + " already exists";
        User.getLoggedInUser().setNickname(nickname);
        return "nickname changed successfully!";
    }

    public String changePassword(Matcher matcher) {
        String currentPassword = matcher.group("currentPassword");
        String newPassword = matcher.group("newPassword");
        if (User.getLoggedInUser().getPassword().equals(currentPassword))
            return "current password is invalid";
        if (User.getLoggedInUser().getPassword().equals(newPassword))
            return "please enter a new password";
        User.getLoggedInUser().setPassword(newPassword);
        return "password changed successfully!";
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
