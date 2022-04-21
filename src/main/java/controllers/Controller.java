package controllers;

import models.User;

public class Controller {

    public static String createUser(String username, String password, String nickname) {
        /*if (User.usernameExist(username))
            return "user with username " + username + " already exists";
        else if (User.nicknameExist(nickname))
            return "user with nickname " + nickname + " already exists";
        User user = new User(username, password, nickname);
        return "user created successfully";*/
        return "ww";
    }

    public static String login(String username, String password) {
        /*if (User.getUser(username,password) == null)
            return "Username and password didn’t match!";
        User.setLoggedInUser(User.getUser(username, password));*/
        return "user logged in successfully";
    }

    public static void changeMenu(String menu) {

    }

    public static String changePassword() {
        return null;
    }

    public static String changeNickname() {
        return null;
    }
}
