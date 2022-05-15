package controllers;

import enums.modelsEnum.TechnologyEnum;
import models.*;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameController {
    private static GameController instance = null;
    private final int LENGTH = 45;
    private final int WIDTH = 30;
    private Game game;

    private GameController() {
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    public void startGame(ArrayList<User> users) {
        Tile[][] map = new Tile[WIDTH][LENGTH];
        game = new Game(null, map);
        MapController.getInstance().createMap(map, WIDTH, LENGTH);
        ArrayList<Civilization> civilizations = new ArrayList<>();
        createCivilizations(civilizations, users);
        game.setCivilizations(civilizations);
    }

    private void createCivilizations(ArrayList<Civilization> civilizations, ArrayList<User> users) {
        int x = 5, y = 5;
        for (User user : users) {
            Civilization civilization = new Civilization(user, x, y);
            civilizations.add(civilization);
            user.setCivilization(civilization);
            x += 5;
            y += 5;
        }
    }

    public String cheatTurn(int turn) {
        for (int i = 0; i < turn; i++)
            this.game.nextTurn();
        return "successful";
    }

    public String cheatGold(int turn) {
        return null;
    }

    public Game getGame() {
        return game;
    }

    public String combat(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        City city;
        MilitaryUnit enemyMilitaryUnit;
        if (game.getSelectedCombatUnit() == null)
            return "no selected combat unit";
        if (!game.getSelectedCombatUnit().getState().equals("ready"))
            return "combat unit is not ready";
        if (!CityController.getInstance().isXTileValid(x))
            return "x position is not valid";
        if (!CityController.getInstance().isYTileValid(y))
            return "y position is not valid";
        if ((city = CityController.getInstance().getCity(x, y)) != null) {
            game.getSelectedCombatUnit().setHasDone(true);
            return CityController.getInstance().combat(city, game.getSelectedCombatUnit(), x, y);
        }
        if ((enemyMilitaryUnit = game.getMap()[x][y].getMilitaryUnit()) != null) {
            game.getSelectedCombatUnit().setHasDone(true);
            return UnitController.getInstance().combat(enemyMilitaryUnit, game.getSelectedCombatUnit());
        }
        return "combat is not possible";
    }

    public int getLENGTH() {
        return LENGTH;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public Civilization getCivilization(int x, int y) {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                for (Tile cityTile : city.getCityTiles()) {
                    if (x == cityTile.getX() && y == cityTile.getY())
                        return civilization;
                }
            }
        }
        return null;
    }

    public String foundCity(Matcher matcher) {
        return CityController.getInstance().createCity(matcher);
    }

    // TODO complete
    public String cancelMission() {
        if (game.getSelectedCombatUnit() != null) {
            if (!game.getSelectedCombatUnit().isHasDone())
                return "unit has no works";

        }
        return null;
    }


    public String technologyStudy(String technologyName) {
        Technology technology = null;
        for (TechnologyEnum technologyEnum : TechnologyEnum.values()) {
            if (technologyName.equals(technologyEnum.getName()))
                technology = new Technology(technologyEnum);
        }
        if (technology == null)
            return "technologyName is invalid";
        if (!game.getCurrentCivilization().isExistTechnology(technologyName))
            return "don not have access to this technology";
        game.getCurrentCivilization().setCurrentTechnology(technology);
        game.getCurrentCivilization().addTechnology(technology);
        game.getCurrentCivilization().decreaseScience(technology.getCost());
        return "technology buy successfully";
    }

    public String technologyChange(String technologyName) {
        Technology technology = null;
        for (TechnologyEnum technologyEnum : TechnologyEnum.values()) {
            if (technologyName.equals(technologyEnum.getName()))
                technology = new Technology(technologyEnum);
        }
        if (technology == null)
            return "technologyName is invalid";
        if (!game.getCurrentCivilization().isExistTechnology(technologyName))
            return "don not have access to this technology";
        if (game.getCurrentCivilization().getCurrentTechnology() == null)
            return "no need to change";
        game.getCurrentCivilization().setCurrentTechnology(technology);
        game.getCurrentCivilization().addTechnology(technology);
        game.getCurrentCivilization().decreaseScience(technology.getCost());
        return "technology change successfully";
    }

    public String technologyMenu() {
        StringBuilder sb = new StringBuilder();
        Civilization currentCivilization = game.getCurrentCivilization();
        sb.append("studyTechnology: " + "\n");

        for (int i = 0; i < currentCivilization.getTechnologies().size(); i++){
            sb.append(currentCivilization.getTechnologies().get(i).getName() + "\n");
        }

        sb.append("availableTechnology: " + "\n");
        for (int i = 0; i < currentCivilization.getAvailableTechnology().size(); i++){
            sb.append(currentCivilization.getAvailableTechnology().get(i).getName() + "\n");
        }
        return sb.toString();
    }
}
