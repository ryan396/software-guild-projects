/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.service.FlooringMasteryAuditDao;

/**
 *
 * @author rianu
 */
public class FlooringMasteryAuditDaoStubImpl implements FlooringMasteryAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws FlooringMasteryPersistenceException {
        //do nothing... we don't want to log this since its a stub
    }
    
}
