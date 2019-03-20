/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author rianu
 */
public interface FlooringMasteryTaxDao {
    
    Tax addTax(String state, Tax tax)
     throws FlooringMasteryPersistenceException;
    
    List<Tax> getAllTaxes()
     throws FlooringMasteryPersistenceException;
    
    Tax removeTax(String state)
     throws FlooringMasteryPersistenceException;
    
    Tax editTax(Tax tax, Tax editTax)
     throws FlooringMasteryPersistenceException;
    
    Tax getTax(String state)
     throws FlooringMasteryPersistenceException;
}
