package models;

import enums.modelsEnum.MilitaryUnitsEnum;

public class MilitaryUnit{
    private int x, y;
    private int cost;
    private String name;
    private int movement;
    private String combatType;
    private int combatStrength;
    private int rangedCombatStrength, range;
    private Technology neededTechnology;
    private Resource neededResource;
    private int xEnd, yEnd;
    private String state= "";

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

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
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

    @Override
    public String toString() {
        return "name: " + name + "x , y: " + x + " , " + y;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Technology getNeededTechnology() {
        return neededTechnology;
    }

    public void setNeededTechnology(Technology neededTechnology) {
        this.neededTechnology = neededTechnology;
    }

    public Resource getNeededResource() {
        return neededResource;
    }

    public void setNeededResource(Resource neededResource) {
        this.neededResource = neededResource;
    }


}
