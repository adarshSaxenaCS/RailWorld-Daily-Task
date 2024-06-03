package main.java.com.weatherforecast.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
     final static String DB_URL = "jdbc:mysql://localhost:3306/railworldproject";
     final static  String USER = "root";
     final static  String PASS = "root";

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
