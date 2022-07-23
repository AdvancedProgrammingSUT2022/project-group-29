package controllers;

import models.City;
import models.MilitaryUnit;
import models.Unit;

import java.util.regex.Matcher;

public class GameMenuController {
    private static GameMenuController instance = null;

    private GameMenuController() {
    }

    public static GameMenuController getInstance() {
        if (instance == null)
            instance = new GameMenuController();
        return instance;
    }

    public String selectCombatUnit(int x, int y) {
        if (x < 0 ||
                x >= GameController.getInstance().getWIDTH())
            return "invalid x";
        if (y < 0 ||
                y >= GameController.getInstance().getLENGTH())
            return "invalid y";
        if (GameController.getInstance().getGame().getMap()[x][y].getMilitaryUnit() == null)
            return "there is no combat unit";
        for (MilitaryUnit militaryUnit : GameController.getInstance().getGame().getCurrentCivilization().getMilitaryUnits()) {
            if (militaryUnit.getX() == x && militaryUnit.getY() == y) {
                GameController.getInstance().getGame().setSelectedCombatUnit(militaryUnit);
                GameController.getInstance().getGame().setSelectedNonCombatUnit(null);
                return "combat unit selected";
            }
        }
        return "combat unit doesn't belong to you";
    }

    public String selectNonCombatUnit(int x, int y) {
        if (x < 0 ||
                x >= GameController.getInstance().getWIDTH())
            return "invalid x";
        if (y < 0 ||
                y >= GameController.getInstance().getLENGTH())
            return "invalid y";
        if (GameController.getInstance().getGame().getMap()[x][y].getCivilian() == null)
            return "there is no unit";
        for (Unit unit : GameController.getInstance().getGame().getCurrentCivilization().getUnits()) {
            if (unit.getX() == x && unit.getY() == y) {
                GameController.getInstance().getGame().setSelectedNonCombatUnit(unit);
                GameController.getInstance().getGame().setSelectedCombatUnit(null);
                return "non combat unit selected";
            }
        }
        return "non combat unit doesn't belong to you";
    }

    public String selectCityByName(Matcher matcher) {
        String name = matcher.group("name");
        for (City city : GameController.getInstance().getGame().getCurrentCivilization().getCities()) {
            if (city.getName().equals(name)) {
                GameController.getInstance().getGame().setSelectedCity(city);
            }
        }
        return "city doesn't exist";
    }

    public String selectCityByPosition(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        for (City city : GameController.getInstance().getGame().getCurrentCivilization().getCities()) {
            if (city.getX() == x && city.getY() == y) {
                GameController.getInstance().getGame().setSelectedCity(city);
            }
        }
        return "city doesn't exist";
    }
}
