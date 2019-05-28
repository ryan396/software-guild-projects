/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dto.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rianu
 */
public interface SightingService {
    
    public void addSighting(Sighting sighting);

    public void deleteSighting(int id);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingById(int id);

    public List<Sighting> getAllSightings();
    
    public List<Sighting> findAllSightingsByDate(LocalDate date);
    
}
