import java.util.ArrayList;

public class Game {
    private ArrayList<Civilization> civilizations = new ArrayList<>();
    private int turn, time;

    public Game(ArrayList<Civilization> civilizations, int time) {
        this.civilizations = civilizations;
        this.time = time;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
