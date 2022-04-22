package models;

import java.util.ArrayList;

public class Civilization {
    private String leaderName;
    private ArrayList<City> cities = new ArrayList<>();
    private City capital;
    private ArrayList<Unit> units = new ArrayList<>();
    private int happiness, gold, turn;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Technology> technologies = new ArrayList<>();
    private ArrayList<Improvement> improvements = new ArrayList<>();



    public Civilization(String leaderName, City capital) {
        this.leaderName = leaderName;
        this.capital = capital;
        cities.add(capital);
    }

    public String getLeaderName() {
        return leaderName;
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
