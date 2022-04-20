package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    LOGIN_USER("user login " +
            "(--username (?<username>.+) --password (?<password>.+)|" +
            "--password (?<password>.+) --username (?<username>.+)|" +
            "--u (?<username>.+) --p (?<password>.+)|" +
            "--p (?<password>.+) --u (?<username>.+))"),
    CREATE_USER("user create " +
            "(--username (?<username>.+) --password (?<password>.+) --nickname (?<nickname>.+)|" +
            "--username (?<username>.+) --nickname (?<nickname>.+) --password (?<password>.+)|" +
            "--password (?<password>.+) --username (?<username>.+) --nickname (?<nickname>.+)|" +
            "--password (?<password>.+) --nickname (?<nickname>.+) --username (?<username>.+)|" +
            "--nickname (?<nickname>.+) --username (?<username>.+) --password (?<password>.+)|" +
            "--nickname (?<nickname>.+) --password (?<password>.+) --username (?<username>.+)|" +
            "--u (?<username>.+) --p (?<password>.+) --n (?<nickname>.+)|" +
            "--u (?<username>.+) --n (?<nickname>.+) --p (?<password>.+)|" +
            "--p (?<password>.+) --u (?<username>.+) --n (?<nickname>.+)|" +
            "--p (?<password>.+) --n (?<nickname>.+) --u (?<username>.+)|" +
            "--n (?<nickname>.+) --u (?<username>.+) --p (?<password>.+)|" +
            "--n (?<nickname>.+) --p (?<password>.+) --u (?<username>.+))"),
    ENTER_MENU("menu enter (?<menu name>Main|Play Game|Profile)");
    private String regex;

    LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher isMatch(String input, LoginMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
