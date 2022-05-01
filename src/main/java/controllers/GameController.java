package controllers;

import enums.TerrainsAndFeaturesEnum;
import models.*;
import views.GameMenu;

import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private static GameController instance = null;
    private final int LENGTH = 30;
    private final int WIDTH = 40;
    private Game game;

    private GameController() {
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    public void startGame(ArrayList<User> users) {
        Tile[][] map = new Tile[LENGTH][WIDTH];
        MapController.getInstance().createMap(map);
        ArrayList<Civilization> civilizations = new ArrayList<>();
        createCivilizations(civilizations, users);
        game = new Game(civilizations, -4000, map);
    }

    private void createCivilizations(ArrayList<Civilization> civilizations, ArrayList<User> users) {
        for (User user : users) {
            Civilization civilization = new Civilization(user);
            civilizations.add(civilization);
            user.setCivilization(civilization);
        }
    }


    public void cheatTurn(int turn) {
    }

    public void cheatGold(int turn) {
    }

    public void setGold(int gold) {

    }

    public Game getGame() {
        return game;
    }

    public void combat(MilitaryUnit militaryUnit, MilitaryUnit militaryUnit1) {

    }

    public void combat(MilitaryUnit militaryUnit, City city) {

    }

    public int getLENGTH() {
        return LENGTH;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public Civilization getCivilization(int x, int y) {
        for (Civilization civilization : game.getCivilizations()) {
            for (Tile tile : civilization.getTiles()) {
                if (tile.getX() == x && tile.getY() == y)
                    return civilization;
            }
        }
        return null;
    }
}
