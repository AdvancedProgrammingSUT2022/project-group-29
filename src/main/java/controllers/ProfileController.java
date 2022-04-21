package controllers;

import models.User;

import java.util.ArrayList;

public class ProfileController {
    private static ProfileController instance = null;

    private ProfileController() {
    }

    public static ProfileController getInstance() {
        if (instance == null)
            instance = new ProfileController();
        return instance;
    }

    public User isExistNickname(String nickname) {
        ArrayList<User> users = User.getAllUsers();
        for (User user : users) {
            if (user.getNickname().equals(nickname))
                return user;
        }
        return null;
    }

    public void changeProfile(String nickname) {
        User.getLoggedInUser().setNickname(nickname);
    }

    public boolean isPasswordCorrect(String current_password) {
        return User.getLoggedInUser().getPassword().equals(current_password);
    }

    public boolean newAndOldPasswordsAreSame(String new_password) {
        return User.getLoggedInUser().getPassword().equals(new_password);
    }

    public void changePassword(String new_password) {
        User.getLoggedInUser().setPassword(new_password);
    }
}
