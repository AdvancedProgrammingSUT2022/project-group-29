package controllers;

import enums.TerrainsAndFeaturesEnum;
import models.*;

import java.util.ArrayList;
import java.util.Random;

public class GameController {

    private static GameController instance = null;

    private GameController() {
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    private Game game;

    public void startGame(ArrayList<User> users) {
        Tile[][] map = new Tile[30][40];
        createMap(map);
        ArrayList<Civilization> civilizations = new ArrayList<>();
        createCivilizations(civilizations,users);
        game = new Game(civilizations,-4000,map,1);
    }

    private void createMap(Tile[][] map) {
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 40; j++) {
                TerrainAndFeature terrain = addTerrain(random);
                map[i][j] = new Tile(i,j,terrain);
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

    private void createCivilizations(ArrayList<Civilization> civilizations,ArrayList<User> users) {
        for (User user : users) {
            Civilization civilization = new Civilization(user);
            civilizations.add(civilization);
            user.setCivilization(civilization);
        }
    }

    public static void showMap() {
    }

    public static void cheatTurn(int turn) {
    }

    public static void cheatGold(int turn) {
    }

    public void setGold(int gold) {
        Civilization civilization = new Civilization(null);
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
