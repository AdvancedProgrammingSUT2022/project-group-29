package app;

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

public class Main extends Application {
    private static Scene scene;
    private static Popup popup;
    private static final MediaPlayer mediaPlayer = new MediaPlayer(new Media(Main.class.getResource("/assets/avatars/song.mp3").toExternalForm()));
    private static final int serverPort = 8000;

    private static String lastPage;
    public static void main(String[] args) {
        if (NetworkController.connect())
            launch();
        else {
            System.out.println("Can not Connect to Server");
            System.exit(0);
        }
    }

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);

        Pane root = (Pane) loadFXML("loginPage");
        scene = new Scene(root);
        playMusic();

        makePopup();

        scene.setFill(Color.RED);
        stage.setScene(scene);
        stage.setTitle("civilization");
        stage.getIcons().add(new Image(Main.class.getResource("/assets/Backgrounds/DioBackground.png").toExternalForm()));
        stage.show();
    }

    public static void playMusic() {
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
    }

    public static void changeMenu(String name) {
        lastPage = name;
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

    public static int getServerPort() {
        return serverPort;
    }

    public static String getLastPage() {
        return lastPage;
    }
}
