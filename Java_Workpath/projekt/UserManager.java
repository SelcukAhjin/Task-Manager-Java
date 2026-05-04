package Java_Workpath.projekt;


import Java_Workpath.projekt.dao.UserDAO;

@SuppressWarnings("DataFlowIssue")
public class UserManager  {
    UserDAO userDAO;
public UserManager(UserDAO injectDAO) {
    this.userDAO = injectDAO;
}
    public User login(String loginInput, String password) {

        User foundUser = userDAO.getUserByInput(loginInput);
        if (foundUser==null) {
            return null;
        }
        else {
            if (BCrypt.checkpw(password,foundUser.getPassword())) {
                return foundUser;
            }
        }
        return null;
    }
    public void addUser(String username, String email, String password)
    {
        User user = new User(username,email,BCrypt.hashpw(password,BCrypt.gensalt()));
        userDAO.addUser(user);
    }
    public boolean usernameExists(String username) {
        return userDAO.usernameExists(username);
    }
    public boolean emailExists(String email) {
            return userDAO.emailExists(email);
        }
    public boolean isValidEmail(String email){
        return email.contains("@")&&!email.isBlank();
    }
    public boolean isValidPassword(String password){
        return password.length() >= 6;
    }
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }
    public void updatePassword(String username, String newPassword) {
        userDAO.updatePassword(username, BCrypt.hashpw(newPassword,BCrypt.gensalt()));
    }
}
