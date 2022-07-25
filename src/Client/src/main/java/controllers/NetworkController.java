package controllers;

import app.Main;
import com.google.gson.Gson;
import models.Data;
import models.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class NetworkController {
    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;

    public static boolean connect() {
        try {
            socket = new Socket("localhost", Main.getServerPort());
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String loginUser(String username, String password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", username);
        hashMap.put("password", password);
        Request request = new Request("login", hashMap);
        String response = null;
        try {
            dataOutputStream.writeUTF(new Gson().toJson(request));
            dataOutputStream.flush();
            response = dataInputStream.readUTF();
            if (response.startsWith("user "))
                Data.getInstance().hash = response.split("-")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String createUser(String username, String password, String nickname) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("username", username);
        hashMap.put("password", password);
        hashMap.put("nickname", nickname);
        Request request = new Request("createUser", hashMap);
        String response = null;
        try {
            dataOutputStream.writeUTF(new Gson().toJson(request));
            dataOutputStream.flush();
            response = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String enterMenu() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("enter", hashMap);
        String response = null;
        try {
            dataOutputStream.writeUTF(new Gson().toJson(request));
            dataOutputStream.flush();
            response = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String logoutUser() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("logout", hashMap);
        String response = null;
        try {
            dataOutputStream.writeUTF(new Gson().toJson(request));
            dataOutputStream.flush();
            response = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static void profilePage() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hash", Data.getInstance().hash);
        Request request = new Request("profile", hashMap);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(request));
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
