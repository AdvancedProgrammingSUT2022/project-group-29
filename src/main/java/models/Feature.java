package models;

import java.util.ArrayList;

public class Feature {
    private String type;
    private int food, production, gold, movementCost;
    private double battleEffect;
    private ArrayList<Resource> possibleResources = new ArrayList<>();

    public Feature(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
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
