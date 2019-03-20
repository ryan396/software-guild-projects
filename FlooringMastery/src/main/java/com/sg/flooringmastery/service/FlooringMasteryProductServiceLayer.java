/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author rianu
 */
public interface FlooringMasteryProductServiceLayer {
    
    List<Product> getAllProducts() throws 
            FlooringMasteryPersistenceException;
    
    Product addProduct(String productType, Product product)  throws
            FlooringMasteryDuplicateProductException,
            FlooringMasteryProductDataValidationException,
            FlooringMasteryPersistenceException;
    
    Product removeProduct(String productType) throws 
            FlooringMasteryPersistenceException;
    
    Product editProduct(Product product, Product editProduct) throws 
            FlooringMasteryPersistenceException;
    
    Product getProduct(String ProductType) throws
            FlooringMasteryPersistenceException;
}
