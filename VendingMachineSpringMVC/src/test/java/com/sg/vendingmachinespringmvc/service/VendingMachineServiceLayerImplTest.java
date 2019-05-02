/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rianu
 */
public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("VendingMachineServiceLayer", VendingMachineServiceLayer.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addMoney method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testAddMoneyGetBalance() {
        service.addMoney("dollar");
        service.addMoney("dollar");
        assertEquals(new BigDecimal("2.00"), service.getBalance());
    }

    /**
     * Test of pickItem method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testPickItem() {
        assertEquals(service.pickItem(0), "0");
    }

    /**
     * Test of makeChange method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testMakeChangeGetItemMessage() {
        service.addMoney("dollar");
        service.addMoney("dollar");
        Change change = service.makeChange();
        assertTrue(change.getQuarter() == 8);
        assertTrue(service.getItemMessage().equals(""));
        
        service.addMoney("dime");
        service.addMoney("nickel");
        Change change1 = service.makeChange();
        assertTrue(change1.getNickel() == 1);
        assertTrue(change1.getDime() == 1);
        assertTrue(service.getItemMessage().equals(""));
    }

    /**
     * Test of getItemById method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetItemById() {
        Item item = service.getItemById(1);
        assertNotNull(item);
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetAllItems() {
        List<Item> itemList = service.getAllItems();
        assertTrue(itemList != null);
    }

    /**
     * Test of purchaseItem method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testPurchaseItemGetMessage() {
    }

    /**
     * Test of getChange method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetChange() {
        service.addMoney("dollar");
        service.addMoney("dollar");
        Change change = service.makeChange();
        assertEquals(change, service.getChange());
    }

    
}
