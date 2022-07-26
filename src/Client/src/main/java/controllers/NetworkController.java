package controllers;

import app.Main;
import com.google.gson.Gson;
import models.*;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class NetworkController {

    private static NetworkController instance = null;

    public static NetworkController getInstance() {
        if (instance == null)
            instance = new NetworkController();
        return instance;
    }

    private NetworkController() {
    }


    private static GetMessageFromServerThread update;
    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;


    public static boolean connect() {
        try {
            socket = new Socket("localhost", Main.getServerPort());
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            update = new GetMessageFromServerThread();
            update.start();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private Response send(Request request) {
        try {
            dataOutputStream.writeUTF(new Gson().toJson(request));
            dataOutputStream.flush();

            String message = dataInputStream.readUTF();
            return new Gson().fromJson(message,Response.class);

        } catch (IOException e) {
            System.out.println("Disconnected From Server");
            System.exit(0);
        }
        return null;
    }


    public String loginUser(String username, String password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", username);
        hashMap.put("password", password);
        Request request = new Request("login", hashMap);
        Response response = send(request);
        if (response.getStatus_code() == 1) {
            Data.getInstance().hash = response.getMessage().split("-")[1];
            response.setMessage(response.getMessage().split("-")[0]);
        }
        return response.getMessage();
    }

    public String createUser(String username, String password, String nickname) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", username);
        hashMap.put("password", password);
        hashMap.put("nickname", nickname);
        Request request = new Request("createUser", hashMap);
        Response response = send(request);
        return response.getMessage();
    }

    public Response enterMenu() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("enter", hashMap);
        return send(request);
    }


    public Response logoutUser() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("logout", hashMap);
        Data.getInstance().hash = "";
        return send(request);
    }

    public Response ScoreBoard() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("scoreBoard", hashMap);
        return send(request);
    }

    public Response profilePage() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("profile", hashMap);
        return send(request);
    }

    public Response changeUsername(String username) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("user",username);
        Request request = new Request("username", hashMap);
        return send(request);
    }

    public Response changePassword(String password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("pass",password);
        Request request = new Request("pass", hashMap);
        return send(request);
    }

    public Response changeAvatar(String path) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("path",path);
        Request request = new Request("avatar", hashMap);
        return send(request);
    }

    public Response getName() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("name", hashMap);
        return send(request);
    }

    public Response addPlayer(String username) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("user",username);
        Request request = new Request("add", hashMap);
        return send(request);
    }

    public Response startGame() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("start", hashMap);
        return send(request);
    }
}

