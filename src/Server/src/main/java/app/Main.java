package app;

import controllers.LoginController;
import controllers.NetworkController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class Main {
    private static final int Server_port = 8000;

    private static Scene scene;
    private static Popup popup;
    private static final MediaPlayer mediaPlayer = new MediaPlayer(new Media(Main.class.getResource("/assets/avatars/song.mp3").toExternalForm()));

    public static void playMusic() {
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
    }

    public static void changeMenu(String name) {
        Parent root = loadFXML(name);
        scene.setRoot(root);
    }

    public static Parent loadFXML(String name) {
        try {
            URL address = new URL(Main.class.getResource("/fxml/" + name + ".fxml").toString());
            return FXMLLoader.load(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void makePopup() {
        popup = new Popup();
        popup.setX(900);
        popup.setY(150);
        popup.setWidth(200);
        popup.setHeight(1000);
        popup.setAutoHide(true);
    }

    public static void showPopupJustText(String text) {
        Main.getPopup().getContent().clear();
        popup.setX(900);
        popup.setY(150);
        if (popup.getContent().size() == 1)
            popup.getContent().remove(0);
        Label label = new Label("");
        label.setStyle(" -fx-background-color: #da76d6;");
        label.setMinWidth(80);
        label.setMinHeight(50);
        label.setText(text);
        popup.getContent().add(label);
        popup.show(scene.getWindow());
    }

    public static Scene getScene() {
        return scene;
    }

    public static Popup getPopup() {
        return popup;
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static int getServer_port() {
        return Server_port;
    }
}
