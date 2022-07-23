package views;

import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import models.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateGameMenu implements Initializable {
    public static int certainX = 5, certainY = 5;
    public RadioButton radio;
    @FXML
    private ChoiceBox<String> playersNumber;
    @FXML
    private TextField username;
    @FXML
    private VBox vbox;
    @FXML
    private Label message;
    private final String[] numbers = {"2 Players", "4 Players", "6 Players"};
    private int numberOfPlayers;
    private ArrayList<String> players = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playersNumber.getItems().addAll(numbers);
        playersNumber.setValue("2 Players");
        numberOfPlayers = 2;
        playersNumber.setOnAction(this::checkNumber);
        vbox.getChildren().add(new Label(User.getLoggedInUser().getUsername()));
        players.add(User.getLoggedInUser().getUsername());
        playersNumber.requestFocus();
    }

    private void checkNumber(ActionEvent actionEvent) {
        int number = playersNumber.getValue().charAt(0) - 48;
        GameController.getInstance().checkNumberOfUsers(players,numberOfPlayers,number,message,playersNumber);
        this.numberOfPlayers =  playersNumber.getValue().charAt(0) - 48;
    }

    public void addPlayer() {
        GameController.getInstance().addUserToGame(username.getText(), players, vbox, message, numberOfPlayers);
    }

    public void removePlayer() {
        GameController.getInstance().removeUserFromGame(username.getText(),players,vbox,message);
    }

    public void startGame() {
        if (numberOfPlayers == players.size()) {
            message.setText("Game Has Started");
            ArrayList<User> players = new ArrayList<>();
            for (String player : this.players) {
                players.add(User.getUserByUsername(player));
            }
            GameController.getInstance().startGame(players, radio.isSelected());
            Main.changeMenu("mapPage");
        }
        else {
            message.setText("You Haven't Add All Users Yet");
        }
    }

    public void setPlace(KeyEvent keyEvent) {
        if (keyEvent.isShiftDown() && keyEvent.isControlDown() && keyEvent.getCode().getName().equals("C")) {
            GameController.getInstance().setPlace();
        }
    }

    public void back() {
        Main.changeMenu("mainPage");
    }
}
