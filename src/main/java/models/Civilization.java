package models;

import controllers.GameController;
import enums.modelsEnum.*;
import models.diplomacy.Trade;

import java.util.ArrayList;

public class Civilization {
    private final User Leader;
    private final String name;
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

    public Civilization(User leader, int x, int y) {
        Leader = leader;
        name = leader.getNickname();
        this.happiness = 5;
        MilitaryUnit militaryUnit = new MilitaryUnit(MilitaryUnitsEnum.WARRIOR, x, y);
        militaryUnits.add(militaryUnit);
        GameController.getInstance().getGame().getMap()[x][y].setMilitaryUnit(militaryUnit);

        Unit unit = new Unit(nonCombatUnitsEnum.SETTLER, x + 1, y + 1);
        units.add(unit);
        GameController.getInstance().getGame().getMap()[x + 1][y + 1].setCivilian(unit);
    }

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

    public void deleteMilitaryUnit(int x, int y) {
        GameController.getInstance().getGame().getMap()[x][y].setMilitaryUnit(null);
        for (int i = 0; i < militaryUnits.size(); i++) {
            if (militaryUnits.get(i).getY() == y && militaryUnits.get(i).getX() == x) {
                militaryUnits.remove(i);
                return;
            }
        }
    }

    public void deleteNonMilitaryUnit(int x, int y) {
        GameController.getInstance().getGame().getMap()[x][y].setCivilian(null);
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getX() == x && units.get(i).getY() == y) {
                units.remove(i);
                return;
            }
        }
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

    public ArrayList<Technology> getAvailableTechnology() {
        ArrayList<Technology> availableTechnology = new ArrayList<>();
        outer:
        for (TechnologyEnum technology : Technology.getAllTechnologies()) {
            for (Technology technology1 : this.technologies) {
                if (technology.getName().equals(technology1.getName()))
                    continue outer;
            }
            boolean flag = true;
            for (Technology neededTechnology : technology.getNeededTechnologies()) {
                boolean flag1 = false;
                for (Technology technology1 : this.technologies) {
                    if (neededTechnology.getName().equals(technology1.getName())) {
                        flag1 = true;
                        break;
                    }
                }
                if (!flag1)
                    flag = false;
            }
            if (flag)
                availableTechnology.add(new Technology(technology));
        }
        return availableTechnology;
    }

    public boolean isExistTechnology(String name) {
        for (Technology technology : getAvailableTechnology()) {
            if (technology.getName().equals(name))
                return true;
        }
        return false;
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

    public ArrayList<Improvement> getAvailableImprovements() {
        ArrayList<Improvement> availableImprovement = new ArrayList<>();

        for (ImprovementsEnum improvement : Improvement.getAllImprovements()) {
            for (Technology technology : this.technologies) {
                if (improvement.getNeededTechnology().getName().equals(technology.getName()))
                    availableImprovement.add(new Improvement(improvement));
            }
        }
        return availableImprovement;
    }

    public boolean isExistImprovement(String name) {
        for (Improvement availableImprovement : getAvailableImprovements()) {
            if (availableImprovement.getName().equals(name))
                return true;
        }
        return false;
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
