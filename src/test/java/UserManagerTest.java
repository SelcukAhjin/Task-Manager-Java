import projekt.UserManager;
import projekt.dao.UserDAO;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    UserDAO mochDAO = new UserDAO();
    UserManager um = new UserManager(mochDAO);


    @org.junit.jupiter.api.Test
    void isValidEmail() {
        assertTrue(um.isValidEmail("dwf@wa"));
        assertFalse(um.isValidEmail("sefdw"));
    }

    @org.junit.jupiter.api.Test
    void isValidPassword() {
        assertFalse(um.isValidPassword(""));
        assertTrue(um.isValidPassword("passwo"));
        assertFalse(um.isValidPassword("passw"));
    }
}