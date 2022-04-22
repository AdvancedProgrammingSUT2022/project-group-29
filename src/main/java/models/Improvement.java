package models;

import java.util.ArrayList;

public class Improvement {
    private String type;
    private String name;
    private Technology neededTechnology;
    private ArrayList<Tile> validTiles = new ArrayList<>();
    private static ArrayList<Improvement> allImprovements = new ArrayList<>();

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

    public static Improvement getImprovementByName(String name) {
        for (Improvement improvement : allImprovements) {
            if (name.equals(improvement.name))
                return improvement;
        }
        return null;
    }
}
