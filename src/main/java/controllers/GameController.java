package controllers;

import models.*;

import java.util.ArrayList;

public class GameController {

    private static GameController instance = null;

    private GameController(){
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }
    private Game game;

    public void startGame(ArrayList<User> users) {
        ArrayList<Tile> map = new ArrayList<>();
        createMap(map);
    }

    private void createMap(ArrayList<Tile> map) {

    }

    public static void showMap() {
    }

    public static void cheatTurn(int turn) {
    }

    public static void cheatGold(int turn) {
    }

    public void setGold(int gold) {
        Civilization civilization = new Civilization(null, null);
        civilization.getGold();
    }

    public void nextTurn() {
        game.nextTurn();
    }

    public Game getGame() {
        return game;
    }

    public void combat(MilitaryUnit militaryUnit, MilitaryUnit militaryUnit1) {

    }

    public void combat(MilitaryUnit militaryUnit, City city) {

    }
}
