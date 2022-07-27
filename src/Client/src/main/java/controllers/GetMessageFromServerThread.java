package controllers;

import app.Main;
import com.google.gson.Gson;
import models.Game;
import models.Request;
import models.Response;
import views.MapView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GetMessageFromServerThread extends Thread {
    private static Socket update;
    private static DataInputStream inputStreamUpdate;
    private static DataOutputStream outputStreamUpdate;

    public void run() {

        try {
            update = new Socket("localHost", Main.getServerPort());
            inputStreamUpdate = new DataInputStream(update.getInputStream());
            outputStreamUpdate = new DataOutputStream(update.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            String message = null;
            try {
                message = inputStreamUpdate.readUTF();
            } catch (IOException e) {
                System.out.println("Disconnected From Server");
                System.exit(0);
            }
            Request request = new Gson().fromJson(message, Request.class);
            switch (request.getAction()) {
                case "add":
                    Main.changeMenu("accept");
                    break;
                case "start":
                    startGame(request.getParams().get("game"),Integer.parseInt(request.getParams().get("x")),Integer.parseInt(request.getParams().get("y")));
                    break;
                case "end":
                    endGame(request.getParams().get("win"));
            }
        }
    }

    public void endGame(String name) {
        Main.showPopupJustText("winner is :" + name);
        Main.changeMenu("mainPage");
    }
    public void startGame(String game,int x,int y) {
        MapView.setG(game);
        MapView.setXMap(x);
        MapView.setYMap(y);
        Main.changeMenu("mapPage");
    }

    public static void write(String opinion) {
        Response response = null;
        if (opinion.equals("accepted")) {
            response = new Response(1, "accepted");
        }
        else {
            response = new Response(0,"rejected");
            try {
                outputStreamUpdate.writeUTF(new Gson().toJson(response));
                outputStreamUpdate.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            System.out.println(response);
            outputStreamUpdate.writeUTF(new Gson().toJson(response));
            outputStreamUpdate.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
