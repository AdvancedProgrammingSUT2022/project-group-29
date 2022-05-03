package controllers;

import enums.modelsEnum.MilitaryUnitsEnum;
import models.City;
import models.Civilization;
import models.MilitaryUnit;
import models.Tile;

public class CityController {
    private static CityController instance = null;

    public static CityController getInstance() {
        if (instance == null)
            instance = new CityController();
        return instance;
    }

    public String createUnit(String unitName) {
        City selectedCity;
        Civilization civilization;
        int gold;
        MilitaryUnitsEnum militaryUnitEnum;
        MilitaryUnit militaryUnit;

        if ((selectedCity = GameController.getInstance().getGame().getSelectedCity()) == null)
            return "no selected city";
        else if ((militaryUnitEnum = UnitController.getInstance().isExistMilitaryUnits(unitName)) == null)
            return "unit name is invalid";
        else if ((gold = selectedCity.getGold()) < militaryUnitEnum.getCost())
            return "gold is not enough";
        else if (selectedCity.getMilitaryUnit() != null)
            return "a military unit exit in city";
        else if (!(civilization = selectedCity.getCivilization()).getTechnologies().contains
                ((militaryUnit = new MilitaryUnit(militaryUnitEnum)).getNeededTechnology()))
            return "do not have needed technology";
        else if (!doesCityHaveNeededResources(selectedCity, militaryUnit))
            return "do not have needed resources";
        else
            return "unit created successfully in city";
    }

    private boolean doesCityHaveNeededResources(City selectedCity, MilitaryUnit militaryUnit) {
        for (Tile tile : selectedCity.getCityTiles()){
            if (tile.getResource().equals(militaryUnit.getNeededResource()))
                return true;
        }
        return false;
    }
}
