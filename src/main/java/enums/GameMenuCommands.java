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
    MOVE_MAP("map move (?<direction>right|left|up|down) (?<c>\\d+)"),
    INCREASE_TURN("increase -turn (?<amount>\\d+)"),
    INCREASE_GOLD("increase -gold (?<amount>\\d+)"),
    CITY_CREATE_UNIT("city create unit (?<unitName>[A-Za-z]+)"),
    CITY_SHOW_RESOURCES("city show resources"),
    CITY_SHOW_UNIT("city show unit"),
    CITY_SHOW_TILE("city show tile"),
    CITY_SHOW_CIVILIZATION("city show civilization"),
    CITY_SHOW_INFORMATION("city show information"),
    CITY_ATTACK("city Attack (?<xPoint>\\d+) (?<yPoint>\\d+)"),
    CITY_CREATE("city create (?<cityName>[A-Za-z]+) (?<xPoint>\\d+) (?<yPoint>\\d+)"),
    LOCK_CITIZEN_TO_TILE("city lock citizen to tile (?<xPoint>\\d+) (?<yPoint>\\d+)"),
    REMOVE_CITIZEN_FROM_TILE("city remove citizen from tile (?<xPoint>\\d+) (?<yPoint>\\d+)"),
    CITY_RESOURCES_OUTPUT("city resources output"),
    CITY_SCREEN_MENU("city screen menu"),
    CITY_UNEMPLOYED_CITIZEN_SECTION("city Unemployed Citizen Section"),
    CITY_BUY_TILE("city buy tile (?<xPoint>\\d+) (?<yPoint>\\d+)");


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
