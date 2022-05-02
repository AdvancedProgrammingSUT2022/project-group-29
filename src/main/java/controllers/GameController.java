package controllers;

import enums.modelsEnum.ResourceEnum;
import enums.modelsEnum.TerrainsAndFeaturesEnum;
import jdk.internal.org.objectweb.asm.tree.TableSwitchInsnNode;
import models.*;
import org.omg.CORBA.TIMEOUT;

import java.util.ArrayList;
import java.util.Random;

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
        MapController.getInstance().createMap(map,WIDTH,LENGTH);
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

    /*public boolean isTerrainVisible(int x, int y) {
        for (City city : game.getCurrentCivilization().getCities()) {
            if ()
        }
    }*/

    public int getLENGTH() {
        return LENGTH;
    }

    public int getWIDTH() {
        return WIDTH;
    }
}
