package models;

import java.util.ArrayList;

public class Game {
    private ArrayList<Civilization> civilizations;
    private Civilization currentCivilization;
    private int time;
    private Tile[][] map;
    private int turn;
    private MilitaryUnit selectedCombatUnit = null;
    private Unit selectedNonCombatUnit = null;
    private City selectedCity = null;

    public Game(ArrayList<Civilization> civilizations, int time, Tile[][] map) {
        this.civilizations = civilizations;
        this.currentCivilization = civilizations.get(0);
        this.time = time;
        this.map = map;
        this.turn = 0;
        giveColor();
    }

    public void nextTurn() {
        currentCivilization = civilizations.get(++turn % civilizations.size());
    }

    public int getTurn() {
        return turn;
    }

    public int getTime() {
        return time;
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
        String color = "\033[48;5;0m";
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
}
