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
        createMap(map);
        ArrayList<Civilization> civilizations = new ArrayList<>();
        createCivilizations(civilizations, users);
        game = new Game(civilizations, -4000, map);
    }

    private void createMap(Tile[][] map) {
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                TerrainAndFeature terrain = addTerrain(random);
                map[i][j] = new Tile(i, j, terrain);
            }
        }
    }

    private TerrainAndFeature addTerrain(Random random) {
        int x = random.nextInt() % 8 + 1;
        if (x == 1)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.DESERT);
        if (x == 2)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.GRASSLAND);
        if (x == 3)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.HILLS);
        if (x == 4)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.MOUNTAIN);
        if (x == 5)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.OCEAN);
        if (x == 6)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.PLAINS);
        if (x == 7)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.SNOW);
        return new TerrainAndFeature(TerrainsAndFeaturesEnum.TUNDRA);
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

    public boolean isTerrainVisible(int x, int y) {
        for (City city : game.getCurrentCivilization().getCities()) {
            for (Tile cityTile : city.getTiles()) {
                if (x == cityTile.getX() && (y == cityTile.getY() || y == cityTile.getY() + 1 || y == cityTile.getY() - 1))
                    return true;
                if (y == cityTile.getY() && (x == cityTile.getX() || x == cityTile.getX() + 1 || x == cityTile.getX() - 1))
                    return true;
            }
        }
        for (int i = x - 2; i < x + 2; i++) {
            for (Unit unit : game.getCurrentCivilization().getUnits()) {
                if (game.getMap()[i][y].getCivilian().equals(unit))
                    return true;
            }
        }
        for (int i = y - 2; i < y + 2; i++) {
            for (Unit unit : game.getCurrentCivilization().getUnits()) {
                if (game.getMap()[x][i].getCivilian().equals(unit))
                    return true;
            }
        }

        for (int i = x - 2; i < x + 2; i++) {
            for (MilitaryUnit unit : game.getCurrentCivilization().getMilitaryUnits()) {
                if (game.getMap()[i][y].getMilitaryUnit().equals(unit))
                    return true;
            }
        }
        for (int i = y - 2; i < y + 2; i++) {
            for (MilitaryUnit unit : game.getCurrentCivilization().getMilitaryUnits()) {
                if (game.getMap()[i][y].getMilitaryUnit().equals(unit))
                    return true;
            }
        }

        return false;
    }

    public int getLENGTH() {
        return LENGTH;
    }

    public int getWIDTH() {
        return WIDTH;
    }
}
