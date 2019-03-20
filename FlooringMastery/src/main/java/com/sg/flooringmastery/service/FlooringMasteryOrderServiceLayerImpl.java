/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryOrderDao;
import static com.sg.flooringmastery.dao.FlooringMasteryOrderDaoFileImpl.ORDERS_FILE;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryProductDao;
import com.sg.flooringmastery.dao.FlooringMasteryTaxDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.io.File;
import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_UP;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author rianu
 */
public class FlooringMasteryOrderServiceLayerImpl implements FlooringMasteryOrderServiceLayer {
    
    FlooringMasteryOrderDao orderDao;
    FlooringMasteryProductDao productDao;
    FlooringMasteryTaxDao taxDao;
    
    private FlooringMasteryAuditDao auditDao;
    
    public FlooringMasteryOrderServiceLayerImpl(FlooringMasteryOrderDao orderDao,
        FlooringMasteryProductDao productDao, FlooringMasteryTaxDao taxDao, 
        FlooringMasteryAuditDao auditDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
        this.auditDao = auditDao;
    }
    
    @Override
    public Order addOrder(LocalDate date, Order order) 
            throws FlooringMasteryPersistenceException, 
            FlooringMasteryInvalidProductException,
            FlooringMasteryInvalidStateException,
            FlooringMasteryOrderDataValidationException {
        
        validateOrderData(order);
        getProductAndTaxInfo(order);
        order = calculateTotal(order);
         auditDao.writeAuditEntry(
            "Order for " + order.getCustomerName() + " CREATED.");
        return orderDao.addOrder(date, order);
        
    }
    
    public Order getProductAndTaxInfo(Order order) throws
            FlooringMasteryPersistenceException, 
            FlooringMasteryInvalidProductException,
            FlooringMasteryInvalidStateException {
        
        Product orderedProduct = productDao.getProduct(order.getProductType());
        Tax taxInfo = taxDao.getTax(order.getState());
        
        if (orderedProduct == null) {
            throw new FlooringMasteryInvalidProductException(
                    "Product Type is not sold here.");
        }
        //set cost per square foot and laborcost per square foot by extracting
        //information from product
        order.setCostPerSquareFoot(orderedProduct.getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(orderedProduct.getLaborCostPerSquareFoot());
        
        if (taxInfo == null) {
            throw new FlooringMasteryInvalidStateException(
                    "State tax information does not exist");
        }
        
        order.setTaxRate(taxInfo.getTaxRate());
        return order;
    }

    @Override
    public List<Order> getAllOrders(LocalDate date) throws
            FlooringMasteryPersistenceException {
        return orderDao.getAllOrders(date);
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber, boolean continueAction) throws 
           FlooringMasteryPersistenceException,
           FlooringMasteryInvalidOrderNumberException{
        validateOrderNumber(orderNumber, date);
        auditDao.writeAuditEntry(
            "Order " + orderNumber + " REMOVED.");
        return orderDao.removeOrder(date, orderNumber, continueAction);
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber, Order editedOrder) throws 
        FlooringMasteryPersistenceException,
        FlooringMasteryInvalidProductException,
        FlooringMasteryInvalidStateException,
        FlooringMasteryOrderDataValidationException,
        FlooringMasteryInvalidOrderNumberException{
        
        validateOrderNumber(orderNumber, date);
        List<Order> orderList = orderDao.getAllOrders(date);
        
        Order previousOrder = new Order();
        
        //get order that is being edited to pull information from
        for (Order currentOrder : orderList) {
                if (currentOrder.getOrderNumber() == orderNumber) {
                    previousOrder = currentOrder;
                }
        }
        
        //the below if statements check if the user just hit enter
        //instead of putting in new info. if they did hit enter, fill in the
        //data with the previous data prior to editing
        if (editedOrder.getCustomerName().equals("")){
            editedOrder.setCustomerName(previousOrder.getCustomerName());
        }
        
        if (editedOrder.getState().equals("")) {
            editedOrder.setState(previousOrder.getState());
        }
        
        if (editedOrder.getProductType().equals("")) {
            editedOrder.setProductType(previousOrder.getProductType());
        }
        
        if (editedOrder.getArea().equals(new BigDecimal("0"))) {
            editedOrder.setArea(previousOrder.getArea());
        }
        editedOrder.setOrderNumber(orderNumber);
        getProductAndTaxInfo(editedOrder);
        
        calculateTotal(editedOrder);
        
        auditDao.writeAuditEntry(
            "Order " + orderNumber + " EDITED.");
        
        
        return orderDao.editOrder(date, orderNumber, editedOrder);
        
    }

    
    @Override
    public void saveWork() throws FlooringMasteryPersistenceException {
        orderDao.saveWork();
    }

 
    @Override
    public Order calculateTotal(Order order) throws FlooringMasteryPersistenceException {
        BigDecimal taxRate = order.getTaxRate();
        BigDecimal area = order.getArea();
        BigDecimal laborCostPerSquareFoot = order.getLaborCostPerSquareFoot();
        BigDecimal materialCostPerSquareFoot = order.getCostPerSquareFoot();
        BigDecimal taxRatePercentage = taxRate.divide(new BigDecimal("100"))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal laborCost = area.multiply(laborCostPerSquareFoot)
                .setScale(2, RoundingMode.HALF_UP);
        
        BigDecimal materialCost = area.multiply(materialCostPerSquareFoot)
                .setScale(2, RoundingMode.HALF_UP);
        
        BigDecimal tax = (laborCost.add(materialCost)).multiply(taxRatePercentage)
                .setScale(2, RoundingMode.HALF_UP);
        
        BigDecimal total = laborCost.add(materialCost).add(tax);
        
        order.setLaborCost(laborCost);
        order.setMaterialCost(materialCost);
        order.setTax(tax);
        order.setTotal(total);
        
        return order;
    }
    
    private void validateOrderData(Order order) throws
            FlooringMasteryOrderDataValidationException {
        
        if (order.getCustomerName() == null 
                || order.getCustomerName().trim().length() == 0
                || order.getProductType() == null
                || order.getProductType().trim().length() == 0
                || order.getState() == null 
                || order.getState().trim().length() == 0
                || order.getArea() == null
                || order.getArea().intValue() <= 0) {
            throw new FlooringMasteryOrderDataValidationException(
            "ERROR: All fields [Customer Name, State, Product Type, Area] are"
                    + " required. Area entered must be greater than 0");
        }
    }
    
    private void validateOrderNumber(int orderNumber, LocalDate date) throws
            FlooringMasteryInvalidOrderNumberException, FlooringMasteryPersistenceException {
         
        List<Order> orderList = orderDao.getAllOrders(date);
        int checkAgainst = -1;
         for (Order currentOrder : orderList) {
                if (currentOrder.getOrderNumber() == orderNumber) {
                    checkAgainst = orderNumber;
                }
        }
         
        if (checkAgainst == -1) {
            throw new FlooringMasteryInvalidOrderNumberException(
                "ERROR: Invalid Order Number. Order number does not exist.");
        }
    }
    
}
