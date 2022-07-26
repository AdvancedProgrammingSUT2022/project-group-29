package views;

import app.Main;
import controllers.NetworkController;
import models.Response;

public class MainMenu {

    public void logout() {
        Response response = NetworkController.getInstance().logoutUser();
        Main.changeMenu("loginPage");
        Main.showPopupJustText(response.getMessage());
    }

    public void profile() {
        Main.changeMenu("profilePage");
    }

    public void scoreBoard() {
        Main.changeMenu("scoreBoardPage");
    }

    public void startGame() {
        Main.changeMenu("createGamePage");
    }


}
