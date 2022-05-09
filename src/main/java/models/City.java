package models;

import java.util.ArrayList;

public class City {
    private String name;
    private Civilization civilization;
    private int happiness;
    private int gold;
    private int production;
    private int food;
    private double combatStrength;
    private int population;
    private int x, y;
    private Unit civilian = null;
    private MilitaryUnit militaryUnit = null;
    private int hitPoint;
    private ArrayList<Tile> cityTiles = new ArrayList<>();
    private int citizen;

    public City(String name, Civilization civilization, ArrayList<Tile> cityTiles) {
        this.name = name;
        this.civilization = civilization;
        this.cityTiles = cityTiles;
        this.happiness = 0;
        this.food = 0;
        this.gold = 0;
        this.combatStrength = 10;
        this.production = 0;
        this.hitPoint = 20;
        this.population = 1;
        this.citizen = 0;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Unit getCivilian() {
        return civilian;
    }

    public void setCivilian(Unit civilian) {
        this.civilian = civilian;
    }

    public MilitaryUnit getMilitaryUnit() {
        return militaryUnit;
    }

    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getProduction() {
        return production;
    }

    public int getFood() {
        return food;
    }

    public double getCombatStrength() {
        return combatStrength;
    }

    public int getPopulation() {
        return population;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getCitizen() {
        return citizen;
    }

    public void increaseCitizen(int amount) {
        this.citizen += amount;
    }
    public void decreaseCitizen(int amount) {
        this.citizen -= amount;
    }
}
