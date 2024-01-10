package org.telerik.web.beertag.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Style;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource("classpath:application.properties")
public class StyleRepositorySqlImpl implements StyleRepository {
    private final String dbUrl, dbUsername, dbPassword;

    @Autowired
    public StyleRepositorySqlImpl(Environment env) {
        dbUrl = env.getProperty("database.url");
        dbUsername = env.getProperty("database.username");
        dbPassword = env.getProperty("database.password");
    }

    @Override
    public List<Style> get() {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("select * from styles");) {
            return getStyles(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Style get(int id) {
        String query = "select * from styles where style_id = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                List<Style> users = getStyles(result);
                if (users.isEmpty()) {
                    throw new EntityNotFoundException("User", id);
                }
                return users.get(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<Style> getStyles(ResultSet stylesData) throws SQLException {
        List<Style> styles = new ArrayList<>();
        while (stylesData.next()) {
            Style style = new Style(stylesData.getInt("style_id"), stylesData.getString("name"));
            styles.add(style);
        }
        return styles;
    }
}
