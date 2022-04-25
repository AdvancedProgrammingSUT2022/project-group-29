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
        GameController.getInstance().startGame(users);

        showMap();
    }

    private void showMap() {
        Tile[][] tiles = GameController.getInstance().getGame().getMap();
        int x = GameController.getInstance().getLENGTH();
        int y = GameController.getInstance().getWIDTH();
        for (int i = 0; i < x * 6 + 2; i++) {
            for (int i1 = 0; i1 < 10 * y + 1; i1++) {

                if (i % 6 == 0) {

                    if (i1 % 20 >= 2 && i1 % 20 < 10)
                        System.out.print("-");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 1) {

                    if (i1 % 20 == 1) {
                        System.out.print("/" + (i / 6 < x && i1 / 10 < y ? tiles[i / 6][i1 / 10].getTerrain().getColor() : "") + "  " + (i / 6 > 9 ? i / 6 : "0" + i / 6) + "," + (i1 / 10 > 9 ? i1 / 10 : "0" + i1 / 10));
                        i1 += 7;
                    } else if (i1 % 20 == 10)
                        System.out.print("\033[000m\\");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 2) {

                    if (i1 % 20 == 0) {
                        /*String s = GameController.getCivilization(tiles[i / 6][i1 / 10]);
                        System.out.print("/    " + (i / 6 < x && i1 / 10 < y ? s : ""));
                        i1 +=5;*/
                        System.out.print("/");
                    }
                    else if (i1 % 20 == 11)
                        System.out.print("\\");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 3) {

                    if (i1 % 20 >= 12)
                        System.out.print("-");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 4) {
                    if (i1 % 20 == 0)
                        System.out.print("\u001B[0m\\");
                    else if (i1 % 20 == 11) {
                        System.out.print("/" + (i / 6 < x && i1 / 10 < y ? tiles[i / 6][i1 / 10].getTerrain().getColor() : "") + "  " + (i / 6 > 9 ? i / 6 : "0" + i / 6) + "," + (i1 / 10 > 9 ? i1 / 10 : "0" + i1 / 10));
                        i1 += 7;
                    } else
                        System.out.print(" ");

                } else if (i % 6 == 5) {

                    if (i1 % 20 == 1)
                        System.out.print("\\");
                    else if (i1 % 20 == 10)
                        System.out.print("/");
                    else
                        System.out.print(" ");

                }
            }
            System.out.println();
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
