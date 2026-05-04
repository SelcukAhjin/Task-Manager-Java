package Java_Workpath.projekt.DAO;

import Java_Workpath.projekt.BCrypt;
import Java_Workpath.projekt.DatabaseManager;
import Java_Workpath.projekt.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userdao {
    public User getUserByInput(String loginInput) {
        String sql = "SELECT * FROM users WHERE username = ? OR email = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, loginInput);
            pstmt.setString(2, loginInput);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String dbUsername = rs.getString("username");
                String dbEmail = rs.getString("email");
                String dbPassword = rs.getString("password");
                User user = new User(dbUsername, dbEmail, dbPassword);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }

    public boolean usernameExists(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean emailExists(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }
    public void addUser(User user) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?,?,?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updatePassword(String username, String newPassword){
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try (Connection conn=DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, BCrypt.hashpw(newPassword,BCrypt.gensalt()));
            pstmt.setString(2,username);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
