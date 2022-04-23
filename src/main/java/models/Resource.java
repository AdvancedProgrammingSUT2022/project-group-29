package models;

import java.util.ArrayList;

public class Resource {
    protected String type ;
    protected String name;
    protected int food;
    protected int production;
    protected int gold;
    protected ArrayList<Tile> validTiles = new ArrayList<>();//ToDO Edit ValidTiles in Cunstructor after Feature
    protected Improvement neededImprovement;

    public Resource(String type, String name, int food, int production, int gold,
                     Improvement neededImprovement) {

        this.type = type;
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.neededImprovement = neededImprovement;
    }

}
