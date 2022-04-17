import java.util.Scanner;

public class LoginMenu {
    private static LoginMenu instance = null;

    private LoginMenu() {
    }

    public static LoginMenu getInstance() {
        if (instance == null)
            return new LoginMenu();
        return instance;
    }

    public void run(Scanner scanner) {
        exit();

    }

    private void exit() {

    }

    private void createUser(String username, String password, String nickname) {
        Controller controller = new Controller();
        controller.createUser(username, password, nickname);
    }

    private void login(String username, String password) {
        Controller controller = new Controller();
        controller.login(username, password);
    }

}
