package models;

import java.util.ArrayList;

public class Tile {
    private int x, y;
    private TerrainAndFeature terrain;
    private TerrainAndFeature feature;

    private Resource resource;
    private Unit civilian ;
    private MilitaryUnit militaryUnit ;
    private ArrayList<Improvement> improvements;
    private int movementCost;

    public Tile(int x, int y, TerrainAndFeature terrain) {
        this.x = x;
        this.y = y;
        this.terrain = terrain;
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
