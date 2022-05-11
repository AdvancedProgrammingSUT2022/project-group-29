package models;

import enums.modelsEnum.nonCombatUnitsEnum;

public class Unit {
    private final String name;
    private final int combatStrength;
    private final int rangedCombatStrength;
    private final int range;
    private final Technology neededTechnology;
    private final Resource neededResource;
    private int x, y;
    private int cost;
    private String type;
    private int movement;
    private final int maxMovement;
    private String combatType;
    private int xEnd, yEnd;
    private String state = "";

    public Unit(nonCombatUnitsEnum type, int x, int y) {
        this.cost = type.getCost();
        this.name = type.getName();
        this.movement = type.getMovement();
        this.maxMovement = type.getMovement();
        this.combatType = type.getCombatType();
        this.combatStrength = type.getCombatStrength();
        this.rangedCombatStrength = type.getRangedCombatStrength();
        this.range = type.getRange();
        this.neededTechnology = type.getNeededTechnology();
        this.neededResource = type.getNeededResource();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getMovement() {
        return movement;
    }

    public int getxEnd() {
        return xEnd;
    }

    public void setxEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public int getyEnd() {
        return yEnd;
    }

    public void setyEnd(int yEnd) {
        this.yEnd = yEnd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public int getMaxMovement() {
        return maxMovement;
    }

    @Override
    public String toString() {
        return "name: " + name + "  -  x , y: " + x + " , " + y;
    }
}
