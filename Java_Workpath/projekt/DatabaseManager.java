package Java_Workpath.projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseManager {
    private static final String PASSWORD = "";
    private static final String USER = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/taskmanager_db";


public static Connection getConnection(){
    try {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}
}