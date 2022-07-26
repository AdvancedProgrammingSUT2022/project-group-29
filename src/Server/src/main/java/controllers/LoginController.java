package controllers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Response;
import models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class LoginController {
    private static LoginController instance = null;

    private LoginController() {
    }

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public Response createUser(String username, String password, String nickname) {
        if (username.equals("") || nickname.equals("") || password.equals(""))
            return new Response(0,"complete fields please");
        if (MainController.getInstance().isExistUsername(username) != null)
            return new Response(0,"user with username " + username + " already exists");
        if (ProfileController.getInstance().isExistNickname(nickname) != null)
            return new Response(0,"user with nickname " + nickname + " already exists");
        new User(username, password, nickname);
        writeUserInfo();
        return new Response(1,"user created successfully!");
    }


    public Response loginUser(String username, String password,SocketController socketController) {
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        final byte[] hashBytes = digest.digest(username.getBytes(StandardCharsets.UTF_8));
        String sha3Hex = bytesToHex(hashBytes);

        User user;
        if ((user = MainController.getInstance().isExistUsername(username)) == null)
            return new Response(0,"Username and password didn’t match!");
        if (!user.getPassword().equals(password))
            return new Response(0, "Username and password didn’t match!");
        User.getHash().put(sha3Hex, user);
        socketController.setUser(user);
        return new Response(1,"user logged in successfully!-" + sha3Hex);
    }

    public Response enterMenu(String hash,User user) {
        if (hash.equals(""))
            return new Response(0,"Please Login First");
        else if (!User.getHash().get(hash).equals(user)) {
            return new Response(0,"You Don't Access To Do This");
        }
        else {
            return new Response(1,"Successful");
        }
    }

    public void writeUserInfo() {
        try {
            FileWriter fileWriter = new FileWriter("user.txt");
            fileWriter.write(new Gson().toJson(User.getAllUsers()));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readUserInfo() {
        try {
            String info = new String(Files.readAllBytes(Paths.get("user.txt")));
            User.setAllUsers(new Gson().fromJson(info, new TypeToken<List<User>>(){}.getType()));
            if (User.getAllUsers() == null)
                User.setAllUsers(new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
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
