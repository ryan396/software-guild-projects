/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rianu
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao {
    
   Map<Integer, Item> itemMap = new HashMap<>();
   
    public VendingMachineDaoInMemImpl() {
        itemMap.put(1, new Item(1, "Peanuts", new BigDecimal("1.50"), 0));
        itemMap.put(2, new Item(2, "M&Ms", new BigDecimal("1.25"), 12));
        itemMap.put(3, new Item(3, "Truffles", new BigDecimal("2.50"), 13));
        itemMap.put(4, new Item(4, "Crackers", new BigDecimal("1.50"), 24));
        itemMap.put(5, new Item(5, "Doritos", new BigDecimal("1.00"), 15));
        itemMap.put(6, new Item(6, "Cashews", new BigDecimal("2.50"), 16));
        itemMap.put(7, new Item(7, "Skittles", new BigDecimal("1.50"), 10));
        itemMap.put(8, new Item(8, "Gummy Bears", new BigDecimal("1.00"), 5));
        itemMap.put(9, new Item(9, "Cookies", new BigDecimal("2.75"), 19));
    }
    
    @Override
    public Item getItemById(int itemId){
        return itemMap.get(itemId);
    }

    @Override
    public List<Item> getAllItems() {
        Collection<Item> i = itemMap.values();
        return new ArrayList<>(i);
    }
    
}
