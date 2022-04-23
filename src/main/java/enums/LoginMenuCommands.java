package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    LOGIN_USER1("user login -(u|-username) (?<username>\\S+) -(p|-password) (?<password>\\S+)"),
    LOGIN_USER2("user login -(p|-password) (?<password>\\S+) -(u|-username) (?<username>\\S+)"),

    CREATE_USER1("user create -(u|-username) (?<username>\\S+) -(p|-password) (?<password>\\S+) -(n|-nickname) (?<nickname>\\S+)"),
    CREATE_USER2("user create -(u|-username) (?<username>\\S+) -(n|-nickname) (?<nickname>\\S+) -(p|-password) (?<password>\\S+)"),
    CREATE_USER3("user create -(p|-password) (?<password>\\S+) -(u|-username) (?<username>\\S+) -(n|-nickname) (?<nickname>\\S+)"),
    CREATE_USER4("user create -(p|-password) (?<password>\\S+) -(n|-nickname) (?<nickname>\\S+) -(u|-username) (?<username>\\S+)"),
    CREATE_USER5("user create -(n|-nickname) (?<nickname>\\S+) -(u|-username) (?<username>\\S+) -(p|-password) (?<password>\\S+)"),
    CREATE_USER6("user create -(n|-nickname) (?<nickname>\\S+) -(p|-password) (?<password>\\S+) -(u|-username) (?<username>\\S+)"),

    ENTER_MENU("menu enter (?<menuName>(Main|Play Game|Profile) Menu)");

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

    public static Matcher isMatchLoginUser(String input) {
        Matcher matcher;
        if ((matcher = isMatch(input, LOGIN_USER1)) != null)
            return matcher;
        if ((matcher = isMatch(input, LOGIN_USER2)) != null)
            return matcher;
        return null;
    }

    public static Matcher isMatchCreateUser(String input) {
        Matcher matcher;
        if ((matcher = isMatch(input, CREATE_USER1)) != null)
            return matcher;
        if ((matcher = isMatch(input, CREATE_USER2)) != null)
            return matcher;
        if ((matcher = isMatch(input, CREATE_USER3)) != null)
            return matcher;
        if ((matcher = isMatch(input, CREATE_USER4)) != null)
            return matcher;
        if ((matcher = isMatch(input, CREATE_USER5)) != null)
            return matcher;
        if ((matcher = isMatch(input, CREATE_USER6)) != null)
            return matcher;
        return null;
    }
}
