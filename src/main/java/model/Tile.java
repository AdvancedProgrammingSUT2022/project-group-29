package model;

import java.util.ArrayList;

public class Tile {
    private int x, y, movementCost;
    private double battleEffect;
    private Unit civilian = null;
    private MilitaryUnit militaryUnit = null;
    private String kind;
    private Feature feature;
    private ArrayList<Resource> possibleResources = new ArrayList<>();
    private Civilization civilization = null;
    private ArrayList<Resource> resources = new ArrayList<>();

    public Tile(int x, int y, String kind) {
        this.x = x;
        this.y = y;
        this.kind = kind;
    }

    public void setCivilian(Unit civilian) {
        this.civilian = civilian;
    }

    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public void setCivilization(Civilization civilization) {
        this.civilization = civilization;
    }
}
