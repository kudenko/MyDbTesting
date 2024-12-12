package org.jdbc.dao;

import org.jdbc.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationDAO {
    private Connection connection;

    public LocationDAO(Connection connection) {
        this.connection = connection;
    }

    private static final String FIND_BY_ID = "SELECT location_id, street_address, postal_code, " +
            "city, state_province, country_id FROM locations WHERE location_id = ?;";

    private static final String INSERT = "INSERT INTO locations (location_id, street_address, postal_code, " +
            "city, state_province, country_id) VALUES(?, ?, ?, ?, ?, ?);";


    public Location findById(int locationId) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, locationId);
            ResultSet resultSet = statement.executeQuery();
            return mapLocation(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Location create(Location dto) {
        try(PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, dto.getLocationId());
            statement.setString(2, dto.getStreetAddress());
            statement.setString(3, dto.getPostalCode());
            statement.setString(4, dto.getCity());
            statement.setString(5, dto.getStateProvince());
            statement.setString(6, dto.getCountryId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Location mapLocation(ResultSet resultSet) throws SQLException {
        Location location = new Location();
        while (resultSet.next()) {
            location.setLocationId(resultSet.getInt("location_id"));
            location.setStateProvince("state_province");
            location.setStreetAddress(resultSet.getString("street_address"));
            location.setCity(resultSet.getString("city"));
            location.setCountryId(resultSet.getString("country_id"));
            location.setPostalCode(resultSet.getString("postal_code"));
        }
        return location;
    }
}
