package views;

import app.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import models.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScoreBoardView implements Initializable {
    public Pane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<User> sortedUser = new ArrayList<>(User.getAllUsers());
        sortedUser.sort((o1, o2) -> {
            if (o1.getScore() > o2.getScore()) return -1;
            if (o1.getLastWin() > o2.getLastWin()) return -1;

            return o1.getNickname().compareTo(o2.getNickname());
        });

        int i = 1;
        for (User user : sortedUser) {
            Pane pane = (Pane) this.pane.getChildren().get(i - 1);


            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(user.getAvatar());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Image image = new Image(inputStream);
            Rectangle rectangle = (Rectangle) pane.getChildren().get(1);
            rectangle.setFill(new ImagePattern(image));

            Label label = (Label) pane.getChildren().get(0);
            label.setText(i + "");

            Label label1 = (Label) pane.getChildren().get(2);
            label1.setText(user.getNickname());

            Label label2 = (Label) pane.getChildren().get(3);
            label2.setText(String.valueOf(user.getScore()));
            i++;
        }
    }

    public void back() {
        Main.changeMenu("mainPage");
    }
}
