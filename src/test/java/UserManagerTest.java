import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import projekt.BCrypt;
import projekt.User;
import projekt.UserManager;
import projekt.UserDAO;

@ExtendWith(MockitoExtension.class)
class UserManagerTest {

    @Mock
    UserDAO mockDAO;

    @InjectMocks
    UserManager um;

    @Test
    void loginWithWrongPassword(){
        User testUser = new User("TestUser","test@mail.de",BCrypt.hashpw("1234567", BCrypt.gensalt()));

        Mockito.when(mockDAO.getUserByInput("TestUser")).thenReturn(testUser);
        Assertions.assertNull(um.login("TestUser", "123457"));


    }
    @Test
    void loginWithCorrectPassword(){
        User testUser = new User("TestingUser","Test@mail.com",BCrypt.hashpw("123457", BCrypt.gensalt()));

        Mockito.when(mockDAO.getUserByInput("TestingUser")).thenReturn(testUser);
        Assertions.assertNotNull(um.login("TestingUser","123457"));
    }

    @Test
    void loginWithWrongUser(){
        User testUser = new User("Test","test@mail.com",BCrypt.hashpw("1234567", BCrypt.gensalt()));
        Assertions.assertNull(um.login("tesst","1234567"));
    }


    @Test
    void isValidEmail() {
        Assertions.assertTrue(um.isValidEmail("dwf@wa"));
        Assertions.assertFalse(um.isValidEmail("sefdw"));
    }

    @Test
    void isValidPassword() {
        Assertions.assertFalse(um.isValidPassword(""));
        Assertions.assertTrue(um.isValidPassword("passwo"));
        Assertions.assertFalse(um.isValidPassword("passw"));
    }
}