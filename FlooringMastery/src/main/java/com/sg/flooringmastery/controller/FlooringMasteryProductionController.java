/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.FlooringMasteryInvalidOrderNumberException;
import com.sg.flooringmastery.service.FlooringMasteryInvalidProductException;
import com.sg.flooringmastery.service.FlooringMasteryInvalidStateException;
import com.sg.flooringmastery.service.FlooringMasteryOrderDataValidationException;
import com.sg.flooringmastery.service.FlooringMasteryOrderServiceLayer;
import com.sg.flooringmastery.userio.FlooringMasteryView;
import com.sg.flooringmastery.userio.UserIO;
import com.sg.flooringmastery.userio.UserIOConsoleImpl;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rianu
 */
public class FlooringMasteryProductionController {
    FlooringMasteryView view;
    FlooringMasteryOrderServiceLayer orderService;
    
    public FlooringMasteryProductionController(FlooringMasteryOrderServiceLayer orderService, 
            FlooringMasteryView view) {
        this.orderService = orderService;
        this.view = view;
    }
    
    private UserIO io = new UserIOConsoleImpl();
  

    public void productionRun() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
        while (keepGoing) {

            menuSelection = getProductionMenuSelection();

            switch (menuSelection) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    removeOrder();
                    break;
                case 4:
                    editOrder();
                    break;
                case 5:
                    saveWork();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getProductionMenuSelection() {
        return view.printProductionMenuAndGetSelection();
    }
    
    private void addOrder() throws FlooringMasteryPersistenceException {
        view.displayAddOrderBanner();
        boolean hasErrors = false;
        do {
            LocalDate date = view.getDate();
            Order newOrder = view.getNewOrderInfo();
            try {
                orderService.addOrder(date, newOrder);
                view.displayAddOrderSuccessBanner();
                hasErrors = false;
            } catch (FlooringMasteryOrderDataValidationException 
                    | FlooringMasteryInvalidProductException
                    | FlooringMasteryInvalidStateException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while(hasErrors);
        
    }
    
    private void displayOrders() throws FlooringMasteryPersistenceException {
        view.displayOrderListBanner();
        LocalDate date = view.getDate();
        List<Order> orderList = orderService.getAllOrders(date);
        view.displayOrderList(orderList);  
  
    }
    
    private void removeOrder() throws FlooringMasteryPersistenceException {
        view.removeOrderBanner();
        boolean hasErrors = false;
        do {
            LocalDate date = view.getDate();
            try {
                List<Order> orderList = orderService.getAllOrders(date);
                view.displayOrderList(orderList);
                int orderNum = view.getOrderNumRem();
                boolean continueAction = view.askIfSureRemove();
                orderService.removeOrder(date, orderNum, continueAction);
                view.removeOrderSuccessBanner(continueAction);
                hasErrors = false;
            } catch (FlooringMasteryInvalidOrderNumberException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while(hasErrors); 
    }
    
    private void editOrder() throws FlooringMasteryPersistenceException {
        view.editOrderBanner();
        boolean hasErrors = false;
        do {
        LocalDate date = view.getDate();
            try {
                List<Order> orderList = orderService.getAllOrders(date);
                view.displayOrderList(orderList);
                int editNum = view.getOrderNumEdit();
                Order editOrder = view.getEditOrderInfo(editNum);
                orderService.editOrder(date, editNum, editOrder);
                view.displayEditProductSuccessBanner();
                hasErrors = false;
        } catch (FlooringMasteryOrderDataValidationException 
                    | FlooringMasteryInvalidProductException
                    | FlooringMasteryInvalidOrderNumberException
                    | FlooringMasteryInvalidStateException e) {
                view.displayErrorMessage(e.getMessage());
                hasErrors = true;
                
            }
        } while(hasErrors);
    }
    
    private void saveWork() throws FlooringMasteryPersistenceException {
        view.saveWorkBanner();
        orderService.saveWork();
        view.saveWorkSuccessBanner();
    }
        
}
