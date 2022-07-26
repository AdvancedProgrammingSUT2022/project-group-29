package views;

import app.Main;
import controllers.GetMessageFromServerThread;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class AcceptGameMenu {
    public Label message;

    public int a = 1;
    public void accept(MouseEvent mouseEvent) {
        if (a == 1) {
            GetMessageFromServerThread.write("accepted");
            message.setText("please wait for starting game...");
            a = 0;
        }

    }

    public void reject(MouseEvent mouseEvent) {
        if (a == 1) {
            GetMessageFromServerThread.write("rejected");
            Main.changeMenu("mainPage");
            a = 0;
        }
    }
}
