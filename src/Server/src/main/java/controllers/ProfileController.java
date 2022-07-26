package controllers;

import app.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import models.Response;
import models.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    public Response changeNickname(String nickname,String hash) {
        if (nickname.equals(""))
            return new Response(0,"enter new nickname");
        if (nickname.equals(User.getHash().get(hash).getNickname()))
            return new Response(0,"please enter a new nickname");
        if (isExistNickname(nickname) != null)
            return new Response(0,"user with nickname " + nickname + " already exists");
        User.getHash().get(hash).setNickname(nickname);
        return new Response(1,"nickname changed successfully!");
    }

    public Response changePassword(String newPassword,String hash) {
        if (newPassword.equals(""))
            return new Response(0,"enter new password");
        if (User.getHash().get(hash).getPassword().equals(newPassword))
            return new Response(0,"please enter a new password");
        User.getHash().get(hash).setPassword(newPassword);
        return new Response(1,"password changed successfully!");
    }

    public User isExistNickname(String nickname) {
        ArrayList<User> users = User.getAllUsers();
        for (User user : users) {
            if (user.getNickname().equals(nickname))
                return user;
        }
        return null;
    }

    public Response showInfo(String hash) {
        StringBuilder str = new StringBuilder();
        User user = User.getHash().get(hash);
        str.append(user.getNickname()).append("-");
        str.append(user.getPassword()).append("-");
        str.append(user.getScore()).append("-");
        str.append(user.getAvatar()).append("-");

        return new Response(1,String.valueOf(str));
    }

    public Response changeAvatar(String hash,String path) {
        User.getHash().get(hash).setAvatar("src/main/resources/assets/avatars/" + path.substring(95));
        return new Response(1,"avatar changed successfully!");
    }
}
