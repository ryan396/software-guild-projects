/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
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
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    
    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
    
    private Map<String, Item> items = new HashMap<>();
    
    @Override
    public List<Item> getAllItems() 
        throws VendingMachinePersistenceException {
            loadInventory();
            return new ArrayList<Item>(items.values());
    }

    @Override
    public Item addItem(String itemID, Item item) 
        throws VendingMachinePersistenceException {
            Item newItem = items.put(itemID, item);
            writeInventory();
            return newItem;
    }

    @Override
    public Item getItem(String itemID) 
        throws VendingMachinePersistenceException {
            loadInventory();
            return items.get(itemID);
    }

    @Override
    public Item removeItem(String itemID) 
        throws VendingMachinePersistenceException {
            Item removedItem = items.remove(itemID);
            writeInventory();
            return removedItem;
    }

    @Override
    public Item editItem(String itemID, Item item) 
        throws VendingMachinePersistenceException {
            Item editItem = items.put(itemID, item);
            writeInventory();
            return editItem;
    }
    
    
    private void loadInventory() throws VendingMachinePersistenceException {
	    Scanner scanner;
	    
	    try {
	        // Create Scanner for reading the file
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(INVENTORY_FILE)));
	    } catch (FileNotFoundException e) {
	        throw new VendingMachinePersistenceException (
	                "-_- Could not load roster data into memory.", e);
	    }
	    String currentLine;
	  
	    String[] currentTokens;

	    while (scanner.hasNextLine()) {
	        currentLine = scanner.nextLine();
	        currentTokens = currentLine.split(DELIMITER);

	        Item currentItem = new Item(currentTokens[0]);
	        // Set the remaining vlaues on currentStudent manually
	        currentItem.setName(currentTokens[1]);
	        currentItem.setCost(formatBigDecimal(currentTokens[2]));
	        currentItem.setCount(formatInt(currentTokens[3]));
	        
	        // Put currentStudent into the map using studentID as the key
                items.put(currentItem.getItemID(), currentItem);
	    }
	    // close scanner
	    scanner.close();
	}
    
    /**
	 * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
	 * for file format.
	 * 
	 * @throws ClassRosterDaoException if an error occurs writing to the file
	 */
	private void writeInventory() throws VendingMachinePersistenceException {
	   
	    PrintWriter out;
	    
	    try {
	        out = new PrintWriter(new FileWriter(INVENTORY_FILE));
	    } catch (IOException e) {
	        throw new VendingMachinePersistenceException(
	                "Could not save student data.", e);
	    }
	    
	
	    List<Item> itemList = this.getAllItems();
	    for (Item currentItem : itemList) {
	        out.println(currentItem.getItemID() + DELIMITER
	                + currentItem.getName() + DELIMITER 
	                + currentItem.getCost() + DELIMITER
	                + currentItem.getCount());
	        out.flush();
	    }

	    out.close();
	}
    
    public int formatInt(String currentToken) {
        int currentInt = Integer.parseInt(currentToken);
        return currentInt;
    }
    
    public BigDecimal formatBigDecimal(String currentToken) {
        BigDecimal currentBigDecimal = new BigDecimal(currentToken);
        return currentBigDecimal;
    }

   
    
}
