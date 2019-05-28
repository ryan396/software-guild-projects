/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dto.Organization;
import java.util.List;

/**
 *
 * @author rianu
 */
public class OrganizationServiceImpl implements OrganizationService {
    
    private OrganizationDao oDao;
    
    public OrganizationServiceImpl(OrganizationDao oDao) {
        this.oDao = oDao;
    }
    
    @Override
    public void addOrganization(Organization organization) {
        oDao.addOrganization(organization);
    }

    @Override
    public void deleteOrganization(int organizationId) {
        oDao.deleteOrganization(organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        oDao.updateOrganization(organization);
    }

    @Override
    public Organization getOrganizationById(int id) {
        return oDao.getOrganizationById(id);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return oDao.getAllOrganizations();
    }
    
}
