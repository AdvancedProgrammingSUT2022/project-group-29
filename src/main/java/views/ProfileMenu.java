package views;

import controllers.Controller;
import controllers.LoginController;
import controllers.ProfileController;
import enums.MenuCommands;

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
            else if (MenuCommands.isMatch(command, MenuCommands.ENTER_MENU) != null)
                System.out.println("please login first");
            else if ((matcher = MenuCommands.isMatch(command, MenuCommands.PROFILE_CHANGE)) != null)
                changeProfile(matcher);
            else
                System.out.println("invalid command");
        }
    }

    private void changeProfile(Matcher matcher) {
        String nickname = matcher.group("nickname");
        if (LoginController.getInstance().isExistNickname(nickname) != null)
            System.out.println("user with nickname " + nickname + " already exists");
//        ProfileController.getInstance().changeProfile(matcher);

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
