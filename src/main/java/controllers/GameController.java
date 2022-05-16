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

    public String cheatGold(int amount) {
        game.getCurrentCivilization().setGold(amount + game.getCurrentCivilization().getGold());
        return "successful";
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
        if (game.getCurrentCivilization().getCurrentTechnology() != null)
            return "no need buy";
        if (technology == null)
            return "technologyName is invalid";
        if (!game.getCurrentCivilization().isExistTechnology(technologyName))
            return "don not have access to this technology";
        if (game.getCurrentCivilization().getScience() < technology.getCost())
            return "science is ont enough";
        game.getCurrentCivilization().setCurrentTechnology(technology);
        game.getCurrentCivilization().addTechnology(technology);
        game.getCurrentCivilization().decreaseScience(technology.getCost());
        game.getCurrentCivilization().setRemainingTurns(20 / game.getCurrentCivilization().getHappiness());
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
        game.getCurrentCivilization().setRemainingTurns(20 / game.getCurrentCivilization().getHappiness());
        return "technology change successfully";
    }

    public String technologyMenu() {
        StringBuilder sb = new StringBuilder();
        Civilization currentCivilization = game.getCurrentCivilization();
        sb.append("studyTechnology: " + "\n");

        for (int i = 0; i < currentCivilization.getTechnologies().size(); i++) {
            sb.append(currentCivilization.getTechnologies().get(i).getName() + "\n");
        }

        sb.append("availableTechnology: " + "\n");
        for (int i = 0; i < currentCivilization.getAvailableTechnology().size(); i++) {
            sb.append(currentCivilization.getAvailableTechnology().get(i).getName() + "\n");
        }
        return sb.toString();
    }

    public void getResourceAndGoldTurn() {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                for (Tile cityTile : city.getCityTiles()) {
                    if (cityTile.isThereCitizen()) {
                        if (civilization.isExistTechnology(cityTile.getResource().getNeededTechnology().getName())) {
                            if (cityTile.getImprovement() != null && cityTile.getImprovement().getName()
                                    .equals(cityTile.getResource().getNeededImprovement().getName()) &&
                                    !cityTile.isNeedRepair()) {
                                if (cityTile.getResource().getType().equals("Luxury")) {
                                    if (!cityTile.getResource().getName().equals("Gold"))
                                        civilization.addLuxuryResource(cityTile.getResource());
                                    else {
                                        int amount = 0;
                                        amount += cityTile.getTerrain().getGold();
                                        amount += cityTile.getResource().getGold();
                                        if (cityTile.getImprovement() != null)
                                            amount += cityTile.getImprovement().getGoldChange();
                                        for (int i = 0; i < cityTile.getRivers().length; i++) {
                                            if (cityTile.getRivers()[i])
                                                amount++;
                                        }

                                        city.setGold(city.getGold() + amount);
                                    }
                                    if (!civilization.hasLuxuryResource(cityTile.getResource()))
                                        civilization.increaseHappiness(10);
                                } else
                                    city.addResource(cityTile.getResource());
                            }
                        }
                    }
                }
            }
        }
    }

    public void decreaseTechnologyTurn() {
        for (Civilization civilization : game.getCivilizations()) {
            if (civilization.getRemainingTurns() != -1) {
                if (civilization.getRemainingTurns() == 0) {
                    civilization.setRemainingTurns(-1);
                    civilization.addTechnology(civilization.getCurrentTechnology());
                    civilization.setCurrentTechnology(null);
                }
                else
                    civilization.setRemainingTurns(civilization.getRemainingTurns() - 1);
            }
        }
    }

    public String cheatHappiness(int amount) {
        game.getCurrentCivilization().increaseHappiness(amount);
        return "successful";
    }

    public String cheatScience(int amount) {
        game.getCurrentCivilization().increaseScience(amount);
        return "successful";
    }

    public String cheatTechnology(int amount) {
        game.getCurrentCivilization().setRemainingTurns(0);
        return "successful";
    }

    public String cheatAllTechnology(String technologyName) {
        Technology technology = null;
        for (TechnologyEnum technologyEnum : TechnologyEnum.values()) {
            if (technologyName.equals(technologyEnum.getName()))
                technology = new Technology(technologyEnum);
        }
        if (game.getCurrentCivilization().getCurrentTechnology() != null)
            return "no need buy";
        if (technology == null)
            return "technologyName is invalid";

        game.getCurrentCivilization().addTechnology(technology);
        return "successful";
    }

    public void food() {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                city.setAllFood(city.getAllFood() - city.getPopulation());
                if (city.getCivilian() != null && !city.getCivilian().isHasDone())
                    city.setAllFood(city.getAllFood() - 1);
            }
        }
    }

    public void makeFood() {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                for (Tile cityTile : city.getCityTiles()) {
                    if (cityTile.isThereCitizen()) {
                        int amount = cityTile.getFood();
                        if (cityTile.getImprovement() != null)
                            amount += cityTile.getImprovement().getFoodChange();
                        city.setAllFood(city.getAllFood() + amount);
                    }
                }
            }
        }
        for (int i = 0; i < game.getCivilizations().size(); i++) {
            for (int i1 = 0; i1 < game.getCivilizations().get(i).getCities().size(); i1++) {
                if (game.getCivilizations().get(i).getCities().get(i1).getAllFood() <= 0) {
                    game.getCivilizations().get(i).getCities().get(i1).setAllFood(0);
                    game.getCivilizations().get(i).decreaseHappiness(10);
                }
            }
        }
    }
}
