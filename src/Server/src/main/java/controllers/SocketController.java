package controllers;

import app.*;
import com.google.gson.Gson;
import models.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketController extends Thread {
    private static int state = 0;
    private Socket socket;
    private User user = null;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Boolean isUpdater;
    private int status;

    public SocketController(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.status = state;
        if (state % 2 == 0)
            isUpdater = false;
        else
            isUpdater = true;
        state++;
    }

    @Override
    public void run() {
        if (!isUpdater) {
            while (true) {
                try {
                    String json = dataInputStream.readUTF();
                    Request request = new Gson().fromJson(json, Request.class);
                    Response response = null;
                    switch (request.getAction()) {
                        case "createUser":
                            response = LoginController.getInstance().createUser(request.getParams().get("username"), request.getParams().get("password"), request.getParams().get("nickname"));
                            break;
                        case "login":
                            response = LoginController.getInstance().loginUser(request.getParams().get("username"), request.getParams().get("password"), this);
                            break;
                        case "enter":
                            response = LoginController.getInstance().enterMenu(request.getParams().get("hash"), user);
                            break;
                        case "logout":
                            response = MainController.getInstance().logout(request.getParams().get("hash"), this);
                            break;
                        case "scoreBoard":
                            response = MainController.getInstance().showScoreBoard(request.getParams().get("hash"));
                            break;
                        case "profile":
                            response = ProfileController.getInstance().showInfo(request.getParams().get("hash"));
                            break;
                        case "username":
                            response = ProfileController.getInstance().changeNickname(request.getParams().get("user"), request.getParams().get("hash"));
                            break;
                        case "pass":
                            response = ProfileController.getInstance().changePassword(request.getParams().get("pass"), request.getParams().get("hash"));
                            break;
                        case "avatar":
                            response = ProfileController.getInstance().changeAvatar(request.getParams().get("hash"), request.getParams().get("path"));
                            break;
                        case "name":
                            response = GameController.getInstance().initGame(request.getParams().get("hash"));
                            break;
                        case "add":
                            response = GameController.getInstance().addUserToGame(request.getParams().get("user"));
                            break;
                        case "addRequest":
                            response = GameController.getInstance().addRequest(request.getParams().get("hash"), request.getParams().get("user"));
                            break;
                        case "showFriends":
                            response = ProfileController.getInstance().showFriends(request.getParams().get("hash"));
                            break;
                        case "showRequestProfile":
                            response = ProfileController.getInstance().showRequestFriend(request.getParams().get("user"));
                            break;
                        case "requestSent":
                            response = GameController.getInstance().requestSent(request.getParams().get("hash"));
                            break;
                        case "requestAsked":
                            response = GameController.getInstance().requestAsked(request.getParams().get("hash"));
                            break;
                        case "addFriend":
                            response = GameController.getInstance().addFriend(request.getParams().get("hash"), request.getParams().get("user"));
                            break;
                        case "rejectFriend":
                            response = GameController.getInstance().rejectFriend(request.getParams().get("hash"), request.getParams().get("user"));
                            break;
                    }
                    dataOutputStream.writeUTF(new Gson().toJson(response));
                    dataOutputStream.flush();
                } catch (IOException e) {
                    for (SocketController socketController : Main.getSockets()) {
                        if (socketController.getStatus() == this.getStatus() + 1) {
                            Main.getSockets().remove(socketController);
                            break;
                        }
                    }
                    for (SocketController socketController : Main.getSockets()) {
                        if (socketController.getStatus() == this.getStatus()) {
                            Main.getSockets().remove(socketController);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public Boolean getUpdater() {
        return isUpdater;
    }

    public int getStatus() {
        return status;
    }
}


