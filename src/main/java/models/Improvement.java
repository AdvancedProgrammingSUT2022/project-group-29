package models;

import enums.ImprovementsEnum;

import java.util.ArrayList;

public class Improvement {
    private String name;
    private Technology neededTechnology;
    private ArrayList<TerrainAndFeature> TerrainFeaturesThatCanBeBuilt;
    private int FoodChange;
    private int ProductionChange;
    private int GoldChange;

    public Improvement(ImprovementsEnum type) {
        this.name = type.getName();
        this.neededTechnology = type.getNeededTechnology();
        TerrainFeaturesThatCanBeBuilt = type.getTerrainFeaturesThatCanBeBuilt();
        this.FoodChange = type.getFoodChange();
        this.ProductionChange = type.getProductionChange();
        this.GoldChange = type.getGoldChange();
    }

    public String getName() {
        return name;
    }

    public Technology getNeededTechnology() {
        return neededTechnology;
    }

    public ArrayList<TerrainAndFeature> getTerrainFeaturesThatCanBeBuilt() {
        return TerrainFeaturesThatCanBeBuilt;
    }

    public int getFoodChange() {
        return FoodChange;
    }

    public int getProductionChange() {
        return ProductionChange;
    }

    public int getGoldChange() {
        return GoldChange;
    }
}
