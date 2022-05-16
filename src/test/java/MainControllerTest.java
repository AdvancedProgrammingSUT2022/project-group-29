import controllers.MainController;
import models.User;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class MainControllerTest {

    User user1;
    User user2;
    @BeforeEach
    public void addUser() {
        user1 = new User("Erfan396","1382","Erfan#DT");
        User.getAllUsers().add(user1);
        user2 = new User("Mohammad396","1383","Mohammad#DT");
        User.getAllUsers().add(user2);
    }
    @Test
    public void checkCreateGame1() {
       int count = MainController.getInstance().checkIsValidPlayGame("play game -p1 1");
        Assertions.assertEquals(-1,count);
    }

    @Test
    public void checkCreateGame2() {
        int count = MainController.getInstance().checkIsValidPlayGame("play game -p1 1 -p3 2");
        Assertions.assertEquals(-1,count);
    }

    @Test
    public void checkCreateGame3() {
        ArrayList<User> users = MainController.getInstance().checkIsValidUsername(2,"play game -p1 Erfan3962 -p2 Mohammad396");
        Assertions.assertEquals(null,users);
    }


    @Test
    public void checkCreateGame4() {
        ArrayList<User> expected = new ArrayList<>();
        expected.add(user1);
        expected.add(user2);

        ArrayList<User> users = MainController.getInstance().checkIsValidUsername(2,"play game -p1 Erfan396 -p2 Mohammad396");
        Assertions.assertNotEquals(users,expected);

    }


    @AfterEach
    public void removeUsers() {
        User.getAllUsers().remove(user1);
        User.getAllUsers().remove(user2);
    }


}
