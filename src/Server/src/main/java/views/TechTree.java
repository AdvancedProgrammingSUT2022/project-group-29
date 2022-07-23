package views;

import app.Main;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class TechTree implements Initializable {

    public AnchorPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Node child : mainPane.getChildren()) {
            if (child.getClass().equals(VBox.class)) {
                Label label = (Label) ((VBox) child).getChildren().get(0);
                Circle circle = (Circle) ((VBox) child).getChildren().get(1);
                circle.setFill(new ImagePattern(new Image(Main.class.getResource("/assets/technology/" + label.getText() + ".png").toExternalForm())));
            }
        }
    }
}
