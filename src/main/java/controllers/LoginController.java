package controllers;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class LoginController {
    private static LoginController instance = null;

    private LoginController() {
    }

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public String createUser(String username, String password, String nickname) {
        if (username.equals("") || nickname.equals("") || password.equals(""))
            return "complete fields please";
        if (MainController.getInstance().isExistUsername(username) != null)
            return "user with username " + username + " already exists";
        if (ProfileController.getInstance().isExistNickname(nickname) != null)
            return "user with nickname " + nickname + " already exists";
        new User(username, password, nickname);
        return "user created successfully!";
    }

    public String loginUser(String username, String password) {

        User user;
        if ((user = MainController.getInstance().isExistUsername(username)) == null)
            return "Username and password didn’t match!";
        if (!user.getPassword().equals(password))
            return "Username and password didn’t match!";
        User.setLoggedInUser(user);
        return "user logged in successfully!";
    }

    public String enterMenu() {
        if (User.getLoggedInUser() == null)
            return "please login first";
        return "successful";
    }

    public void writeUserInfo() {
        try {
            FileWriter fileWriter = new FileWriter("user.txt");
            for (int i = 0; i < User.getAllUsers().size(); i++) User.getAllUsers().get(i).setCivilization(null);
            fileWriter.write(new Gson().toJson(User.getAllUsers()));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readUserInfo() {
        try {
            String info = new String(Files.readAllBytes(Paths.get("user.txt")));
            User.setAllUsers(new Gson().fromJson(info, new TypeToken<List<User>>(){}.getType()));
            if (User.getAllUsers() == null)
                User.setAllUsers(new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
