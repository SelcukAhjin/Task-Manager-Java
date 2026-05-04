package Java_Workpath.Test;

import Java_Workpath.projekt.UserManager;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    UserManager um = new UserManager();


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