package models;

import java.util.ArrayList;

public class Resource {
    protected String type;
    protected int food;
    protected int production;
    protected int gold;
    protected ArrayList<Tile> validTiles = new ArrayList<>();
    protected Improvement neededImprovement;

    public Resource(String type) {
        this.type = type;
    }

}
