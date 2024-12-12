package org.example;

import org.jdbc.config.DatabaseConnectionManager;
import org.jdbc.dao.LocationDAO;
import org.jdbc.model.Location;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseConnectionManager dbcm = new DatabaseConnectionManager("localhost", "learning", "Grey", "312");

        try {
            Connection connection = dbcm.getConnection();
            LocationDAO ld = new LocationDAO(connection);
            Location location = ld.findById(1000);
            System.out.println(location);

            Location locIns = new Location();

            locIns.setLocationId(3300);
            locIns.setPostalCode("postalCode");
            locIns.setCountryId("JP");
            locIns.setCity("City");
            locIns.setStreetAddress("Street Address");
            locIns.setStateProvince("State");

            ld.create(locIns);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}