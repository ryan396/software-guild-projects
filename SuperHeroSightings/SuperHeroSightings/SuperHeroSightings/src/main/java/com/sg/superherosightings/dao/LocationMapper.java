/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author rianu
 */
public final class LocationMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet rs, int i) throws SQLException {
        Location l = new Location();
        l.setLocationName(rs.getString("location_name"));
        l.setLatitude(rs.getBigDecimal("latitude"));
        l.setLongitude(rs.getBigDecimal("longitude"));
        l.setStreet(rs.getString("street"));
        l.setCity(rs.getString("city"));
        l.setZipCode(rs.getInt("zip_code"));
        l.setLocationId(rs.getInt("location_id"));
        return l;
    }
}
