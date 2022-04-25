package models;

import enums.ResourceEnum;

import java.util.ArrayList;

public class Resource {
    protected String type;
    protected String name;
    protected int food;
    protected int production;
    protected int gold;
    protected Improvement neededImprovement;
    protected ArrayList<TerrainAndFeature> CanBeFoundOnThisTerrain = new ArrayList<>();
    //TODO Edit CanBeFoundOnThisTerrain in Constructor after TerrainAndFeature

    public Resource(ResourceEnum type) {
        this.type = type.getType();
        this.name = type.getName();
        this.food = type.getFood();
        this.production = type.getProduction();
        this.gold = type.getGold();
        this.neededImprovement = type.getNeededImprovement();
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getFood() {
        return food;
    }

    public int getProduction() {
        return production;
    }

    public int getGold() {
        return gold;
    }

    public Improvement getNeededImprovement() {
        return neededImprovement;
    }

    public ArrayList<TerrainAndFeature> getCanBeFoundOnThisTerrain() {
        return CanBeFoundOnThisTerrain;
    }
}
