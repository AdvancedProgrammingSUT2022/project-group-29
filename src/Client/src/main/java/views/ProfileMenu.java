package views;

import app.Main;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import models.User;

import java.io.File;

public class ProfileMenu {
    public TextField changepassword;
    public TextField changenickname;
    public Rectangle avatar;

    // TODO show avatar

    public void changeUsername() {
        String message = ProfileController.getInstance().changeNickname(changenickname.getText());
        Main.showPopupJustText(message);
        ProfileController.getInstance().showInfo();
    }

    public void changePassword() {
        String message = ProfileController.getInstance().changePassword(changepassword.getText());
        Main.showPopupJustText(message);
        ProfileController.getInstance().showInfo();
    }

    public void changeAvatar() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("avatar");
        fileChooser.setInitialDirectory(new File("src/main/resources/assets/avatars/"));
        try {

        File file = fileChooser.showOpenDialog(Main.getScene().getWindow());
        User.getLoggedInUser().setAvatar(file.getAbsolutePath());
        ProfileController.getInstance().showInfo();
        } catch (Exception e) {

        }

    }

    public void backToMain() {
        Main.changeMenu("mainPage");
    }
}
