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
        Controller.changePassword();
    }

    private void changeNickname() {
        Controller.changeNickname();
    }

    private void exit() {

    }
}
