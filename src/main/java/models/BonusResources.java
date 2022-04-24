package models;

import java.util.ArrayList;

public class BonusResources extends Resource {
    private static ArrayList<BonusResources> allBonusResources = new ArrayList<>();
    //TODO in constructor Feature validTile

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
