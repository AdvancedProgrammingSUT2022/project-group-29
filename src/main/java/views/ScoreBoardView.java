package views;

import app.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import models.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ScoreBoardView implements Initializable {

    public AnchorPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<User> sortedUser = new ArrayList<>(User.getAllUsers());
        sortedUser.sort((o1, o2) -> {
            if (o1.getScore() > o2.getScore()) return -1;
            if (o1.getLastVisit() > o2.getLastVisit()) return -1;

            return o1.getNickname().compareTo(o2.getNickname());
        });
        int i = 0;
        for (User user : sortedUser) {
            if (User.getLoggedInUser().getUsername().equals(user.getUsername()))
                user.setLastVisit(System.currentTimeMillis());
            VBox vBox = new VBox();
            Rectangle rectangle = new Rectangle(100, 100);
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(user.getAvatar());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image image = new Image(inputStream);
            rectangle.setFill(new ImagePattern(image));
            Label label = new Label("nickname: " + user.getNickname());
            Label label1 = new Label("\nscore: " + user.getScore());
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
            Date resultdate = new Date(user.getLastVisit());
            Label label2 = new Label("\nlast visit: " + sdf.format(resultdate));
            vBox.getChildren().add(rectangle);
            vBox.getChildren().add(label);
            vBox.getChildren().add(label1);
            vBox.getChildren().add(label2);
            mainPane.getChildren().add(vBox);
            vBox.setLayoutY(i * 250);
            vBox.setLayoutX(540);
            i++;
        }
    }

    public void back() {
        Main.changeMenu("mainPage");
    }
}
