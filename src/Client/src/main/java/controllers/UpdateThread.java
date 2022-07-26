package controllers;

import app.Main;

import java.util.Date;

public class UpdateThread extends Thread{
    public void run() {
        long time = new Date().getTime();
        while (true) {
            if (new Date().getTime() > time + 10000) {
                time = new Date().getTime();
                if (Main.getLastPage().equals("scoreBoardPage"))
                    Main.changeMenu("scoreBoardPage");
            }
        }
    }
}
