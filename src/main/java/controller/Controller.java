package controller;

import model.User;

public class Controller {

    public String createUser(String username, String password, String nickname) {

        User user = new User(username, password, nickname);
        return username;
    }

    public void login(String username, String password) {
    }

    public void changeMenu(String menu) {
    }

    public static void changePassword() {
    }

    public static void changeNickname() {
    }
}
