package enums;

import models.Improvement;
import models.LuxuryResources;
import models.StrategicResource;

public enum ResourceEnum {
    BANANA("Bonus", "Banana", 1, 0, 0, new Improvement(ImprovementsEnum.PLANTATION)),
    CATTLE("Bonus", "Cattle", 1, 0, 0, new Improvement(ImprovementsEnum.PASTURE)),
    DEER("Bonus", "Deer", 1, 0, 0,  new Improvement(ImprovementsEnum.CAMP)),
    SHEEP("Bonus", "Sheep", 1, 0, 0, new Improvement(ImprovementsEnum.PASTURE)),
    WHEAT("Bonus", "Wheat", 1, 0, 0, new Improvement(ImprovementsEnum.FARM)),

    COTTON("Luxury", "Cotton", 0, 0, 2, new Improvement(ImprovementsEnum.PLANTATION)),
    DYES("Luxury", "Dyes", 0, 0, 2, new Improvement(ImprovementsEnum.PLANTATION)),
    FURS("Luxury", "Furs", 0, 0, 2, new Improvement(ImprovementsEnum.CAMP)),
    GEMS("Luxury", "Gems", 0, 0, 3, new Improvement(ImprovementsEnum.MINE)),
    GOLD("Luxury", "Gold", 0, 0, 2,  new Improvement(ImprovementsEnum.MINE)),
    INCENSE("Luxury", "Incense", 0, 0, 2, new Improvement(ImprovementsEnum.PLANTATION)),
    IVORY("Luxury", "Ivory", 0, 0, 2, new Improvement(ImprovementsEnum.CAMP)),
    MARBLE("Luxury", "Marble", 0, 0, 2, new Improvement(ImprovementsEnum.QUARRY)),
    SILK("Luxury", "Silk", 0, 0, 2, new Improvement(ImprovementsEnum.PLANTATION)),
    SILVER("Luxury", "Silver", 0, 0, 2, new Improvement(ImprovementsEnum.MINE)),
    SUGAR("Luxury", "Sugar", 0, 0, 2, new Improvement(ImprovementsEnum.PLANTATION)),

    COAL("Strategic", "Coal", 0, 1, 0,  new Improvement(ImprovementsEnum.MINE)),
    HORSES("Strategic", "Horses", 0, 1, 0, new Improvement(ImprovementsEnum.PASTURE)),
    IRON("Strategic", "Iron", 0, 1, 0,  new Improvement(ImprovementsEnum.MINE));
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
