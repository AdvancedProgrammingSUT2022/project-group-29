package models;

import enums.modelsEnum.BuildingsEnum;
import enums.modelsEnum.TechnologyEnum;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Building {

    private static List<BuildingsEnum> allBuildings = Arrays.asList(BuildingsEnum.values());
    private String name;
    private int cost;
    private int maintenance;
    private Technology neededTechnology;
    private Building neededBuilding;

    public Building(BuildingsEnum buildingsEnum) {
        this.name = buildingsEnum.getName();
        this.cost = buildingsEnum.getCost();
        this.maintenance = buildingsEnum.getMaintenance();
        this.neededTechnology = buildingsEnum.getNeededTechnology();
        this.neededBuilding = buildingsEnum.getNeededBuilding();
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public Technology getNeededTechnology() {
        return neededTechnology;
    }

    public Building getNeededBuilding() {
        return neededBuilding;
    }

    public static List<BuildingsEnum> getAllBuildings() {
        return allBuildings;
    }
}
