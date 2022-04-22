package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    ENTER_MENU("menu enter ((?<menuName>Main|Play Game|Login) Menu)"),
    NICKNAME_CHANGE("profile change --(n|nickname) (?<nickname>\\S+)"),
    PASSWORD_CHANGE1("profile change --(p|password) --(c|current) (?<currentPassword>\\S+) --(n|new) (?<newPassword>\\S+)"),
    PASSWORD_CHANGE2("profile change --(p|password) --(n|new) (?<newPassword>\\S+) --(c|current) (?<currentPassword>\\S+)");

    private String regex;
    ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher isMatch(String input, ProfileMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

    public static Matcher isMatchChangePassword(String input) {
        Matcher matcher;
        if ((matcher = isMatch(input, PASSWORD_CHANGE1)) != null)
            return matcher;
        if ((matcher = isMatch(input, PASSWORD_CHANGE2)) != null)
            return matcher;
        return null;
    }

}
