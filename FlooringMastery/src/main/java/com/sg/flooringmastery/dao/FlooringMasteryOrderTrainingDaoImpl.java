/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import static com.sg.flooringmastery.dao.FlooringMasteryOrderDaoFileImpl.DELIMITER;
import static com.sg.flooringmastery.dao.FlooringMasteryOrderDaoFileImpl.ORDERS_FILE;
import static com.sg.flooringmastery.dao.FlooringMasteryOrderDaoFileImpl.ORDER_NUMBERS;
import com.sg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author rianu
 */
public class FlooringMasteryOrderTrainingDaoImpl implements FlooringMasteryOrderDao {
  
        
    private List<Integer> orderNumbers = new ArrayList<>();
    private Map<LocalDate, List<Order>> orders = new HashMap<>();
    public static final String ORDER_NUMBERS = "OrderNumbers.txt";
    public static final String ORDERS_FILE = "Orders_";
    public static final String DELIMITER = ",";
    public static final String HEADER = "OrderNumber,CustomerName,State,TaxRate"
            + ",ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,"
            + "LaborCost,Tax,Total";
  
    private int generateOrderNumber()
     throws FlooringMasteryPersistenceException {
        loadOrderNumbers();
        int newOrderNumber;
        if (orderNumbers.isEmpty()) {
            newOrderNumber = 1;
            orderNumbers.add(newOrderNumber);
            //writeOrderNumbers();
        } else {
            int lastOrderNumber = orderNumbers.get(0);
            newOrderNumber = lastOrderNumber + 1;
            orderNumbers.set(0, newOrderNumber);
            //writeOrderNumbers();
        }
        return newOrderNumber;
        
    } 
    
    @Override
    public Order addOrder(LocalDate date, Order order)
     throws FlooringMasteryPersistenceException {
        int orderNumber = generateOrderNumber();
        order.setOrderNumber(orderNumber);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddYYYY");
        String currentDate = date.format(formatter);
        String checkFileName = "orders/" + ORDERS_FILE + currentDate + ".txt";
        
        //check if file exists, if it does, load orders for that date
        if (new File(checkFileName).isFile() == true) {
            
            loadOrders(date);
            
            List<Order> orderList = orders.get(date);
            orderList.add(order);
            orders.put(date, orderList);
            return order;
        } else {
            List<Order> orderList = new ArrayList();
            orderList.add(order);
            orders.put(date, orderList);
            return order;
        }
        
    }

    @Override
    public List<Order> getAllOrders(LocalDate date)
     throws FlooringMasteryPersistenceException {
        loadOrders(date);
            
        List<Order> orderList = orders.get(date);
        return orderList;
    }
    

    @Override
    public Order removeOrder(LocalDate date, int orderNumber, boolean continueAction)
     throws FlooringMasteryPersistenceException {
        Order removedOrder = null;
        //load orders, extract order from hashmap. if they user wants to continue
        //iterate through orders, checking to find the order that matches the
        //order number they want to remove. once its found, remove the order
        //from the list, put back the ordersList into the order hashmap, and 
        //write the orders and return the removed order
        if (continueAction == true) {
            loadOrders(date);
            List<Order> orderList = orders.get(date);
            for (Order currentOrder : orderList) {
                if (currentOrder.getOrderNumber() == orderNumber) {
                    removedOrder = currentOrder;
                    orderList.remove(removedOrder);
                    orders.put(date, orderList);
                    return removedOrder;
                }
            }
        } 
        return removedOrder;
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber, Order editOrder)
     throws FlooringMasteryPersistenceException {
        loadOrders(date);
        List<Order> orderList = orders.get(date);
        //iterate through the orderList extracted from the hashmap and find
        //the order number of the one they would like to edit. once found,
        //remove the current one from the list and add the edited order
        //information back in the list.
        //the service will contain logic to keep the information the same if
        //the user leaves fields blank, along with keeping the order number 
        //the same
        for (Order currentOrder : orderList) {
                if (currentOrder.getOrderNumber() == orderNumber) {
                    orderList.add(editOrder);
                    orderList.remove(currentOrder);
                    orders.put(date, orderList);
                    //writeOrders(date);
                    return editOrder;
                }
    }
        return editOrder;
    }
    
    @Override
    public void saveWork()
        throws FlooringMasteryPersistenceException {
        //do nothing
    }
    
    private void loadOrderNumbers() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ORDER_NUMBERS)));
        } catch (FileNotFoundException e) {
           throw new FlooringMasteryPersistenceException("-_- Could not load order number"
                   + "data into memory.", e);
        }
        
        while (scanner.hasNextInt()) {
            orderNumbers.add(scanner.nextInt());
        }
        scanner.close();
    }
    
    /*not needed for training mode
    private void writeOrderNumbers() throws FlooringMasteryPersistenceException {
        
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ORDER_NUMBERS));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not save order number.", e);
        }
        
        
        for (int currentNumber : orderNumbers) {
            out.println(currentNumber);
            out.flush();
        }
        out.close();
    }
    */
    private void loadOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        Scanner scanner;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddYYYY");
        String currentDate = date.format(formatter);
        String fileName = "orders/" + ORDERS_FILE + currentDate + ".txt";
        
        File readFile = new File(fileName);
        String pathName = readFile.getAbsolutePath();
        
        if (orders.containsKey(date) == false) {
    
        try {

            scanner = new Scanner(new BufferedReader(new FileReader(pathName)));
        } catch (FileNotFoundException e){
            throw new FlooringMasteryPersistenceException("-_- Could not load order data"
                    + "into memory.", e);
        }
        
        String currentLine;
        String[] currentTokens;
        //check scanner for next line add a check here
        if(scanner.hasNext()==true){
            scanner.nextLine();
        }
        
        List<Order> orderList = new ArrayList();
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Order currentOrder = new Order();
            currentOrder.setOrderNumber(formatInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setState(currentTokens[2]);
            currentOrder.setTaxRate(formatBigDecimal(currentTokens[3]));
            currentOrder.setProductType(currentTokens[4]);
            currentOrder.setArea(formatBigDecimal(currentTokens[5]));
            currentOrder.setCostPerSquareFoot(formatBigDecimal(currentTokens[6]));
            currentOrder.setLaborCostPerSquareFoot(formatBigDecimal(currentTokens[7]));
            currentOrder.setMaterialCost(formatBigDecimal(currentTokens[8]));
            currentOrder.setLaborCost(formatBigDecimal(currentTokens[9]));
            currentOrder.setTax(formatBigDecimal(currentTokens[10]));
            currentOrder.setTotal(formatBigDecimal(currentTokens[11]));
            
            orderList.add(currentOrder);
        }
        orders.put(date, orderList);
        scanner.close();
        }
    } 
    
    /* not needed for training mode
    private void writeOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        
        PrintWriter out;
        
        //generates a string of the current date in format MMddYYYY
        //so that when the file is written, the title contains the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddYYYY");
        String currentDate = date.format(formatter);
        
        try {
            //this is where you add the date to the file header
            out = new PrintWriter(new FileWriter(new File("orders", ORDERS_FILE + currentDate + ".txt")));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not save order data.", e);
        }
        
        
        
        out.println(HEADER);
        List<Order> orderList = orders.get(date);
        for (Order currentOrder : orderList) {
            out.println(currentOrder.getOrderNumber() + DELIMITER
                        + currentOrder.getCustomerName() + DELIMITER
                        + currentOrder.getState() + DELIMITER 
                        + currentOrder.getTaxRate() + DELIMITER
                        + currentOrder.getProductType() + DELIMITER
                        + currentOrder.getArea() + DELIMITER
                        + currentOrder.getCostPerSquareFoot() + DELIMITER
                        + currentOrder.getLaborCostPerSquareFoot() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getLaborCost() + DELIMITER
                        + currentOrder.getTax() + DELIMITER 
                        + currentOrder.getTotal());
            out.flush();
        }
        out.close();
        
        
    }
    
    */
    
    public int formatInt(String currentToken) {
        int currentInt = Integer.parseInt(currentToken);
        return currentInt;
    }
    
    public BigDecimal formatBigDecimal(String currentToken) {
        BigDecimal currentBigDecimal = new BigDecimal(currentToken);
        return currentBigDecimal;
    }

}
