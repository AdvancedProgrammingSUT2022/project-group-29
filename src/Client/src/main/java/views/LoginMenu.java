package views;

import app.Main;
import controllers.NetworkController;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import models.Response;

public class LoginMenu {
    public TextField username;
    public TextField password;
    public TextField nickname;
    public Pane pane;


    public void login() {
        String message = NetworkController.getInstance().loginUser(username.getText(), password.getText());
        Main.showPopupJustText(message);
    }

    public void register() {
        String message = NetworkController.getInstance().createUser(username.getText(), password.getText(), nickname.getText());
        Main.showPopupJustText(message);
    }

    public void enterMainMenu() {
        Response response = NetworkController.getInstance().enterMenu();
        Main.showPopupJustText(response.getMessage());
        if (response.getStatus_code() == 1)
            Main.changeMenu("mainPage");
    }
}
