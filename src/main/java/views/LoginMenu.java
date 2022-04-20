package views;

import controllers.Controller;
import controllers.LoginController;
import enums.LoginMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            if ((matcher = LoginMenuCommands.isMatch(command, LoginMenuCommands.CREATE_USER)) != null)
                LoginController.getInstance().createUser(matcher);
            else if ((matcher = LoginMenuCommands.isMatch(command, LoginMenuCommands.LOGIN_USER)) != null)
                LoginController.getInstance().loginUser(matcher);
            else if (command.equals("menu exit"))
                System.exit(0);
            else if (command.equals("menu show-current"))
                System.out.println("Login menu");
            else if (command.startsWith("menu enter"))
                System.out.println("please login first");
            else
                System.out.println("invalid command");
        }
    }

}
