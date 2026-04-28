package Java_Workpath.projekt;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;


public class UserManager implements Saveable {
    private ArrayList<User> users = new ArrayList<>();

    public void load(String fileName) {
        users.clear();
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return;
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String username = parts[0];
                    String email = parts[1];
                    String password = parts[2];
                    User user = new User(username,email,password);
                    users.add(user);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error while loading tasks!");
        }
    }
    public void addUser(User user){
        users.add(user);
    }
    public User login(String loginInput,String password){
        for (int i = 0; i<users.size();i++){
            if (users.get(i).getUsername().equals(loginInput) ||users.get(i).getEmail().equals(loginInput)) {
                if (users.get(i).checkPassword(password)) {
                    return users.get(i);
                }
            }
        }
        return null;
    }
    public boolean usernameExists(String username) {
        for (int i = 0; i<users.size(); i++){
            if (users.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    public void save(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (User user : users) {
                writer.write(user.toFileString());
                writer.write("\n");
            }
            writer.close();
            System.out.println("Tasks saved successfully!");
        } catch (Exception e) {
            System.out.println("Error while saving tasks!");
        }
    }
    public boolean emailExists(String email) {
        for (int i = 0; i<users.size(); i++){
            if (users.get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    public boolean isValidEmail(String email){
        return email.contains("@")&&!email.isBlank();
    }
    public boolean isValidPassword(String password){
        return password.length() >= 6;
    }
    public void deleteUser(User user){
        users.remove(user);
    }
}
