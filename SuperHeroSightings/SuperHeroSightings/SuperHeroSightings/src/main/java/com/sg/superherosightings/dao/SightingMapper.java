/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author rianu
 */
public final class SightingMapper implements RowMapper<Sighting> {

    @Override
    public Sighting mapRow(ResultSet rs, int i) throws SQLException {
        Sighting s = new Sighting();
        s.setSightingId(rs.getInt("sighting_id"));
        s.setDate(rs.getTimestamp("sighting_date").toLocalDateTime().toLocalDate());
        s.setLocationId(rs.getInt("location_id"));
        return s;
    }
}
