/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author rianu
 */
public class FlooringMasteryProductStubDaoImpl implements FlooringMasteryProductDao {
    
    private Map<String, Product> products = new HashMap<>();

    
    @Override
    public Product addProduct(String productName, Product product) 
     throws FlooringMasteryPersistenceException {
        Product newProduct = products.put(productName, product);
        return newProduct;
    }
   

    @Override
    public List<Product> getAllProducts() 
     throws FlooringMasteryPersistenceException {
        return new ArrayList<Product>(products.values());
    }

    @Override
    public Product removeProduct(String productName)
     throws FlooringMasteryPersistenceException {
        Product removedProduct = products.remove(productName);
        return removedProduct;
    }

    @Override
    public Product editProduct(Product product, Product editProduct)
     throws FlooringMasteryPersistenceException {
        products.remove(product.getProductType());
        products.put(editProduct.getProductType(), editProduct);
        return editProduct;
    }

    @Override
    public Product getProduct(String productType) throws FlooringMasteryPersistenceException {
       return products.get(productType);
    }
}
