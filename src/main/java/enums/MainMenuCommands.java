package enums;

import com.sun.tools.javac.Main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    ENTER_MENU("menu enter (?<menuName>(Login|Play Game|Profile) Menu)"),
    PLAY_GAME("play game .*"),
    PLAYER("(p|-player)[0-9]+ (?<username>\\S+)");

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

    public static int countMatches(String input) {
        Matcher matcher = Pattern.compile(PLAYER.regex).matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }


}
