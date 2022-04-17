import java.util.Scanner;

public class GameMenu {
    public void run(Scanner scanner) {

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
}
