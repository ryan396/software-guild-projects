/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.VendingMachineDataValidationException;
import com.sg.vendingmachine.service.VendingMachineDuplicateIDException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.util.List;

/**
 *
 * @author rianu
 */
public class VendingMachineAdminController {
    
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    private UserIO io = new UserIOConsoleImpl();
    
    public VendingMachineAdminController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    
    public void adminRun() {
        boolean keepGoing = true;
        int adminMenuSelection = 0;
        boolean passwordCheck = checkPassword();
        try {
            
        while(keepGoing) {
                if(passwordCheck==true){
                    adminMenuSelection = getAdminMenuAndGetSelection();
                    switch(adminMenuSelection) {
                        case 1:
                            addItem();
                            break;
                        case 2:
                            removeItem();
                            break;
                        case 3:
                            viewItem();
                            break;
                        case 4:
                            editItem();
                            break;
                        case 5:
                            keepGoing = false;
                            break;
                        default:
                            unknownCommand();
                            break;
                    }
                } else {
                    view.errorIncorrectPass();
                    return;
                }
            }
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
     private int getAdminMenuAndGetSelection() {
        return view.printAdminMenuAndGetSelection();
    }
    
    private boolean checkPassword() {
        return view.checkPassword();
    }
    
    private void incorrectPass() {
        view.errorIncorrectPass();
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
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    
}
