package controllers;

import enums.TerrainsAndFeaturesEnum;
import models.*;

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
        createCivilizations(civilizations,users);
        game = new Game(civilizations,-4000,map,1);
        printMap();
    }

    private void createMap(Tile[][] map) {
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
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
        StringBuilder stringBuilder = new StringBuilder("A new game started between ");
        for (User user : users) {
            Civilization civilization = new Civilization(user);
            civilizations.add(civilization);
            user.setCivilization(civilization);
            stringBuilder.append(user.getUsername()).append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        System.out.println(stringBuilder);
    }

    private void printMap(){
        Tile[][] tiles = game.getMap();

        for (int i = 0; i < LENGTH * 6; i++) {
            for (int j = 0; j < 11 * WIDTH; j++) {

                if (i % 6 == 0) {

                    if (j % 20 >= 2 && j % 20 < 10)
                        System.out.print("=");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 1) {

                    if (j % 20 == 1) {
                        System.out.print("/" + (i / 6 < LENGTH && j / 10 < WIDTH ? tiles[i / 6][j / 10].getTerrain().getColor() : "") +
                                "  " + (i / 6 > 9 ? i / 6 : "0" + i / 6) + "," + (j / 10 > 9 ? j / 10 : "0" + j / 10));
                        j += 7;
                    } else if (j % 20 == 10)
                        System.out.print("\033[000m\\");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 2) {

                    if (j % 20 == 0) {
                        /*String s = GameController.getCivilization(tiles[i / 6][j / 10]);
                        System.out.print("/    " + (i / 6 < x && j / 10 < y ? s : ""));
                        j +=5;*/
                        System.out.print("/");
                    }
                    else if (j % 20 == 11)
                        System.out.print("\\");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 3) {

                    if (j % 20 >= 12)
                        System.out.print("=");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 4) {
                    if (j % 20 == 0)
                        System.out.print("\u001B[0m\\");
                    else if (j % 20 == 11) {
                        System.out.print("/" + (i / 6 < LENGTH && j / 10 < WIDTH ? tiles[i / 6][j / 10].getTerrain().getColor() : "") +
                                "  " + (i / 6 > 9 ? i / 6 : "0" + i / 6) + "," + (j / 10 > 9 ? j / 10 : "0" + j / 10));
                        j += 7;
                    } else
                        System.out.print(" ");

                } else  {

                    if (j % 20 == 1)
                        System.out.print("\\");
                    else if (j % 20 == 10)
                        System.out.print("/");
                    else
                        System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void cheatTurn(int turn) {
    }

    public void cheatGold(int turn) {
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

    public int getLENGTH() {
        return LENGTH;
    }

    public int getWIDTH() {
        return WIDTH;
    }
}
