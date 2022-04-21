package controllers;

import models.User;

public class ProfileController {
    private static ProfileController instance = null;

    private ProfileController() {
    }

    public static ProfileController getInstance() {
        if (instance == null)
            instance = new ProfileController();
        return instance;
    }

    public void changeProfile(String nickname) {
        User.getLoggedInUser().setNickname(nickname);
    }

}
