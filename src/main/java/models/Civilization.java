package models;

import java.util.ArrayList;

public class Civilization {
    private User Leader;
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<MilitaryUnit> militaryUnits = new ArrayList<>();
    private ArrayList<City> cities = new ArrayList<>();
    private City capital;
    private int happiness, gold;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Technology> technologies = new ArrayList<>();
    private ArrayList<Improvement> improvements = new ArrayList<>();
    private String color;

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
}
