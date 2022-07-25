package views;

import app.Main;
import controllers.NetworkController;

public class MainMenu {

    public void logout() {
        String message = NetworkController.logoutUser();
        Main.changeMenu("loginPage");
        Main.showPopupJustText(message);
    }

    public void profile() {
        Main.changeMenu("profilePage");
        NetworkController.profilePage();
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
}
