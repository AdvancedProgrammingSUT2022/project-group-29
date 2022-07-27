package views;

import app.Main;
import controllers.NetworkController;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import models.Response;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfileMenu implements Initializable {
    public TextField changepassword;
    public TextField changenickname;
    public Rectangle avatar;
    public Pane pane;
    public TextField friendName;

    public void initialize(URL location, ResourceBundle resources) {
        Response response = NetworkController.getInstance().profilePage();
        String[] strings = response.getMessage().split("-");
        Label label = (Label) pane.getChildren().get(1);
        label.setText(strings[0]);

        Label label1 = (Label) pane.getChildren().get(3);
        label1.setText(strings[1]);

        Label label2 = (Label) pane.getChildren().get(5);
        label2.setText(strings[2]);

        Rectangle rectangle = (Rectangle) pane.getChildren().get(13);

        try {
            InputStream inputStream = new FileInputStream(strings[3]);
            Image image = new Image(inputStream);
            rectangle.setFill(new ImagePattern(image));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void changeUsername() {
        Response response = NetworkController.getInstance().changeUsername(changenickname.getText());
        Main.showPopupJustText(response.getMessage());
        Main.changeMenu("profilePage");
    }

    public void changePassword() {
        Response response = NetworkController.getInstance().changePassword(changepassword.getText());
        Main.showPopupJustText(response.getMessage());
        Main.changeMenu("profilePage");
    }

    public void changeAvatar() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("avatar");
        fileChooser.setInitialDirectory(new File("src/main/resources/assets/avatars/"));
        try {
        File file = fileChooser.showOpenDialog(Main.getScene().getWindow());
        NetworkController.getInstance().changeAvatar(file.getAbsolutePath());
        Main.changeMenu("profilePage");
        } catch (Exception e) {

        }
    }

    public void backToMain() {
        Main.changeMenu("mainPage");
    }

    public void showFriends() {
        Response response = NetworkController.getInstance().showFriends();
        Main.showPopupJustText(response.getMessage());
    }

    public void addFriend() {
        Response response = NetworkController.getInstance().showRequestProfile(friendName.getText());
        if (response.getStatus_code() == 0) Main.showPopupJustText(response.getMessage());
        else {
            Label nickname = new Label("nickname: " + response.getMessage().split("-")[0]);
            Label score = new Label("score: " + response.getMessage().split("-")[1]);
            Rectangle rectangle = new Rectangle(70, 70);

            try {
                InputStream inputStream = new FileInputStream(response.getMessage().split("-")[2]);
                Image image = new Image(inputStream);
                rectangle.setFill(new ImagePattern(image));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Button button = new Button("send");
            button.setOnMouseClicked(event -> {
                Response response1 = NetworkController.getInstance().addRequest(friendName.getText());
                Main.showPopupJustText(response1.getMessage());
            });
            Main.getPopup().getContent().clear();
            VBox vBox = new VBox(nickname, score, rectangle, button);
            vBox.setStyle("-fx-background-color: pink");
            Main.getPopup().getContent().add(vBox);
            Main.getPopup().show(Main.getScene().getWindow());
        }
    }

    public void requestAsked() {
        Response response = NetworkController.getInstance().requestAsked();
        String[] strings = response.getMessage().split("-");
        ArrayList<VBox> vBoxes = new ArrayList<>();
        for (String string : strings) {
            Button accept = new Button("accept");
            accept.setOnMouseClicked(event -> NetworkController.getInstance().addFriend(string));
            Button reject = new Button("reject");
            reject.setOnMouseClicked(event -> NetworkController.getInstance().rejectFriend(string));
            VBox box = new VBox(new Label("username: " + string), accept, reject);
            vBoxes.add(box);
        }
        VBox vBox = new VBox();
        Main.getPopup().getContent().clear();
        for (VBox box : vBoxes) {
            vBox.getChildren().add(box);
        }
        Main.getPopup().getContent().clear();
        vBox.setStyle("-fx-background-color: pink");
        Main.getPopup().getContent().add(vBox);
        Main.getPopup().show(Main.getScene().getWindow());
    }

    public void requestSent() {
        Response response = NetworkController.getInstance().requestSent();
        Main.showPopupJustText(response.getMessage());
    }
}
