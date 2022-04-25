package enums;

import models.Technology;
import models.TerrainAndFeature;

import java.util.ArrayList;
import java.util.Arrays;

public enum ImprovementsEnum {
    //TODO Change DEFAULT Technology And TerrainANDFeature
    CAMP("Camp", new Technology("DEFAULT"), new ArrayList<TerrainAndFeature>(), 0, 0, 0),
    FARM("Farm", new Technology("DEFAULT"), new ArrayList<TerrainAndFeature>(), 1, 0, 0),
    MINE("Mine", new Technology("DEFAULT"), new ArrayList<TerrainAndFeature>(), 0, 1, 0),
    PASTURE("Pasture", new Technology("DEFAULT"), new ArrayList<TerrainAndFeature>(), 0, 0, 0),
    PLANTATION("Plantation", new Technology("DEFAULT"), new ArrayList<TerrainAndFeature>(), 0, 0, 0),
    QUARRY("Quarry", new Technology("DEFAULT"), new ArrayList<TerrainAndFeature>(), 0, 0, 0),
    TRADING_POST("TradingPost", new Technology("DEFAULT"), new ArrayList<TerrainAndFeature>(), 0, 0, 2),
    LUMBER_MILL("LumberMill", new Technology("DEFAULT"), new ArrayList<TerrainAndFeature>(), 0, 1, 0);

    private String name;
    private Technology neededTechnology;
    private ArrayList<TerrainAndFeature> TerrainFeaturesThatCanBeBuilt;
    private int FoodChange;
    private int ProductionChange;
    private int GoldChange;

    ImprovementsEnum(String name, Technology neededTechnology, ArrayList<TerrainAndFeature> terrainFeaturesThatCanBeBuilt,
                     int foodChange, int productionChange, int goldChange) {
        this.name = name;
        this.neededTechnology = neededTechnology;
        TerrainFeaturesThatCanBeBuilt = terrainFeaturesThatCanBeBuilt;
        FoodChange = foodChange;
        ProductionChange = productionChange;
        GoldChange = goldChange;
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
