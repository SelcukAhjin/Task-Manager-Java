package com.example.demo;

public class User {

    private String username;
    private String email;
    private String password;
    private int id;
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
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
