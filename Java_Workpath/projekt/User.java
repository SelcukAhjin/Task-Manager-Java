package Java_Workpath.projekt;

public class User {

    private String username;
    private String email;
    private String password;
    private TaskManager taskManager = new TaskManager();

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public TaskManager getTaskManager() {
        return taskManager;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String toFileString(){
        return username + ";" + email + ";" + password;
    }
    public void changePassword(String newPassword){
        this.password = newPassword;
    }
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
