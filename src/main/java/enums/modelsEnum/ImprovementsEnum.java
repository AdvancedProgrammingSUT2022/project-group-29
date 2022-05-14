package enums.modelsEnum;

import enums.modelsEnum.TechnologyEnum;
import models.Resource;
import models.Technology;
import models.TerrainAndFeature;

import java.util.ArrayList;

import static enums.modelsEnum.ResourceEnum.*;
import static enums.modelsEnum.TerrainsAndFeaturesEnum.*;

public enum ImprovementsEnum {

    CAMP("Camp", new ArrayList<Resource>(){{
        add(new Resource(IVORY));
        add(new Resource(FURS));
        add(new Resource(DEER));
    }},new Technology(TechnologyEnum.TRAPPING), new ArrayList<TerrainAndFeature>(){{
        add(new TerrainAndFeature(PLAINS));
        add(new TerrainAndFeature(FOREST));
        add(new TerrainAndFeature(TUNDRA));
        add(new TerrainAndFeature(HILLS));
    }}, 0, 0, 0),

    FARM("Farm", new ArrayList<Resource>(){{
        add(new Resource(WHEAT));
    }},new Technology(TechnologyEnum.AGRICULTURE), new ArrayList<TerrainAndFeature>(){{
        add(new TerrainAndFeature(GRASSLAND));
        add(new TerrainAndFeature(PLAINS));
        add(new TerrainAndFeature(DESERT));
    }}, 1, 0, 0),

    MINE("Mine", new ArrayList<Resource>(){{
        add(new Resource(IRON));
        add(new Resource(COAL));
        add(new Resource(GEMS));
        add(new Resource(GOLD));
        add(new Resource(SILVER));
    }},new Technology(TechnologyEnum.MINING), new ArrayList<TerrainAndFeature>(){{
        add(new TerrainAndFeature(GRASSLAND));
        add(new TerrainAndFeature(PLAINS));
        add(new TerrainAndFeature(DESERT));
        add(new TerrainAndFeature(TUNDRA));
        add(new TerrainAndFeature(JUNGLE));
        add(new TerrainAndFeature(SNOW));
        add(new TerrainAndFeature(HILLS));
    }}, 0, 1, 0),

    PASTURE("Pasture", new ArrayList<Resource>(){{
        add(new Resource(HORSES));
        add(new Resource(CATTLE));
        add(new Resource(SHEEP));
    }},new Technology(TechnologyEnum.ANIMAL_HUSBANDRY), new ArrayList<TerrainAndFeature>(){{
        add(new TerrainAndFeature(GRASSLAND));
        add(new TerrainAndFeature(PLAINS));
        add(new TerrainAndFeature(DESERT));
        add(new TerrainAndFeature(TUNDRA));
        add(new TerrainAndFeature(HILLS));
    }}, 0, 0, 0),

    PLANTATION("Plantation", new ArrayList<Resource>(){{
        add(new Resource(BANANA));
        add(new Resource(DYES));
        add(new Resource(SILK));
        add(new Resource(SUGAR));
        add(new Resource(COTTON));
        add(new Resource(INCENSE));
    }},new Technology(TechnologyEnum.CALENDAR), new ArrayList<TerrainAndFeature>(){{
        add(new TerrainAndFeature(GRASSLAND));
        add(new TerrainAndFeature(PLAINS));
        add(new TerrainAndFeature(DESERT));
        add(new TerrainAndFeature(FOREST));
        add(new TerrainAndFeature(MARSH));
        add(new TerrainAndFeature(FLOODPLAINS));
        add(new TerrainAndFeature(JUNGLE));
    }}, 0, 0, 0),

    QUARRY("Quarry", new ArrayList<Resource>(){{
        add(new Resource(MARBLE));
    }},new Technology(TechnologyEnum.MASONRY), new ArrayList<TerrainAndFeature>(){{
        add(new TerrainAndFeature(GRASSLAND));
        add(new TerrainAndFeature(PLAINS));
        add(new TerrainAndFeature(DESERT));
        add(new TerrainAndFeature(TUNDRA));
        add(new TerrainAndFeature(HILLS));
    }}, 0, 0, 0),

    TRADING_POST("TradingPost", new ArrayList<>(),new Technology(TechnologyEnum.TRAPPING), new ArrayList<TerrainAndFeature>(){{
        add(new TerrainAndFeature(GRASSLAND));
        add(new TerrainAndFeature(PLAINS));
        add(new TerrainAndFeature(DESERT));
        add(new TerrainAndFeature(TUNDRA));
    }}, 0, 0, 1),

    LUMBER_MILL("LumberMill", new ArrayList<>(),new Technology(TechnologyEnum.ENGINEERING), new ArrayList<TerrainAndFeature>(){{
        add(new TerrainAndFeature(FOREST));
    }}, 0, 1, 0),
    MANUFACTORY("Manufactory", new ArrayList<>(),new Technology(TechnologyEnum.ENGINEERING), new ArrayList<TerrainAndFeature>(){{
        add(new TerrainAndFeature(GRASSLAND));
        add(new TerrainAndFeature(PLAINS));
        add(new TerrainAndFeature(DESERT));
        add(new TerrainAndFeature(TUNDRA));
        add(new TerrainAndFeature(SNOW));
    }}, 0, 2, 0);


    private String name;
    private Technology neededTechnology;
    private ArrayList<Resource> ImproveResources;
    private ArrayList<TerrainAndFeature> TerrainFeaturesThatCanBeBuilt;
    private int FoodChange;
    private int ProductionChange;
    private int GoldChange;

    ImprovementsEnum(String name,  ArrayList<Resource> ImproveResources,Technology neededTechnology, ArrayList<TerrainAndFeature> terrainFeaturesThatCanBeBuilt,
                     int foodChange, int productionChange, int goldChange) {
        this.name = name;
        this.ImproveResources = ImproveResources;
        this.neededTechnology = neededTechnology;
        TerrainFeaturesThatCanBeBuilt = terrainFeaturesThatCanBeBuilt;
        FoodChange = foodChange;
        ProductionChange = productionChange;
        GoldChange = goldChange;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Resource> getImproveResources() {
        return ImproveResources;
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
