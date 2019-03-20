/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.userio;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rianu
 */
public class FlooringMasteryView {
    private UserIO io;
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
    public int printMenuAndGetSelection() {
        io.print("<<< Flooring Program >>>");
        io.print("1. Main Menu");
        io.print("2. Admin Mode");
        io.print("3. Exit");

        return io.readInt("Please select from the"
                + " above choices.", 1, 3);
    }

    public int printProductionMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. View Orders");
        io.print("2. Add Order");
        io.print("3. Remove Order");
        io.print("4. Edit Order");
        io.print("5. Save Work");
        io.print("6. Exit Main Menu");

        return io.readInt("Please select from the"
                + " above choices.", 1, 6);
    }

    public int printAdminMenuAndGetSelection() {
        io.print("Admin Menu");
        io.print("1. View Products");
        io.print("2. Add Product");
        io.print("3. Edit Product");
        io.print("4. Remove Product");
        io.print("5. View Taxes");
        io.print("6. Add Tax");
        io.print("7. Edit Tax");
        io.print("8. Remove Tax");
        io.print("9. Exit Admin Mode");

        return io.readInt("Please select from the"
                + " above choices.", 1, 9);
    }

    //tax view information
    public Tax getNewTaxInfo() {
        String state = io.readString("Please enter a abbreviated state name");
        BigDecimal taxRate = io.readBigDecimal("Please enter the tax rate for the state");
        Tax currentTax = new Tax(state);
        currentTax.setTaxRate(taxRate);
        return currentTax;
    }
    
    public void displayAddTaxBanner() {
        io.print("=== Add Tax ===");
    }
    
    public void displayAddTaxSuccessBanner() {
        io.readString("Tax successfully created. Please hit enter to continue");
    }
       
    public void displayTaxList(List<Tax> taxList) {
        for (Tax currentTax : taxList) {
            io.print("State: " + currentTax.getState() + " |Tax Rate: " 
                    + currentTax.getTaxRate());
        }
    }
    
    public String getTaxStateChoice() {
        return io.readString("Please enter the state abbreviation");
    }
    
    public void displayTaxListBanner() {
        io.print("=== Display All Taxes ===");
    }
    
    public void displayRemoveTaxBanner() {
        io.print("=== Remove Tax ===");
    }
    
    public void displayRemoveTaxSuccessBanner() {
        io.readString("Tax successfully removed. Please hit enter to continue");
    }
    
    public void displayEditTaxBanner() {
        io.print("=== Edit Tax ===");
    }
    
    public void displayEditTaxSuccessBanner() {
        io.readString("Tax successfully edited. Please hit enter to continue");
    }
    
    //product view information
    public Product getNewProductInfo() {
        String productType = io.readString("Please enter a product type");
        BigDecimal costPerSquareFoot = io.readBigDecimal("Please enter the "
                + "cost per square foot for the product");
        BigDecimal laborCostPerSquareFoot = io.readBigDecimal("Please enter the "
                + "labor cost per square foot for the product");
        Product currentProduct = new Product(productType);
        currentProduct.setCostPerSquareFoot(costPerSquareFoot);
        currentProduct.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        return currentProduct;
    }
    
    public void displayAddProductBanner() {
        io.print("=== Add Product ===");
    }
    
    public void displayAddProductSuccessBanner() {
        io.readString("Product successfully created. Please hit enter to continue");
    }
       
    public void displayProductList(List<Product> productList) {
        for (Product currentProduct : productList) {
            io.print("Product Type: " + currentProduct.getProductType() + " "
                    + "|Cost Per Square Foot: $" 
                    + currentProduct.getCostPerSquareFoot() + " "
                    + "|Labor Cost Per Square Foot: $"
                    + currentProduct.getLaborCostPerSquareFoot());
        }
    }
    
    public String getProductTypeChoice() {
        return io.readString("Please enter the product type");
    }
    
    public void displayProductListBanner() {
        io.print("=== Display All Products ===");
    }
    
    public void displayRemoveProductBanner() {
        io.print("=== Remove Product ===");
    }
    
    public void displayRemoveProductSuccessBanner() {
        io.readString("Product successfully removed. Please hit enter to continue");
    }
    
    public void displayEditProductBanner() {
        io.print("=== Edit Product ===");
    }
    
    public void displayEditProductSuccessBanner() {
        io.readString("Product successfully edited. Please hit enter to continue");
    }
    
    //Unknown and exit banners
    public void displayExitBanner() {
        io.print("Good Bye!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    //order information
     public Order getNewOrderInfo() {
        String customerName = io.readString("Please enter customer's name");
        String state = io.readString("Please enter the state that order is being placed in");
        String productType = io.readString("Please enter a product type for order");
        BigDecimal area = io.readBigDecimalTwo("Please enter the square footage");
        Order currentOrder = new Order();
        currentOrder.setCustomerName(customerName);
        currentOrder.setProductType(productType);
        currentOrder.setState(state);
        currentOrder.setArea(area);
        return currentOrder;
    }
     
    public void displayOrderList(List<Order> orderList) {
      for (Order currentOrder : orderList) {
          io.print(currentOrder.getOrderNumber() 
                  + "| Customer Name: " + currentOrder.getCustomerName() 
                  + "| State: " + currentOrder.getState() 
                  + "| Tax Rate: " + currentOrder.getTaxRate()
                  + "| Product Type: " + currentOrder.getProductType()
                  + "| Square Footage: " + currentOrder.getArea()
                  + "| Cost Per Square Foot: " + currentOrder.getCostPerSquareFoot()
                  + "| Labor Cost Per Square Foot: "
                  + "| Material Cost: " + currentOrder.getMaterialCost()
                  + "| Labor Cost: " + currentOrder.getLaborCost()
                  + "| Tax: " + currentOrder.getTax()
                  + "| Total: " + currentOrder.getTotal());
      }
    }
    
    public void displayAddOrderBanner() {
        io.print("=== Add Order ===");
    }
    
    public void displayAddOrderSuccessBanner() {
        io.readString("Order successfully created. Please hit enter to continue");
    }
    
    public void displayOrderListBanner() {
        io.print("=== Display All Orders ===");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public LocalDate getDate() {
        LocalDate date = io.parseDate();
        return date;
    }
    
    public int getOrderNumRem() {
        return io.readInt("Please enter the order number of the order you would like to"
                + " remove");
    }
    
    public void removeOrderBanner() {
        io.print("=== Remove Order ===");
    }
    
    public void removeOrderSuccessBanner(boolean check) {
        io.printTrueOrFalse("Order successfully removed. Please hit enter to continue",
                "Order not removed. Please hit enter to continue.", check);
        io.print(" ");
    }
    
    public boolean askIfSureRemove() {
        return io.checkIfTrue("Are you sure? Y/N");
    }
    
    public void editOrderBanner() {
        io.print("=== EDIT ORDER ===");
    }
    
    public Order getEditOrderInfo(int orderNumber) {
        String customerName = io.readString("Please enter customer's name");
        String state = io.readString("Please enter the state that order is being placed in");
        String productType = io.readString("Please enter a product type for order");
        BigDecimal area = io.readBigDecimalTwo("Please enter the square footage");
        Order currentOrder = new Order();
        currentOrder.setOrderNumber(orderNumber);
        currentOrder.setCustomerName(customerName);
        currentOrder.setProductType(productType);
        currentOrder.setState(state);
        currentOrder.setArea(area);
        return currentOrder;
    }
    
    public void editOrderSuccessBanner() {
        io.readString("Order successfully edited. Please hit enter to continue.");
    }
    
    public int getOrderNumEdit() {
        return io.readInt("Please enter the order number of the order you would like to"
                + " edit");
    }
    
    public void saveWorkBanner() {
        io.print("=== Save Work ===");
    }
    
    public void saveWorkSuccessBanner() {
        io.readString("Work successfully saved. Please hit enter to continue");
    }
}
