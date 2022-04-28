package enums.modelsEnum;

import enums.modelsEnum.ResourceEnum;
import enums.modelsEnum.TechnologyEnum;
import models.Resource;
import models.Technology;

public enum MilitaryUnitsEnum {
    ARCHER("Ancient", "Archer", 70, "Archery", 4, 6, 2, 2, null, new Technology(TechnologyEnum.ARCHERY)),
    CHARIOT_ARCHER("Ancient", "ChariotArcher", 60, "Mounted", 3, 6, 2, 4, new Resource(ResourceEnum.HORSES), new Technology(TechnologyEnum.THE_WHEEL)),
    SCOUT("Ancient", "Scout", 25, "Recon", 4, 0, 0, 2, null, null),
    SPEARMAN("Ancient", "Spearman", 50, "Melee", 7, 0, 0, 2, null, new Technology(TechnologyEnum.BRONZE_WORKING)),
    WARRIOR("Ancient", "Warrior", 40, "Melee", 6, 0, 0, 2, null, null),

    CATAPULT("Classical", "Catapult", 100, "Siege", 4, 14, 2, 2, new Resource(ResourceEnum.IRON), new Technology(TechnologyEnum.MATHEMATICS)),
    HORSEMAN("Classical", "HorseMan", 80, "Mounted", 12, 0, 0, 4, new Resource(ResourceEnum.HORSES), new Technology(TechnologyEnum.HORSEBACK_RIDING)),
    SWORDSMAN("Classical", "Swordsman", 80, "Melee", 11, 0, 0, 2, new Resource(ResourceEnum.IRON), new Technology(TechnologyEnum.IRON_WORKING)),

    CROSSBOWMAN("Medieval", "Crossbowman", 120, "Archery", 6, 12, 2, 2, null, new Technology(TechnologyEnum.MACHINERY)),
    KNIGHT("Medieval", "knight", 150, "Mounted", 18, 0, 0, 3, new Resource(ResourceEnum.HORSES), new Technology(TechnologyEnum.CHIVALRY)),
    LONGSWORDSMAN("Medieval", "Longswordsman", 150, "Melee", 18, 0, 0, 3, new Resource(ResourceEnum.IRON), new Technology(TechnologyEnum.STEEL)),
    PIKEMAN("Medieval", "Pikeman", 100, "Melee", 10, 0, 0, 2, null, new Technology(TechnologyEnum.CIVIL_SERVICE)),
    TREBUCHET("Medieval", "Trebuchet", 170, "Siege", 6, 20, 2, 2, new Resource(ResourceEnum.IRON), new Technology(TechnologyEnum.PHYSICS)),

    CANON("Renaissance", "Canon", 250, "Siege", 10, 26, 2, 2, null, new Technology(TechnologyEnum.CHEMISTRY)),
    CAVALRY("Renaissance", "Cavalry", 260, "Mounted", 25, 0, 0, 3, new Resource(ResourceEnum.HORSES), new Technology(TechnologyEnum.MILITARY_SCIENCE)),
    LANCER("Renaissance", "Lancer", 220, "Mounted", 22, 0, 0, 4, new Resource(ResourceEnum.HORSES), new Technology(TechnologyEnum.METALLURGY)),
    MUSKETMAN("Renaissance", "Musketman", 120, "Gunpowder", 16, 0, 0, 2, null, new Technology(TechnologyEnum.GUNPOWDER)),
    RIFLEMAN("Renaissance", "Rifleman", 200, "Gunpowder", 25, 0, 0, 2, null, new Technology(TechnologyEnum.RIFLING)),

    ANTI_TANK_GUN("Industrial", "Anti-TankGun", 300, "Gunpowder", 32, 0, 0, 2, null, new Technology(TechnologyEnum.REPLACEABLE_PARTS)),
    ARTILLERY("Industrial", "Artillery", 420, "Siege", 16, 32, 3, 2, null, new Technology(TechnologyEnum.DYNAMITE)),
    INFANTRY("Industrial", "Infantry", 300, "Gunpowder", 36, 0, 0, 2, null, new Technology(TechnologyEnum.REPLACEABLE_PARTS)),
    PANZER("Industrial", "Panzer", 450, "Armored", 60, 0, 0, 5, null, new Technology(TechnologyEnum.COMBUSTION)),
    TANK("Industrial", "Tank", 450, "Armored", 50, 0, 0, 4, null, new Technology(TechnologyEnum.COMBUSTION));

    private String era;
    private String name;
    private int cost;
    private String combatType;
    private int combatStrength;
    private int rangedCombatStrength;
    private int range;
    private int movement;
    private Resource neededResource;
    private Technology neededTechnology;

    MilitaryUnitsEnum(String era, String name, int cost, String combatType, int combatStrength, int rangedCombatStrength, int range, int movement, Resource neededResource, Technology neededTechnology) {
        this.era = era;
        this.name = name;
        this.cost = cost;
        this.combatType = combatType;
        this.combatStrength = combatStrength;
        this.rangedCombatStrength = rangedCombatStrength;
        this.range = range;
        this.movement = movement;
        this.neededResource = neededResource;
        this.neededTechnology = neededTechnology;
    }

    public String getEra() {
        return era;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getCombatType() {
        return combatType;
    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public int getRangedCombatStrength() {
        return rangedCombatStrength;
    }

    public int getRange() {
        return range;
    }

    public int getMovement() {
        return movement;
    }

    public Resource getNeededResource() {
        return neededResource;
    }

    public Technology getNeededTechnology() {
        return neededTechnology;
    }
}
