/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rianu
 */
public interface FlooringMasteryOrderServiceLayer {
    
    Order addOrder(LocalDate date, Order order)
        throws FlooringMasteryPersistenceException,
               FlooringMasteryInvalidProductException,
               FlooringMasteryInvalidStateException,
               FlooringMasteryOrderDataValidationException;
    
    List<Order> getAllOrders(LocalDate date)
        throws FlooringMasteryPersistenceException;
    
    Order removeOrder(LocalDate date, int orderNumber, boolean continueAction)
        throws FlooringMasteryPersistenceException,
               FlooringMasteryInvalidOrderNumberException;
    
    Order editOrder(LocalDate date, int orderNumber, Order editedOrder)
        throws FlooringMasteryPersistenceException,
               FlooringMasteryInvalidProductException,
               FlooringMasteryInvalidStateException,
               FlooringMasteryOrderDataValidationException,
               FlooringMasteryInvalidOrderNumberException;
    
    void saveWork() throws FlooringMasteryPersistenceException;
    
    Order calculateTotal(Order order)
            throws FlooringMasteryPersistenceException;

}
