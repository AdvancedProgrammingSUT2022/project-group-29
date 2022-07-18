package models;

import enums.modelsEnum.MilitaryUnitsEnum;

public class MilitaryUnit {
    private int x, y;
    private int cost;
    private int hp;
    private int fullHp;
    private String name;
    private int movement;
    private final int maxMovement;
    private String combatType;
    private int combatStrength;
    private int rangedCombatStrength, range;
    private Technology neededTechnology;
    private Resource neededResource;
    private int xEnd, yEnd;
    private String state = "ready";
    private boolean hasDone = false;
    private boolean readySiege = true;

    public MilitaryUnit(MilitaryUnitsEnum type, int x, int y) {
        this.cost = type.getCost();
        this.name = type.getName();
        this.movement = type.getMovement();
        this.maxMovement = type.getMovement();
        this.combatType = type.getCombatType();
        this.combatStrength = type.getCombatStrength();
        this.x = x;
        this.y = y;
        this.rangedCombatStrength = type.getRangedCombatStrength();
        this.range = type.getRange();
        this.hp = type.getHp();
        this.neededTechnology = type.getNeededTechnology();
        this.neededResource = type.getNeededResource();
        this.fullHp = type.getHp();
        this.xEnd = -1;
        this.yEnd = -1;

        if (type.getCombatType().equals("Siege"))
            this.readySiege = false;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "name: " + name + "  -  x , y: " + x + " , " + y + "  state: " + state;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Technology getNeededTechnology() {
        return neededTechnology;
    }

    public Resource getNeededResource() {
        return neededResource;
    }

    public int getFullHp() {
        return fullHp;
    }

    public int getMaxMovement() {
        return maxMovement;
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

    public boolean isHasDone() {
        return hasDone;
    }

    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }

    public boolean isReadySiege() {
        return readySiege;
    }

    public void setReadySiege(boolean readySiege) {
        this.readySiege = readySiege;
    }

    public String getCombatType() {
        return combatType;
    }
}
