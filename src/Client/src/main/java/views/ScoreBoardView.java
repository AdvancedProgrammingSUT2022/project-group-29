package views;

import app.Main;
import controllers.NetworkController;
import controllers.UpdateThread;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import models.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ScoreBoardView implements Initializable {

    public AnchorPane mainPane;
    public UpdateThread updateThread = new UpdateThread();
    {
        updateThread.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Response response = NetworkController.getInstance().ScoreBoard();
        String[] strings = response.getMessage().split("-");
        int i = 0;
        for (int j = 0; j < Integer.parseInt(strings[0]); j++) {
            VBox vBox = new VBox();
            Rectangle rectangle = new Rectangle(100, 100);
            InputStream inputStream;
            try {
                inputStream = new FileInputStream(strings[4*i + 1]);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Image image = new Image(inputStream);
            rectangle.setFill(new ImagePattern(image));
            Label label = new Label("nickname: " + strings[4*i + 2]);
            Label label1 = new Label("\nscore: " + strings[4*i + 3]);
            Label label2;
            if (strings[4*i + 4].equals("online")) {
                label2 = new Label("\nlast visit: " + "online");
            }
            else {
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
                Date resultdate = new Date(Long.parseLong(strings[4 * i + 4]));
                label2 = new Label("\nlast visit: " + sdf.format(resultdate));
            }
            vBox.getChildren().add(rectangle);
            vBox.getChildren().add(label);
            vBox.getChildren().add(label1);
            vBox.getChildren().add(label2);
            mainPane.getChildren().add(vBox);
            vBox.setLayoutY(i * 250);
            vBox.setLayoutX(540);
            i++;
        }

    }

    public void back() {
        Main.changeMenu("mainPage");
    }

    public void refresh(MouseEvent mouseEvent) {
        Main.changeMenu("scoreBoardPage");
    }
}