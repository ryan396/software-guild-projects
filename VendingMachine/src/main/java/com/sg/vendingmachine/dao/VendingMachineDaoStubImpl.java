/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rianu
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    private Map<String, Item> items = new HashMap<>();
    private BigDecimal currentBalance; 
    
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return new ArrayList<Item>(items.values());
    }

    @Override
    public Item addItem(String itemID, Item item) throws VendingMachinePersistenceException {
        Item newItem = items.put(itemID, item);
        return newItem;
    }

    @Override
    public Item getItem(String itemID) throws VendingMachinePersistenceException {
        return items.get(itemID);
    }

    @Override
    public Item removeItem(String itemID) throws VendingMachinePersistenceException {
        Item removedItem = items.remove(itemID);
        return removedItem;
    }

    @Override
    public Item editItem(String itemID, Item item) throws VendingMachinePersistenceException {
        Item editItem = items.put(itemID, item);
        return editItem;
    }
    
    
}
