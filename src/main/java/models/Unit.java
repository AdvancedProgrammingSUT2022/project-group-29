package models;

public class Unit {
    protected int cost;
    protected String type;
    protected int movement;
    protected String combatType;

    public Unit(String type) {
        this.type = type;
    }
}
