/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Power;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author rianu
 */
public class LocationDaoDbImpl implements LocationDao {
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_LOCATION
            = "insert into locations (location_name, latitude, longitude, "
            + "street, city, zip_code) values (?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from locations where location_id = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update locations set location_name = ?, latitude = ?, longitude = ?, "
            + "street = ?, city = ?, zip_code = ? "
            + "where location_id =  ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from locations where location_id = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from locations";

    //prepared statements sighting table
    private static final String SQL_DELETE_SIGHTING_BY_LOCATION
            = "delete from sightings where location_id = ?";

    @Override
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getLatitude(),
                location.getLongitude(),
                location.getStreet(),
                location.getCity(),
                location.getZipCode());

        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        location.setLocationId(locationId);
    }

    @Override
    public void deleteLocation(int id) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING_BY_LOCATION, id);
        jdbcTemplate.update(SQL_DELETE_LOCATION, id);
        //manage hero sightings bridge
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getLatitude(),
                location.getLongitude(),
                location.getStreet(),
                location.getCity(),
                location.getZipCode(),
                location.getLocationId());
    }

    @Override
    public Location getLocationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new LocationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS,
                new LocationMapper());
    }
  
}
