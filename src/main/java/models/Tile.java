package models;

import enums.TerrainsAndFeaturesEnum;

import java.util.ArrayList;

public class Tile {
    private int x, y;
    private TerrainAndFeature terrain;
    private TerrainAndFeature feature;
    private Unit civilian = null;
    private MilitaryUnit militaryUnit = null;
    private ArrayList<Resource> possibleResources = new ArrayList<>();
    private ArrayList<Resource> resources = new ArrayList<>();
    private ArrayList<Improvement> improvements;
    private int movementCost;

    public Tile(int x, int y, TerrainAndFeature terrain) {
        this.x = x;
        this.y = y;
        this.terrain = terrain;
        this.feature = new TerrainAndFeature(TerrainsAndFeaturesEnum.FOREST);
        this.movementCost = terrain.getMovementCost() + feature.getMovementCost();
        this.improvements = new ArrayList<>();
    }

    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public void setCivilian(Unit civilian) {
        this.civilian = civilian;
    }

    public TerrainAndFeature getTerrain() {
        return terrain;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MilitaryUnit getMilitaryUnit() {
        return militaryUnit;
    }

    public int getMovementCost() {
        return movementCost;
    }
}
