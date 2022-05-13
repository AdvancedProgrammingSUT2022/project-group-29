package models;

import enums.modelsEnum.ImprovementsEnum;

import java.util.ArrayList;

public class Improvement {
    private String name;
    private Technology neededTechnology;
    private ArrayList<TerrainAndFeature> TerrainFeaturesThatCanBeBuilt;
    private ArrayList<Resource> ImproveResources;
    private int FoodChange;
    private int ProductionChange;
    private int GoldChange;

    public Improvement(ImprovementsEnum type) {
        this.name = type.getName();
        ImproveResources = type.getImproveResources();
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

    public ArrayList<Resource> getImproveResources() {
        return ImproveResources;
    }
}
