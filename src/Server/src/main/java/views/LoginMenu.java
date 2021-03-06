package views;

import app.Main;
import controllers.LoginController;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginMenu {
    public TextField username;
    public TextField password;
    public TextField nickname;
    public Pane pane;

    // TODO set background and avatar
    public void login() {
        String message = LoginController.getInstance().loginUser(username.getText(), password.getText());
        Main.showPopupJustText(message);
    }

    public void register() {
        String message = LoginController.getInstance().createUser(username.getText(), password.getText(), nickname.getText());
        Main.showPopupJustText(message);
    }

    public void enterMainMenu() {
        String message = LoginController.getInstance().enterMenu();
        Main.showPopupJustText(message);
        if (message.equals("successful"))
            Main.changeMenu("mainPage");
    }
}
