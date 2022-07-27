package models;


import java.util.ArrayList;
import java.util.List;

public class City {
    private int allFood = 0;
    private String name;
    private Civilization civilization;
    private int happiness;
    private int gold;
    private int production;
    private int food;
    private double combatStrength;
    private int population;
    private int science;
    private int x, y;
    private Unit civilian = null;
    private int hitPoint;
    private ArrayList<Tile> cityTiles = new ArrayList<>();
    private int citizen;
    private ArrayList<Resource> allResources = new ArrayList<>();

    private ArrayList<Building> canBuild = new ArrayList<>();

    private ArrayList<Building> canTBuild = new ArrayList<>();

    private ArrayList<Building> buildings = new ArrayList<>();


    public void setName(String name) {
        this.name = name;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Tile> getCityTiles() {
        return cityTiles;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Unit getCivilian() {
        return civilian;
    }

    public MilitaryUnit getMilitaryUnit() {
        for (Tile cityTile : this.cityTiles) {
            if (cityTile.getMilitaryUnit() != null)
                return cityTile.getMilitaryUnit();
        }
        return null;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getProduction() {
        return production;
    }

    public int getFood() {
        return food;
    }

    public double getCombatStrength() {
        return combatStrength;
    }

    public int getPopulation() {
        return citizen;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getCitizen() {
        return citizen;
    }

    public void increaseCitizen(int amount) {
        this.citizen += amount;
    }
    public void decreaseCitizen(int amount) {
        this.citizen -= amount;
    }

    public int getScience() {
        return science;
    }

    public void addTileToCity(Tile tile) {
        this.cityTiles.add(tile);
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public ArrayList<Resource> getAllResources() {
        return allResources;
    }

    public void addResource(Resource resource) {
        allResources.add(resource);
    }

    public void removeResource(Resource resource) {
        for (int i = 0; i < allResources.size(); i++) {
            if (allResources.get(i).getName().equals(resource.getName())) {
                allResources.remove(i);
                return;
            }
        }
    }


    public void setPopulation(int population) {
        this.population = population;
    }

    public int getAllFood() {
        return allFood;
    }

    public void setAllFood(int allFood) {
        this.allFood = allFood;
    }

    private boolean haveTech(Building building) {
        Technology tech = building.getNeededTechnology();
        if (tech == null)
            return true;
        for (Technology technology : civilization.getTechnologies()) {
            if (tech.getName().equals(technology.getName()))
                return true;
        }
        return false;
    }

    public ArrayList<Building> getCanBuild() {
        return canBuild;
    }

    public ArrayList<Building> getCanTBuild() {
        return canTBuild;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setCombatStrength(double combatStrength) {
        this.combatStrength = combatStrength;
    }

    public void setProduction(int production) {
        this.production = production;
    }
}
