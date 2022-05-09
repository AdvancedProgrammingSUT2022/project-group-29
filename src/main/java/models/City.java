package models;

import controllers.GameController;

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
    private int x, y;
    private Unit civilian = null;
    private MilitaryUnit militaryUnit = null;
    private int hitPoint;
    private ArrayList<Tile> cityTiles = new ArrayList<>();


    public City(String name, Civilization civilization, boolean isCapital, int x, int y) {
        this.name = name;
        this.civilization = civilization;
        this.isCapital = isCapital;
        this.x = x;
        this.y = y;
        this.happiness = 0;
        this.food = 0;
        this.gold = 0;
        this.combatStrength = 10;
        this.production = 0;
        this.hitPoint = 20;
        this.population = 1;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                this.cityTiles.add(GameController.getInstance().getGame().getMap()[i][j]);
            }
        }
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

    public boolean isCapital() {
        return isCapital;
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
}
