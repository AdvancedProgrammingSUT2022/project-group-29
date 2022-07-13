package models;

import enums.modelsEnum.TerrainsAndFeaturesEnum;

public class TerrainAndFeature {
    private final String kind;
    private final String color;
    private final int food;
    private final int production;
    private final int gold;
    private final int movementCost;
    private final double battleEffect;

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

    public double getBattleEffect() {
        return battleEffect;
    }

    public int getGold() {
        return gold;
    }

    public int getFood() {
        return food;
    }

    public int getProduction() {
        return production;
    }
}
