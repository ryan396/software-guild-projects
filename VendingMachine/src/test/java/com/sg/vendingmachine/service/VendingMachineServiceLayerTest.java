/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
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
public class VendingMachineServiceLayerTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerTest() {
        /*
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
        */
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
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
        List<Item> itemList = service.getAllItems();
            for (Item item : itemList) {
                service.removeItem(item.getItemID());
        }
    }
    
    @After
    public void tearDown() {
    }

        /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() throws Exception {
        Item item1 = createTestItem1();
        service.addItem(item1.getItemID(), item1);
        Item item2 = createTestItem2();
        service.addItem(item2.getItemID(), item2);
        
        assertEquals(2, service.getAllItems().size());
        
    }

    /**
     * Test of addItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetAddItem() throws Exception {
        Item item = createTestItem1();
        service.addItem(item.getItemID(), item);
        Item fromDao = service.getItem(item.getItemID());
        assertEquals(item, fromDao);
       
    }

    /**
     * Test of removeItem method, of class VendingMachineDao.
     */
    @Test
    public void testRemoveItem() throws Exception {
        Item item1 = createTestItem1();
        service.addItem(item1.getItemID(), item1);
        Item item2 = createTestItem2();
        service.addItem(item2.getItemID(), item2);
        
        service.removeItem(item1.getItemID());
        assertEquals(1, service.getAllItems().size());
        assertNull(service.getItem(item1.getItemID()));
        
        service.removeItem(item2.getItemID());
        assertEquals(0, service.getAllItems().size());
        assertNull(service.getItem(item2.getItemID()));
        
    }

    /**
     * Test of editItem method, of class VendingMachineDao.
     */
    @Test
    public void testEditItem() throws Exception {
        Item item1 = createTestItem1();
        service.addItem(item1.getItemID(), item1);
        
        Item itemEdit = new Item("0004");
        itemEdit.setName("Juice");
        itemEdit.setCost(new BigDecimal("4.00"));
        itemEdit.setCount(50);
        
        service.editItem(item1.getItemID(), itemEdit);
        
        assertTrue(itemEdit.getItemID().equals("0004"));
        assertTrue(itemEdit.getName().equals("Juice"));
        assertTrue(itemEdit.getCost().equals(new BigDecimal("4.00")));
        assertTrue(itemEdit.getCount() == 50);
    }
    
    @Test
    public void testGetAddMoney() throws Exception {
        BigDecimal moneyAdded = new BigDecimal("2.00");
        service.addMoney(moneyAdded);
        assertEquals(moneyAdded, service.getBalance());
    }
    
    @Test
    public void testRefund() throws Exception {
        BigDecimal balance = new BigDecimal("2.00");
        service.addMoney(balance);
        Change changeDueExpected = new Change();
        changeDueExpected.setQuarter(8);
        changeDueExpected.setDime(0);
        changeDueExpected.setNickel(0);
        changeDueExpected.setPenny(0);
        
        Change changeDueTest = service.refund();
        assertTrue(changeDueExpected.getQuarter() == changeDueTest.getQuarter());
        assertTrue(changeDueExpected.getDime() == changeDueTest.getDime());
        assertTrue(changeDueExpected.getNickel() == changeDueTest.getNickel());
        assertTrue(changeDueExpected.getPenny() == changeDueTest.getPenny());
    }
    
    @Test
    public void testCalculateChange() throws Exception {
        BigDecimal changeInPennies = new BigDecimal("200");
        Change changeDueExpected = new Change();
        changeDueExpected.setQuarter(8);
        changeDueExpected.setDime(0);
        changeDueExpected.setNickel(0);
        changeDueExpected.setPenny(0);
        
        Change changeDueTest = service.calculateChange(changeInPennies);
        assertTrue(changeDueExpected.getQuarter() == changeDueTest.getQuarter());
        assertTrue(changeDueExpected.getDime() == changeDueTest.getDime());
        assertTrue(changeDueExpected.getNickel() == changeDueTest.getNickel());
        assertTrue(changeDueExpected.getPenny() == changeDueTest.getPenny());
        
        BigDecimal changeInPennies2 = new BigDecimal("18");
        Change changeDueExpected2 = new Change();
        changeDueExpected2.setQuarter(0);
        changeDueExpected2.setDime(1);
        changeDueExpected2.setNickel(1);
        changeDueExpected2.setPenny(3);
        
        Change changeDueTest2 = service.calculateChange(changeInPennies2);
        assertTrue(changeDueExpected2.getQuarter() == changeDueTest2.getQuarter());
        assertTrue(changeDueExpected2.getDime() == changeDueTest2.getDime());
        assertTrue(changeDueExpected2.getNickel() == changeDueTest2.getNickel());
        assertTrue(changeDueExpected2.getPenny() == changeDueTest2.getPenny());
    }
    
    @Test
    public void testAddInvalidDataItem() throws Exception {
        Item item1 = createTestItem1();
        item1.setName("");
        
        try {
            service.addItem(item1.getItemID(), item1);
            fail("Expected VendingMachineDataValidationException was not thrown");
        } catch (VendingMachineDataValidationException e) {
            return;
        }
    }
    
    @Test
    public void testAddInvalidMoneyAmount() throws Exception {
        BigDecimal invalidMoney = new BigDecimal("-1.00");
        
        try {
            service.addMoney(invalidMoney);
            fail("Expected VendingMachineInvalidMoneyAmountException was not thrown");
        } catch (VendingMachineInvalidMoneyAmountException e) {
            return;
        }
    }
    
    @Test
    public void testValidateSufficientFunds() throws Exception {
        Item item1 = createTestItem1();
        service.addItem(item1.getItemID(), item1);
        BigDecimal balance = new BigDecimal("1.00");
        service.addMoney(balance);
        
        try {
            service.purchaseItem("0001");
            fail("Expected VendingMachineInsufficientFundsException was not thrown");
        } catch (VendingMachineInsufficientFundsException e) {
            return;
        }
    }
    
    @Test
    public void testValidateItemInventory() throws Exception {
        Item item1 = createTestItem1();
        item1.setCount(0);
        service.addItem(item1.getItemID(), item1);
        try {
            service.purchaseItem(item1.getItemID());
            fail("Expected VendingMachineNoItemInventoryException was not thrown");
        } catch (VendingMachineNoItemInventoryException e){
            return;
        }
    }
    
    @Test
    public void testPurchaseItem() throws Exception {
        Item item1 = createTestItem1();
        service.addItem(item1.getItemID(), item1);
        service.addMoney(new BigDecimal("2.00"));
        
        BigDecimal remainingMoneyInPennies = (service.getBalance()
                .subtract(item1.getCost())).multiply(new BigDecimal("100"));
        
        Change changeDue = service.purchaseItem(item1.getItemID());
        Change changeDueExpected = new Change();
        changeDueExpected.setQuarter(2);
        changeDueExpected.setDime(0);
        changeDueExpected.setNickel(0);
        changeDueExpected.setPenny(0);
        //check to make sure that change is as expected after purchasing item
        assertTrue(changeDueExpected.getQuarter() == changeDue.getQuarter());
        assertTrue(changeDueExpected.getDime() == changeDue.getDime());
        assertTrue(changeDueExpected.getNickel() == changeDue.getNickel());
        assertTrue(changeDueExpected.getPenny() == changeDue.getPenny());
        
        //check to see if program reset balance to 0
        assertEquals(service.getBalance(), new BigDecimal("0.00"));
        //check to see if the inventory of item1 was decreased by 1
        assertEquals(4, item1.getCount());
        
    }
}
