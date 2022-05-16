import controllers.LoginController;
import controllers.MainController;
import enums.LoginMenuCommands;
import models.User;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mock;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.mockito.Mockito.when;

public class LoginControllerTest {

    Matcher matcher;

    User user;

    @BeforeEach
    public void addUser() {
        user = new User("Erfan396","1382","Erfan#DT");
        User.getAllUsers().add(user);
    }


    @Test
    public void checkCreateUser1() {
        matcher = LoginMenuCommands.isMatchCreateUser("user create -u Erfan396 -p 2 -n 3");
        String result = LoginController.getInstance().createUser(matcher);
        Assertions.assertNotEquals(result,"user with username Erfan396 already exists");
    }

    @Test
    public void checkCreateUser2() {
        matcher = LoginMenuCommands.isMatchCreateUser("user create -u Erfan39 -p 2 -n Erfan#DT");
        String result = LoginController.getInstance().createUser(matcher);
        Assertions.assertEquals(result,"user with nickname Erfan#DT already exists");
    }

    @Test
    public void checkCreateUser3() {
        matcher = LoginMenuCommands.isMatchCreateUser("user create -u Erfan39 -p 2 -n Erfan#D");
        String result = LoginController.getInstance().createUser(matcher);
        Assertions.assertEquals(result,"user created successfully!");
    }

    @Test
    public void checkLoginUser1() {
        matcher = LoginMenuCommands.isMatchCreateUser("user create -u Erfan39 -p 2 -n Erfan#D");
        String result = LoginController.getInstance().loginUser(matcher);
        Assertions.assertNotEquals(result,"Username and password didn’t match!");
    }

    @Test
    public void checkLoginUser2() {
        matcher = LoginMenuCommands.isMatchCreateUser("user create -u Erfan396 -p 138 -n Erfan#D");
        String result = LoginController.getInstance().loginUser(matcher);
        Assertions.assertEquals(result,"Username and password didn’t match!");
    }

    @Test
    public void checkLoginUser3() {
        matcher = LoginMenuCommands.isMatchCreateUser("user create -u Erfan396 -p 1382 -n Erfan#D");
        String result = LoginController.getInstance().loginUser(matcher);
        Assertions.assertNotEquals(result,"user logged in successfully!");
    }

    @Test
    public void checkEnterMainMenu1() {
        User.setLoggedInUser(null);
        matcher = LoginMenuCommands.isMatch("menu enter Main Menu",LoginMenuCommands.ENTER_MENU);
        String result = LoginController.getInstance().enterMenu(matcher);
        Assertions.assertEquals(result,"please login first");
    }

    @Test
    public void checkEnterMainMenu2() {
        User.setLoggedInUser(user);
        matcher = LoginMenuCommands.isMatch("menu enter Main Menu",LoginMenuCommands.ENTER_MENU);
        String result = LoginController.getInstance().enterMenu(matcher);
        Assertions.assertEquals(result,"");
    }

    @Test
    public void checkEnterMainMenu3() {
        User.setLoggedInUser(user);
        matcher = LoginMenuCommands.isMatch("menu enter Profile Menu",LoginMenuCommands.ENTER_MENU);
        String result = LoginController.getInstance().enterMenu(matcher);
        Assertions.assertEquals(result,"menu navigation is not possible");
    }

    @AfterEach
    public void removeUser() {
        User.getAllUsers().remove(user);
    }
}
