package views;

import controllers.Controller;
import controllers.LoginController;
import enums.LoginMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private static LoginMenu instance = null;

    private LoginMenu() {
    }

    public static LoginMenu getInstance() {
        if (instance == null)
            instance = new LoginMenu();
        return instance;
    }

    public void run(Scanner scanner) {
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;
            if ((matcher = LoginMenuCommands.isMatchCreateUser(command)) != null)
                LoginController.getInstance().createUser(matcher);
            else if ((matcher = LoginMenuCommands.isMatchLoginUser(command)) != null)
                LoginController.getInstance().loginUser(matcher);
            else if (command.equals("menu exit"))
                System.exit(0);
            else if (command.equals("menu show-current"))
                System.out.println("Login menu");
            else if ((matcher = LoginMenuCommands.isMatch(command, LoginMenuCommands.ENTER_MENU)) != null) {
                if (LoginController.getInstance().enterMenu(matcher))
                    break;
            } else
                System.out.println("invalid command");
        }
        MainMenu.getInstance().run(scanner);
    }
}
