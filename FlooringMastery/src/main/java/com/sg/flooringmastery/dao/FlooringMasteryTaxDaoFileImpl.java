/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

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
public class FlooringMasteryTaxDaoFileImpl implements FlooringMasteryTaxDao {

    private Map<String, Tax> taxes = new HashMap<>();
    public static final String TAXES_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";
    public static final String HEADER = "State,TaxRate";
    
    
    @Override
    public Tax addTax(String state, Tax tax)
     throws FlooringMasteryPersistenceException {
        Tax newTax = taxes.put(state, tax);
        writeTaxes();
        return newTax;
    }

    @Override
    public List<Tax> getAllTaxes()
     throws FlooringMasteryPersistenceException {
        loadTaxes();
        return new ArrayList<Tax>(taxes.values());
    }

    @Override
    public Tax removeTax(String state)
     throws FlooringMasteryPersistenceException{
        Tax removedTax = taxes.remove(state);
        writeTaxes();
        return removedTax;
    }

    @Override
    public Tax editTax(Tax tax, Tax editTax)
     throws FlooringMasteryPersistenceException {
        taxes.remove(tax.getState());
        taxes.put(editTax.getState(), editTax);
        writeTaxes();
        return editTax;
    }
    
    private void loadTaxes() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(TAXES_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("-_- Could not load taxes data "
                    + "into memory", e);
        }
        
        String currentLine;
        String[] currentTokens;
        
        if(scanner.hasNext()==true){
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Tax currentTax = new Tax(currentTokens[0]);
            currentTax.setTaxRate(formatBigDecimal(currentTokens[1]));
            
            taxes.put(currentTax.getState(), currentTax);
        }
    }
    
    private void writeTaxes() throws FlooringMasteryPersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(TAXES_FILE));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(""
                    + "Could not save tax data.", e);
        }
        
        List<Tax> taxList = this.getAllTaxes();
        out.println(HEADER);
        for (Tax currentTax : taxList) {
            out.println(currentTax.getState() + DELIMITER
                    + currentTax.getTaxRate());
            out.flush();
        }
        out.close();
    }
    
    public BigDecimal formatBigDecimal(String currentToken) {
      BigDecimal currentBigDecimal = new BigDecimal(currentToken);
      return currentBigDecimal;
    }

    @Override
    public Tax getTax(String state) throws FlooringMasteryPersistenceException {
       loadTaxes();
       return taxes.get(state);
    }
    
}
