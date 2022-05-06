package enums.modelsEnum;

import models.Resource;
import models.Technology;

public enum nonCombatUnitsEnum {
    SETTLER("Ancient", "settler", 89, "Civilian", 0, 0, 0, 2, null, null),
    WORKER("Ancient", "worker", 70, "Civilian", 0, 0, 0, 2, null, null);

    private String era;
    private String name;
    private int cost;
    private String combatType;
    private int combatStrength;
    private int rangedCombatStrength;
    private int range;
    private int movement;
    private Resource neededResource;
    private Technology neededTechnology;

    nonCombatUnitsEnum(String era, String name, int cost, String combatType, int combatStrength, int rangedCombatStrength, int range, int movement, Resource neededResource, Technology neededTechnology) {
        this.era = era;
        this.name = name;
        this.cost = cost;
        this.combatType = combatType;
        this.combatStrength = combatStrength;
        this.rangedCombatStrength = rangedCombatStrength;
        this.range = range;
        this.movement = movement;
        this.neededResource = neededResource;
        this.neededTechnology = neededTechnology;
    }

    public String getEra() {
        return era;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getCombatType() {
        return combatType;
    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public int getRangedCombatStrength() {
        return rangedCombatStrength;
    }

    public int getRange() {
        return range;
    }

    public int getMovement() {
        return movement;
    }

    public Resource getNeededResource() {
        return neededResource;
    }

    public Technology getNeededTechnology() {
        return neededTechnology;
    }
}
