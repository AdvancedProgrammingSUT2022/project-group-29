package view;

import controller.Controller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu {
    private static LoginMenu instance = null;

    private LoginMenu() {
    }

    public static LoginMenu getInstance() {
        if (instance == null)
            return new LoginMenu();
        return instance;
    }

    public void run(Scanner scanner) {
        String command = scanner.nextLine();
        Matcher matcher = getCommandMatcher(command);
        if (command.startsWith("user login") &&
                matcher != null)
            login(matcher.group("username"), matcher.group("password"), scanner);
        else if (command.startsWith("user create") &&
                matcher != null)
            createUser(matcher.group("username"), matcher.group("password"), matcher.group("nickname"));
        else if (command.equals("menu exit"))
            exit();
        else if (command.equals("menu show-current"))
            System.out.println("Login and Register menu");
        else
            System.out.println("invalid command");
    }

    private void exit() {
        System.exit(0);
    }

    private void createUser(String username, String password, String nickname) {
        Controller controller = new Controller();
        String message = controller.createUser(username, password, nickname);
    }

    private void login(String username, String password, Scanner scanner) {
        Controller controller = new Controller();
        controller.login(username, password);
        MainMenu.getInstance().run(scanner);
    }

    private Matcher getCommandMatcher(String command) {
        Pattern[] pattern = {Pattern.compile("user login --username (?<username>.+) --password (?<password>.+)"),
                Pattern.compile("user login --password (?<password>.+) --username (?<username>.+)"),
                Pattern.compile("user login --u (?<username>.+) --p (?<password>.+)"),
                Pattern.compile("user login --p (?<password>.+) --u (?<username>.+)"),
                Pattern.compile("user create --username (?<username>.+) --password (?<password>.+)" +
                        " --nickname (?<nickname>.+)"),
                Pattern.compile("user create --username (?<username>.+) --nickname (?<nickname>.+)" +
                        " --password (?<password>.+)"),
                Pattern.compile("user create --password (?<password>.+) --username (?<username>.+)" +
                        " --nickname (?<nickname>.+)"),
                Pattern.compile("user create --password (?<password>.+) --nickname (?<nickname>.+)" +
                        " --username (?<username>.+)"),
                Pattern.compile("user create --nickname (?<nickname>.+) --username (?<username>.+)" +
                        " --password (?<password>.+)"),
                Pattern.compile("user create --nickname (?<nickname>.+) --password (?<password>.+)" +
                        " --username (?<username>.+)"),
                Pattern.compile("user create --u (?<username>.+) --p (?<password>.+) --n (?<nickname>.+)"),
                Pattern.compile("user create --u (?<username>.+) --n (?<nickname>.+) --p (?<password>.+)"),
                Pattern.compile("user create --p (?<password>.+) --u (?<username>.+) --n (?<nickname>.+)"),
                Pattern.compile("user create --p (?<password>.+) --n (?<nickname>.+) --u (?<username>.+)"),
                Pattern.compile("user create --n (?<nickname>.+) --u (?<username>.+) --p (?<password>.+)"),
                Pattern.compile("user create --n (?<nickname>.+) --p (?<password>.+) --u (?<username>.+)")};
        for (Pattern value : pattern) {
            if (value.matcher(command).matches())
                return value.matcher(command);
        }
        return null;
    }

}
