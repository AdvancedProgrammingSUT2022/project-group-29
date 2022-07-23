package controllers;

import app.Main;
import com.google.gson.Gson;
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
            response = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
