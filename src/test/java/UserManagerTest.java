import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import projekt.BCrypt;
import projekt.User;
import projekt.UserManager;
import projekt.dao.UserDAO;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserManagerTest {

    @BeforeEach
    void setUp(){
        org.mockito.MockitoAnnotations.openMocks(this);
    }

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