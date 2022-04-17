import java.util.Scanner;

public class ProfileMenu {
    private static ProfileMenu instance = null;

    private ProfileMenu() {
    }

    public static ProfileMenu getInstance() {
        if (instance == null)
            return new ProfileMenu();
        return instance;
    }

    public void run(Scanner scanner) {

    }

    private void changePassword() {
        Controller controller = new Controller();
        controller.changePassword();
    }

    private void changeNickname() {
        Controller controller = new Controller();
        controller.changeNickname();
    }

    private void exit() {

    }
}
