package Java_Workpath.projekt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserManager  {


    public User login(String loginInput, String password) {
            String sql = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
            try (Connection conn = DatabaseManager.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, loginInput);
                pstmt.setString(2, loginInput);
                pstmt.setString(3, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String dbUsername = rs.getString("username");
                    String dbEmail = rs.getString("email");
                    String dbPassword = rs.getString("password");
                    int dbId = rs.getInt("id");
                    User user = new User(dbUsername, dbEmail, dbPassword);
                    user.setId(dbId);
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
    }
    public void addUser(User user) {
        //  users.add(user);
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
    public boolean isValidEmail(String email){
        return email.contains("@")&&!email.isBlank();
    }
    public boolean isValidPassword(String password){
        return password.length() >= 6;
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
    public void updatePassword(String username, String newPassword){
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try (Connection conn=DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,newPassword);
            pstmt.setString(2,username);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
