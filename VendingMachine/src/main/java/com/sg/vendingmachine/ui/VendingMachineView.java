/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author rianu
 */
public class VendingMachineView {
    private UserIO io;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print(" **** MAIN MENU **** ");
        io.print("1. List Items");
        io.print("2. Add Money");
        io.print("3. Purchase Item");
        io.print("4. Refund & Exit");
        io.print("5. Admin Functions");

        return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    public int printAdminMenuAndGetSelection() {
        io.print("=== Admin Menu ===");
        io.print("1. Add Item");
        io.print("2. Remove Item");
        io.print("3. View Item");
        io.print("4. Edit Item");
        io.print("5. EXIT");
        return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    public boolean checkPassword() {
        String userAttempt = io.readString("Please enter a password: ");
        return io.checkAdminPassword(userAttempt, "openadmin");
    }
    
    public BigDecimal getAddMoney() {
        BigDecimal money = io.readBigDecimal("Please enter the amount of "
                + "money you would like to add");
        
        return money;
    }
    
    public void displayChange(Change changeDue) {
        io.print("=== Change Due ===");
        io.print("Quarters: " + changeDue.getQuarter());
        io.print("Dimes   : " + changeDue.getDime());
        io.print("Nickels : " + changeDue.getNickel());
        io.print("Pennies : " + changeDue.getPenny());
        io.print(" ");
    }
    
    public Item getNewItemInfo() {
        String itemID = io.readString("Please enter Item ID");
        String name = io.readString("Please enter name of item");
        BigDecimal cost = io.readBigDecimal("Please enter cost of item in format $0.00");
        int count = io.readInt("Please enter inventory level");
        Item currentItem = new Item(itemID);
        currentItem.setName(name);
        currentItem.setCost(cost);
        currentItem.setCount(count);
        return currentItem;   
    }
    
    
    public void displayItemList(List<Item> itemList) {
        for (Item currentItem : itemList ) {
            io.print(currentItem.getItemID() + ": " + currentItem.getName() +  
                " Cost: $"  +  currentItem.getCost() + " Inventory: " + 
                    currentItem.getCount());
        }
    }
    
    public String getItemIDChoice() {
        return io.readString("Please enter the Item ID");
    }
    
    public void displayItem(Item item) {
        if (item != null) {
            io.print(item.getItemID() + ": " + item.getName() +  
                " Cost: $"  +  item.getCost() + " Inventory: " + 
                    item.getCount());
            io.print(" ");
        } else {
            io.print("No such item.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayAddItemBanner() {
        io.print("=== Add Item ===");
    }
    
    public void displayAddSuccessBanner(){
        io.readString("Item successfully added. Please hit enter to continue");
    }
    
    public void displayAllItemsBanner() {
        io.print("=== List All Items === ");
    }
    
    public void displayItemBanner() {
        io.print("=== View Item === ");
    }
    
    public void displayRemoveItemBanner() {
        io.print("=== Remove Item ===");
    }
    
    public void displayItemRemoveSuccessBanner() {
        io.readString("Item successfully removed. Please hit enter to continue.");
    }
    
    public void displayEditItemBanner(){
        io.print("=== Edit Item ===");
    }
    
    public void displayEditItemSuccessBanner(){
        io.readString("Item successsfully edited. Please hit enter to continue");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void addMoneyBanner() {
        io.print("=== Add Money === ");
    }
    
    public void addMoneySuccessBanner() {
        io.readString("Money successfully added. Please hit enter to continue");
    }
    
    public void displayErrorMessage(String errorMsg) {
	    io.print("=== ERROR ===");
	    io.print(errorMsg);
    }
    
    public void displayPurchaseItemBanner() {
        io.print("=== Purchase Item ===");
    }
    
    public void displayRefundBanner() {
        io.print("=== Refund ===");
    }
    
    public void errorIncorrectPass() {
        io.print("Incorrect Password!");
    }
    
 }
