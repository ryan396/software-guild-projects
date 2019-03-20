/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryProductDao;
import com.sg.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author rianu
 */
public class FlooringMasteryProductServiceLayerImpl implements FlooringMasteryProductServiceLayer {
    
    FlooringMasteryProductDao dao;
    private FlooringMasteryAuditDao auditDao;
    
    public FlooringMasteryProductServiceLayerImpl(FlooringMasteryProductDao dao, 
            FlooringMasteryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public List<Product> getAllProducts() throws FlooringMasteryPersistenceException {
        return dao.getAllProducts();
    }

    @Override
    public Product addProduct(String productType, Product product) throws
            FlooringMasteryDuplicateProductException, 
            FlooringMasteryProductDataValidationException, 
            FlooringMasteryPersistenceException {
        
        if (dao.getProduct(productType) != null) {
            throw new FlooringMasteryDuplicateProductException(
                "ERROR: Could not add product. Product type " + product.getProductType()
                + " already exists.");
        }
        
        validateProductData(product);
        
        Product newProduct = dao.addProduct(productType, product);
        //auditDao.writeAuditEntry("Product " + product.getProductType() + " CREATED");
        
        return newProduct;
       
    }
    
    @Override
    public Product removeProduct(String productType) throws 
            FlooringMasteryPersistenceException {
        //auditDao.writeAuditEntry("Product " + productType + " REMOVED");
        return dao.removeProduct(productType);
    }

    @Override
    public Product editProduct(Product product, Product EditProduct) throws 
            FlooringMasteryPersistenceException {
        //auditDao.writeAuditEntry("Product " + product.getProductType() + " EDITED");
        return dao.editProduct(product, EditProduct);
    }
    
    private void validateProductData(Product product) throws
            FlooringMasteryProductDataValidationException {

         if (product.getProductType() == null
                || product.getProductType().trim().length() == 0
                || product.getCostPerSquareFoot() == null
                || product.getCostPerSquareFoot().intValue() < 0
                || product.getLaborCostPerSquareFoot() == null
                || product.getCostPerSquareFoot().intValue() < 0) {
            
            throw new FlooringMasteryProductDataValidationException(
                    "ERROR: All fields [ProductType, CostPerSquareFoot, "
                            + "LaborCostPerSquareFoot] are required and"
                            + " CostPerSquareFoot and LaborCostPerSquareFoot must"
                            + " be greater than 0.");
        }
    
    }

    @Override
    public Product getProduct(String ProductType) throws FlooringMasteryPersistenceException {
        return dao.getProduct(ProductType);
    }
}