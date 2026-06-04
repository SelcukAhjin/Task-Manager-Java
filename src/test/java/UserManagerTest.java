import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import projekt.BCrypt;
import projekt.User;
import projekt.UserManager;
import projekt.dao.UserDAO;

import static org.junit.jupiter.api.Assertions.*;
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
        assertNull(um.login("TestUser", "123457"));


    }
    @Test
    void loginWithCorrectPassword(){
        User testUser = new User("TestingUser","Test@mail.com",BCrypt.hashpw("123457", BCrypt.gensalt()));

        Mockito.when(mockDAO.getUserByInput("TestingUser")).thenReturn(testUser);
        assertNotNull(um.login("TestingUser","123457"));
    }

    @Test
    void loginWithWrongUser(){
        User testUser = new User("Test","test@mail.com",BCrypt.hashpw("1234567", BCrypt.gensalt()));
        assertNull(um.login("tesst","1234567"));
    }


    @Test
    void isValidEmail() {
        assertTrue(um.isValidEmail("dwf@wa"));
        assertFalse(um.isValidEmail("sefdw"));
    }

    @Test
    void isValidPassword() {
        assertFalse(um.isValidPassword(""));
        assertTrue(um.isValidPassword("passwo"));
        assertFalse(um.isValidPassword("passw"));
    }
}