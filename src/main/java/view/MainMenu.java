package view;

import controller.Controller;

import java.util.Scanner;

public class MainMenu {
    private static MainMenu instance = null;

    public void run(Scanner scanner) {
        exit();
    }

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        if (instance == null)
            return new MainMenu();
        return instance;
    }

    private void exit() {

    }

    private void ChangeMenu(String menu) {
        Controller controller = new Controller();
        controller.changeMenu(menu);
    }
}
