package views;

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
        String message;
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;
            if ((matcher = LoginMenuCommands.isMatchCreateUser(command)) != null)
                System.out.println(LoginController.getInstance().createUser(matcher));
            else if ((matcher = LoginMenuCommands.isMatchLoginUser(command)) != null)
                System.out.println(LoginController.getInstance().loginUser(matcher));
            else if (command.equals("menu exit")) {
                //LoginController.getInstance().writeUserInfo();
                System.exit(0);
            }
            else if (command.equals("menu show-current"))
                System.out.println("Login menu");
            else if ((matcher = LoginMenuCommands.isMatch(command, LoginMenuCommands.ENTER_MENU)) != null) {
                message = LoginController.getInstance().enterMenu(matcher);
                if (message.equals(""))
                    break;
                System.out.println(message);
            } else
                System.out.println("invalid command");
        }
        MainMenu.getInstance().run(scanner);
    }
}
