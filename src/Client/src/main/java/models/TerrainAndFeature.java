package models;


public class TerrainAndFeature {
    private String kind;
    private String color;
    private int food;
    private int production;
    private int gold;
    private int movementCost;
    private double battleEffect;


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
