package models;

import java.util.ArrayList;

public class BonusResources extends Resource {
    private static ArrayList<BonusResources> allBonusResources = new ArrayList<>();
    //TODO in cunstrutor Feature validtiles
    static {
        allBonusResources.add(new BonusResources("Bonus", "Banana", 1, 0, 0,  Improvement.getImprovementByName("Plantation")));
        allBonusResources.add(new BonusResources("Bonus", "Cattle", 1, 0, 0,  Improvement.getImprovementByName("Pasture")));
        allBonusResources.add(new BonusResources("Bonus", "Deer", 1, 0, 0,  Improvement.getImprovementByName("Camp")));
        allBonusResources.add(new BonusResources("Bonus", "Sheep", 1, 0, 0,  Improvement.getImprovementByName("Pasture")));
        allBonusResources.add(new BonusResources("Bonus", "Wheat", 1, 0, 0,  Improvement.getImprovementByName("Farm")));
    }

    public BonusResources(String type, String name, int food, int production, int gold, Improvement neededImprovement) {
        super(type, name, food, production, gold, neededImprovement);
    }

    public static BonusResources getBonusResourcesByName(String name) {
        for (BonusResources bonus : allBonusResources) {
            if (name.equals(bonus.name))
                return bonus;
        }
        return null;
    }
}
