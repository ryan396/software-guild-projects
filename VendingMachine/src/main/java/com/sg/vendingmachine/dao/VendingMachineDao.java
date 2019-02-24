/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author rianu
 */
public interface VendingMachineDao {
    
    List<Item> getAllItems()
            throws VendingMachinePersistenceException;
    
    Item addItem(String itemID, Item item)
            throws VendingMachinePersistenceException;
    
    Item getItem(String itemID)
            throws VendingMachinePersistenceException;
    
    Item removeItem(String itemID)
            throws VendingMachinePersistenceException;
    
    Item editItem(String itemID, Item item)
            throws VendingMachinePersistenceException;
            
}
