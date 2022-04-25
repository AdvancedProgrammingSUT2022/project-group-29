package models;

import enums.MilitaryUnitsEnum;

import java.util.ArrayList;

public class Civilization {
    private User Leader;
    private ArrayList<Unit> units;
    private ArrayList<MilitaryUnit> militaryUnits;
    private ArrayList<City> cities = new ArrayList<>();
    private City capital;
    private int happiness, gold;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Technology> technologies = new ArrayList<>();
    private ArrayList<Improvement> improvements = new ArrayList<>();

    public Civilization(User leader) {
        Leader = leader;
        MilitaryUnit unit = new MilitaryUnit(MilitaryUnitsEnum.ARCHER);
        militaryUnits.add(unit);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public User getLeader() {
        return Leader;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public ArrayList<MilitaryUnit> getMilitaryUnits() {
        return militaryUnits;
    }

    public ArrayList<City> getCities() {
        return cities;
    }
}
