package com.ligabetplay.Pais.adapters.out;

import com.ligabetplay.Pais.domain.models.Pais;
import com.ligabetplay.Pais.infrastructure.PaisRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaisMySQLRepository implements PaisRepository {
    private final String url;
    private final String user;
    private final String password;

    public PaisMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Pais pais) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO pais (nombre) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, pais.getNombre());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Pais pais) {
        boolean updated = false;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE pais SET nombre = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, pais.getNombre());
                statement.setInt(2, pais.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return updated;
        }
        updated = true;
        return updated;
    }

    @Override
    public Optional<Pais> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM pais WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Pais pais = new Pais(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre")
                        );
                        return Optional.of(pais);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM pais WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return deleted;
        }
        deleted = true;
        return deleted;
    }

    @Override
    public List<Pais> findAll() {
        List<Pais> paises = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM pais";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Pais pais = new Pais(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre")
                    );
                    paises.add(pais);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paises;
    }
}
