package models;

import java.util.ArrayList;

public class City {
    private String name;
    private Civilization civilization;
    private int happiness;
    private int gold;
    private int production;
    private int food;
    private boolean isCapital = false;
    private double combatStrength;
    private int population;

    private Unit civilian = null;
    private MilitaryUnit militaryUnit = null;
    private int hitPoint;
    private ArrayList<Tile> cityTiles = new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();


    public City(String name, Civilization civilization, boolean isCapital, ArrayList<Tile> cityTiles) {
        this.name = name;
        this.civilization = civilization;
        this.isCapital = isCapital;
        this.cityTiles = cityTiles;
        this.happiness = 0;
        this.food = 0;
        this.gold = 0;
        this.combatStrength = 10;
        this.production = 0;
        this.hitPoint = 20;
        this.population = 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }


    public String getName() {
        return name;
    }

    public ArrayList<Tile> getCityTiles() {
        return cityTiles;
    }
}
