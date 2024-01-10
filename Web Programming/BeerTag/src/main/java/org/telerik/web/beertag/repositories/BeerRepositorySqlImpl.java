package org.telerik.web.beertag.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Beer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource("classpath:application.properties")
public class BeerRepositorySqlImpl implements BeerRepository {
    private final String dbUrl, dbUsername, dbPassword;

    @Autowired
    public BeerRepositorySqlImpl(Environment env) {
        dbUrl = env.getProperty("database.url");
        dbUsername = env.getProperty("database.username");
        dbPassword = env.getProperty("database.password");
    }

    @Override
    public List<Beer> get() {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("select beer_id,name,abv from beers");) {
            return getBeers(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Beer> get(String name, Double maxAbv, Double minAbv, Integer styleId, String sortBy, String sortOrder) {
        String query = "select * from beers where name = ? and abv < ? and abv > ? and style_id = ? order by ? ? ";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setDouble(2, maxAbv);
            statement.setDouble(3, minAbv);
            statement.setInt(4, styleId);
            statement.setString(5, sortBy);
            statement.setString(6, sortOrder);
            try (ResultSet resultSet = statement.executeQuery()) {
                return getBeers(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Beer get(int id) {
        String query = "select beer_id, name, abv from beers where beer_id = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Beer> result = getBeers(resultSet);
                if (result.isEmpty()) {
                    throw new EntityNotFoundException("Beer", id);
                }
                return result.get(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Beer get(String name) {
        String query = "select beer_id, name, abv from beers where name = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Beer> result = getBeers(resultSet);
                if (result.isEmpty()) {
                    throw new EntityNotFoundException("Beer", "name", name);
                }
                return result.get(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void create(Beer beer) {
        String query = "insert into beers(name, abv, style_id, user_id) values (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, beer.getName());
            statement.setDouble(2, beer.getAbv());
            statement.setInt(3, beer.getStyle().getId());
            statement.setInt(4, beer.getCreatedBy().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Beer update(Beer beer) {
        String query = "update beers set name = ?, abv = ?, style_id = ? where beer_id = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, beer.getName());
            statement.setDouble(2, beer.getAbv());
            statement.setInt(3, beer.getStyle().getId());
            statement.setInt(4, beer.getId());
            statement.executeUpdate();
            return get(beer.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "delete from beers where beer_id = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<Beer> getBeers(ResultSet beersData) throws SQLException {
        List<Beer> beers = new ArrayList<>();
        while (beersData.next()) {
            Beer beer = new Beer(beersData.getInt("beer_id"), beersData.getString("name"), beersData.getDouble("abv"));
            beers.add(beer);
        }
        return beers;
    }
}
