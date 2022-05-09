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

    City selectedCity;
    Civilization civilization;
    int gold;
    MilitaryUnitsEnum militaryUnitEnum;
    MilitaryUnit militaryUnit;
    nonCombatUnitsEnum nonCombatUnitsEnum;
    Unit civilianUnit;
    StringBuilder sb = new StringBuilder();

    public String createUnit(String unitName) {

        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) == null)
            return "no selected city";
        else if ((militaryUnitEnum = UnitController.getInstance().isExistMilitaryUnits(unitName)) != null) {
            if ((gold = selectedCity.getGold()) < militaryUnitEnum.getCost())
                return "gold is not enough";
            else if (selectedCity.getMilitaryUnit() != null)
                return "a military unit exit in city";
            civilization = selectedCity.getCivilization();
            militaryUnit = new MilitaryUnit(militaryUnitEnum);
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
        } else if (x > 45 || x < 0) {
            return "x is out of map";
        } else if (y > 30 || y < 0) {
            return "y is out of map";
        } else
            return "attack successful";
    }

    public String createCity(Matcher matcher) {
        if (GameController.getInstance().getGame().getSelectedNonCombatUnit() == null)
            return "no selected unit";

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


        City city = new City(name, GameController.getInstance().getGame().getCurrentCivilization(), false, x, y);
        GameController.getInstance().getGame().getCurrentCivilization().addCity(city);
        return "city created successfully";
    }
}
