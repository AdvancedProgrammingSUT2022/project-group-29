package models;

import enums.modelsEnum.ResourceEnum;

import java.util.ArrayList;

public class Resource {
    private final String type;
    private final String name;
    private final int food;
    private final int production;
    private final int gold;
    private final Improvement neededImprovement;
    private final Technology neededTechnology;
    private static ArrayList<Resource> discoveredLuxuryResources = new ArrayList<>();

    public Resource(ResourceEnum type) {
        this.type = type.getType();
        this.name = type.getName();
        this.food = type.getFood();
        this.production = type.getProduction();
        this.gold = type.getGold();
        this.neededImprovement = type.getNeededImprovement();
        this.neededTechnology = type.getNeededTechnology();
    }

    public String getType() {
        return type;
    }
    public String getName() {
        return name;
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
    public Improvement getNeededImprovement() {
        return neededImprovement;
    }
    public Technology getNeededTechnology() {
        return neededTechnology;
    }
    public static ArrayList<Resource> getDiscoveredLuxuryResources() {
        return discoveredLuxuryResources;
    }
    public static void addDiscoveredLuxuryResource(Resource resource) {
        discoveredLuxuryResources.add(resource);
    }

}
