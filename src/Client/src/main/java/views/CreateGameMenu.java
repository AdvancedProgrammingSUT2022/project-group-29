package views;


import app.Main;
import controllers.NetworkController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import models.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateGameMenu implements Initializable {
    public static int certainX = 5, certainY = 5;
    public VBox invites;
    @FXML
    private ChoiceBox<String> playersNumber;
    @FXML
    private ChoiceBox<String> username;
    @FXML
    private VBox vbox;
    @FXML
    private Label message;
    private final String[] numbers = {"2 Players", "4 Players", "6 Players"};
    private int numberOfPlayers;

    private ArrayList<String> players = new ArrayList<>();
    private ArrayList<String> onlinePlayers = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playersNumber.getItems().addAll(numbers);
        playersNumber.setValue("2 Players");
        numberOfPlayers = 2;
        playersNumber.setOnAction(this::checkNumber);

        Response response = NetworkController.getInstance().getName();
        String[] strings = response.getMessage().split("-");
        vbox.getChildren().add(new Label(strings[0]));
        playersNumber.requestFocus();
        for (int i = 1; i < strings.length - 1; i++) {
            onlinePlayers.add(strings[i]);
        }
        username.getItems().addAll(onlinePlayers);

    }

    private void checkNumber(ActionEvent actionEvent) {
        //int number = playersNumber.getValue().charAt(0) - 48;
        //GameController.getInstance().checkNumberOfUsers(players,numberOfPlayers,number,message,playersNumber);
        //this.numberOfPlayers =  playersNumber.getValue().charAt(0) - 48;
    }

    public void addPlayer() {
        if (playersNumber.getValue().charAt(0) - 48 > vbox.getChildren().size()) {
            String user = username.getValue();
            Label label1 = new Label(user);
            invites.getChildren().add(label1);
            Response response = NetworkController.getInstance().addPlayer(username.getValue());
            message.setText(response.getMessage());
            if (response.getStatus_code() == 1) {
                Label label = new Label(user);
                vbox.getChildren().add(label);
            }
        }
        else
            message.setText("Capacity is full");
    }


    public void startGame() {
        if (playersNumber.getValue().charAt(0) - 48 == vbox.getChildren().size()) {
            Response response = NetworkController.getInstance().startGame();
            message.setText("Game Has Started");
        }
        else {
            message.setText("You Haven't Add All Users Yet");
        }
    }

    public void setPlace(KeyEvent keyEvent) {
        //if (keyEvent.isShiftDown() && keyEvent.isControlDown() && keyEvent.getCode().getName().equals("C")) {
        //    GameController.getInstance().setPlace();
        //}
    }

    public void back() {
        Main.changeMenu("mainPage");
    }
}
