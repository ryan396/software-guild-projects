/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dto.Organization;
import java.util.List;

/**
 *
 * @author rianu
 */
public interface OrganizationService {
    
    public void addOrganization(Organization organization);

    public void deleteOrganization(int organizationId);

    public void updateOrganization(Organization organization);

    public Organization getOrganizationById(int id);

    public List<Organization> getAllOrganizations();
    
}
