/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author rianu
 */
public final class OrganizationMapper implements RowMapper<Organization> {

    @Override
    public Organization mapRow(ResultSet rs, int i) throws SQLException {
        Organization o = new Organization();
        o.setOrganizationName(rs.getString("organization_name"));
        o.setDescription(rs.getString("organization_description"));
        o.setStreet(rs.getString("street"));
        o.setCity(rs.getString("city"));
        o.setZipCode(rs.getInt("zip_code"));
        o.setPhone(rs.getString("phone"));
        o.setEmail(rs.getString("email"));
        o.setOrganizationId(rs.getInt("organization_id"));
        return o;
    }
}
