package models;

import java.util.ArrayList;

public class Civilization {
    private String name;
    private int happiness, gold, turn;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Technology> technologies = new ArrayList<>();
    private ArrayList<Improvement> improvements = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<City> cities = new ArrayList<>();
    private City capital;

    public Civilization(String name, int turn, ArrayList<Tile> tiles, City capital) {
        this.name = name;
        this.turn = turn;
        this.tiles = tiles;
        this.capital = capital;
    }

    public String getName() {
        return name;
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
