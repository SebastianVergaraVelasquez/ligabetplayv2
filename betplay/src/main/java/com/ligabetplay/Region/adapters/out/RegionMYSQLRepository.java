package com.ligabetplay.Region.adapters.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ligabetplay.Pais.domain.models.Pais;
import com.ligabetplay.Region.domain.models.Region;
import com.ligabetplay.Region.infrastructure.RegionRepository;

public class RegionMYSQLRepository implements RegionRepository {

    private final String url;
    private final String user;
    private final String password;

    public RegionMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Region region) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO region (nombre) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, region.getNombre());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Region> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM region WHERE id =?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Region region = new Region(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"));
                        return Optional.of(region);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Region region) {
        boolean updated = true;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE region SET nombre = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, region.getNombre());
                statement.setInt(2, region.getId());
                statement.executeUpdate();
                return updated;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            updated = false;
            return updated;
        }
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = true;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM region WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                return deleted;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            deleted = false;
            return deleted;
        }
    }

    @Override
    public List<Region> findAll() {
        List<Region> regiones = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM region";
            try (PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Region region = new Region(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"));
                    regiones.add(region);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regiones;
    }
}
