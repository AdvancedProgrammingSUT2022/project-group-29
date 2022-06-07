package views;

import app.Main;
import controllers.MainController;
import controllers.ProfileController;
import javafx.scene.input.MouseEvent;

public class MainMenu {

    public void logout() {
        String message = MainController.getInstance().logoutUser();
        Main.changeMenu("loginPage");
        Main.showPopup(message);
    }

    public void profile() {
        Main.changeMenu("profilePage");
        ProfileController.getInstance().showInfo();
    }

    public void scoreBoard() {
        Main.changeMenu("scoreBoardPage");
    }

    public void chatRoom() {
        Main.changeMenu("chatRoom");
    }

    public void startGame(MouseEvent mouseEvent) {
        Main.changeMenu("createGamePage");
    }
}
