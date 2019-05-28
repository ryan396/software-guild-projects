/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.PowerDao;
import com.sg.superherosightings.dto.Power;
import java.util.List;

/**
 *
 * @author rianu
 */
public class PowerServiceImpl implements PowerService {
    
    private PowerDao pDao;
    
    public PowerServiceImpl(PowerDao pDao) {
        this.pDao = pDao;
    }
    
    @Override
    public void addPower(Power power) {
        pDao.addPower(power);
    }

    @Override
    public void deletePower(int id) {
        pDao.deletePower(id);
    }

    @Override
    public void updatePower(Power power) {
        pDao.updatePower(power);
    }

    @Override
    public Power getPowerById(int id) {
        return pDao.getPowerById(id);
    }

    @Override
    public List<Power> getAllPowers() {
        return pDao.getAllPowers();
    }
    
}
