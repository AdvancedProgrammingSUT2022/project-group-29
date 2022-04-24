package controllers;

import com.google.gson.reflect.TypeToken;
import models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.util.regex.Matcher;

public class LoginController {
    private static LoginController instance = null;

    private LoginController() {
    }

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
            readUserInfo();
        }
        return instance;
    }

    public String createUser(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String nickname = matcher.group("nickname");

        if (MainController.getInstance().isExistUsername(username) != null)
            return "user with username " + username + " already exists";
        if (ProfileController.getInstance().isExistNickname(nickname) != null)
            return "user with nickname " + nickname + " already exists";
        User user = new User(username, password, nickname);
        return "user created successfully!";
    }

    public String loginUser(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        User user;
        if ((user = MainController.getInstance().isExistUsername(username)) == null)
            return "Username and password didn’t match!";
        if (!user.getPassword().equals(password))
            return "Username and password didn’t match!";
        User.setLoggedInUser(user);
        return "user logged in successfully!";
    }

    public String enterMenu(Matcher matcher) {
        String menuName = matcher.group("menuName");
        if (User.getLoggedInUser() == null)
            return "please login first";
        if (menuName.equals("Main Menu"))
            return "";
        return "menu navigation is not possible";
    }



    public void writeUserInfo() {
        System.out.println("1");
        try {
            FileWriter fileWriter = new FileWriter("user.txt");
            fileWriter.write(new Gson().toJson(User.getAllUsers()));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readUserInfo() {
        try {
            String info = new String(Files.readAllBytes(Paths.get("user.txt")));
            User.setAllUsers(new Gson().fromJson(info, new TypeToken<List<User>>(){}.getType()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
