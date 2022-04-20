package models;

import java.util.ArrayList;

public class Game {
    private ArrayList<Civilization> civilizations = new ArrayList<>();
    private int turn, time;

    public Game(ArrayList<Civilization> civilizations, int time) {
        this.civilizations = civilizations;
        this.time = time;
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
