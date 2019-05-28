/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Location;
import java.util.List;

/**
 *
 * @author rianu
 */
public interface LocationDao {
    
    public void addLocation(Location location);

    public void deleteLocation(int id);

    public void updateLocation(Location location);

    public Location getLocationById(int id);

    public List<Location> getAllLocations(); 
   
}
