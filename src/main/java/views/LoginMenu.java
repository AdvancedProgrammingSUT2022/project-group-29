package views;

import controllers.LoginController;
import enums.MenuCommands;

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
<<<<<<< HEAD
            if ((matcher = MenuCommands.isMatchCreateUser(command)) != null)
                LoginController.getInstance().createUser(matcher);
            else if ((matcher = MenuCommands.isMatchLoginUser(command)) != null)
                LoginController.getInstance().loginUser(matcher);
=======
            if ((matcher = LoginMenuCommands.isMatchCreateUser(command)) != null)
                System.out.println(LoginController.getInstance().createUser(matcher));
            else if ((matcher = LoginMenuCommands.isMatchLoginUser(command)) != null)
                System.out.println(LoginController.getInstance().loginUser(matcher));
>>>>>>> Erfan
            else if (command.equals("menu exit"))
                System.exit(0);
            else if (command.equals("menu show-current"))
                System.out.println("Login menu");
<<<<<<< HEAD
            else if ((matcher = MenuCommands.isMatch(command, MenuCommands.ENTER_MENU)) != null) {
                if (LoginController.getInstance().enterMenu(matcher))
=======
            else if ((matcher = LoginMenuCommands.isMatch(command, LoginMenuCommands.ENTER_MENU)) != null) {
                message = LoginController.getInstance().enterMenu(matcher);
                if (message.equals(""))
>>>>>>> Erfan
                    break;
                System.out.println(message);
            } else
                System.out.println("invalid command");
        }
        MainMenu.getInstance().run(scanner);
    }
}
