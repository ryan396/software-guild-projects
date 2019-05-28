/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dto.Location;
import java.util.List;

/**
 *
 * @author rianu
 */
public class LocationServiceImpl implements LocationService {
    
    private LocationDao lDao;
    
    public LocationServiceImpl(LocationDao lDao) {
        this.lDao = lDao;
    }
    
    @Override
    public void addLocation(Location location) {
        lDao.addLocation(location);
    }

    @Override
    public void deleteLocation(int id) {
        lDao.deleteLocation(id);
    }

    @Override
    public void updateLocation(Location location) {
        lDao.updateLocation(location);
    }

    @Override
    public Location getLocationById(int id) {
        return lDao.getLocationById(id);
    }

    @Override
    public List<Location> getAllLocations() {
        return lDao.getAllLocations();
    }
    
}
