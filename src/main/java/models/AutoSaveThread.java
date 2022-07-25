package models;

import controllers.GameController;

import java.util.Date;

public class AutoSaveThread extends Thread{

    private Game game;
    public AutoSaveThread(Game game) {
        this.game = game;
    }

    public void run() {

        long time = new Date().getTime();

        while (true) {
            if (new Date().getTime() > 60000 + time) {
                GameController.getInstance().saveGame();
                time = new Date().getTime();
            }

        }
    }
}