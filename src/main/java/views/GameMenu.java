package views;

import controllers.GameController;
import models.Civilization;
import models.Game;
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
        GameController.startGame();
    }

    private void showMap() {
        GameController.showMap();
    }

    private void cheatTurn(int turn) {
        GameController.cheatTurn(turn);
    }

    private void cheatGold(int turn) {
        GameController.cheatGold(turn);
    }

    private void showInfo() {
        ArrayList<Civilization> civilizations = GameController.getGame().getCivilizations();
    }
}
