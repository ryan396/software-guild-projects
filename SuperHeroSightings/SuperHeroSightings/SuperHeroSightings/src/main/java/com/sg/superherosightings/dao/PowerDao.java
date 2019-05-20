/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Power;
import java.util.List;

/**
 *
 * @author rianu
 */
public interface PowerDao {
    
    public void addPower(Power power);

    public void deletePower(int id);

    public void updatePower(Power power);

    public Power getPowerById(int id);

    public List<Power> getAllPowers();
    
}
