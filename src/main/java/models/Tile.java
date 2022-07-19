package models;


import java.util.ArrayList;

public class Tile {
    private int x, y;
    private TerrainAndFeature terrain;
    private TerrainAndFeature feature;
    private Resource resource;
    private Unit civilian;
    private MilitaryUnit militaryUnit;
    private boolean isThereCitizen;
    private boolean hasRoute = false;
    private ArrayList<Tile> neighbourTiles;
    private boolean[] rivers;
    private Improvement improvement;
    private int value = 1;
    private boolean needRepair = false;

    private boolean discoveredRuin = false;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.terrain = null;
        this.feature = null;
        this.resource = null;
        this.neighbourTiles = null;
        this.rivers = new boolean[6];
        for (int i = 0; i < 6; i++) {
            rivers[i] = false;
        }
    }

    public void setTerrain(TerrainAndFeature terrain) {
        this.terrain = terrain;
    }

    public void setFeature(TerrainAndFeature feature) {
        this.feature = feature;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setCivilian(Unit civilian) {
        this.civilian = civilian;
    }

    public void setMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public void setNeighbourTiles(ArrayList<Tile> neighbourTiles) {
        this.neighbourTiles = neighbourTiles;
    }

    public TerrainAndFeature getTerrain() {
        return terrain;
    }

    public TerrainAndFeature getFeature() {
        return feature;
    }

    public Resource getResource() {
        return resource;
    }

    public ArrayList<Tile> getNeighbourTiles() {
        return neighbourTiles;
    }

    public boolean[] getRivers() {
        return rivers;
    }

    public void addRiver(int x) {
        this.rivers[x] = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MilitaryUnit getMilitaryUnit() {
        return militaryUnit;
    }

    public int getMovementCost() {
        return (terrain == null ? 0 : terrain.getMovementCost()) + (feature == null ? 0 : feature.getMovementCost());
    }

    public Unit getCivilian() {
        return civilian;
    }

    public boolean isThereCitizen() {
        return isThereCitizen;
    }

    public void setThereCitizen(boolean thereCitizen) {
        isThereCitizen = thereCitizen;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Improvement getImprovement() {
        return improvement;
    }

    public void setImprovement(Improvement improvement) {
        this.improvement = improvement;
    }

    public int getGold() {
        return (feature == null ? 0 : feature.getGold()) + (terrain == null ? 0 : terrain.getGold());
    }

    public boolean isNeedRepair() {
        return needRepair;
    }

    public void setNeedRepair(boolean needRepair) {
        this.needRepair = needRepair;
    }

    public boolean isHasRoute() {
        return hasRoute;
    }

    public void setHasRoute(boolean hasRoute) {
        this.hasRoute = hasRoute;
    }

    public double getCombatChange() {
        return ((feature == null ? 0 : feature.getBattleEffect()) / 100) + ((terrain == null ? 0 : terrain.getBattleEffect() / 100));
    }

    public int getFood() {
        return (feature == null ? 0 : feature.getFood()) + (terrain == null ? 0 : terrain.getFood());
    }

    public int getProduction() {
        return (feature == null ? 0 : feature.getProduction()) + (terrain == null ? 0 : terrain.getProduction()) + (resource == null ? 0 : resource.getProduction()) + (improvement == null ? 0 : improvement.getProductionChange());
    }

    public boolean isDiscoveredRuin() {
        return discoveredRuin;
    }

    public void setDiscoveredRuin(boolean discoveredRuin) {
        this.discoveredRuin = discoveredRuin;
    }
}
