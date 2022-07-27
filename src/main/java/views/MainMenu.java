package views;

import app.Main;
import controllers.GameController;
import controllers.MainController;
import controllers.ProfileController;
import models.User;

public class MainMenu {

    public void logout() {
        String message = MainController.getInstance().logoutUser();
        Main.changeMenu("loginPage");
        Main.showPopupJustText(message);
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

    public void startGame() {
        Main.changeMenu("createGamePage");
    }

    public void loadGame() {
        GameController.getInstance().loadGame();
        Main.changeMenu("mapPage");
    }
}
