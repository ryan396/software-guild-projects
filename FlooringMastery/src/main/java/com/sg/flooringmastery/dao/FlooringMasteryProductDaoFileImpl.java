/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.File;
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
public class FlooringMasteryProductDaoFileImpl implements FlooringMasteryProductDao {
    
    private Map<String, Product> products = new HashMap<>();
    public static final String PRODUCTS_FILE = "Products.txt";
    public static final String HEADER = "ProductType,CostPerSquareFoot,"
            + "LaborCostPerSquareFoot";
    public static final String DELIMITER = ",";
    
    @Override
    public Product addProduct(String productName, Product product) 
     throws FlooringMasteryPersistenceException {
        Product newProduct = products.put(productName, product);
        writeProducts();
        return newProduct;
    }
   

    @Override
    public List<Product> getAllProducts() 
     throws FlooringMasteryPersistenceException {
        loadProducts();
        return new ArrayList<Product>(products.values());
    }

    @Override
    public Product removeProduct(String productName)
     throws FlooringMasteryPersistenceException {
        Product removedProduct = products.remove(productName);
        writeProducts();
        return removedProduct;
    }

    @Override
    public Product editProduct(Product product, Product editProduct)
     throws FlooringMasteryPersistenceException {
        products.remove(product.getProductType());
        products.put(editProduct.getProductType(), editProduct);
        writeProducts();
        return editProduct;
    }
    
    private void loadProducts() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(
                    new FileReader(PRODUCTS_FILE)));
            
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("-_- Could not load product "
                    + "data into memory.", e);
        }
        
        String currentLine;
        
        String[] currentTokens;
        
        
        if(scanner.hasNext()==true){
            scanner.nextLine();
        }
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Product currentProduct = new Product(currentTokens[0]);
            currentProduct.setCostPerSquareFoot(formatBigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPerSquareFoot(formatBigDecimal(currentTokens[2]));
            
            products.put(currentProduct.getProductType(), currentProduct);
        }
        scanner.close();
    }
    
    private void writeProducts() throws FlooringMasteryPersistenceException {
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(PRODUCTS_FILE));
        } catch (IOException e){
            throw new FlooringMasteryPersistenceException(
                "Could not save products data.", e);
        }
        
        List<Product> productsList = this.getAllProducts();
        out.println(HEADER);
        for (Product currentProduct : productsList) {
            out.println(currentProduct.getProductType() + DELIMITER
                    + currentProduct.getCostPerSquareFoot() + DELIMITER
                    + currentProduct.getLaborCostPerSquareFoot());
            out.flush();
        }
        
        out.close();
    }
    
    public BigDecimal formatBigDecimal(String currentToken) {
      BigDecimal currentBigDecimal = new BigDecimal(currentToken);
      return currentBigDecimal;
    }

    @Override
    public Product getProduct(String productType) throws FlooringMasteryPersistenceException {
       loadProducts();
       return products.get(productType);
    }
}
