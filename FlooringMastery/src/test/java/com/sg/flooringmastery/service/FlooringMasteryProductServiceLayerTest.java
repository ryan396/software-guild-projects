/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rianu
 */
public class FlooringMasteryProductServiceLayerTest {
    
    private FlooringMasteryProductServiceLayer productService;
    
    public FlooringMasteryProductServiceLayerTest() {
        /*
        FlooringMasteryProductDao productDao = new FlooringMasteryProductStubDaoImpl();
        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStubImpl();
        
        productService = new FlooringMasteryProductServiceLayerImpl(productDao, auditDao);
        */
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        productService = ctx.getBean("productService", FlooringMasteryProductServiceLayer.class);
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
        List<Product> productList = productService.getAllProducts();
        for (Product product : productList) {
            productService.removeProduct(product.getProductType());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllProducts method, of class FlooringMasteryProductServiceLayer.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = createProduct1();
        productService.addProduct(product1.getProductType(), product1);
        Product product2 = createProduct2();
        productService.addProduct(product2.getProductType(), product2);
        
        assertEquals(2, productService.getAllProducts().size());
    }

    /**
     * Test of addProduct method, of class FlooringMasteryProductServiceLayer.
     */
    @Test
    public void testAddProduct() throws Exception {
        Product product1 = createProduct1();
        productService.addProduct(product1.getProductType(), product1);
        Product fromDao = productService.getProduct(product1.getProductType());
        assertTrue(fromDao.getProductType().equals("Wood"));
        assertTrue(fromDao.getCostPerSquareFoot().equals((new BigDecimal("2.00"))));
        assertTrue(fromDao.getLaborCostPerSquareFoot().equals((new BigDecimal("3.00"))));
    }

    /**
     * Test of removeProduct method, of class FlooringMasteryProductServiceLayer.
     */
    @Test
    public void testRemoveProduct() throws Exception {
        Product product1 = createProduct1();
        productService.addProduct(product1.getProductType(), product1);
        Product product2 = createProduct2();
        productService.addProduct(product2.getProductType(), product2);
        
        productService.removeProduct(product1.getProductType());
        assertEquals(1, productService.getAllProducts().size());
        assertNull(productService.getProduct(product1.getProductType()));
        
        productService.removeProduct(product2.getProductType());
        assertEquals(0, productService.getAllProducts().size());
        assertNull(productService.getProduct(product2.getProductType()));
    }

    /**
     * Test of editProduct method, of class FlooringMasteryProductServiceLayer.
     */
    @Test
    public void testEditProduct() throws Exception {
        Product product1 = createProduct1();
        productService.addProduct(product1.getProductType(), product1);
        
        Product productEdit = new Product("Tile");
        productEdit.setCostPerSquareFoot(new BigDecimal("2.50"));
        productEdit.setLaborCostPerSquareFoot(new BigDecimal("4.00"));
        
        productService.editProduct(product1, productEdit);
        
        assertTrue(productEdit.getProductType().equals("Tile"));
        assertTrue(productEdit.getCostPerSquareFoot().equals((new BigDecimal("2.50"))));
        assertTrue(productEdit.getLaborCostPerSquareFoot().equals((new BigDecimal("4.00"))));
    }
    
     
    @Test
    public void testAddInvalidProduct() throws Exception {
        Product product1 = createProduct1();
        product1.setCostPerSquareFoot(new BigDecimal("-1"));
        
        try {
            productService.addProduct(product1.getProductType(), product1);
            fail("Expected FlooringMasteryDataValidationException was not thrown");
        } catch (FlooringMasteryProductDataValidationException e) {
            return;
        }
    }

}
