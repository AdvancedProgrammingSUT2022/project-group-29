package enums.modelsEnum;

import models.Building;
import models.Technology;

public enum BuildingsEnum {
    BARRACKS("Barracks",80,1,new Technology(TechnologyEnum.BRONZE_WORKING),null),
    GRANARY("Granary",100,1,new Technology(TechnologyEnum.POTTERY),null),
    LIBRARY("Library",100,1,new Technology(TechnologyEnum.WRITING),null),
    MONUMENT("Monument",60,1,null,null),
    WALLS("Walls",100,1,new Technology(TechnologyEnum.MASONRY),null),
    WATER_MILL("Water Mill",120,2,new Technology(TechnologyEnum.THE_WHEEL),null),
    ARMORY("Armory",130,3,new Technology(TechnologyEnum.IRON_WORKING),new Building(BARRACKS)),
    BURIAL_TOMB("Burial Tomb",120,0,new Technology(TechnologyEnum.PHILOSOPHY),null),
    CIRCUS("Circus",150,3,new Technology(TechnologyEnum.HORSEBACK_RIDING),null),
    COLOSSEUM("Colosseum",150,3,new Technology(TechnologyEnum.CONSTRUCTION),null),
    COURTHOUSE("Courthouse",200,5,new Technology(TechnologyEnum.MATHEMATICS),null),
    STABLE("Stable",100,1,new Technology(TechnologyEnum.HORSEBACK_RIDING),null),
    TEMPLE("temple",120,2,new Technology(TechnologyEnum.PHILOSOPHY),new Building(MONUMENT)),
    CASTLE("Castle",200,3,new Technology(TechnologyEnum.CHIVALRY),new Building(WALLS)),
    FORGE("Forge",150,2,new Technology(TechnologyEnum.METAL_CASTING),null),
    GARDEN("Garden",120,2,new Technology(TechnologyEnum.THEOLOGY),null),
    MARKET("Market",120,0,new Technology(TechnologyEnum.CURRENCY),null),
    MINT("Mint",120,0,new Technology(TechnologyEnum.CURRENCY),null),
    MONASTERY("Monastery",120,2,new Technology(TechnologyEnum.THEOLOGY),null),
    UNIVERSITY("University",200,3,new Technology(TechnologyEnum.EDUCATION),new Building(LIBRARY)),
    WORKSHOP("Workshop",100,2,new Technology(TechnologyEnum.METAL_CASTING),null),
    BANK("Bank",220,0,new Technology(TechnologyEnum.BANKING),new Building(MARKET)),
    MILITARY_ACADEMY("Military Academy",350,3,new Technology(TechnologyEnum.MILITARY_SCIENCE),new Building(BARRACKS)),
    OPERA_HOUSE("Opera House",220,3,new Technology(TechnologyEnum.ACOUSTICS),new Building(TEMPLE)),
    MUSEUM("Museum",350,3,new Technology(TechnologyEnum.ARCHAEOLOGY),new Building(OPERA_HOUSE)),
    PUBLIC_SCHOOL("Public School",350,3,new Technology(TechnologyEnum.SCIENTIFIC_THEORY),new Building(UNIVERSITY)),
    SATRAP_COURT("Satrap's Court",220,0,new Technology(TechnologyEnum.BANKING),new Building(MARKET)),
    THEATER("Theater",300,5,new Technology(TechnologyEnum.PRINTING_PRESS),new Building(COLOSSEUM)),
    WINDMILL("Windmill",180,2,new Technology(TechnologyEnum.ECONOMICS),null),
    ARSENAL("Arsenal",350,3,new Technology(TechnologyEnum.RAILROAD),new Building(MILITARY_ACADEMY)),
    BROADCAST_TOWER("Broadcast Tower",600,3,new Technology(TechnologyEnum.RADIO),new Building(MUSEUM)),
    FACTORY("Factory",300,3,new Technology(TechnologyEnum.STEAM_POWER),null),
    HOSPITAL("Hospital",400,2,new Technology(TechnologyEnum.BIOLOGY),null),
    MILITARY_BASE("Military Base",450,4,new Technology(TechnologyEnum.TELEGRAPH),new Building(CASTLE)),
    STOCK_EXCHANGE("Stock Exchange",650,0,new Technology(TechnologyEnum.ELECTRICITY),new Building(BANK));

    private String name;
    private int cost;
    private int maintenance;
    private Technology neededTechnology;
    private Building neededBuilding;


    BuildingsEnum(String name, int cost, int maintenance, Technology neededTechnology, Building neededBuilding) {
        this.name = name;
        this.cost = cost;
        this.maintenance = maintenance;
        this.neededTechnology = neededTechnology;
        this.neededBuilding = neededBuilding;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public Technology getNeededTechnology() {
        return neededTechnology;
    }

    public Building getNeededBuilding() {
        return neededBuilding;
    }
}
