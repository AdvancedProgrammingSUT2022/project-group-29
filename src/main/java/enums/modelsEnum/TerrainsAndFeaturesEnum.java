package enums.modelsEnum;

public enum TerrainsAndFeaturesEnum {
    /*Terrains: */
    DESERT("desert",0,0,0,1,-33, "\u001B[41m"),
    GRASSLAND("grassLand",2,0,0,1,-33, "\u001B[42m"),
    HILLS("hills",0,2,0,2,+25, "\u001B[40m"),
    MOUNTAIN("mountain",0,0,0,1,0, "\u001B[45m"),
    OCEAN("ocean",0,0,0,1,0, "\u001B[44m"),
    PLAINS("plains",1,1,0,1,-33, "\u001B[43m"),
    SNOW("snow",0,0,0,1,-33, "\u001B[47m"),
    TUNDRA("tundra",1,0,0,1,-33, "\u001B[46m"),

    /*Features: */
    FLOODPLAINS("floodPlains",2,0,0,1,-33, "F"),
    FOREST("forest",1,1,0,2,+25, "R"),
    ICE("ice",0,0,0,1,0, "I"),
    JUNGLE("jungle",1,-1,0,2,+25, "J"),
    MARSH("marsh",-1,0,0,2,-33, "M"),
    OASIS("oasis",3,0,1,1,-33, "O");

    private String kind;
    private int food;
    private int production;
    private int gold;
    private int movementCost;
    private double battleEffect;
    private String color;

    TerrainsAndFeaturesEnum(String kind, int food, int production, int gold, int movementCost, double battleEffect, String color) {
        this.kind = kind;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.movementCost = movementCost;
        this.battleEffect = battleEffect;
        this.color = color;
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

    public String getColor() {
        return color;
    }
}
