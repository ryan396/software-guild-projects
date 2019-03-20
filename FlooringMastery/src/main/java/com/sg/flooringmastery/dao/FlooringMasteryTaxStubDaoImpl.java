/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import static com.sg.flooringmastery.dao.FlooringMasteryTaxDaoFileImpl.DELIMITER;
import static com.sg.flooringmastery.dao.FlooringMasteryTaxDaoFileImpl.HEADER;
import static com.sg.flooringmastery.dao.FlooringMasteryTaxDaoFileImpl.TAXES_FILE;
import com.sg.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author rianu
 */
public class FlooringMasteryTaxStubDaoImpl implements FlooringMasteryTaxDao {
    
    private Map<String, Tax> taxes = new HashMap<>();
    
    @Override
    public Tax addTax(String state, Tax tax)
     throws FlooringMasteryPersistenceException {
        Tax newTax = taxes.put(state, tax);
        return newTax;
    }

    @Override
    public List<Tax> getAllTaxes()
     throws FlooringMasteryPersistenceException {
        return new ArrayList<Tax>(taxes.values());
    }

    @Override
    public Tax removeTax(String state)
     throws FlooringMasteryPersistenceException{
        Tax removedTax = taxes.remove(state);
        return removedTax;
    }

    @Override
    public Tax editTax(Tax tax, Tax editTax)
     throws FlooringMasteryPersistenceException {
        taxes.remove(tax.getState());
        taxes.put(editTax.getState(), editTax);
        return editTax;
    }

    @Override
    public Tax getTax(String state) throws FlooringMasteryPersistenceException {
       return taxes.get(state);
    }
}
