package models;

import enums.TerrainsAndFeaturesEnum;

import java.util.ArrayList;

public class TerrainAndFeature {
    private final String kind;
    private final String color;
    private final int food;
    private final int production;
    private final int gold;
    private final int movementCost;
    private final double battleEffect;
    private ArrayList<Resource> possibleResources;

    public TerrainAndFeature(TerrainsAndFeaturesEnum type) {
        this.kind = type.getKind();
        this.food = type.getFood();
        this.production = type.getProduction();
        this.gold = type.getGold();
        this.movementCost = type.getMovementCost();
        this.battleEffect = type.getBattleEffect();
        this.color = type.getColor();
    }

    public String getColor() {
        return color;
    }

    public String getKind() {
        return kind;
    }

    public int getMovementCost() {
        return movementCost;
    }
}
