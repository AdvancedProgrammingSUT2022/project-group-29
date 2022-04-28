package models;

import enums.modelsEnum.MilitaryUnitsEnum;

public class MilitaryUnit{
    private int cost;
    private String name;
    private int movement;
    private String combatType;
    private int combatStrength;
    private int rangedCombatStrength, range;
    private Technology neededTechnology;
    private Resource neededResource;

    public MilitaryUnit(MilitaryUnitsEnum type) {
        this.cost = type.getCost();
        this.name = type.getName();
        this.movement = type.getMovement();
        this.combatType = type.getCombatType();
        this.combatStrength = type.getCombatStrength();;
        this.rangedCombatStrength = type.getRangedCombatStrength();
        this.range = type.getRange();
        this.neededTechnology = type.getNeededTechnology();
        this.neededResource = type.getNeededResource();
    }

}
