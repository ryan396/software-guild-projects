/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Hero;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author rianu
 */
public final class HeroMapper implements RowMapper<Hero> {

    @Override
    public Hero mapRow(ResultSet rs, int i) throws SQLException {
        Hero h = new Hero();
        h.setHeroName(rs.getString("hero_name"));
        h.setDescription(rs.getString("description"));
        h.setHeroId(rs.getInt("hero_id"));
        return h;
    }
}
