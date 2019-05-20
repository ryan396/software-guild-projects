/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rianu
 */
public class OrganizationDaoDbImpl implements OrganizationDao {
    
     private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organizations (organization_name, organization_description, "
            + "street, city, zip_code, phone, email) values "
            + "(?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organizations where organization_id = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organizations set organization_name = ?, organization_description = ?, "
            + "street = ?, city = ?, zip_code = ?, phone = ?, email = ? where organization_id =  ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from organizations where organization_id = ?";
 
    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from organizations";
    private static final String SQL_DELETE_HERO_ORGANIZATION
            = "delete from heroorganizations where hero_id = ?";

    @Override
    @Transactional
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getDescription(),
                organization.getStreet(),
                organization.getCity(),
                organization.getZipCode(),
                organization.getPhone(),
                organization.getEmail());

        int organizationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        organization.setOrganizationId(organizationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(int id) {
        jdbcTemplate.update(SQL_DELETE_HERO_ORGANIZATION, id);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, id);
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getDescription(),
                organization.getStreet(),
                organization.getCity(),
                organization.getZipCode(),
                organization.getPhone(),
                organization.getEmail(),
                organization.getOrganizationId());
    }

    @Override
    public Organization getOrganizationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrganizationMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrganizationMapper());
    }

     
}
