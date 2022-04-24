package enums;

import models.Improvement;
import models.LuxuryResources;
import models.StrategicResource;

public enum ResourceEnum {
    BANANA("Bonus", "Banana", 1, 0, 0, Improvement.getImprovementByName("Plantation")),
    CATTLE("Bonus", "Cattle", 1, 0, 0, Improvement.getImprovementByName("Pasture")),
    DEER("Bonus", "Deer", 1, 0, 0, Improvement.getImprovementByName("Camp")),
    SHEEP("Bonus", "Sheep", 1, 0, 0, Improvement.getImprovementByName("Pasture")),
    WHEAT("Bonus", "Wheat", 1, 0, 0, Improvement.getImprovementByName("Farm")),

    COTTON("Luxury", "Cotton", 0, 0, 2, Improvement.getImprovementByName("Field")),
    DYES("Luxury", "Dyes", 0, 0, 2, Improvement.getImprovementByName("Plantation")),
    FURS("Luxury", "Furs", 0, 0, 2, Improvement.getImprovementByName("Camp")),
    GEMS("Luxury", "Gems", 0, 0, 3, Improvement.getImprovementByName("Mine")),
    GOLD("Luxury", "Gold", 0, 0, 2, Improvement.getImprovementByName("Mine")),
    INCENSE("Luxury", "Incense", 0, 0, 2, Improvement.getImprovementByName("Plantation")),
    IVORY("Luxury", "Ivory", 0, 0, 2, Improvement.getImprovementByName("Camp")),
    MARBLE("Luxury", "Marble", 0, 0, 2, Improvement.getImprovementByName("Quarry")),
    SILK("Luxury", "Silk", 0, 0, 2, Improvement.getImprovementByName("Plantation")),
    SILVER("Luxury", "Silver", 0, 0, 2, Improvement.getImprovementByName("Mine")),
    SUGAR("Luxury", "Sugar", 0, 0, 2, Improvement.getImprovementByName("Plantation")),

    COAL("Strategic", "Coal", 0, 1, 0, Improvement.getImprovementByName("Mine")),
    HORSES("Strategic", "Horses", 0, 1, 0, Improvement.getImprovementByName("Pasture")),
    IRON("Strategic", "Iron", 0, 1, 0, Improvement.getImprovementByName("Mine"));
    private String type;
    private String name;
    private int food;
    private int production;
    private int gold;
    private Improvement neededImprovement;

    ResourceEnum(String type, String name, int food, int production, int gold, Improvement neededImprovement) {
        this.type = type;
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.neededImprovement = neededImprovement;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getFood() {
        return food;
    }

    public int getProduction() {
        return production;
    }

    public int getGold() {
        return gold;
    }

    public Improvement getNeededImprovement() {
        return neededImprovement;
    }
}
