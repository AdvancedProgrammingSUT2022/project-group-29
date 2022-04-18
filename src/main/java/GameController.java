public class GameController {
    private static Game game;

    public static void startGame() {

    }

    public static void showMap() {
    }

    public static void cheatTurn(int turn) {
    }

    public static void cheatGold(int turn) {
    }

    public void setGold(int gold) {
        Civilization civilization = new Civilization(null, 1, null, null);
        civilization.getGold();
    }

    public void nextTurn() {
        game.nextTurn();
    }

    public static Game getGame() {
        return game;
    }

    public void combat(MilitaryUnit militaryUnit, MilitaryUnit militaryUnit1) {

    }

    public void combat(MilitaryUnit militaryUnit, City city) {

    }
}
