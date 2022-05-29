package views;

import app.Main;
import controllers.ProfileController;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import models.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileMenu {
    public TextField changepassword;
    public TextField changenickname;
    public Rectangle avatar;

    // TODO show avatar

    public void changeUsername() {
        String message = ProfileController.getInstance().changeNickname(changenickname.getText());
        Main.showPopup(message);
        ProfileController.getInstance().showInfo();
    }

    public void changePassword() {
        String message = ProfileController.getInstance().changePassword(changepassword.getText());
        Main.showPopup(message);
        ProfileController.getInstance().showInfo();
    }

    public void changeAvatar() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("avatar");
        fileChooser.setInitialDirectory(new File("src/main/resources/assets/avatars/"));
        File file = fileChooser.showOpenDialog(Main.getScene().getWindow());
        User.getLoggedInUser().setAvatar(file.getAbsolutePath());
        ProfileController.getInstance().showInfo();

    }

    public void backToMain() {
        Main.changeMenu("mainPage");
    }
}
