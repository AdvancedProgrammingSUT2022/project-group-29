package controllers;

import models.*;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameController {
    private static GameController instance = null;
    private final int LENGTH = 45;
    private final int WIDTH = 30;
    private Game game;

    private GameController() {
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    public void startGame(ArrayList<User> users) {
        Tile[][] map = new Tile[WIDTH][LENGTH];
        game = new Game(null, map);
        MapController.getInstance().createMap(map, WIDTH, LENGTH);
        ArrayList<Civilization> civilizations = new ArrayList<>();
        createCivilizations(civilizations, users);
        game.setCivilizations(civilizations);
    }

    private void createCivilizations(ArrayList<Civilization> civilizations, ArrayList<User> users) {
        int x = 5,y = 5;
        for (User user : users) {
            Civilization civilization = new Civilization(user, x, y);
            civilizations.add(civilization);
            user.setCivilization(civilization);
            x += 5;
            y += 5;
        }
    }

    public String cheatTurn(int turn) {
        for (int i = 0; i < turn; i++)
            this.game.nextTurn();
        return null;
    }

    public String cheatGold(int turn) {
        return null;
    }

    public void setGold(int gold) {

    }

    public Game getGame() {
        return game;
    }

    public String combat(Matcher matcher) {
        return null;
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

    public String foundCity(Matcher matcher) {
        return CityController.getInstance().createCity(matcher);
    }

    public String cancelMission() {
        return null;
    }
}
