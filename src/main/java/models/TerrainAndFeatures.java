package models;

import enums.TerrainsAndFeaturesEnum;

import java.util.ArrayList;

public class TerrainAndFeatures {
    private final String kind;
    private final int food;
    private final int production;
    private final int gold;
    private final int movementCost;
    private final double battleEffect;
    private ArrayList<Resource> possibleResources;

    public TerrainAndFeatures(TerrainsAndFeaturesEnum type) {
        this.kind = type.getKind();
        this.food = type.getFood();
        this.production = type.getProduction();
        this.gold = type.getGold();
        this.movementCost = type.getMovementCost();
        this.battleEffect = type.getBattleEffect();
    }

    public String getKind() {
        return kind;
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

    public int getMovementCost() {
        return movementCost;
    }

    public double getBattleEffect() {
        return battleEffect;
    }

    public ArrayList<Resource> getPossibleResources() {
        return possibleResources;
    }

}
