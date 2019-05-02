/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author rianu
 */
public interface VendingMachineServiceLayer {
    
    public BigDecimal addMoney(String type);

    
    public String pickItem(int itemId);
    
    public String getItemMessage();
    
    public Change purchaseItem(int itemId);
    
    public Change makeChange();
    
    public Item getItemById(int itemId);
    
    public List<Item> getAllItems();
    
    public BigDecimal getBalance();
    
    public Change getChange();
    
    public String getMessage();
}
