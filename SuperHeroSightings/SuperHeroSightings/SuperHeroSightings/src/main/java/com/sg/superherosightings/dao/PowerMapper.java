/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Power;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author rianu
 */
public final class PowerMapper implements RowMapper<Power> {

    @Override
    public Power mapRow(ResultSet rs, int i) throws SQLException {
        Power p = new Power();
        p.setPowerId(rs.getInt("power_id"));
        p.setPowerDescription(rs.getString("description"));
        return p;
    }
}
