package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            String URL = "jdbc:postgresql://localhost:5432/test";
            String USER = "postgres";
            String PASS = "56275627";
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
