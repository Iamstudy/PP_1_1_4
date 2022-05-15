package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "admin";
    public static final String URL = "jdbc:mysql://localhost:3306/mysql";
    public static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}





