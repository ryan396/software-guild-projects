/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryTaxDao;
import com.sg.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author rianu
 */
public class FlooringMasteryTaxServiceLayerImpl implements FlooringMasteryTaxServiceLayer {
    
    FlooringMasteryTaxDao taxDao;
    private FlooringMasteryAuditDao auditDao;
    
    public FlooringMasteryTaxServiceLayerImpl(FlooringMasteryTaxDao taxDao, 
            FlooringMasteryAuditDao auditDao) {
        this.taxDao = taxDao;
        this.auditDao = auditDao;
    }
    
    @Override
    public List<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        return taxDao.getAllTaxes();
    }

    @Override
    public Tax addTax(String state, Tax tax) throws 
            FlooringMasteryDuplicateTaxException, 
            FlooringMasteryTaxDataValidationException, 
            FlooringMasteryPersistenceException {
        if (taxDao.getTax(state) != null) {
                throw new FlooringMasteryDuplicateTaxException(
                    "ERROR: Could not create tax info. State tax info for "
                    + tax.getState() + " already exists.");
            } 
        
        validateTaxData(tax);
        
        Tax newTax = taxDao.addTax(tax.getState(), tax);
        //auditDao.writeAuditEntry(
        //    "Tax info for " + tax.getState() + " CREATED.");
        return newTax;
    }

    @Override
    public Tax removeTax(String state) throws FlooringMasteryPersistenceException {
        //auditDao.writeAuditEntry(
        //    "Tax info for " + state + " REMOVED.");
        return taxDao.removeTax(state);
    }

    @Override
    public Tax editTax(Tax tax, Tax editTax) throws FlooringMasteryPersistenceException {
        //auditDao.writeAuditEntry(
        //    "Tax info for " + tax.getState() + " EDITED.");
        return taxDao.editTax(tax, editTax);
    }

    @Override
    public Tax getTax(String state) throws FlooringMasteryPersistenceException {
        return taxDao.getTax(state);
    }
    
    private void validateTaxData(Tax tax) throws
            FlooringMasteryTaxDataValidationException {
        
        if (tax.getState() == null
                || tax.getState().trim().length() == 0
                || tax.getTaxRate().intValue() < 0
                || tax.getTaxRate() == null) {
            throw new FlooringMasteryTaxDataValidationException(
                "ERROR:  All fields [State, Tax Rate] are required and tax rate"
                        + " must be greater than or equal to zero");
        }
    }
    
}
