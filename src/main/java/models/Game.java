package models;

import controllers.UnitController;

import java.util.ArrayList;

public class Game {
    private ArrayList<Civilization> civilizations;
    private Civilization currentCivilization;
    private Tile[][] map;
    private int turn;
    private MilitaryUnit selectedCombatUnit = null;
    private Unit selectedNonCombatUnit = null;
    private City selectedCity = null;

    public Game(ArrayList<Civilization> civilizations, Tile[][] map) {
        this.civilizations = civilizations;
        this.map = map;
        this.turn = 0;
    }

    public void nextTurn() {
        UnitController.getInstance().changePlaceAfterTurnAllUnits();
        currentCivilization = civilizations.get(++turn % civilizations.size());
        currentCivilization.increaseScience(3 + currentCivilization.calculatePopulation());
    }

    public int getTurn() {
        return turn;
    }

    public ArrayList<Civilization> getCivilizations() {
        return civilizations;
    }

    public Tile[][] getMap() {
        return map;
    }

    public Civilization getCurrentCivilization() {
        return currentCivilization;
    }

    private void giveColor() {
        for (int i = 0; i < civilizations.size(); i++) {
            civilizations.get(i).setColor("\033[48;5;" + (10*i) +  "m");
        }
    }

    public void setSelectedCombatUnit(MilitaryUnit selectedCombatUnit) {
        this.selectedCombatUnit = selectedCombatUnit;
    }

    public void setSelectedNonCombatUnit(Unit selectedNonCombatUnit) {
        this.selectedNonCombatUnit = selectedNonCombatUnit;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }

    public MilitaryUnit getSelectedCombatUnit() {
        return selectedCombatUnit;
    }

    public Unit getSelectedNonCombatUnit() {
        return selectedNonCombatUnit;
    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setCivilizations(ArrayList<Civilization> civilizations) {
        this.civilizations = civilizations;
        this.currentCivilization = civilizations.get(0);
        giveColor();
    }
}
