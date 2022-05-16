import controllers.MainController;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainControllerTest {


    @Test
    public void checkExistUser() {
        User user = new User("Erfan396","","");
        User.getAllUsers().add(user);
        User result = MainController.getInstance().isExistUsername("Erfan396");
        Assertions.assertEquals(result,user);
    }
}
