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
        this.isCapital = isCapital;
        this.hitPoint = 20;
        this.population = 1;
    }

    public String getName() {
        return name;
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

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    public double getCombatStrength() {
        return combatStrength;
    }

    public void setCombatStrength(double combatStrength) {
        this.combatStrength = combatStrength;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public ArrayList<Tile> getCityTiles() {
        return cityTiles;
    }

    public void setCityTiles(ArrayList<Tile> cityTiles) {
        this.cityTiles = cityTiles;
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

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

}
