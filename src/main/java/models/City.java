package models;

import java.util.ArrayList;

public class City {
    private ArrayList<Tile> tiles = new ArrayList<>();

    private Unit civilian = null;
    private MilitaryUnit militaryUnit = null;

    public City(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

}
