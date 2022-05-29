package controllers;

import app.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
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

    public String changeNickname(String nickname) {
        if (nickname.equals(""))
            return "enter new nickname";
        if (isExistNickname(nickname) != null)
            return "user with nickname " + nickname + " already exists";
        User.getLoggedInUser().setNickname(nickname);
        return "nickname changed successfully!";
    }

    public String changePassword(String newPassword) {
        if (newPassword.equals(""))
            return "enter new password";
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

    public void showInfo() {
        Pane pane = (Pane) Main.getScene().getRoot();

        Label label = (Label) pane.getChildren().get(1);
        label.setText(User.getLoggedInUser().getUsername());

        Label label1 = (Label) pane.getChildren().get(3);
        label1.setText(User.getLoggedInUser().getPassword());

        Label label2 = (Label) pane.getChildren().get(5);
        label2.setText(String.valueOf(User.getLoggedInUser().getScore()));

        Rectangle rectangle = (Rectangle) pane.getChildren().get(13);

        try {
            InputStream inputStream = new FileInputStream(User.getLoggedInUser().getAvatar());
            Image image = new Image(inputStream);
            rectangle.setFill(new ImagePattern(image));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
