package controllers;

import enums.modelsEnum.MilitaryUnitsEnum;
import enums.modelsEnum.nonCombatUnitsEnum;
import models.*;

import java.util.ArrayList;

public class CityController {
    private static CityController instance = null;

    public static CityController getInstance() {
        if (instance == null)
            instance = new CityController();
        return instance;
    }
    Game game;
    City selectedCity;
    Civilization civilization;
    int gold;
    MilitaryUnitsEnum militaryUnitEnum;
    MilitaryUnit militaryUnit;
    nonCombatUnitsEnum nonCombatUnitsEnum;
    Unit civilianUnit;
    StringBuilder sb = new StringBuilder();

    public String createCity(String cityName, int x, int y) {
        game = GameController.getInstance().getGame();
        if (isXTileValid(x))
            return "x is out of map";
        if (isYTileValid(y))
            return "y is out of map";
        else if (game.getSelectedNonCombatUnit() == null ||
                !game.getSelectedNonCombatUnit().getName().equals("settler"))
            return "nonSettler can not create City";
        else if (!isTileEmpty(game, x, y))
            return "There is a city in this Tile";
        else {
            ArrayList<Tile>tileNewCity = new ArrayList<Tile>();
            tileNewCity.add(new Tile(x, y));
            City city = new City(cityName, game.getCurrentCivilization(),tileNewCity);
            game.getCurrentCivilization().addCityToCivilization(city);
            game.setSelectedNonCombatUnit(null);
            return "City created successfully";
        }
    }

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
        game = GameController.getInstance().getGame();
        City city ;
        if((city = game.getSelectedCity())==null)
            return "select city";
        if (isXTileValid(x))
            return "x is out of map";
        if (isYTileValid(y))
            return "y is out of map";
        else if(ownerTile(x,y) == null || game.getCurrentCivilization().getName().equals(ownerTile(x,y).getName()))
            return "This tile is not for this civilization";
        else if(hasTileCitizen(x,y))
            return "This tile has citizen";
        else{
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
        City city ;
        if((city = game.getSelectedCity())==null)
            return "select city";
        if (isXTileValid(x))
            return "x is out of map";
        if (isYTileValid(y))
            return "y is out of map";
        else if(ownerTile(x,y) == null || game.getCurrentCivilization().getName().equals(ownerTile(x,y).getName()))
            return "This tile is not for this civilization";
        else if(!hasTileCitizen(x,y))
            return "This tile has not citizen";
        else{
            Tile tile = game.getMap()[x][y];
            tile.setThereCitizen(false);
            city.increaseCitizen(1);
            updateCity();
            return "Citizen removed from work";
        }

    }

    private static String unemployedCitizenSection(int x, int y) {
        return "";
    }

    private static String buyingTile(String name) {
        return "";
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

        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) == null)
            return "no selected city";
        else if ((militaryUnitEnum = UnitController.getInstance().isExistMilitaryUnits(unitName)) != null) {
            if ((gold = selectedCity.getGold()) < militaryUnitEnum.getCost())
                return "gold is not enough";
            else if (selectedCity.getMilitaryUnit() != null)
                return "a military unit exit in city";
            else if (!(civilization = selectedCity.getCivilization()).getTechnologies().contains
                    ((militaryUnit = new MilitaryUnit(militaryUnitEnum)).getNeededTechnology()))
                return "do not have needed technology";
            else if (!doesCityHaveNeededResources(selectedCity, militaryUnit))
                return "do not have needed resources";
            else
                addMilitaryUnitToCity();
            return "unit created successfully in city";
        } else if ((nonCombatUnitsEnum = UnitController.getInstance().isExistNonCombatUnits(unitName)) != null) {
            if ((gold = selectedCity.getGold()) < nonCombatUnitsEnum.getCost())
                return "gold is not enough";
            else if (selectedCity.getCivilian() != null)
                return "a civilian unit exit in city";
            else
                addCivilianToCity();
            return "unit created successfully in city";
        } else
            return "unit name is invalid";
    }

    private void addCivilianToCity() {
        for (Tile tile : selectedCity.getCityTiles()) {
            tile.setCivilian(civilianUnit = new Unit(nonCombatUnitsEnum));
        }
        selectedCity.setGold(selectedCity.getGold() - gold);
        civilization.addCivilianToCity(civilianUnit);
    }

    private void addMilitaryUnitToCity() {
        for (Tile tile : selectedCity.getCityTiles()) {
            tile.setMilitaryUnit(militaryUnit);
        }
        selectedCity.setGold(selectedCity.getGold() - gold);
        civilization.addMilitaryUnit(militaryUnit);
    }

    private boolean doesCityHaveNeededResources(City selectedCity, MilitaryUnit militaryUnit) {
        for (Tile tile : selectedCity.getCityTiles()) {
            if (tile.getResource().getName().equals(militaryUnit.getNeededResource().getName()))
                return true;
        }
        return false;
    }

    public String cityShowTilePosition() {
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
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) != null) {
            return selectedCity.getCivilization().getName();
        }
        return "first select a city!";
    }

    public String cityShowResources() {
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) != null) {
            sb = new StringBuilder();
            for (Tile tile : selectedCity.getCityTiles()) {
                sb.append(tile.getResource() + "\n");
            }
            return sb.toString();
        } else
            return "first select a city!";
    }


    public String cityShowUnit() {
        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) != null) {
            return "militaryUnit: " + selectedCity.getMilitaryUnit() + "civilianUnit: " + selectedCity.getCivilian();
        }
        return "first select a city!";
    }

    public String cityShowInformation() {
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
        Game game = GameController.getInstance().getGame();
        if (game.getCurrentCivilization() != selectedCity.getCivilization()) {
            return "choose city in your civilization";
        } else if (isXTileValid(x)) {
            return "x is out of map";
        } else if (isYTileValid(y)) {
            return "y is out of map";
        } else
            return "attack successful";
    }

    public static boolean isXTileValid(int x) {
        return x <= 45 && x >= 0;
    }

    public static boolean isYTileValid(int y) {
        return y <= 30 && y >= 0;
    }
}
