package enums;

import com.sun.tools.javac.Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    ENTER_MENU("menu enter (?<menuName>(Login|Play Game|Profile) Menu)");
    private String regex;
    MainMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher isMatch(String input, MainMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

}
