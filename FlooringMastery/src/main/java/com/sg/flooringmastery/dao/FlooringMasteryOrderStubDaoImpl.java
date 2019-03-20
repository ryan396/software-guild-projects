/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rianu
 */
public class FlooringMasteryOrderStubDaoImpl implements FlooringMasteryOrderDao {
    private List<Integer> orderNumbers = new ArrayList<>();
    private Map<LocalDate, List<Order>> orders = new HashMap<>();
    
     private int generateOrderNumber()
     throws FlooringMasteryPersistenceException {
        int newOrderNumber;
        if (orderNumbers.isEmpty()) {
            newOrderNumber = 1;
            orderNumbers.add(newOrderNumber);
        } else {
            int lastOrderNumber = orderNumbers.get(0);
            newOrderNumber = lastOrderNumber + 1;
        }
        
        
        return newOrderNumber;
        
    } 
    
    @Override
    public Order addOrder(LocalDate date, Order order)
     throws FlooringMasteryPersistenceException {
        int orderNumber = generateOrderNumber();
        order.setOrderNumber(orderNumber);
        List<Order> orderList = orders.get(date);
        //check if file exists, if it does, load orders for that date
        if (orderList == null) {
            orderList = new ArrayList();
            orderList.add(order);
            orders.put(date, orderList);
            return order;
        } else {
            orderList.add(order);
            orders.put(date, orderList);
            return order;
        }
        
    }

    @Override
    public List<Order> getAllOrders(LocalDate date)
     throws FlooringMasteryPersistenceException {
        List<Order> orderList = orders.get(date);
        return orderList;
    }
    

    @Override
    public Order removeOrder(LocalDate date, int orderNumber, boolean continueAction)
     throws FlooringMasteryPersistenceException {
        Order removedOrder = null;
        //load orders, extract order from hashmap. if they user wants to continue
        //iterate through orders, checking to find the order that matches the
        //order number they want to remove. once its found, remove the order
        //from the list, put back the ordersList into the order hashmap, and 
        //write the orders and return the removed order
        if (continueAction == true) {
            List<Order> orderList = orders.get(date);
            for (Order currentOrder : orderList) {
                if (currentOrder.getOrderNumber() == orderNumber) {
                    removedOrder = currentOrder;
                    orderList.remove(removedOrder);
                    orders.put(date, orderList);
                    return removedOrder;
                }
            }
        } 
        return removedOrder;
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber, Order editOrder)
     throws FlooringMasteryPersistenceException {
        List<Order> orderList = orders.get(date);
        //iterate through the orderList extracted from the hashmap and find
        //the order number of the one they would like to edit. once found,
        //remove the current one from the list and add the edited order
        //information back in the list.
        //the service will contain logic to keep the information the same if
        //the user leaves fields blank, along with keeping the order number 
        //the same
        for (Order currentOrder : orderList) {
                if (currentOrder.getOrderNumber() == orderNumber) {
                    orderList.add(editOrder);
                    orderList.remove(currentOrder);
                    orders.put(date, orderList);
                    //writeOrders(date);
                    return editOrder;
                }
    }
        return editOrder;
    }
    
    @Override
    public void saveWork()
        throws FlooringMasteryPersistenceException {
        //do nothing for training mode, since no saving is required
    }
}
