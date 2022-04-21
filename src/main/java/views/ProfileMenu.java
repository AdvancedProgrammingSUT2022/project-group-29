package views;

import controllers.Controller;
import enums.LoginMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private static ProfileMenu instance = null;

    private ProfileMenu() {
    }

    public static ProfileMenu getInstance() {
        if (instance == null)
            return new ProfileMenu();
        return instance;
    }

    public void run(Scanner scanner) {
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;
            if (command.equals("menu exit"))
                System.exit(0);
            else if (command.equals("menu show-current"))
                System.out.println("Profile menu");
            else if (LoginMenuCommands.isMatch(command, LoginMenuCommands.ENTER_MENU) != null)
                System.out.println("please login first");
            else
                System.out.println("invalid command");
        }
    }

    private void changePassword() {

        Controller.changePassword();
    }

    private void changeNickname() {

        Controller.changeNickname();
    }

    private void exit() {

    }
}
