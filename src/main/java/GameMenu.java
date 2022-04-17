import java.util.Scanner;

public class GameMenu {
    public void run(Scanner scanner) {

    }

    private void showMap() {
        GameController.showMap();
    }

    private void cheatTurn(int turn) {
        GameController gameController = new GameController();
        gameController.cheatTurn(turn);
    }

    private void cheatGold(int turn) {
        GameController gameController = new GameController();
        gameController.cheatGold(turn);
    }
}
