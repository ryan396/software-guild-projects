/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author rianu2
 */
public interface VendingMachineServiceLayer {
    
    Item addItem(String itemID, Item item) throws
            VendingMachineDuplicateIDException,
            VendingMachineDataValidationException,
            VendingMachinePersistenceException;
    
    List<Item> getAllItems() throws
            VendingMachinePersistenceException;
    
    Item getItem(String itemID) throws
            VendingMachinePersistenceException;
    
    Item removeItem(String itemID) throws
            VendingMachinePersistenceException;
    
    Item editItem(String itemID, Item item) 
            throws VendingMachinePersistenceException; 
    
    BigDecimal addMoney(BigDecimal money) throws
            VendingMachinePersistenceException,
            VendingMachineInvalidMoneyAmountException; 
    
            
    Change purchaseItem(String itemID) throws
            VendingMachinePersistenceException,
            VendingMachineInsufficientFundsException,
            VendingMachineNoItemExistsException,
            VendingMachineNoItemInventoryException;
    
    BigDecimal getBalance() throws
            VendingMachinePersistenceException;
    
    Change refund() throws
            VendingMachinePersistenceException;
    
    Change calculateChange(BigDecimal moneyInPennies) throws
            VendingMachinePersistenceException;
}
