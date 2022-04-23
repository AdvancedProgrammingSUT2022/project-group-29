package models;

import java.util.ArrayList;

public class Civilization {
    private User Leader;
    private ArrayList<Unit> units;
    private ArrayList<City> cities = new ArrayList<>();
    private City capital;
    private int happiness, gold, turn;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Technology> technologies = new ArrayList<>();
    private ArrayList<Improvement> improvements = new ArrayList<>();

    public Civilization(User leader) {
        Leader = leader;
    }

    public User getLeader() {
        return Leader;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getGold() {
        return gold;
    }

    public int getTurn() {
        return turn;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public ArrayList<Technology> getTechnologies() {
        return technologies;
    }

    public ArrayList<Improvement> getImprovements() {
        return improvements;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public City getCapital() {
        return capital;
    }
}
