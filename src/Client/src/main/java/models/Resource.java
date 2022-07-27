package models;


import java.util.ArrayList;

public class Resource {
    private String type;
    private String name;
    private int food;
    private int production;
    private int gold;
    private Improvement neededImprovement;
    private Technology neededTechnology;
    private static ArrayList<Resource> discoveredLuxuryResources = new ArrayList<>();

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
