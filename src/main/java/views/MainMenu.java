package views;

import controllers.MainController;
import enums.MainMenuCommands;


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
        String message;

        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;
            if (command.equals("menu show-current"))
                System.out.println("Main Menu");
            else if (command.equals("menu exit"))
                break;
            else if (command.equals("user logout")) {
                System.out.println(MainController.getInstance().logoutUser());
                break;
            } else if ((matcher = MainMenuCommands.isMatch(command, MainMenuCommands.ENTER_MENU)) != null) {
                message = MainController.getInstance().enterMenu(matcher);
                if (message.equals("")) {
                    a = 2;
                    break;
                }
                System.out.println(message);
            } else
                System.out.println("invalid command");
        }

        if (a == 1)
            LoginMenu.getInstance().run(scanner);
        else
            ProfileMenu.getInstance().run(scanner);
    }
}
