package views;

import controllers.ProfileController;
import enums.ProfileMenuCommands;


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
                break;
            else if (command.equals("menu show-current"))
                System.out.println("Profile menu");
            else if (ProfileMenuCommands.isMatch(command, ProfileMenuCommands.ENTER_MENU) != null)
                System.out.println("menu navigation is not possible");
            else if ((matcher = ProfileMenuCommands.isMatch(command, ProfileMenuCommands.NICKNAME_CHANGE)) != null)
                System.out.println(ProfileController.getInstance().changeNickname(matcher));
            else if ((matcher = ProfileMenuCommands.isMatchChangePassword(command)) != null)
                System.out.println(ProfileController.getInstance().changePassword(matcher));
            else
                System.out.println("invalid command");
        }
        MainMenu.getInstance().run(scanner);
    }


}
