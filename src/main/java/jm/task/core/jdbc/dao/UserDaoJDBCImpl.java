package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet result;
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                " name VARCHAR(30), " +
                " lastName VARCHAR(30), " +
                " age TINYINT)";
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("USE user");
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    public void dropUsersTable() {
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS user.users");
        } catch (SQLException e) {
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
            }
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("USE user");
            preparedStatement = connection.prepareStatement("INSERT INTO Users(name, lastName, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    public void removeUserById(long id) {
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("USE user");
            preparedStatement = connection.prepareStatement("DELETE FROM user.users WHERE id = ?");
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new LinkedList<>();
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM user.users");
            while (result.next()) {
                User user = new User();
                user.setId((long)result.getInt(1));
                user.setName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setAge((byte) result.getInt(4));
                userList.add(user);
            }
        } catch (SQLException | NullPointerException e) {
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
            }
        }
        return userList;
    }
    public void cleanUsersTable() {
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE user.users");
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
            }
        }
    }
}

