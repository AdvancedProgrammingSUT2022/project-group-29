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

    public Response getCity(String name) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("name", name);
        Request request = new Request("city", hashMap);
        return send(request);
    }

    public Response createUnit(String name) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("name", name);
        Request request = new Request("unit", hashMap);
        return send(request);
    }

    public Response buyTile(int x,int y) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("x", String.valueOf(x));
        hashMap.put("y", String.valueOf(y));
        Request request = new Request("tile", hashMap);
        return send(request);
    }

    public Response cityAttack(int x,int y) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("x", String.valueOf(x));
        hashMap.put("y", String.valueOf(y));
        Request request = new Request("cityA", hashMap);
        return send(request);
    }

    public Response getCanBuild(String name) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("name", name);
        Request request = new Request("canBuild", hashMap);
        return send(request);
    }

    public Response getBuild(String name) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("name", name);
        Request request = new Request("Building", hashMap);
        return send(request);
    }

    public Response build(String bu,String city) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("name", bu);
        hashMap.put("city",city);
        Request request = new Request("build", hashMap);
        return send(request);
    }

    public Response cheat(String string) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("name", string);
        Request request = new Request("cheat", hashMap);
        return send(request);
    }

    public Response bar() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("bar", hashMap);
        return send(request);
    }

    public Response technology(String type,String name) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("type",type);
        hashMap.put("name", name);
        Request request = new Request("technology", hashMap);
        return send(request);
    }

    public Response nextTurn() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("next", hashMap);
        return send(request);
    }

    public Response showFriends() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("showFriends", hashMap);
        return send(request);
    }

    public Response showRequestProfile(String username) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("user", username);
        Request request = new Request("showRequestProfile", hashMap);
        return send(request);
    }

    public Response addFriend(String username) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("user", username);
        Request request = new Request("addFriend", hashMap);
        return send(request);
    }

    public Response requestSent() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("requestSent", hashMap);
        return send(request);
    }

    public Response requestAsked() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("requestAsked", hashMap);
        return send(request);
    }

    public Response addRequest(String username) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("user", username);
        Request request = new Request("addRequest", hashMap);
        return send(request);
    }

    public void rejectFriend(String username) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        hashMap.put("user", username);
        Request request = new Request("rejectFriend", hashMap);
        send(request);
    }


}

