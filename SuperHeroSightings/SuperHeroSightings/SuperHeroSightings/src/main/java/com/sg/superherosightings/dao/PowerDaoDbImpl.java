/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Power;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rianu
 */
public class PowerDaoDbImpl implements PowerDao {
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_POWER
            = "insert into powers (description) values (?)";

    private static final String SQL_DELETE_POWER
            = "delete from powers where power_id = ?";

    private static final String SQL_UPDATE_POWER
            = "update powers set description = ?"
            + "where power_id =  ?";

    private static final String SQL_SELECT_POWER
            = "select * from powers where power_id = ?";

    private static final String SQL_SELECT_ALL_POWERS
            = "select * from powers";

    private static final String SQL_DELETE_POWER_FROM_HEROPOWERS
            = "delete from heropowers where power_id = ?";

    @Override
    @Transactional
    public void addPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWER,
                power.getPowerDescription());

        int powerId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        power.setPowerId(powerId);

    }

    @Override
    public void deletePower(int id) {
        jdbcTemplate.update(SQL_DELETE_POWER_FROM_HEROPOWERS, id);
        jdbcTemplate.update(SQL_DELETE_POWER, id);
    }

    @Override
    public void updatePower(Power power) {
        jdbcTemplate.update(SQL_UPDATE_POWER,
                power.getPowerDescription(),
                power.getPowerId());
    }

    @Override
    public Power getPowerById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POWER,
                    new PowerMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWERS, new PowerMapper());
    }


}
