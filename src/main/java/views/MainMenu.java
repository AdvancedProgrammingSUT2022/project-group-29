package views;

import com.sun.tools.javac.Main;
import controllers.Controller;
import controllers.LoginController;
import controllers.MainController;
import enums.LoginMenuCommands;
import enums.MainMenuCommands;
import models.User;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    private static MainMenu instance = null;

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        if (instance == null)
            instance = new MainMenu();
        return instance;
    }

    public void run(Scanner scanner) {
        int a = 1;
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;
            if (command.equals("menu show-current"))
                System.out.println("Main Menu");
            else if (command.equals("menu exit"))
                break;
            else if (command.equals("user logout")) {
                MainController.getInstance().logoutUser();
                break;
            } else if ((matcher = MainMenuCommands.isMatch(command, MainMenuCommands.ENTER_MENU)) != null) {
                if (MainController.getInstance().enterMenu(matcher)) {
                    a = 2;
                    break;
                }
            } else
                System.out.println("invalid command");
        }
        if (a == 1)
            LoginMenu.getInstance().run(scanner);
        else
            ProfileMenu.getInstance().run(scanner);
    }
}
