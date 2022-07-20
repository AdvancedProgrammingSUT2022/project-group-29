package models;

import enums.modelsEnum.nonCombatUnitsEnum;

public class Unit {
    private final String name;
    private int hp = 10;
    private int x, y;
    private int cost;
    private int movement;
    private final int maxMovement;
    private String combatType;
    private int xEnd, yEnd;
    private String state = "ready";
    private boolean hasDone = false;

    public Unit(nonCombatUnitsEnum type, int x, int y) {
        this.cost = type.getCost();
        this.name = type.getName();
        this.movement = type.getMovement();
        this.maxMovement = type.getMovement();
        this.combatType = type.getCombatType();

        this.x = x;
        this.y = y;
        this.xEnd = -1;
        this.yEnd = -1;
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

    public boolean isHasDone() {
        return hasDone;
    }

    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "name: " + name + "  -  x , y: " + x + " , " + y;
    }
}
