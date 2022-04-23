package models;

import java.util.ArrayList;

public class Terrain {
    private String type;
    private int food;
    private int production;
    private int gold;
    private int movementCost;
    private double battleEffect;
    private boolean isMovementPossible;
    private ArrayList<Resource> possibleResources = new ArrayList<>();
    private ArrayList<Feature> possibleFeatures = new ArrayList<>();
    public static ArrayList<Terrain> allTerrains = new ArrayList<>();

    static {
        allTerrains.add(new Terrain("Desert", 0, 0, 0, 1, -0.33, true));
        allTerrains.add(new Terrain("Grassland", 2, 0, 0, 1, -0.33, true));
        allTerrains.add(new Terrain("Hills", 0, 2, 0, 2, +0.25, true));
        allTerrains.add(new Terrain("Mountain", 0, 0, 0, 0, 0, false));
        allTerrains.add(new Terrain("Ocean", 0, 0, 0, 0, 0, false));
        allTerrains.add(new Terrain("Plain", 1, 1, 0, 1, -0.33, true));
        allTerrains.add(new Terrain("Snow", 0, 0, 0, 1, -0.33, true));
        allTerrains.add(new Terrain("Tundra", 1, 0, 0, 1, -0.33, true));
    }

    public Terrain(String type, int food, int production, int gold, int movementCost, double battleEffect, boolean isMovementPossible) {
        this.type = type;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.movementCost = movementCost;
        this.battleEffect = battleEffect;
        this.isMovementPossible = isMovementPossible;
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

    public static Terrain getTerrainByType(String type) {
        for (Terrain terrain : allTerrains) {
            if (terrain.type.equals(type))
                return terrain;
        }
        return null;
    }
}
