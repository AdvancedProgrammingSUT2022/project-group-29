package app;

import controllers.LoginController;
import controllers.MusicController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private static Scene scene;
    private static Popup popup;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        LoginController.getInstance().readUserInfo();

        Pane root = (Pane) loadFXML("loginPage");
        scene = new Scene(root);

        //MusicController.getInstance().playMusic();

        makePopup();

        scene.setFill(Color.RED);
        stage.setScene(scene);
        stage.setTitle("civilization");
        stage.show();
    }

    public static void changeMenu(String name) {
        Parent root = loadFXML(name);
        scene.setRoot(root);
    }

    private static Parent loadFXML(String name) {
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
        popup.setX(930);
        popup.setY(150);
        Label label = new Label("");
        label.setStyle(" -fx-background-color: #da76d6;");
        label.setMinWidth(80);
        label.setMinHeight(50);
        popup.setAutoHide(true);
        popup.getContent().add(label);
    }

    public static void showPopup(String text) {
        Label label = (Label) popup.getContent().get(0);
        label.setText(text);
        popup.show(scene.getWindow());
    }

    public static Scene getScene() {
        return scene;
    }
}
