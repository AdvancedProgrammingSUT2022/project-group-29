package enums;

public enum TerrainsAndFeaturesEnum {
    /*Terrains: */
    DESERT("desert",0,0,0,1,-33),
    GRASSLAND("grassLand",2,0,0,1,-33),
    HILLS("hills",0,2,0,2,+25),
    MOUNTAIN("mountain",0,0,0,-1,0),
    OCEAN("ocean",0,0,0,-1,0),
    PLAINS("plains",1,1,0,1,-33),
    SNOW("snow",0,0,0,1,-33),
    TUNDRA("tundra",1,0,0,1,-33),

    /*Features: */
    FLOODPLAINS("floodPlains",2,0,0,1,-33),
    FOREST("forest",1,1,0,2,+25),
    ICE("ice",0,0,0,-1,0),
    JUNGLE("jungle",1,-1,0,2,+25),
    MARSH("marsh",-1,0,0,2,-33),
    OASIS("oasis",3,0,1,1,-33);

    private String kind;
    private int food;
    private int production;
    private int gold;
    private int movementCost;
    private double battleEffect;

    TerrainsAndFeaturesEnum(String kind, int food, int production, int gold, int movementCost, double battleEffect) {
        this.kind = kind;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.movementCost = movementCost;
        this.battleEffect = battleEffect;
    }

    public String getKind() {
        return kind;
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

    public int getMovementCost() {
        return movementCost;
    }

    public double getBattleEffect() {
        return battleEffect;
    }
}
