package models;

import java.util.ArrayList;

public class Tile {
    private int x, y;
    private TerrainAndFeature terrain;
    private TerrainAndFeature feature;
    private Unit civilian = null;
    private MilitaryUnit militaryUnit = null;
    private ArrayList<Resource> possibleResources = new ArrayList<>();
    private ArrayList<Resource> resources = new ArrayList<>();

    public Tile(int x, int y, TerrainAndFeature terrain) {
        this.x = x;
        this.y = y;
        this.terrain = terrain;
        this.feature = null;
    }

    public void setCivilian(Unit civilian) {
        this.civilian = civilian;
    }

    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public TerrainAndFeature getTerrain() {
        return terrain;
    }

    public TerrainAndFeature getFeature() {
        return feature;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Unit getCivilian() {
        return civilian;
    }

    public MilitaryUnit getMilitaryUnit() {
        return militaryUnit;
    }
}
