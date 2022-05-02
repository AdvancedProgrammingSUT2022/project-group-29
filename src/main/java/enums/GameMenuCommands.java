package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    SELECT_COMBAT("select unit combat (?<x>\\d+) (?<y>\\d+)"),
    SELECT_NON_COMBAT("select unit noncombat (?<x>\\d{1,2}) (?<y>\\d{1,2})"),
    SELECT_CITY1("select city (?<name>\\S+)"),
    SELECT_CITY2("select city (?<x>\\d{1,2}) (?<y>\\d{1,2})"),
    UNIT_MOVE("unit moveto (?<x>\\d{1,2}) (?<y>\\d{1,2})"),
    ATTACK("unit attack (?<x>\\d{1,2}) (?<y>\\d{1,2})"),
    SHOW_MAP1("map show (?<x>\\d{1,2}) (?<y>\\d{1,2})"),
    SHOW_MAP2("map show (?<name>)\\S+"),
    MOVE_MAP("map move (?<direction>right|left|up|down) (?<c>\\d+)");

    private String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, GameMenuCommands regex) {
        Matcher matcher = Pattern.compile(regex.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }

}
