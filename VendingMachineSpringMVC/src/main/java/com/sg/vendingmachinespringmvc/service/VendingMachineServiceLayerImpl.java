/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author rianu
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    private VendingMachineDao dao;
    
    BigDecimal currentBalance = new BigDecimal("0.00");
    String itemMessage = "";
    String message = "";
    Change changeDue = new Change();
    
    @Inject
    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }
    
    
    
    @Override
    public BigDecimal addMoney(String type) {
        if (getMessage().equals("Thank you!")) {
            message = "";
        }
        changeDue.setQuarter(0);
        changeDue.setDime(0);
        changeDue.setNickel(0);
        changeDue.setPenny(0);
        if (type.equals("dollar")) {
            currentBalance = currentBalance.add(new BigDecimal("1.00"));
        } else if (type.equals("quarter")) {
            currentBalance = currentBalance.add(new BigDecimal("0.25"));
        } else if (type.equals("dime")) {
            currentBalance = currentBalance.add(new BigDecimal("0.10"));
        } else if (type.equals("nickel")) {
            currentBalance = currentBalance.add(new BigDecimal("0.05"));
        }
        return currentBalance;
    }

    @Override
    public String pickItem(int itemId) {
        changeDue.setQuarter(0);
        changeDue.setDime(0);
        changeDue.setNickel(0);
        changeDue.setPenny(0);
        message = "";
        itemMessage = Integer.toString(itemId);
        return itemMessage;
    }

    @Override
    public Change makeChange() {
        itemMessage = "";
        BigDecimal moneyInPennies = currentBalance.multiply(new BigDecimal("100"));
        int pennies = 0;
        int nickels = 0;
        int dimes = 0;
        int quarters = 0;
        
        BigDecimal pennyValue = new BigDecimal("1.00");
        BigDecimal nickelValue = new BigDecimal("5.00");
        BigDecimal dimeValue = new BigDecimal("10.00");
        BigDecimal quarterValue = new BigDecimal("25.00");
        
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

    @Override
    public Item getItemById(int itemId) {
        return dao.getItemById(itemId);
    }

    @Override
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public BigDecimal getBalance() {
        return currentBalance;
    }

    @Override
    public String getItemMessage() {
        return itemMessage;
    }

    @Override
    public Change purchaseItem(int itemId) {
        
        BigDecimal money = getBalance();
        
        if (dao.getItemById(itemId).getQuantity() <= 0) {
            message = "SOLD OUT!!!";
        } else if (getItemMessage().equals("")){
            message = "Please select an item.";
        } else {
             if ((money.subtract(dao.getItemById(itemId).getPrice())).compareTo(new BigDecimal("0.00")) == -1) {
                String moneyNeeded = dao.getItemById(itemId).getPrice().subtract(money).toString();
                message = "Please insert: $" + moneyNeeded;
            } else {
                currentBalance = (money.subtract(dao.getItemById(itemId).getPrice()));
                changeDue = makeChange();
                int currentCount = dao.getItemById(itemId).getQuantity();
                Item editedItem = dao.getItemById(itemId);
                editedItem.setQuantity(currentCount - 1);
                message = "Thank you!";
            }
        }

        return changeDue;
    }

    @Override
    public Change getChange() {
        return changeDue;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
    
    
}
