/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dto.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rianu
 */
public class SightingServiceImpl implements SightingService {

    private SightingDao sDao;

    public SightingServiceImpl(SightingDao sDao) {
        this.sDao = sDao;
    }

    @Override
    public void addSighting(Sighting sighting) {
        sDao.addSighting(sighting);
    }

    @Override
    public void deleteSighting(int id) {
        sDao.deleteSighting(id);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        sDao.updateSighting(sighting);
    }

    @Override
    public Sighting getSightingById(int id) {
        return sDao.getSightingById(id);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return sDao.getAllSightings();
    }

    @Override
    public List<Sighting> findAllSightingsByDate(LocalDate date) {
        return sDao.findAllSightingsByDate(date);
    }

}
