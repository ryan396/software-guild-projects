/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.VendingMachineDataValidationException;
import com.sg.vendingmachine.service.VendingMachineDuplicateIDException;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineInvalidMoneyAmountException;
import com.sg.vendingmachine.service.VendingMachineNoItemExistsException;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author rianu
 */
public class VendingMachineController {
    
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    private VendingMachineAdminController adminController;
    private UserIO io = new UserIOConsoleImpl();
    
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view,
    VendingMachineAdminController adminController) {
        this.service = service;
        this.view = view;
        this.adminController = adminController;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        int adminMenuSelection = 0;
        try {
        while(keepGoing) {
            
            menuSelection = getMenuSelection();
            
            switch (menuSelection) {
                case 1:
                    listItems();
                    break;
                case 2:
                    addMoney();
                    break;
                case 3:
                    purchaseItem();
                    break;
                case 4:
                    refund();
                    keepGoing = false;
                    break;
                case 5:
                    adminController.adminRun();
                    break;
                default:
                    unknownCommand();
            }
        }
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private int getAdminMenuAndGetSelection() {
        return view.printAdminMenuAndGetSelection();
    }
    
    private boolean checkPassword() {
        return view.checkPassword();
    }
    
    private void addItem() throws VendingMachinePersistenceException {
        view.displayAddItemBanner();
        boolean hasErrors = false;
        do {
        Item newItem = view.getNewItemInfo();
        try {
            service.addItem(newItem.getItemID(), newItem);
            view.displayAddSuccessBanner();
            hasErrors = false;
        } catch (VendingMachineDuplicateIDException | VendingMachineDataValidationException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }
        } while (hasErrors);
    }
    
    public void addMoney() throws VendingMachinePersistenceException {
        view.displayAddItemBanner();
        boolean hasErrors = false;
        do {
            BigDecimal money = view.getAddMoney();
        try {
            service.addMoney(money);
            view.addMoneySuccessBanner();
            hasErrors = false;
        } catch (VendingMachineInvalidMoneyAmountException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }
        } while(hasErrors);
    }
    private void listItems() throws VendingMachinePersistenceException {
        view.displayAllItemsBanner();
        List<Item> itemList = service.getAllItems();
        view.displayItemList(itemList);
    }
    
    private void viewItem() throws VendingMachinePersistenceException {
        view.displayItemBanner();
        String itemID = view.getItemIDChoice();
        Item item = service.getItem(itemID);
        view.displayItem(item);
    }
    
    private void removeItem() throws VendingMachinePersistenceException {
        view.displayRemoveItemBanner();
        String itemID = view.getItemIDChoice();
        service.removeItem(itemID);
        view.displayItemRemoveSuccessBanner();
    } 
    
    public void editItem() throws VendingMachinePersistenceException {
        view.displayEditItemBanner();
        String itemID = view.getItemIDChoice();
        Item editItem = view.getNewItemInfo();
        service.editItem(itemID, editItem);
        view.displayEditItemSuccessBanner();
    }
    
    public void purchaseItem() throws 
            VendingMachinePersistenceException {
        view.displayPurchaseItemBanner();
        
        boolean hasErrors = false;
        do {
             String itemID = view.getItemIDChoice();
        try {
            view.displayChange(service.purchaseItem(itemID));
            hasErrors = false;
        } catch (VendingMachinePersistenceException 
           | VendingMachineInsufficientFundsException 
           | VendingMachineNoItemExistsException
           | VendingMachineNoItemInventoryException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
            return;
        }
        } while(hasErrors);
        
    }
    
    private void refund() throws VendingMachinePersistenceException {
        view.displayRefundBanner();
        Change change = service.refund();
        view.displayChange(change);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
}
