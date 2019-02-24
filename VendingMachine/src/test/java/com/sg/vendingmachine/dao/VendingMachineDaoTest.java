/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
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
public class VendingMachineDaoTest {
    
    private VendingMachineDao dao = new VendingMachineDaoFileImpl();
    
    public VendingMachineDaoTest() {
    }
    
    public Item createTestItem1() {
        Item item = new Item("0001");
        item.setName("Peanuts");
        item.setCost(new BigDecimal("1.50"));
        item.setCount(5);
        return item;
    }
    
      public Item createTestItem2() {
        Item item = new Item("0002");
        item.setName("Cookies");
        item.setCost(new BigDecimal("2.50"));
        item.setCount(3);
        return item;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<Item> itemList = dao.getAllItems();
        for (Item item : itemList) {
            dao.removeItem(item.getItemID());
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() throws Exception {
        Item item1 = createTestItem1();
        dao.addItem(item1.getItemID(), item1);
        Item item2 = createTestItem2();
        dao.addItem(item2.getItemID(), item2);
        
        assertEquals(2, dao.getAllItems().size());
        
    }

    /**
     * Test of addItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetAddItem() throws Exception {
        Item item = createTestItem1();
        dao.addItem(item.getItemID(), item);
        Item fromDao = dao.getItem(item.getItemID());
        assertEquals(item, fromDao);
       
    }

    /**
     * Test of removeItem method, of class VendingMachineDao.
     */
    @Test
    public void testRemoveItem() throws Exception {
        Item item1 = createTestItem1();
        dao.addItem(item1.getItemID(), item1);
        Item item2 = createTestItem2();
        dao.addItem(item2.getItemID(), item2);
        
        dao.removeItem(item1.getItemID());
        assertEquals(1, dao.getAllItems().size());
        assertNull(dao.getItem(item1.getItemID()));
        
        dao.removeItem(item2.getItemID());
        assertEquals(0, dao.getAllItems().size());
        assertNull(dao.getItem(item2.getItemID()));
        
    }

    /**
     * Test of editItem method, of class VendingMachineDao.
     */
    @Test
    public void testEditItem() throws Exception {
        Item item1 = createTestItem1();
        dao.addItem(item1.getItemID(), item1);
        
        Item itemEdit = new Item("0004");
        itemEdit.setName("Juice");
        itemEdit.setCost(new BigDecimal("4.00"));
        itemEdit.setCount(50);
        
        dao.editItem(item1.getItemID(), itemEdit);
        
        assertTrue(itemEdit.getItemID().equals("0004"));
        assertTrue(itemEdit.getName().equals("Juice"));
        assertTrue(itemEdit.getCost().equals(new BigDecimal("4.00")));
        assertTrue(itemEdit.getCount() == 50);
    }

    
}
