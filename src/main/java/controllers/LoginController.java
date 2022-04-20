package controllers;

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
    }

    public void loginUser(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

    }
}