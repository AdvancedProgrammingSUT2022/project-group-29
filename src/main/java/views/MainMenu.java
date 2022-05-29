package views;

import app.Main;
import controllers.MainController;
import controllers.ProfileController;

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
}
