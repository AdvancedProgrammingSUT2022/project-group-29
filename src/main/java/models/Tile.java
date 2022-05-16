package models;


import java.util.ArrayList;

public class Tile {
    private int x, y;
    private TerrainAndFeature terrain;
    private TerrainAndFeature feature;
    private Resource resource;
    private Unit civilian;
    private MilitaryUnit militaryUnit;
    private boolean isThereCitizen;

    private ArrayList<Tile> neighbourTiles ;
    private  boolean[] rivers;
    private Improvement improvement;
    private int movementCost;
    private int value = 0;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.terrain = null;
        this.feature = null;
        this.resource = null;
        this.neighbourTiles = null;
        this.rivers = new boolean[6];
        for (int i = 0; i < 6; i++) {
            rivers[i] = false;
        }
    }

    public void setTerrain(TerrainAndFeature terrain) {
        this.terrain = terrain;
    }

    public void setFeature(TerrainAndFeature feature) {
        this.feature = feature;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setCivilian(Unit civilian) {
        this.civilian = civilian;
    }

    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public void setNeighbourTiles(ArrayList<Tile> neighbourTiles) {
        this.neighbourTiles = neighbourTiles;
    }

    public TerrainAndFeature getTerrain() {
        return terrain;
    }

    public TerrainAndFeature getFeature() {
        return feature;
    }

    public Resource getResource() {
        return resource;
    }

    public ArrayList<Tile> getNeighbourTiles() {
        return neighbourTiles;
    }

    public boolean[] getRivers() {
        return rivers;
    }

    public void addRiver(int x) {
        this.rivers[x] = true;
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

    public Unit getCivilian() {
        return civilian;
    }

    public boolean isThereCitizen() {
        return isThereCitizen;
    }

    public void setThereCitizen(boolean thereCitizen) {
        isThereCitizen = thereCitizen;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setImprovement(Improvement improvement) {
        this.improvement = improvement;
    }
}
