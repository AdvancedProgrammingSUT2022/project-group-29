package models;


import java.util.Arrays;
import java.util.List;

public class Building {

    private String name;
    private int cost;
    private int maintenance;
    private Technology neededTechnology;
    private Building neededBuilding;

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

    public void setMaintenance(int maintenance) {
        this.maintenance = maintenance;
    }
}
