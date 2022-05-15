package models;

import controllers.GameController;
import enums.modelsEnum.MilitaryUnitsEnum;
import enums.modelsEnum.nonCombatUnitsEnum;

import java.util.ArrayList;

public class Civilization {
    private final User Leader;
    private String name;
    private final ArrayList<Unit> units = new ArrayList<>();
    private final ArrayList<MilitaryUnit> militaryUnits = new ArrayList<>();
    private final ArrayList<City> cities = new ArrayList<>();
    private City capital;
    private int happiness, gold, science;
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<Technology> technologies = new ArrayList<>();
    private final ArrayList<Technology> availableTechnology = new ArrayList<>();
    private final ArrayList<Improvement> improvements = new ArrayList<>();
    private String color;
    private Technology currentTechnology = null;

    public Civilization(User leader, int x, int y) {
        Leader = leader;

        MilitaryUnit militaryUnit = new MilitaryUnit(MilitaryUnitsEnum.WARRIOR, x, y);
        militaryUnits.add(militaryUnit);
        GameController.getInstance().getGame().getMap()[x][y].setMilitaryUnit(militaryUnit);

        Unit unit = new Unit(nonCombatUnitsEnum.SETTLER, x + 1, y + 1);
        units.add(unit);
        GameController.getInstance().getGame().getMap()[x + 1][y + 1].setCivilian(unit);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public ArrayList<MilitaryUnit> getMilitaryUnits() {
        return militaryUnits;
    }

    public void addMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnits.add(militaryUnit);
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public String LeaderName() {
        return this.Leader.getUsername();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Technology> getTechnologies() {
        return technologies;
    }

    public String getColor() {
        return color;
    }

    public City getCapital() {
        return capital;
    }

    public void addCivilianToCity(Unit civilianUnit) {
        units.add(civilianUnit);
    }

    public String getName() {
        return name;
    }


    public Technology getCurrentTechnology() {
        return currentTechnology;
    }

    public void setCurrentTechnology(Technology currentTechnology) {
        this.currentTechnology = currentTechnology;
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void deleteMilitaryUnit(int x, int y) {
        GameController.getInstance().getGame().getMap()[x][y].setMilitaryUnit(null);
        for (int i = 0; i < militaryUnits.size(); i++) {
            if (militaryUnits.get(i).getY() == y && militaryUnits.get(i).getX() == x) {
                militaryUnits.remove(i);
                return;
            }
        }
    }

    public void deleteNonMilitaryUnit(int x, int y) {
        GameController.getInstance().getGame().getMap()[x][y].setCivilian(null);
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getX() == x && units.get(i).getY() == y) {
                units.remove(i);
                return;
            }
        }
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public void increaseHappiness(int amount) {
        this.happiness += amount;
    }

    public void decreaseHappiness(int amount) {
        this.happiness += amount;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return gold;
    }

    public ArrayList<Technology> getAvailableTechnology() {
        return availableTechnology;
    }

    public boolean isExistTechnology(String name) {
        for (int i = 0; i < technologies.size(); i++) {
            if (availableTechnology.get(i).getName().equals(name))
                return true;
        }
        return false;
    }

    public void addTechnology(Technology technology) {
        technologies.add(technology);
    }

    public int getScience() {
        return science;
    }

    public void increaseScience(int amount) {
        this.science += amount;
    }

    public void decreaseScience(int amount) {
        this.science -= amount;
    }

    public int calculatePopulation() {
        int count = 0;
        for (int i = 0; i < cities.size(); i++){
            count +=cities.get(i).getPopulation();
        }
        return count;
    }
}
