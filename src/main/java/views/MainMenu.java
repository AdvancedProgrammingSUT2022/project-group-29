package views;

import controllers.MainController;
import enums.MainMenuCommands;
import models.User;


import java.util.ArrayList;
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
        int count;
        ArrayList <User> users = null;
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
            } else if (MainMenuCommands.isMatch(command, MainMenuCommands.PLAY_GAME) != null) {
                if ((count = MainController.getInstance().checkIsValidPlayGame(command)) == -1)
                    System.out.println("invalid command1");
                else if ((users = MainController.getInstance().checkIsValidUsername(count,command)) == null)
                    System.out.println("some usernames are invalid");
                else {
                    System.out.println("A new game started");
                    a = 3;
                    break;
                }
            } else
                System.out.println("invalid command0");
        }

        if (a == 1)
            LoginMenu.getInstance().run(scanner);
        else if (a == 2)
            ProfileMenu.getInstance().run(scanner);
        else
            GameMenu.getInstance().run(scanner,users);
    }
}
