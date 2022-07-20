package controllers;

import enums.modelsEnum.MilitaryUnitsEnum;
import enums.modelsEnum.nonCombatUnitsEnum;
import models.*;

import java.util.ArrayList;
import java.util.Collections;
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
        if (!isXTileValid(x))
            return "x is out of map";
        if (!isYTileValid(y))
            return "y is out of map";
        else if (ownerTile(x, y) == null || !game.getCurrentCivilization().getName().equals(ownerTile(x, y).getName()))
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
        City city;
        if ((city = game.getSelectedCity()) == null)
            return "select city";
        if (!isXTileValid(x))
            return "x is out of map";
        if (!isYTileValid(y))
            return "y is out of map";
        else if (ownerTile(x, y) == null || !game.getCurrentCivilization().getName().equals(ownerTile(x, y).getName()))
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
        if (game.getSelectedCity() != null)
            return "unemployedCitizenSection: " + game.getSelectedCity().getCitizen();
        return "first select a city!";
    }

    public String cityBuyTile(int x, int y) {
        City city;
        Tile tile;
        if ((city = game.getSelectedCity()) == null)
            return "select city";
        if (!isXTileValid(x))
            return "x is out of map";
        if (!isYTileValid(y))
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
        if ((selectedCity = game.getSelectedCity()) == null)
            return "no selected city";
        else if ((militaryUnitEnum = UnitController.getInstance().isExistMilitaryUnits(unitName)) != null) {
            if (selectedCity.getGold() < militaryUnitEnum.getCost())
                return "gold is not enough";
            else if (selectedCity.getMilitaryUnit() != null)
                return "a military unit exist in city";

            MilitaryUnit militaryUnit = new MilitaryUnit(militaryUnitEnum, selectedCity.getX(), selectedCity.getY());
            for (Technology technology : game.getCurrentCivilization().getTechnologies()) {
                if (!technology.getName().equals(militaryUnit.getNeededTechnology().getName()))
                    return "do not have needed technology";
            }
            if (!doesCityHaveNeededResources(selectedCity, militaryUnit))
                return "do not have needed resources";
            addMilitaryUnitToCity(militaryUnit, selectedCity, militaryUnitEnum.getCost());
            game.getCurrentCivilization().addToNotifications("combat unit " + militaryUnit.getName() + " created");
            return "unit created successfully in city";
        } else if ((nonCombatUnitsEnum = UnitController.getInstance().isExistNonCombatUnits(unitName)) != null) {
            if (selectedCity.getGold() < nonCombatUnitsEnum.getCost())
                return "gold is not enough";
            if (selectedCity.getCivilian() != null)
                return "a civilian unit exist in city";

            Unit civilianUnit = new Unit(nonCombatUnitsEnum, selectedCity.getX(), selectedCity.getY());
            addCivilianToCity(civilianUnit, selectedCity, nonCombatUnitsEnum.getCost());

            game.getCurrentCivilization().addToNotifications("non combat unit " + civilianUnit.getName() + "created");
            return "unit created successfully in city";
        } else
            return "unit name is invalid";
    }

    public String createBuilding(Building building,City city) {
        for (Building build : city.getCanTBuild()) {
            if (build.getName().equals(building.getName()))
                return "you can't build this building";
        }
        if (building.getCost() > city.getGold())
            return "not enough Gold";

        city.setGold(city.getGold() - building.getCost());
        city.getBuildings().add(building);

        for (Tile cityTile : city.getCityTiles()) {
            if (cityTile.getBuilding() == null) {
                cityTile.setBuilding(building);
                break;
            }
        }
        buildingEffect(building,city);
        return "building built";
    }

    private void buildingEffect(Building building,City city) {
        //TODO ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    }

    private void addCivilianToCity(Unit civilianUnit, City selectedCity, int gold) {
        selectedCity.setCivilian(civilianUnit);
        selectedCity.setGold(selectedCity.getGold() - gold);
        game.getCurrentCivilization().addCivilianToCity(civilianUnit);
    }

    private void addMilitaryUnitToCity(MilitaryUnit militaryUnit, City selectedCity, int gold) {
        selectedCity.removeResource(militaryUnit.getNeededResource());
        selectedCity.setMilitaryUnit(militaryUnit);
        selectedCity.setGold(selectedCity.getGold() - gold);
        game.getCurrentCivilization().addMilitaryUnit(militaryUnit);
    }

    private boolean doesCityHaveNeededResources(City selectedCity, MilitaryUnit militaryUnit) {
        for (Resource resource : selectedCity.getAllResources()) {
            if (resource.getName().equals(militaryUnit.getNeededResource().getName()))
                return true;
        }
        return false;
    }

    public String cityShowTilePosition() {
        City selectedCity;
        if ((selectedCity = game.getSelectedCity()) != null) {
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
        if ((selectedCity = game.getSelectedCity()) != null) {
            return selectedCity.getCivilization().getName();
        }
        return "first select a city!";
    }

    public String cityShowResources() {
        City selectedCity;
        if ((selectedCity = game.getSelectedCity()) != null) {
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
        if ((selectedCity = game.getSelectedCity()) != null) {
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
        if ((selectedCity = game.getSelectedCity()) != null) {
            return "militaryUnit: " + selectedCity.getMilitaryUnit() + "civilianUnit: " + selectedCity.getCivilian();
        }
        return "first select a city!";
    }

    public String cityShowInformation() {
        City selectedCity;
        if ((selectedCity = game.getSelectedCity()) != null) {
            return "name: " + selectedCity.getName() + "\n" +
                    "happiness: " + selectedCity.getHappiness() + "\n" +
                    "food: " + selectedCity.getFood() + "\n" +
                    "production: " + selectedCity.getProduction() + "\n" +
                    "gold: " + selectedCity.getGold() + "\n" +
                    "combatStrength: " + selectedCity.getCombatStrength() + "\n" +
                    "population: " + selectedCity.getPopulation() + "\n" +
                    "hitPoint: " + selectedCity.getHitPoint();
        }
        return "first select a city!";
    }
    public String cityShowDiplomaticInformation() {
        //TODO: complete
        Game game = GameController.getInstance().getGame();
        ArrayList<Civilization> enemyCivilizations = new ArrayList<>();
        ArrayList<Civilization> friendlyCivilizations = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (game.getCurrentCivilization() == null)
            return "there is not any civilization";
        return "";
    }

    public String cityAttack(int x, int y) {
        City selectedCity = game.getSelectedCity();
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
        if (game.getMap()[x][y].getMilitaryUnit() == null)
            return "no combat unit there";
        if (!isInRange(x, y, selectedCity))
            return "position is not in city range";
        if (!selectedCity.getMilitaryUnit().getState().equals("ready"))
            return "combat unit is not ready";
        if (selectedCity.getMilitaryUnit().isHasDone())
            return "city has attacked in this turn";
        cityAttack(x, y, selectedCity);
        return "attack successful";
    }

    private void cityAttack(int x, int y, City selectedCity) {
        selectedCity.getMilitaryUnit().setHasDone(true);
        MilitaryUnit militaryUnit = game.getMap()[x][y].getMilitaryUnit();
        if (1 == getLeastDistance(selectedCity, x, y)) {
            if (game.getMap()[selectedCity.getX()][selectedCity.getY()].getTerrain().getKind().equals("hills")) {
                if (militaryUnit.getHp() <= selectedCity.getMilitaryUnit().getCombatStrength() * 2)
                    destroyCombatUnit(x, y);
                else
                    militaryUnit.setHp(militaryUnit.getHp() - selectedCity.getMilitaryUnit().getCombatStrength() * 2);
            } else {
                if (militaryUnit.getHp() <= selectedCity.getMilitaryUnit().getCombatStrength())
                    destroyCombatUnit(x, y);
                else
                    militaryUnit.setHp(militaryUnit.getHp() - selectedCity.getMilitaryUnit().getCombatStrength());
            }
        } else {
            if (game.getMap()[selectedCity.getX()][selectedCity.getY()].getTerrain().getKind().equals("hills")) {
                if (militaryUnit.getHp() <= selectedCity.getMilitaryUnit().getRangedCombatStrength() * 2)
                    destroyCombatUnit(x, y);
                else
                    militaryUnit.setHp(militaryUnit.getHp() - selectedCity.getMilitaryUnit().getRangedCombatStrength() * 2);
            } else {
                if (militaryUnit.getHp() < selectedCity.getMilitaryUnit().getRangedCombatStrength())
                    destroyCombatUnit(x, y);
                else
                    militaryUnit.setHp(militaryUnit.getHp() - selectedCity.getMilitaryUnit().getRangedCombatStrength());
            }
        }
    }

    private int getLeastDistance(City city, int x, int y) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (Tile cityTile : city.getCityTiles()) {
            arrayList.add((cityTile.getX() - x) * (cityTile.getX() - x) + (cityTile.getY() - y) * (cityTile.getY() - y));
        }
        return Collections.min(arrayList);
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

    public String createCity(String name) {
        if (game.getSelectedNonCombatUnit() == null ||
                !game.getSelectedNonCombatUnit().getName().equals("settler"))
            return "no selected settler unit";

        int x = game.getSelectedNonCombatUnit().getX();
        int y = game.getSelectedNonCombatUnit().getY();

        for (Civilization civilizations : game.getCivilizations()) {
            for (City civilizationsCity : civilizations.getCities()) {
                for (Tile civilizationsCityCityTile : civilizationsCity.getCityTiles()) {
                    if (civilizationsCityCityTile.getX() == x && civilizationsCityCityTile.getY() == y)
                        return "there is already a city";
                }
            }
        }


        City city = new City(name, game.getCurrentCivilization(), x, y);
        game.getCurrentCivilization().addCity(city);
        destroySettler();
        if (game.getCurrentCivilization().getCapital() == null)
            game.getCurrentCivilization().setCapital(city);
        game.getCurrentCivilization().decreaseHappiness(1);
        game.getCurrentCivilization().addToNotifications("city " + name + " created");
        return "city created successfully";
    }

    private void destroySettler() {
        int x = game.getSelectedNonCombatUnit().getX();
        int y = game.getSelectedNonCombatUnit().getY();
        for (int i = 0; i < game.getCurrentCivilization().getUnits().size(); i++) {
            if (game.getCurrentCivilization().getUnits().get(i).getY() == y
                    && game.getCurrentCivilization().getUnits().get(i).getX() == x) {
                game.getCurrentCivilization().getUnits().remove(i);
                game.getMap()[x][y].setCivilian(null);
                game.setSelectedNonCombatUnit(null);
                return;
            }

        }
    }

    public boolean isXTileValid(int x) {
        return x <= 45 && x >= 0;
    }

    public boolean isYTileValid(int y) {
        return y <= 30 && y >= 0;
    }

    //TODO SHOULD COMPLETE relative to turn
    public String cityScreenMenu() {
        City selectedCity;
        if ((selectedCity = game.getSelectedCity()) != null) {
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
        if ((selectedCity = game.getSelectedCity()) != null) {
            return "name: " + selectedCity.getName() + "\n" +
                    "happiness: " + selectedCity.getHappiness() + "\n" +
                    "food: " + selectedCity.getFood() + "\n" +
                    "production: " + selectedCity.getProduction() + "\n" +
                    "science: " + selectedCity.getScience();
        }
        return "first select a city!";

    }

    public City getCity(int x, int y) {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                if (city.getX() == x && city.getY() == y)
                    return city;
            }
        }
        return null;
    }

    public String combat(City city, MilitaryUnit combatUnit, int x, int y) {
        if (!combatUnit.getState().equals("ready"))
            return "unit is not ready";
        if (combatUnit.isHasDone())
            return "unit has done its work";
        if (!combatUnit.isReadySiege())
            return "siege unit is not set";
        if (combatUnit.getRange() == 0) {
            if ((y - combatUnit.getY()) * (y - combatUnit.getY()) +
                    (x - combatUnit.getX()) * (x - combatUnit.getX()) > 1)
                return "out of range";
        }
        else {
            if ((y - combatUnit.getY()) * (y - combatUnit.getY()) +
                    (x - combatUnit.getX()) * (x - combatUnit.getX()) >
                    combatUnit.getRange() * combatUnit.getRange())
                return "position is not in range";
        }
        unitAttackCity(city, combatUnit, x, y);
        return "attacked successfully";
    }

    private void unitAttackCity(City city, MilitaryUnit combatUnit, int x, int y) {
        if (!(combatUnit.getName().equals("HorseMan") || combatUnit.getName().equals("Knight") ||
                combatUnit.getName().equals("Cavalry")) || combatUnit.getName().equals("Lancer") ||
                combatUnit.getName().equals("Tank"))
            combatUnit.setHasDone(true);
        int attack = combatUnit.getCombatStrength();
        int rangedAttack = combatUnit.getRangedCombatStrength();
        attack = (int) (attack * game.getMap()[combatUnit.getX()][combatUnit.getY()].getCombatChange() + attack);
        rangedAttack = (int) (rangedAttack * game.getMap()[combatUnit.getX()][combatUnit.getY()].getCombatChange() + rangedAttack);
        if (combatUnit.getCombatType().equals("Siege"))
            rangedAttack += 10;
        if (1 == (y - combatUnit.getY()) * (y - combatUnit.getY())
                + (x - combatUnit.getX()) * (x - combatUnit.getX())) {
            if (city.getMilitaryUnit() == null) {
                if (attack >= city.getHitPoint())
                    takeCity(city);
                else
                    city.setHitPoint(city.getHitPoint() - attack);
            } else {
                if (attack >= city.getHitPoint())
                    destroyCity(city);
                else
                    city.setHitPoint(city.getHitPoint() - attack);
            }
        } else {
            if (city.getMilitaryUnit() == null) {
                if (rangedAttack >= city.getHitPoint())
                    takeCity(city);
                else
                    city.setHitPoint(city.getHitPoint() - rangedAttack);
            } else {
                if (rangedAttack >= city.getHitPoint())
                    destroyCity(city);
                else
                    city.setHitPoint(city.getHitPoint() - rangedAttack);
            }
        }
        if (combatUnit.getCombatType().equals("Siege"))
            combatUnit.setReadySiege(false);
    }

    private void takeCity(City city) {
        for (Civilization civilization : game.getCivilizations()) {
            for (City civilizationCity : civilization.getCities()) {
                if (city.getName().equals(civilizationCity.getName())) {
                    civilization.decreaseHappiness(5);
                    destroyCitySettler(city);
                    game.getCurrentCivilization().getCities().add(city);
                    game.getCurrentCivilization().decreaseHappiness(5);
                    civilization.getCities().remove(civilizationCity);
                    civilization.addToNotifications("city " + city.getName() + " captured :(");
                    game.getCurrentCivilization().addToNotifications("city " + city.getName() + "captured :)");
                    break;
                }
            }
        }
    }

    private void destroyCitySettler(City city) {
        Unit unit;
        for (int i = 0; i < city.getCityTiles().size(); i++) {
            if ((unit = city.getCityTiles().get(i).getCivilian()) != null) {
                GameController.getInstance().getCivilization(city.getX(), city.getY()).
                        deleteNonMilitaryUnit(unit.getX(), unit.getY());
                return;
            }
        }
    }

    private void destroyCity(City city) {
        game.getCurrentCivilization().setGold(game.getCurrentCivilization().getGold() + city.getGold());
        for (int i = 0; i < city.getCityTiles().size(); i++) {
            city.getCityTiles().get(i).setNeedRepair(true);
        }
        for (Civilization civilization : game.getCivilizations()) {
            for (int i = 0; i < civilization.getCities().size(); i++) {
                if (civilization.getCities().get(i).getName().equals(city.getName())) {
                    civilization.getCities().remove(i);
                    return;
                }
            }
        }
    }

    //TODO add buy unit to queue
    /*
    public String buyUnit(String unitName) {
        if ((selectedCity = game.getSelectedCity()) == null)
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
