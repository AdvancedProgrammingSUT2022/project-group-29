package controllers;

import enums.modelsEnum.MilitaryUnitsEnum;
import enums.modelsEnum.nonCombatUnitsEnum;
import models.*;

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
            if (tile.getResource().equals(militaryUnit.getNeededResource()))
                return true;
        }
        return false;
    }
}
