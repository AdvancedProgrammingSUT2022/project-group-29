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
