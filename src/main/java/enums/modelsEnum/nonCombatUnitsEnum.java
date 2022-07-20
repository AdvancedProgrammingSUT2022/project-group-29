package enums.modelsEnum;

import models.Resource;
import models.Technology;

public enum nonCombatUnitsEnum {
    SETTLER("Ancient", "settler", 89, "Civilian", 2),
    WORKER("Ancient", "worker", 70, "Civilian", 2);

    private String era;
    private String name;
    private int cost;
    private String combatType;
    private int movement;

    nonCombatUnitsEnum(String era, String name, int cost, String combatType, int movement) {
        this.era = era;
        this.name = name;
        this.cost = cost;
        this.combatType = combatType;
        this.movement = movement;
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

    public int getMovement() {
        return movement;
    }
}
