package models;

import java.util.ArrayList;

public class LuxuryResources extends Resource {
    private static ArrayList<LuxuryResources> allLuxuryResources = new ArrayList<>();

    static {
        allLuxuryResources.add(new LuxuryResources("Luxury", "Cotton", 0, 0, 2,  Improvement.getImprovementByName("Field")));
        allLuxuryResources.add(new LuxuryResources("Luxury", "Dyes", 0, 0, 2,  Improvement.getImprovementByName("Plantation")));
        allLuxuryResources.add(new LuxuryResources("Luxury", "Furs", 0, 0, 2,  Improvement.getImprovementByName("Camp")));
        allLuxuryResources.add(new LuxuryResources("Luxury", "Furs", 0, 0, 2,  Improvement.getImprovementByName("Camp")));
        allLuxuryResources.add(new LuxuryResources("Luxury", "Gems", 0, 0, 3,  Improvement.getImprovementByName("Mine")));
        allLuxuryResources.add(new LuxuryResources("Luxury", "Gold", 0, 0, 2,  Improvement.getImprovementByName("Mine")));
        allLuxuryResources.add(new LuxuryResources("Luxury", "Incense", 0, 0, 2,  Improvement.getImprovementByName("Plantation")));
        allLuxuryResources.add(new LuxuryResources("Luxury", "Ivory", 0, 0, 2,  Improvement.getImprovementByName("Camp")));
        allLuxuryResources.add(new LuxuryResources("Luxury", "Marble", 0, 0, 2,  Improvement.getImprovementByName("Quarry")));
        allLuxuryResources.add(new LuxuryResources("Luxury", "Silk", 0, 0, 2,  Improvement.getImprovementByName("Plantation")));
        allLuxuryResources.add(new LuxuryResources("Luxury", "Silver", 0, 0, 2,  Improvement.getImprovementByName("Mine")));
        allLuxuryResources.add(new LuxuryResources("Luxury", "Sugar", 0, 0, 2,  Improvement.getImprovementByName("Plantation")));
    }
    public LuxuryResources(String type, String name, int food, int production, int gold, Improvement neededImprovement) {
        super(type, name, food, production, gold, neededImprovement);
    }
}
