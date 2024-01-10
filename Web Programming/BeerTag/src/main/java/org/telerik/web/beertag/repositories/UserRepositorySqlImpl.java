package org.telerik.web.beertag.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource("classpath:application.properties")
public class UserRepositorySqlImpl implements UserRepository {
    private final String dbUrl, dbUsername, dbPassword;

    @Autowired
    public UserRepositorySqlImpl(Environment env) {
        dbUrl = env.getProperty("database.url");
        dbUsername = env.getProperty("database.username");
        dbPassword = env.getProperty("database.password");
    }

    @Override
    public User create(User user) {
        String query = "insert into users(username, password, is_admin) values ( ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isAdmin());
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from users")) {
            return getUsers(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User getById(int id) {
        String query = "select * from users where user_id = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                List<User> users = getUsers(result);
                if (users.isEmpty()) {
                    throw new EntityNotFoundException("User", id);
                }
                return users.get(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User getByUsername(String username) {
        String query = "select * from users where username = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet result = statement.executeQuery()) {
                List<User> users = getUsers(result);
                if (users.isEmpty()) {
                    throw new EntityNotFoundException("User", "username", username);
                }
                return users.get(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<User> getUsers(ResultSet usersData) throws SQLException {
        List<User> users = new ArrayList<>();
        while (usersData.next()) {
            User user = new User(usersData.getInt("user_id"), usersData.getString("username"),
                    usersData.getString("password"), usersData.getString("first_name"),
                    usersData.getString("last_name"), usersData.getString("email"),
                    usersData.getBoolean("is_admin"));
            users.add(user);
        }
        return users;
    }
}
