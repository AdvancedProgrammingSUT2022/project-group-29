import views.LoginMenu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginMenu.getInstance().run(scanner);
    }
}
