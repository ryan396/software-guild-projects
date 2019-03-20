/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import static com.sg.flooringmastery.dao.FlooringMasteryOrderDaoFileImpl.ORDERS_FILE;
import com.sg.flooringmastery.dto.Order;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
public class FlooringMasteryOrderServiceLayerTest {
    
    private FlooringMasteryOrderServiceLayer orderService;
    
    public FlooringMasteryOrderServiceLayerTest() {
        /*
        FlooringMasteryOrderDao orderDao = new FlooringMasteryOrderStubDaoImpl();
        FlooringMasteryProductDao productDao = new FlooringMasteryProductDaoFileImpl();
        FlooringMasteryTaxDao taxDao = new FlooringMasteryTaxDaoFileImpl();
        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStubImpl();
        
        orderService = new FlooringMasteryOrderServiceLayerImpl(orderDao, productDao,
            taxDao, auditDao);
        */
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        orderService = ctx.getBean("orderService", FlooringMasteryOrderServiceLayer.class);
    }
    
    public Order createOrder1() {
        Order order = new Order();
        order.setCustomerName("Ryan");
        
        order.setProductType("Wood");
        
        BigDecimal area = new BigDecimal("100");
        order.setArea(area);
        
        order.setState("OH");
           
        return order;
        
    }

    
    public Order createOrder2() {
        
        Order order = new Order();
        order.setCustomerName("Bob");
        
        order.setProductType("Tile");
        
        BigDecimal area = new BigDecimal("100");
        order.setArea(area);
        
        order.setState("OH");
        
        /* 
        calculations not needed as they are made in the service layer
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
        */
        
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
     * Test of addOrder method, of class FlooringMasteryOrderServiceLayer.
     */
    @Test
    public void testGetAddOrder() throws Exception {
        String dateStr = "04032012";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        
        Order order1 = createOrder1();
         
        order1 = orderService.addOrder(date, order1);
        
        List<Order> orderList = orderService.getAllOrders(date);
        
        Order addOrderGet = null;
        
        for (Order currentOrder : orderList) {
           if (currentOrder.getOrderNumber() == order1.getOrderNumber()) {
               addOrderGet = currentOrder;
           }
        }
        
        assertEquals(order1, addOrderGet);
    }


    /**
     * Test of removeOrder method, of class FlooringMasteryOrderServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        String dateStr = "04032012";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        
        List<Order> initialOrderList = new ArrayList();
        String checkFileName = "orders/" + ORDERS_FILE + date + ".txt";
         if (new File(checkFileName).isFile() == true) {
            initialOrderList = orderService.getAllOrders(date);
        }
         
        Order order1 = createOrder1();
        
        orderService.addOrder(date, order1);
        orderService.getAllOrders(date).size();
        assertEquals(1, orderService.getAllOrders(date).size() - initialOrderList.size());
        
        
       
        orderService.removeOrder(date, order1.getOrderNumber(), true);

        assertEquals(initialOrderList.size(), orderService.getAllOrders(date).size());
    }

    /**
     * Test of editOrder method, of class FlooringMasteryOrderServiceLayer.
     */
    @Test
    public void testEditOrder() throws Exception {
        Order order1 = createOrder1();
        Order order2 = createOrder2();
        
        String dateStr = "04032012";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        
        orderService.addOrder(date, order1);
        
        Order editOrder = 
                orderService.editOrder(date, order1.getOrderNumber(), order2);
        
        List<Order> orderList = orderService.getAllOrders(date);
        
        Order editOrderGet = null;
        
        for (Order currentOrder : orderList) {
           if (currentOrder.getOrderNumber() == order1.getOrderNumber()) {
               editOrderGet = currentOrder;
           }
        }
        
        assertEquals(editOrder, editOrderGet);
    }
    
     
    /**
     * Test of calculateTotal method, of class FlooringMasteryOrderServiceLayer.
     */
    @Test
    public void testCalculateTotal() throws Exception {
        Order order2 = createOrder2();
        String dateStr = "04032012";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        order2 = orderService.addOrder(date, order2);
        
        
        Order order = new Order();
        order.setCustomerName("Bob");
        order.setOrderNumber(1);
        
        order.setProductType("Tile");
        
        BigDecimal area = new BigDecimal("100");
        order.setArea(area);
        
        order.setState("OH");
        
        //redo calculations and check against 
        BigDecimal costPerSquareFoot = new BigDecimal("3.50");
        order.setCostPerSquareFoot(costPerSquareFoot);
       
        BigDecimal laborCostPerSquareFoot = new BigDecimal("4.15");
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
        assertEquals(order2, order);
    }
    
    @Test
    public void testInvalidStateException() throws Exception {
        Order order1 = createOrder1();
        order1.setState("adwawd");
        
        String dateStr = "04032012";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        
        try {
            orderService.addOrder(date, order1);
            fail("Expected FlooringMasteryInvalidStateException was not thrown");
        } catch (FlooringMasteryInvalidStateException e) {
            return;
        }
    }
    
    @Test
    public void testInvalidProductException() throws Exception {
        Order order1 = createOrder1();
        order1.setProductType("adwawd");
        
        String dateStr = "04032012";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        
        try {
            orderService.addOrder(date, order1);
            fail("Expected FlooringMasteryInvalidProductException was not thrown");
        } catch (FlooringMasteryInvalidProductException e) {
            return;
        }
    }
    
    @Test
    public void testInvalidOrderNumberException() throws Exception {
        Order order1 = createOrder1();
        String dateStr = "04032012";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        orderService.addOrder(date, order1);
        
        try {
            orderService.removeOrder(date, -100, true);
            fail("Expected FlooringMasteryInvalidOrderNumberException was not thrown");
        } catch (FlooringMasteryInvalidOrderNumberException e) {
            return;
        }
    }

   
    
}
