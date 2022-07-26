package controllers;

import app.Main;
import com.google.gson.Gson;
import controllers.LoginController;
import models.Request;
import models.Response;
import models.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SocketController extends Thread {
    Socket socket;
    User user = null;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public SocketController(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
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
                        response = LoginController.getInstance().loginUser(request.getParams().get("username"), request.getParams().get("password"),this);
                        break;
                    case "enter":
                        response = LoginController.getInstance().enterMenu(request.getParams().get("hash"),user);
                        break;
                    case "logout":
                        response = MainController.getInstance().logout(request.getParams().get("hash"),this);
                        break;
                    case "scoreBoard":
                        response = MainController.getInstance().showScoreBoard(request.getParams().get("hash"));
                        break;
                    case "profile":
                        response = ProfileController.getInstance().showInfo(request.getParams().get("hash"));
                        break;
                    case "username":
                        response = ProfileController.getInstance().changeNickname(request.getParams().get("user"),request.getParams().get("hash"));
                        break;
                    case "pass":
                        response = ProfileController.getInstance().changePassword(request.getParams().get("pass"),request.getParams().get("hash"));
                        break;
                    case "avatar":
                        response = ProfileController.getInstance().changeAvatar(request.getParams().get("hash"),request.getParams().get("path"));
                        break;

                }
                dataOutputStream.writeUTF(new Gson().toJson(response));
                dataOutputStream.flush();
            } catch (IOException e) {
                Main.getSockets().remove(this);
                break;
            }
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}


