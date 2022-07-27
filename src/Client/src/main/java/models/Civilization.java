package models;

import models.diplomacy.Trade;

import java.util.ArrayList;

public class Civilization {
    private User Leader;
    private String name;
    private final ArrayList<Unit> units = new ArrayList<>();
    private final ArrayList<MilitaryUnit> militaryUnits = new ArrayList<>();
    private final ArrayList<City> cities = new ArrayList<>();
    private City capital;
    private int happiness, gold, science;
    private final ArrayList<Technology> technologies = new ArrayList<>();
    private final ArrayList<Civilization> civilizationsInWar = new ArrayList<>();
    private final ArrayList<Trade> allTrades = new ArrayList<>();
    private ArrayList<String> messages = new ArrayList<>();

    private String color;
    private Technology currentTechnology = null;
    private ArrayList<String> notifications = new ArrayList<>();
    private ArrayList<Resource> luxuryResources = new ArrayList<>();
    private int remainingTurns = -1;

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public ArrayList<MilitaryUnit> getMilitaryUnits() {
        return militaryUnits;
    }

    public void addMilitaryUnit(MilitaryUnit militaryUnit) {
        this.militaryUnits.add(militaryUnit);
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public String leaderName() {
        return this.Leader.getUsername();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Technology> getTechnologies() {
        return technologies;
    }

    public String getColor() {
        return color;
    }

    public City getCapital() {
        return capital;
    }

    public void addCivilianToCity(Unit civilianUnit) {
        units.add(civilianUnit);
    }

    public String getName() {
        return name;
    }

    public Technology getCurrentTechnology() {
        return currentTechnology;
    }

    public void setCurrentTechnology(Technology currentTechnology) {
        this.currentTechnology = currentTechnology;
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public void increaseHappiness(int amount) {
        this.happiness += amount;
    }

    public void decreaseHappiness(int amount) {
        this.happiness += amount;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        int cityGold = 0;
        for (City city : this.cities) {
            cityGold += city.getGold();
        }
        return gold + cityGold;
    }


    public void addTechnology(Technology technology) {
        technologies.add(technology);
    }

    public void increaseScience(int amount) {
        this.science += amount;
    }

    public void decreaseScience(int amount) {
        this.science -= amount;
    }

    public int calculatePopulation() {
        int count = 0;
        for (City city : cities) {
            count += city.getPopulation();
        }
        return count;
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public void addToNotifications(String string) {
        this.notifications.add(string);
    }
    public int getHappiness() {
        int cityHappiness = 0;
        for (City city : this.cities) {
            cityHappiness += city.getHappiness();
        }
        return happiness + cityHappiness;
    }

    public void addLuxuryResource(Resource resource) {
        this.luxuryResources.add(resource);
    }
    public void removeLuxuryResource(Resource resource) {
        this.luxuryResources.remove(resource);
    }

    public boolean hasLuxuryResource(Resource resource) {
        for (Resource luxuryResource : this.luxuryResources) {
            if (luxuryResource.getName().equals(resource.getName()))
                return true;
        }
        return false;
    }

    public int getScience() {
        return science;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }

    public void setRemainingTurns(int remainingTurns) {
        this.remainingTurns = remainingTurns;
    }

    public User getLeader() {
        return Leader;
    }

    public ArrayList<Civilization> getCivilizationsInWar() {
        return civilizationsInWar;
    }

    public ArrayList<Trade> getAllTrades() {
        return allTrades;
    }

    public void addToTrade(Trade trade) {
        allTrades.add(trade);
    }
    public void removeTrade(int id) {
        for (Trade trade : allTrades) {
            if (trade.getId() == id) {
                allTrades.remove(trade);
                return;
            }
        }
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

}
