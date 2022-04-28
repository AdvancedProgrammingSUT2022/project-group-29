package models;

import java.util.ArrayList;

public class Tile {
    private int x, y;
    private TerrainAndFeature terrain;
    private TerrainAndFeature feature;
    private Resource resource;
    private Unit civilian ;
    private MilitaryUnit militaryUnit ;

    public Tile(int x, int y, TerrainAndFeature terrain, TerrainAndFeature feature, Resource resource) {
        this.x = x;
        this.y = y;
        this.terrain = terrain;
        this.feature = feature;
        this.resource = resource;
    }

    public void setCivilian(Unit civilian) {
        this.civilian = civilian;
    }

    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public TerrainAndFeature getTerrain() {
        return terrain;
    }

    public TerrainAndFeature getFeature() {
        return feature;
    }
}
