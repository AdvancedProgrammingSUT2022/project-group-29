package views;

import enums.MenuCommands;

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
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;
            if (command.equals("menu exit"))
                System.exit(0);
            else if (command.equals("menu show-current"))
                System.out.println("Login menu");
            else if (MenuCommands.isMatch(command, MenuCommands.ENTER_MENU) != null)
                System.out.println("please login first");
            else
                System.out.println("invalid command");
        }
    }
}
