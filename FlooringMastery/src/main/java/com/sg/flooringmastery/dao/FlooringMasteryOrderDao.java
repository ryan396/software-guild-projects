

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rianu
 */
public interface FlooringMasteryOrderDao {
    
    Order addOrder(LocalDate date, Order order)
        throws FlooringMasteryPersistenceException ;
    
    List<Order> getAllOrders(LocalDate date)
        throws FlooringMasteryPersistenceException;
    
    Order removeOrder(LocalDate date, int orderNumber, boolean continueAction)
        throws FlooringMasteryPersistenceException;
    
    Order editOrder(LocalDate date, int orderNumber, Order order)
        throws FlooringMasteryPersistenceException;
    
    void saveWork() throws FlooringMasteryPersistenceException;
    
}
