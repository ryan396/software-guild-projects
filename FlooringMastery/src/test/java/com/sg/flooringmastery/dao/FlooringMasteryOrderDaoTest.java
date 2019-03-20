/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import static com.sg.flooringmastery.dao.FlooringMasteryOrderDaoFileImpl.ORDERS_FILE;
import com.sg.flooringmastery.dto.Order;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class FlooringMasteryOrderDaoTest {
    
    private FlooringMasteryOrderDao orderDao = 
            new FlooringMasteryOrderDaoFileImpl();
    
    public FlooringMasteryOrderDaoTest() {
    }
    
    
    public Order createOrder1() {
        Order order = new Order();
        order.setCustomerName("Ryan");
        
        order.setProductType("Carpet");
        
        BigDecimal area = new BigDecimal("100");
        order.setArea(area);
        
        order.setState("OH");
        
        BigDecimal costPerSquareFoot = new BigDecimal("2.25");
        order.setCostPerSquareFoot(costPerSquareFoot);
       
        BigDecimal laborCostPerSquareFoot = new BigDecimal("2.10");
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        
        BigDecimal materialCost = area.multiply(costPerSquareFoot).setScale(2,RoundingMode.HALF_UP);
        order.setMaterialCost(materialCost);
        
        BigDecimal laborCost = area.multiply(laborCostPerSquareFoot).setScale(2,RoundingMode.HALF_UP);
        order.setLaborCost(laborCost);
        
        BigDecimal taxRate = new BigDecimal("6.25");
        order.setTaxRate(taxRate);
        BigDecimal taxRatePercentage = taxRate.divide(new BigDecimal("100"))
                .setScale(2, RoundingMode.HALF_UP);
        
        
        BigDecimal tax = laborCost.add(materialCost).multiply(taxRatePercentage)
                .setScale(2, RoundingMode.HALF_UP);
        order.setTax(tax);
        
        BigDecimal total = laborCost.add(materialCost).add(tax);
        order.setTotal(total);
        
        return order;
        
    }
    
    public Order createOrder2() {
        
        Order order = new Order();
        order.setCustomerName("Bob");
        
        order.setProductType("Wood");
        
        BigDecimal area = new BigDecimal("100");
        order.setArea(area);
        
        order.setState("OH");
        
        BigDecimal costPerSquareFoot = new BigDecimal("2.25");
        order.setCostPerSquareFoot(costPerSquareFoot);
       
        BigDecimal laborCostPerSquareFoot = new BigDecimal("2.10");
        order.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        
        BigDecimal materialCost = area.multiply(costPerSquareFoot).setScale(2,RoundingMode.HALF_UP);
        order.setMaterialCost(materialCost);
        
        BigDecimal laborCost = area.multiply(laborCostPerSquareFoot).setScale(2,RoundingMode.HALF_UP);
        order.setLaborCost(laborCost);
        
        BigDecimal taxRate = new BigDecimal("6.25");
        order.setTaxRate(taxRate);
        BigDecimal taxRatePercentage = taxRate.divide(new BigDecimal("100"))
                .setScale(2, RoundingMode.HALF_UP);
        
        
        BigDecimal tax = laborCost.add(materialCost).multiply(taxRatePercentage)
                .setScale(2, RoundingMode.HALF_UP);
        order.setTax(tax);
        
        BigDecimal total = laborCost.add(materialCost).add(tax);
        order.setTotal(total);
        
        return order;
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testGetAddOrder() throws Exception {
        String dateStr = "04032012";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        
        //initial size of list<Order> for a given date must be initialized in
        //case orders exist for that date
        //check if file exists, because if not, and you run the code block
        //within the if statement, it returns an error and test fails.
        int initialOrderSize = 0;
        String checkFileName = "orders/" + ORDERS_FILE + date + ".txt";
         if (new File(checkFileName).isFile() == true) {
            initialOrderSize = orderDao.getAllOrders(date).size();
        }
        
        Order order1 = createOrder1();
        
        orderDao.addOrder(date, order1);
        
        assertEquals(1, orderDao.getAllOrders(date).size() - initialOrderSize);
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        
        String dateStr = "04032012";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        
        int initialOrderSize = 0;
        String checkFileName = "orders/" + ORDERS_FILE + date + ".txt";
         if (new File(checkFileName).isFile() == true) {
            initialOrderSize = orderDao.getAllOrders(date).size();
        }
         
        Order order1 = createOrder1();
        
        orderDao.addOrder(date, order1);

        assertEquals(1, orderDao.getAllOrders(date).size() - initialOrderSize);
        
        orderDao.removeOrder(date, order1.getOrderNumber(), true);

        assertEquals(initialOrderSize, orderDao.getAllOrders(date));
    }

    /**
     * Test of editOrder method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testEditOrder() throws Exception {
        Order order1 = createOrder1();
        Order order2 = createOrder2();
        
        String dateStr = "04032012";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        
        orderDao.addOrder(date, order1);
        
        Order editOrder = 
                orderDao.editOrder(date, order1.getOrderNumber(), order2);
        
        List<Order> orderList = orderDao.getAllOrders(date);
        
        Order editOrderGet = null;
        
        for (Order currentOrder : orderList) {
           if (currentOrder.getOrderNumber() == order1.getOrderNumber()) {
               editOrderGet = currentOrder;
           }
        }
        
        assertEquals(editOrder, editOrderGet);

    }

    /**
     * Test of saveWork method, of class FlooringMasteryOrderDao.
     */
    @Test
    public void testSaveWork() throws Exception {
    }

    /**
     * Test of loadEverything method, of class FlooringMasteryOrderDao.
     */
 
    
}
