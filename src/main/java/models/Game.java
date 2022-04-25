package models;

import java.util.ArrayList;

public class Game {
    private ArrayList<Civilization> civilizations;
    private Civilization currentCivilization;
    private int time;
    private Tile[][] map;
    private int turn;

    public Game(ArrayList<Civilization> civilizations, int time, Tile[][] map) {
        this.civilizations = civilizations;
        this.currentCivilization = civilizations.get(0);
        this.time = time;
        this.map = map;
        this.turn = 1;
    }

    public void nextTurn() {
        currentCivilization = civilizations.get(++turn);
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
}
