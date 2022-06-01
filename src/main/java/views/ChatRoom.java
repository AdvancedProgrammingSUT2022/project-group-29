package views;

import app.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import models.Room;
import models.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatRoom implements Initializable {
    public ChoiceBox<String> privateChat;
    public ChoiceBox<String> rooms;
    public TextField room;

    // TODO complete
    public void createRoom() {
        String message = "";
        if (room.getText().equals(""))
            message = "enter a name";
        Main.showPopup(message);
    }

    public void enterPrivateChat() {
        if (privateChat.getValue() == null) {
            Main.showPopup("choose a player");
            return;
        }
    }

    public void enterRoom() {
        if (rooms.getValue() == null) {
            Main.showPopup("choose a room");
            return;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> strings = new ArrayList<>();
        for (User user : User.getAllUsers()) {
            if (!user.getUsername().equals(User.getLoggedInUser().getUsername()))
                strings.add(user.getNickname());
        }
        privateChat.getItems().addAll(strings);

        ArrayList<String> strings1 = new ArrayList<>();
        for (Room room1 : Room.getRooms()) {
            if (!room1.isChat())
                strings1.add(room1.getName());
        }
        rooms.getItems().addAll(strings1);
    }

    public void publicChat() {

    }
}
