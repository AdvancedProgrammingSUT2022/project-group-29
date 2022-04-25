package views;

import controllers.GameController;
import models.Civilization;
import models.Game;
import models.Tile;
import models.User;

import java.util.ArrayList;
import java.util.Scanner;

public class GameMenu {
    private static GameMenu instance = null;

    private GameMenu() {
    }

    public static GameMenu getInstance() {
        if (instance == null)
            instance = new GameMenu();
        return instance;
    }



    public void run(Scanner scanner, ArrayList <User> users) {
        printGameStarted(users);

        GameController.getInstance().startGame(users);

        showMap();
    }

    private void printGameStarted(ArrayList <User> users) {
        StringBuilder stringBuilder = new StringBuilder("A new game started between ");
        for (User user : users) {
            stringBuilder.append(user.getUsername()).append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        System.out.println(stringBuilder);
    }

    private void showMap() {
        Tile[][] tiles = GameController.getInstance().getGame().getMap();
        int x = GameController.getInstance().getLENGTH();
        int y = GameController.getInstance().getWIDTH();
        for (int i = 0; i < x * 6 + 2; i++) {
            for (int j = 0; j < 10 * y + 1; j++) {

                if (i % 6 == 0) {

                    if (j % 20 >= 2 && j % 20 < 10)
                        System.out.print("-");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 1) {

                    if (j % 20 == 1) {
                        String color;
                        if (GameController.getInstance().isTerrainVisible(i / 6, j / 10))
                            color = (i / 6 < x && j / 10 < y ? tiles[i / 6][j / 10].getTerrain().getColor() : "");
                        else
                            color = (i / 6 < x && j / 10 < y ? "\033[48;5;250m" : "");
                        System.out.print("/" + color + "  " + (i / 6 > 9 ? i / 6 : "0" + i / 6) + "," + (j / 10 > 9 ? j / 10 : "0" + j / 10));
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

                    } else if (j % 20 == 11)

                        System.out.print("\\");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 3) {

                    if (j % 20 >= 12)
                        System.out.print("-");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 4) {
                    if (j % 20 == 0)
                        System.out.print("\u001B[0m\\");
                    else if (j % 20 == 11) {
                        String color;
                        if (GameController.getInstance().isTerrainVisible(i / 6, j / 10))
                            color = (i / 6 < x && j / 10 < y ? tiles[i / 6][j / 10].getTerrain().getColor() : "");
                        else
                            color = (i / 6 < x && j / 10 < y ? "\033[48;5;250m" : "");
                        System.out.print("/" + color + "  " + (i / 6 > 9 ? i / 6 : "0" + i / 6) + "," + (j / 10 > 9 ? j / 10 : "0" + j / 10));
                        j += 7;
                    } else
                        System.out.print(" ");

                } else if (i % 6 == 5) {

                    if (j % 20 == 1)
                        System.out.print("\\");
                    else if (j % 20 == 10)
                        System.out.print("/");
                    else
                        System.out.print(" ");
                }
            }
        }
    }

    private void cheatTurn(int turn) {
        GameController.getInstance().cheatTurn(turn);
    }

    private void cheatGold(int turn) {
        GameController.getInstance().cheatGold(turn);
    }

    private void showInfo() {
        ArrayList<Civilization> civilizations = GameController.getInstance().getGame().getCivilizations();
    }
}
