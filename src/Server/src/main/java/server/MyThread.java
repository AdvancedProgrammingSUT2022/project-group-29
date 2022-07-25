package server;

import com.google.gson.Gson;
import controllers.LoginController;
import models.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyThread extends Thread {
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    public MyThread(Socket socket) throws IOException {
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
                String response;
                switch (request.getAction()) {
                    case "createUser":
                        response = LoginController.getInstance().createUser(request.getParams().get("username"), request.getParams().get("password"), request.getParams().get("nickname"));
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                    case "login":
                        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
                        final byte[] hashBytes = digest.digest(request.getParams().get("username").getBytes(StandardCharsets.UTF_8));
                        String sha3Hex = bytesToHex(hashBytes);

                        response = LoginController.getInstance().loginUser(request.getParams().get("username"), request.getParams().get("password"), sha3Hex);
                        response += "-" + sha3Hex;
                        dataOutputStream.writeUTF(response);
                        dataOutputStream.flush();
                        break;
                }
            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            break;

        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

