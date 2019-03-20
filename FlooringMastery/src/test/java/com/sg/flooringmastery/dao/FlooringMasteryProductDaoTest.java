/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rianu
 */
public class FlooringMasteryProductDaoTest {
    
    private FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoFileImpl();
    
    public FlooringMasteryProductDaoTest() {
    }
    
    public Product createProduct1() {
        Product product = new Product("Wood");
        product.setCostPerSquareFoot(new BigDecimal("2.00"));
        product.setLaborCostPerSquareFoot(new BigDecimal("3.00"));
        return product;
    }
    
    public Product createProduct2() {
        Product product = new Product("Carpet");
        product.setCostPerSquareFoot(new BigDecimal("3.00"));
        product.setLaborCostPerSquareFoot(new BigDecimal("4.00"));
        return product;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<Product> productList = productDao.getAllProducts();
        for (Product product : productList) {
            productDao.removeProduct(product.getProductType());
        }
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = createProduct1();
        productDao.addProduct(product1.getProductType(), product1);
        Product product2 = createProduct2();
        productDao.addProduct(product2.getProductType(), product2);
        
        assertEquals(2, productDao.getAllProducts().size());
        
    }

    /**
     * Test of addItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetAddProduct() throws Exception {
        Product product1 = createProduct1();
        productDao.addProduct(product1.getProductType(), product1);
        Product fromDao = productDao.getProduct(product1.getProductType());
        assertTrue(fromDao.getProductType().equals("Wood"));
        assertTrue(fromDao.getCostPerSquareFoot().equals((new BigDecimal("2.00"))));
        assertTrue(fromDao.getLaborCostPerSquareFoot().equals((new BigDecimal("3.00"))));
       
    }

    /**
     * Test of removeItem method, of class VendingMachineDao.
     */
    @Test
    public void testRemoveProduct() throws Exception {
        Product product1 = createProduct1();
        productDao.addProduct(product1.getProductType(), product1);
        Product product2 = createProduct2();
        productDao.addProduct(product2.getProductType(), product2);
        
        productDao.removeProduct(product1.getProductType());
        assertEquals(1, productDao.getAllProducts().size());
        assertNull(productDao.getProduct(product1.getProductType()));
        
        productDao.removeProduct(product2.getProductType());
        assertEquals(0, productDao.getAllProducts().size());
        assertNull(productDao.getProduct(product2.getProductType()));
        
    }

    /**
     * Test of editItem method, of class VendingMachineDao.
     */
    @Test
    public void testEditProduct() throws Exception {
        Product product1 = createProduct1();
        productDao.addProduct(product1.getProductType(), product1);
        
        Product productEdit = new Product("Tile");
        productEdit.setCostPerSquareFoot(new BigDecimal("2.50"));
        productEdit.setLaborCostPerSquareFoot(new BigDecimal("4.00"));
        
        productDao.editProduct(product1, productEdit);
        
        assertTrue(productEdit.getProductType().equals("Tile"));
        assertTrue(productEdit.getCostPerSquareFoot().equals((new BigDecimal("2.50"))));
        assertTrue(productEdit.getLaborCostPerSquareFoot().equals((new BigDecimal("4.00"))));
    }

    
}
