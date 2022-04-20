package models;

import java.util.ArrayList;

public class Improvement {
    private String type;
    private Technology neededTechnology;
    private ArrayList<Tile> validTiles = new ArrayList<>();

    public Improvement(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Technology getNeededTechnology() {
        return neededTechnology;
    }

    public ArrayList<Tile> getValidTiles() {
        return validTiles;
    }
}
