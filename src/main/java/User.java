import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private int score;
    private static ArrayList<User> allUsers;
    private static User loggedInUser;

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        allUsers.add(this);
    }

    public static boolean usernameExist(String username) {
        for (User user : allUsers) {
            if (user.username.equals(username))
                return true;
        }
        return false;
    }

    public static User getUser(String username, String password) {
        for (User user : allUsers) {
            if (user.username.equals(username) &&
            user.password.equals(password))
                return user;
        }
        return null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }
}
