//package views;
//
//import app.Main;
//import javafx.fxml.Initializable;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.TextField;
//import models.Room;
//import models.User;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//public class ChatRoom implements Initializable {
//    public ChoiceBox<String> privateChat;
//    public ChoiceBox<String> rooms;
//    public TextField room;
//    public ChoiceBox<String> playerToRoom;
//    private ArrayList<String> added = new ArrayList<>();
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        ArrayList<String> strings = new ArrayList<>();
//        for (User user : User.getAllUsers()) {
//            if (!user.getUsername().equals(User.getLoggedInUser().getUsername()))
//                strings.add(user.getNickname());
//        }
//        privateChat.getItems().addAll(strings);
//
//        showRooms();
//
//        showPlayerRoom();
//    }
//
//
//    // TODO complete and move to controller
//    public void createRoom() {
//        String message = "";
//        if (room.getText().equals(""))
//            message = "enter a name";
//        else if (roomExist(room.getText()))
//            message = "name exist";
//        else if (added.size() == 0)
//            message = "choose a player";
//        else if (added.size() == 1)
//            message= "choose at least 2 players";
//        else {
//            ArrayList<User> users = new ArrayList<>();
//            for (String s : added) {
//                users.add(User.getUserByNickname(s));
//            }
//            users.add(User.getLoggedInUser());
//            new Room(users, room.getText());
//            message = "successful";
//        }
//        Main.showPopupJustText(message);
//    }
//
//    public void enterPrivateChat() {
//        if (privateChat.getValue() == null) Main.showPopupJustText("choose a player");
//        else {
//            new Room(new ArrayList<User>() {{
//                add(User.getLoggedInUser());
//                add(User.getUserByNickname(privateChat.getValue()));
//            }}, "chat");
//        }
//    }
//
//    public void enterRoom() {
//        if (rooms.getValue() == null)
//            Main.showPopupJustText("choose a room");
//        else {
//            Room.setCurrentRoom(Room.getRoomByName(rooms.getValue()));
//        }
//    }
//
//
//    public void publicChat() {
//
//    }
//
//    public void back() {
//        Main.changeMenu("mainPage");
//    }
//
//    public void addToRoom() {
//        if (playerToRoom.getValue() == null) {
//            Main.showPopupJustText("choose a player");
//            return;
//        }
//        added.add(this.playerToRoom.getValue());
//    }
//
//    private boolean roomExist(String name) {
//        for (Room room1 : Room.getRooms()) {
//            if (room1.getName().equals(name))
//                return true;
//        }
//        return false;
//    }
//
//    public void showPlayerRoom() {
//        playerToRoom.getItems().clear();
//        ArrayList<String> show = new ArrayList<>();
//        for (User user : User.getAllUsers()) {
//            if (!contains(user.getNickname()) && !user.getNickname().equals(User.getLoggedInUser().getNickname()))
//                show.add(user.getNickname());
//        }
//
//        this.playerToRoom.getItems().addAll(show);
//    }
//
//    private boolean contains(String name) {
//        for (String s : added) {
//            if (s.equals(name))
//                return true;
//        }
//        return false;
//    }
//
//    public void showRooms() {
//        rooms.getItems().clear();
//        ArrayList<String> strings1 = new ArrayList<>();
//        for (Room room1 : Room.getRooms()) {
//            if (!room1.isChat() && room1.hasUser(User.getLoggedInUser()))
//                strings1.add(room1.getName());
//        }
//        rooms.getItems().addAll(strings1);
//    }
//}
