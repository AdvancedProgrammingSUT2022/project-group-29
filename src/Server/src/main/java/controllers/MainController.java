package controllers;

import app.Main;
import models.Response;
import models.User;
import java.util.ArrayList;

public class MainController {
    private static MainController instance = null;

    private MainController(){
    }

    public static MainController getInstance() {
        if (instance == null)
            instance = new MainController();
        return instance;
    }

    public Response logout(String hash,SocketController socketController) {
        socketController.setUser(null);
        User.getHash().remove(hash);
        return new Response(1,"user logged out successfully!");
    }

    public Response showScoreBoard(String hash) {
        ArrayList<User> sortedUser = new ArrayList<>(User.getAllUsers());
        sortedUser.sort((o1, o2) -> {
            if (o1.getScore() > o2.getScore()) return -1;
            if (o1.getLastVisit() > o2.getLastVisit()) return -1;

            return o1.getNickname().compareTo(o2.getNickname());
        });

        StringBuilder str = new StringBuilder();
        str.append(sortedUser.size()).append("-");
        outer:
        for (User user : sortedUser) {
            if (User.getHash().get(hash).equals(user))
                user.setLastVisit(System.currentTimeMillis());
            str.append(user.getAvatar()).append("-").append(user.getNickname()).append("-");
            str.append(user.getScore()).append("-");
            for (SocketController socketController : Main.getSockets()) {
                if (!socketController.getUpdater() && socketController.getUser() != null && socketController.getUser().equals(user)) {
                    str.append("online").append("-");
                    continue outer;
                }
            }
            str.append(user.getLastVisit()).append("-");
        }
        return new Response(1,String.valueOf(str));
    }

    public User isExistUsername(String username) {
        ArrayList<User> users = User.getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }
}
