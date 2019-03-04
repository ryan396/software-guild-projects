/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author rianu
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    BigDecimal currentBalance = new BigDecimal("0.00");
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, 
            VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
     
    @Override
    public Item addItem(String itemID, Item item) throws VendingMachineDuplicateIDException, VendingMachineDataValidationException, VendingMachinePersistenceException {
         
        if (dao.getItem(item.getItemID()) != null) {
            throw new VendingMachineDuplicateIDException (
                "ERROR: Could not create item. Item ID " + item.getItemID() + 
                        " already exists");
        }
        
        validateItemData(itemID, item);
        
        Item newItem = dao.addItem(item.getItemID(), item);
        
        
        //auditDao.writeAuditEntry(
        //    "Item " + item.getItemID() + " CREATED.");
        
        return newItem;
    
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String itemID) throws VendingMachinePersistenceException {
        return dao.getItem(itemID);
    }

    @Override
    public Item removeItem(String itemID) throws VendingMachinePersistenceException {
        //auditDao.writeAuditEntry("Item " + itemID + " REMOVED");
        return dao.removeItem(itemID);
    }
    
    @Override
    public Item editItem(String itemID, Item item) throws VendingMachinePersistenceException {
        //auditDao.writeAuditEntry("Item " + itemID + " EDITED");
        return dao.editItem(itemID, item);
    }
   
    
    public void validateItemData(String itemID, Item item) throws
            VendingMachineDataValidationException {
        if (item.getItemID() == null
            || item.getItemID().trim().length() == 0
            || item.getName() == null
            || item.getName().trim().length() == 0
            || item.getCost().intValue() < 0 
            || item.getCount() < 0) {
            
            throw new VendingMachineDataValidationException (
                "ERROR: All fields [itemID, Name, Cost, Count] are required. Count and cost"
                        + " must also be greater than 0.");
        }
    }
    
    public void validateMoneyData(BigDecimal money) throws
            VendingMachineInvalidMoneyAmountException {
        
        if (money.compareTo(new BigDecimal("0")) == -1 
                || money.compareTo(new BigDecimal("0")) == 0) {
            throw new VendingMachineInvalidMoneyAmountException (
            "ERROR: Money entered must be greater than 0.");
        }
    }
    
    public void validateSufficientFunds(String itemID, BigDecimal money) throws
            VendingMachineInsufficientFundsException, VendingMachinePersistenceException {
        
        if (money.compareTo(dao.getItem(itemID).getCost()) == -1) {
            throw new VendingMachineInsufficientFundsException (
                "Error: Not enough money to purchase item " + itemID +
                    " You only have $" + currentBalance + " in the machine.");
        }
    }
        
    
    
    
    public void validateItemInventory(String itemID) throws
            VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        if (dao.getItem(itemID).getCount() == 0) {
            throw new VendingMachineNoItemInventoryException (
                "Error: " + itemID + " inventory is 0. Cannot dispense item.");
        }
    }
    
    @Override
    public BigDecimal addMoney(BigDecimal money) throws 
            VendingMachinePersistenceException,
            VendingMachineInvalidMoneyAmountException {
        validateMoneyData(money);
        currentBalance = currentBalance.add(money);
        //auditDao.writeAuditEntry("MONEY IN THE AMOUNT: $" + money + 
        //        " ADDED TO THE MACHINE");
        
        return currentBalance;
        
    }
    
    

    @Override
    public Change purchaseItem(String itemID) throws 
            VendingMachinePersistenceException, 
            VendingMachineInsufficientFundsException, 
            VendingMachineNoItemInventoryException,
            VendingMachineNoItemExistsException {
        
        if (dao.getItem(itemID) == null) {
            throw new VendingMachineNoItemExistsException (
                "ERROR: Item does not exist.");
        }
        
        BigDecimal money = getBalance();
        
        validateItemInventory(itemID);
        validateSufficientFunds(itemID, money);
        
        BigDecimal remainingChange = ((money.subtract(dao.getItem(itemID).getCost())).multiply(new BigDecimal("100")));
       
        Change changeDue = calculateChange(remainingChange);
        
        //adjust inventory by utilizing edit function
        int currentCount = dao.getItem(itemID).getCount();
        Item editedItem = dao.getItem(itemID);
        editedItem.setName(editedItem.getName());
        editedItem.setCost(editedItem.getCost());
        editedItem.setCount(currentCount - 1);
        dao.editItem(itemID, editedItem);
        //auditDao.writeAuditEntry("Item " + itemID + " PURCHASED");
        return changeDue;
    }
        
    @Override
    public BigDecimal getBalance() {
         return currentBalance;
       }
    
    @Override
    public Change refund() throws VendingMachinePersistenceException {
        BigDecimal balance = getBalance().multiply(new BigDecimal("100"));
        Change changeDue = calculateChange(balance); 
        return changeDue;
    }

    @Override
    public Change calculateChange(BigDecimal moneyInPennies) throws VendingMachinePersistenceException {
        
        int pennies = 0;
        int nickels = 0;
        int dimes = 0;
        int quarters = 0;
        
        BigDecimal pennyValue = new BigDecimal("1.00");
        BigDecimal nickelValue = new BigDecimal("5.00");
        BigDecimal dimeValue = new BigDecimal("10.00");
        BigDecimal quarterValue = new BigDecimal("25.00");
        Change changeDue = new Change();
        
        while(moneyInPennies.compareTo(new BigDecimal("0.00")) == 1) {
            if (moneyInPennies.compareTo(quarterValue) == 1
                    || moneyInPennies.compareTo(quarterValue) == 0){
                quarters++;
                moneyInPennies = moneyInPennies.subtract(quarterValue);
            } else if ((moneyInPennies.compareTo(dimeValue) == 1
                    || moneyInPennies.compareTo(dimeValue) == 0)
                    && (moneyInPennies.compareTo(quarterValue) == -1)) {
                dimes++;
                 moneyInPennies = moneyInPennies.subtract(dimeValue);
            } else if ((moneyInPennies.compareTo(nickelValue) == 1
                    || moneyInPennies.compareTo(nickelValue) == 0)
                    && (moneyInPennies.compareTo(dimeValue) == -1)) {
                nickels++;
                 moneyInPennies = moneyInPennies.subtract(nickelValue);
            } else if ((moneyInPennies.compareTo(pennyValue) == 1
                    || moneyInPennies.compareTo(pennyValue) == 0)
                    && (moneyInPennies.compareTo(nickelValue) == -1)) {
                pennies++;
                moneyInPennies = moneyInPennies.subtract(pennyValue);
            
            }
            changeDue.setQuarter(quarters);
            changeDue.setDime(dimes);
            changeDue.setNickel(nickels);
            changeDue.setPenny(pennies);
        }
        currentBalance = new BigDecimal("0.00");

        return changeDue;
    }
}



