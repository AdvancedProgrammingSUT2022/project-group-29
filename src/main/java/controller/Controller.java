package controller;

import model.User;

public class Controller {

    public static String createUser(String username, String password, String nickname) {

        User user = new User(username, password, nickname);
        return username;
    }

    public static String login(String username, String password) {
        return null;
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
