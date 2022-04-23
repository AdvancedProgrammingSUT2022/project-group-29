package models;

import java.util.ArrayList;

public class Game {
    private ArrayList<Civilization> civilizations;
    private int time;
    private Tile[][] map;
    private int turn;

    public Game(ArrayList<Civilization> civilizations, int time, Tile[][] map, int turn) {
        this.civilizations = civilizations;
        this.time = time;
        this.map = map;
        this.turn = turn;
    }

    public void nextTurn() {
        turn++;
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
}
