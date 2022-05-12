package models;

import java.util.ArrayList;

public class Civilization {
    private User Leader;
    private String name;
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<MilitaryUnit> militaryUnits = new ArrayList<>();
    private ArrayList<City> cities = new ArrayList<>();
    private City capital;
    private int happiness, gold;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Technology> technologies = new ArrayList<>();
    private ArrayList<Improvement> improvements = new ArrayList<>();
    private String color;
    private Technology currentTechnology = null;

    public Civilization(User leader) {
        Leader = leader;
        //MilitaryUnit unit = new MilitaryUnit(MilitaryUnitsEnum.ARCHER);
        //militaryUnits.add(unit);
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

    public void addMilitaryUnit (MilitaryUnit militaryUnit){
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

    public void increaseHappiness(int amount) {
        this.happiness += amount;
    }
    public void decreaseHappiness(int amount) {
        this.happiness += amount;
    }
}
