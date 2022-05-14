package controllers;

import enums.modelsEnum.MilitaryUnitsEnum;
import enums.modelsEnum.nonCombatUnitsEnum;
import models.*;

import java.util.regex.Matcher;


public class CityController {
    private static CityController instance = null;

    public static CityController getInstance() {
        if (instance == null)
            instance = new CityController();
        return instance;
    }

    private Game game = GameController.getInstance().getGame();
    private StringBuilder sb = new StringBuilder();

    private static boolean isCityNameExist(Game game, String cityName) {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                if (city.getName().equals(cityName))
                    return true;
            }
        }
        return false;
    }

    private static boolean isTileEmpty(Game game, int x, int y) {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                for (Tile tile : city.getCityTiles()) {
                    if (tile.getX() == x && tile.getY() == y)
                        return false;
                }
            }
        }
        return true;
    }

    public String lockingCitizenToTile(int x, int y) {
        City city;
        if ((city = game.getSelectedCity()) == null)
            return "select city";
        if (isXTileValid(x))
            return "x is out of map";
        if (isYTileValid(y))
            return "y is out of map";
        else if (ownerTile(x, y) == null || game.getCurrentCivilization().getName().equals(ownerTile(x, y).getName()))
            return "This tile is not for this civilization";
        else if (hasTileCitizen(x, y))
            return "This tile has citizen";
        else {
            Tile tile = game.getMap()[x][y];
            tile.setThereCitizen(true);
            city.decreaseCitizen(1);
            updateCity();
            return "Citizen locked to tile";
        }

    }

    //TODO COMPLETE UPDATE
    private void updateCity() {
    }

    private boolean hasTileCitizen(int x, int y) {
        Tile tile = game.getMap()[x][y];
        return tile.isThereCitizen();
    }

    private Civilization ownerTile(int x, int y) {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                for (Tile tile : city.getCityTiles()) {
                    if (tile.getX() == x && tile.getY() == y)
                        return civilization;
                }
            }
        }
        return null;
    }

    public String removingCitizenFromWork(int x, int y) {
        game = GameController.getInstance().getGame();
        City city;
        if ((city = game.getSelectedCity()) == null)
            return "select city";
        if (isXTileValid(x))
            return "x is out of map";
        if (isYTileValid(y))
            return "y is out of map";
        else if (ownerTile(x, y) == null || game.getCurrentCivilization().getName().equals(ownerTile(x, y).getName()))
            return "This tile is not for this civilization";
        else if (!hasTileCitizen(x, y))
            return "This tile has not citizen";
        else {
            Tile tile = game.getMap()[x][y];
            tile.setThereCitizen(false);
            city.increaseCitizen(1);
            updateCity();
            return "Citizen removed from work";
        }

    }

    public String unemployedCitizenSection() {
        game = GameController.getInstance().getGame();
        if (GameController.getInstance().getGame().getSelectedCity() != null)
            return "unemployedCitizenSection: " + game.getSelectedCity().getCitizen();
        return "first select a city!";
    }

    public String cityBuyTile(int x, int y) {
        City city;
        Tile tile;
        if ((city = game.getSelectedCity()) == null)
            return "select city";
        if (isXTileValid(x))
            return "x is out of map";
        if (isYTileValid(y))
            return "y is out of map";
        else if (ownerTile(x, y) != null)
            return "This tile has owner";
        else if ((tile = game.getMap()[x][y]).getValue() > city.getGold())
            return "gold is not enough";
        else {
            city.addTileToCity(tile);
            city.setGold(city.getGold() - tile.getValue());
            return "Successful buy";
        }
    }

    private static String construction(String name) {
        return "";
    }

    private static String changeConstruction(String name) {
        return "";
    }

    private static String destroyingCities(String name) {
        return "";
    }

    private static String appendCity(String name) {
        return "";
    }

    private static String warCityAndUnit(String name) {
        return "";
    }

    private static String hpWarCityAndUnit(String name) {
        return "";
    }

    private static String defenceCity(String name) {
        return "";
    }

    public String createUnit(String unitName) {
        City selectedCity;
        MilitaryUnitsEnum militaryUnitEnum;
        nonCombatUnitsEnum nonCombatUnitsEnum;
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) == null)
            return "no selected city";
        else if ((militaryUnitEnum = UnitController.getInstance().isExistMilitaryUnits(unitName)) != null) {
            if (selectedCity.getGold() < militaryUnitEnum.getCost())
                return "gold is not enough";
            else if (selectedCity.getMilitaryUnit() != null)
                return "a military unit exist in city";

            MilitaryUnit militaryUnit = new MilitaryUnit(militaryUnitEnum, selectedCity.getX(), selectedCity.getY());
            for (Technology technology : GameController.getInstance().getGame().getCurrentCivilization().getTechnologies()) {
                if (!technology.getName().equals(militaryUnit.getNeededTechnology().getName()))
                    return "do not have needed technology";
            }
            if (!doesCityHaveNeededResources(selectedCity, militaryUnit))
                return "do not have needed resources";
            addMilitaryUnitToCity(militaryUnit, selectedCity, militaryUnitEnum.getCost());
            return "unit created successfully in city";
        } else if ((nonCombatUnitsEnum = UnitController.getInstance().isExistNonCombatUnits(unitName)) != null) {
            if (selectedCity.getGold() < nonCombatUnitsEnum.getCost())
                return "gold is not enough";
            if (selectedCity.getCivilian() != null)
                return "a civilian unit exist in city";

            Unit civilianUnit = new Unit(nonCombatUnitsEnum, selectedCity.getX(), selectedCity.getY());
            addCivilianToCity(civilianUnit, selectedCity, nonCombatUnitsEnum.getCost());

            return "unit created successfully in city";
        } else
            return "unit name is invalid";
    }

    private void addCivilianToCity(Unit civilianUnit, City selectedCity, int gold) {
        selectedCity.setCivilian(civilianUnit);
        selectedCity.setGold(selectedCity.getGold() - gold);
        GameController.getInstance().getGame().getCurrentCivilization().addCivilianToCity(civilianUnit);
    }

    private void addMilitaryUnitToCity(MilitaryUnit militaryUnit, City selectedCity, int gold) {
        selectedCity.setMilitaryUnit(militaryUnit);
        selectedCity.setGold(selectedCity.getGold() - gold);
        GameController.getInstance().getGame().getCurrentCivilization().addMilitaryUnit(militaryUnit);
    }

    private boolean doesCityHaveNeededResources(City selectedCity, MilitaryUnit militaryUnit) {
        for (Tile tile : selectedCity.getCityTiles()) {
            if (tile.getResource().getName().equals(militaryUnit.getNeededResource().getName()))
                return true;
        }
        return false;
    }

    public String cityShowTilePosition() {
        City selectedCity;
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) != null) {
            sb = new StringBuilder();
            for (Tile tile : selectedCity.getCityTiles()) {
                sb.append("x :" + tile.getX() + "y:" + tile.getY() + "\n");
            }
            return sb.toString();
        }
        return "first select a city!";
    }

    public String cityShowCivilization() {
        City selectedCity;
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) != null) {
            return selectedCity.getCivilization().getName();
        }
        return "first select a city!";
    }

    public String cityShowResources() {
        City selectedCity;
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) != null) {
            sb = new StringBuilder();
            for (Tile tile : selectedCity.getCityTiles()) {
                sb.append(tile.getResource() + "\n");
            }
            return sb.toString();
        } else
            return "first select a city!";
    }

    public String cityShowStrategicResources() {
        City selectedCity;
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) != null) {
            sb = new StringBuilder();
            for (Tile tile : selectedCity.getCityTiles()) {
                if (tile.getResource().getType().equals("Strategic"))
                    sb.append(tile.getResource() + "\n");
            }
            return sb.toString();
        } else
            return "first select a city!";
    }


    public String cityShowUnit() {
        City selectedCity;
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) != null) {
            return "militaryUnit: " + selectedCity.getMilitaryUnit() + "civilianUnit: " + selectedCity.getCivilian();
        }
        return "first select a city!";
    }

    public String cityShowInformation() {
        City selectedCity;
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) != null) {
            return "name: " + selectedCity.getName() + "\n" +
                    "happiness: " + selectedCity.getHappiness() + "\n" +
                    "food: " + selectedCity.getFood() + "\n" +
                    "production: " + selectedCity.getProduction() + "\n" +
                    "combatStrength: " + selectedCity.getCombatStrength() + "\n" +
                    "population: " + selectedCity.getPopulation() + "\n" +
                    "hitPoint: " + selectedCity.getHitPoint();
        }
        return "first select a city!";
    }

    //Todo complete attack
    public String cityAttack(int x, int y) {
        City selectedCity = GameController.getInstance().getGame().getSelectedCity();
        if (selectedCity == null)
            return "no selected city";
        Game game = GameController.getInstance().getGame();
        if (game.getCurrentCivilization() != selectedCity.getCivilization())
            return "choose city in your civilization";
        if (isXTileValid(x))
            return "x is out of map";
        if (isYTileValid(y))
            return "y is out of map";
        if (selectedCity.getMilitaryUnit() == null)
            return "no combat unit in city";
        if (GameController.getInstance().getGame().getMap()[x][y].getMilitaryUnit() == null)
            return "no combat unit there";
        if (!isInRange(x, y, selectedCity))
            return "position is not in city range";
        if (selectedCity.getMilitaryUnit().getState().equals("sleep"))
            return "combat unit is sleeping";
        if (selectedCity.getMilitaryUnit().isHasDone())
            return "city has attacked in this turn";
        cityAttack(x, y, selectedCity);
        return "attack successful";
    }

    private void cityAttack(int x, int y, City selectedCity) {
        selectedCity.getMilitaryUnit().setHasDone(true);
        MilitaryUnit militaryUnit = game.getMap()[x][y].getMilitaryUnit();
        if (1 == (selectedCity.getY() - y) * (selectedCity.getY() - y)
                + (selectedCity.getX() - x) * (selectedCity.getX() - x)) {
            if (militaryUnit.getHp() < selectedCity.getMilitaryUnit().getCombatStrength())
                destroyCombatUnit(x, y);
            else
                militaryUnit.setHp(militaryUnit.getHp() - selectedCity.getMilitaryUnit().getCombatStrength());
        } else {
            if (militaryUnit.getHp() < selectedCity.getMilitaryUnit().getRangedCombatStrength())
                destroyCombatUnit(x, y);
            else
                militaryUnit.setHp(militaryUnit.getHp() - selectedCity.getMilitaryUnit().getRangedCombatStrength());
        }
    }

    private void destroyCombatUnit(int x, int y) {
        GameController.getInstance().getCivilization(x, y).deleteMilitaryUnit(x, y);
    }

    private boolean isInRange(int x, int y, City selectedCity) {
        if (selectedCity.getMilitaryUnit().getRange() * selectedCity.getMilitaryUnit().getRange() <
                (selectedCity.getY() - y) * (selectedCity.getY() - y)
                        + (selectedCity.getX() - x) * (selectedCity.getX() - x))
            return false;
        return true;
    }

    public String createCity(Matcher matcher) {
        if (game.getSelectedNonCombatUnit() == null ||
                !game.getSelectedNonCombatUnit().getName().equals("settler"))
            return "no selected settler unit";

        String name = matcher.group("name");
        int x = GameController.getInstance().getGame().getSelectedNonCombatUnit().getX();
        int y = GameController.getInstance().getGame().getSelectedNonCombatUnit().getY();

        for (Civilization civilizations : GameController.getInstance().getGame().getCivilizations()) {
            for (City civilizationsCity : civilizations.getCities()) {
                for (Tile civilizationsCityCityTile : civilizationsCity.getCityTiles()) {
                    if (civilizationsCityCityTile.getX() == x && civilizationsCityCityTile.getY() == y)
                        return "there is already a city";
                }
            }
        }


        City city = new City(name, GameController.getInstance().getGame().getCurrentCivilization(), x, y);
        GameController.getInstance().getGame().getCurrentCivilization().addCity(city);
        destroySettler();
        if (GameController.getInstance().getGame().getCurrentCivilization().getCapital() == null)
            GameController.getInstance().getGame().getCurrentCivilization().setCapital(city);
        GameController.getInstance().getGame().getCurrentCivilization().decreaseHappiness(1);
        return "city created successfully";
    }

    private void destroySettler() {
        int x = GameController.getInstance().getGame().getSelectedNonCombatUnit().getX();
        int y = GameController.getInstance().getGame().getSelectedNonCombatUnit().getY();
        for (int i = 0; i < GameController.getInstance().getGame().getCurrentCivilization().getUnits().size(); i++) {
            if (GameController.getInstance().getGame().getCurrentCivilization().getUnits().get(i).getY() == y
                    && GameController.getInstance().getGame().getCurrentCivilization().getUnits().get(i).getX() == x) {
                GameController.getInstance().getGame().getCurrentCivilization().getUnits().remove(i);
                GameController.getInstance().getGame().getMap()[x][y].setCivilian(null);
                GameController.getInstance().getGame().setSelectedNonCombatUnit(null);
                return;
            }

        }
    }

    public static boolean isXTileValid(int x) {
        return x <= 45 && x >= 0;
    }

    public static boolean isYTileValid(int y) {
        return y <= 30 && y >= 0;
    }

    //TODO SHOULD COMPLETE relative to turn
    public String cityScreenMenu() {
        City selectedCity;
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) != null) {
            return "name: " + selectedCity.getName() + "\n" +
                    "science: " + selectedCity.getScience() + "\n" +
                    "gold: " + selectedCity.getGold() + "\n" +
                    "happiness: " + selectedCity.getHappiness() + "\n" +
                    "strategicResources: " + cityShowStrategicResources();
        }
        return "first select a city!";
    }


    public String cityResourcesOutput() {
        City selectedCity;
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) != null) {
            return "name: " + selectedCity.getName() + "\n" +
                    "happiness: " + selectedCity.getHappiness() + "\n" +
                    "food: " + selectedCity.getFood() + "\n" +
                    "production: " + selectedCity.getProduction() + "\n" +
                    "science: " + selectedCity.getScience();
        }
        return "first select a city!";

    }

    //TODO add buy unit to queue
    /*
    public String buyUnit(String unitName) {
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) == null)
            return "no selected city";
        else if ((militaryUnitEnum = UnitController.getInstance().isExistMilitaryUnits(unitName)) != null) {
            if ((gold = selectedCity.getGold()) < militaryUnitEnum.getCost() / 5)
                return "gold is not enough";
            else if (selectedCity.getMilitaryUnit() != null)
                return "a military unit exist in city";
            civilization = selectedCity.getCivilization();
            militaryUnit = new MilitaryUnit(militaryUnitEnum, 0, 0);
            for (Technology technology : civilization.getTechnologies()) {
                if (!technology.getName().equals(militaryUnit.getNeededTechnology().getName()))
                    return "do not have needed technology";
            }
            if (!doesCityHaveNeededResources(selectedCity, militaryUnit))
                return "do not have needed resources";
            else
                addMilitaryUnitToCity();
            return "unit created successfully in city";
        } else if ((nonCombatUnitsEnum = UnitController.getInstance().isExistNonCombatUnits(unitName)) != null) {
            if ((gold = selectedCity.getGold()) < nonCombatUnitsEnum.getCost() / 5)
                return "gold is not enough";
            else if (selectedCity.getCivilian() != null)
                return "a civilian unit exist in city";
            else
                addCivilianToCity();
            return "unit created successfully in city";
        } else
            return "unit name is invalid";
    }
*/
}
