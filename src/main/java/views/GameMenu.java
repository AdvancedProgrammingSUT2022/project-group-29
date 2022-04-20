package views;

import controllers.GameController;
import models.Civilization;

import java.util.ArrayList;
import java.util.Scanner;

public class GameMenu {
    public void run(Scanner scanner) {
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
