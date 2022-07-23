package enums.modelsEnum;

import models.Improvement;
import models.Technology;

public enum ResourceEnum {
    /*Bonus Resources*/
    BANANA("Bonus", "Banana", 1, 0, 0, new Improvement(ImprovementsEnum.PLANTATION), null),
    CATTLE("Bonus", "Cattle", 1, 0, 0, new Improvement(ImprovementsEnum.PASTURE), null),
    DEER("Bonus", "Deer", 1, 0, 0, new Improvement(ImprovementsEnum.CAMP), null),
    SHEEP("Bonus", "Sheep", 2, 0, 0, new Improvement(ImprovementsEnum.PASTURE), null),
    WHEAT("Bonus", "Wheat", 1, 0, 0, new Improvement(ImprovementsEnum.FARM), null),

    /*Luxury Resources*/
    COTTON("Luxury", "Cotton", 0, 0, 2, new Improvement(ImprovementsEnum.PLANTATION), null),
    DYES("Luxury", "Dyes", 0, 0, 2, new Improvement(ImprovementsEnum.PLANTATION), null),
    FURS("Luxury", "Furs", 0, 0, 2, new Improvement(ImprovementsEnum.CAMP), null),
    GEMS("Luxury", "Gems", 0, 0, 3, new Improvement(ImprovementsEnum.MINE), null),
    GOLD("Luxury", "Gold", 0, 0, 2, new Improvement(ImprovementsEnum.MINE), null),
    INCENSE("Luxury", "Incense", 0, 0, 2, new Improvement(ImprovementsEnum.PLANTATION), null),
    IVORY("Luxury", "Ivory", 0, 0, 2, new Improvement(ImprovementsEnum.CAMP), null),
    MARBLE("Luxury", "Marble", 0, 0, 2, new Improvement(ImprovementsEnum.QUARRY), null),
    SILK("Luxury", "Silk", 0, 0, 2, new Improvement(ImprovementsEnum.PLANTATION), null),
    SILVER("Luxury", "Silver", 0, 0, 2, new Improvement(ImprovementsEnum.MINE), null),
    SUGAR("Luxury", "Sugar", 0, 0, 2, new Improvement(ImprovementsEnum.PLANTATION), null),

    /*Strategic Resources*/
    COAL("Strategic", "Coal", 0, 1, 0, new Improvement(ImprovementsEnum.MINE), new Technology(TechnologyEnum.SCIENTIFIC_THEORY)),
    HORSES("Strategic", "Horses", 0, 1, 0, new Improvement(ImprovementsEnum.PASTURE), new Technology(TechnologyEnum.ANIMAL_HUSBANDRY)),
    IRON("Strategic", "Iron", 0, 1, 0, new Improvement(ImprovementsEnum.MINE), new Technology(TechnologyEnum.IRON_WORKING));


    private final String type;
    private final String name;
    private final int food;
    private final int production;
    private final int gold;
    private final Improvement neededImprovement;
    private final Technology neededTechnology;

    ResourceEnum(String type, String name, int food, int production, int gold, Improvement neededImprovement, Technology neededTechnology) {
        this.type = type;
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.neededImprovement = neededImprovement;
        this.neededTechnology = neededTechnology;
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

    public Technology getNeededTechnology() {
        return neededTechnology;
    }
}
