package models;

import java.util.ArrayList;

public class LuxuryResources extends Resource {
    private static ArrayList<LuxuryResources> allLuxuryResources = new ArrayList<>();
    /*TODO First Happiness = 4
     */

    public LuxuryResources(String type, String name, int food, int production, int gold, Improvement neededImprovement) {
        super(type, name, food, production, gold, neededImprovement);
    }
}
