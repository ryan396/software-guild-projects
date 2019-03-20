/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.FlooringMasteryDuplicateProductException;
import com.sg.flooringmastery.service.FlooringMasteryDuplicateTaxException;
import com.sg.flooringmastery.service.FlooringMasteryProductDataValidationException;
import com.sg.flooringmastery.service.FlooringMasteryProductServiceLayer;
import com.sg.flooringmastery.service.FlooringMasteryTaxDataValidationException;
import com.sg.flooringmastery.service.FlooringMasteryTaxServiceLayer;
import com.sg.flooringmastery.userio.FlooringMasteryView;
import com.sg.flooringmastery.userio.UserIO;
import com.sg.flooringmastery.userio.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author rianu
 */
public class FlooringMasteryAdminController {
    FlooringMasteryView view;
    FlooringMasteryTaxServiceLayer taxService;
    FlooringMasteryProductServiceLayer productService;
    
    public FlooringMasteryAdminController(
        FlooringMasteryView view,
        FlooringMasteryTaxServiceLayer taxService,
        FlooringMasteryProductServiceLayer productService) {
        this.view = view;
        this.taxService = taxService;
        this.productService = productService;
    }
    private UserIO io = new UserIOConsoleImpl();
  

    public void adminRun() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
        while (keepGoing) {

            menuSelection = getAdminMenuSelection();

            switch (menuSelection) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    editProduct();
                    break;
                case 4:
                    removeProduct();
                    break;
                case 5:
                    displayTaxes();
                    break;
                case 6:
                    addTax();
                    break;
                case 7:
                    editTax();
                    break;
                case 8:
                    removeTax();
                    break;
                case 9:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getAdminMenuSelection() {
        return view.printAdminMenuAndGetSelection();
    }
    
    private void addTax() throws FlooringMasteryPersistenceException {
        view.displayAddTaxBanner();
        boolean hasErrors = false;
        do {
            Tax newTax = view.getNewTaxInfo();
            try {
                taxService.addTax(newTax.getState(), newTax);
                view.displayAddTaxSuccessBanner();
                hasErrors = false;
            } catch (FlooringMasteryDuplicateTaxException | 
                    FlooringMasteryTaxDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors); 
    }
    
    private void displayTaxes() throws FlooringMasteryPersistenceException {
        view.displayTaxListBanner();
        List<Tax> taxList = taxService.getAllTaxes();
        view.displayTaxList(taxList);
    }
    
    private void removeTax() throws FlooringMasteryPersistenceException{
        view.displayRemoveTaxBanner();
        String state = view.getTaxStateChoice();
        taxService.removeTax(state);
        view.displayRemoveTaxSuccessBanner();
    }
    
    public void editTax() throws FlooringMasteryPersistenceException {
        view.displayEditTaxBanner();
        String state = view.getTaxStateChoice();
        Tax tax = taxService.getTax(state);
        Tax editTax = view.getNewTaxInfo();
        taxService.editTax(tax, editTax);
        view.displayEditTaxSuccessBanner();
    }
    
     private void addProduct() throws FlooringMasteryPersistenceException {
        view.displayAddProductBanner();
        boolean hasErrors = false;
        do {
            Product newProduct = view.getNewProductInfo();
            try {
                productService.addProduct(newProduct.getProductType(), newProduct);
                view.displayAddProductSuccessBanner();
                hasErrors = false;
            } catch (FlooringMasteryDuplicateProductException |
                    FlooringMasteryProductDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while(hasErrors);
        
    }
    
    private void displayProducts() throws FlooringMasteryPersistenceException {
        view.displayProductListBanner();
        List<Product> productList = productService.getAllProducts();
        view.displayProductList(productList);
    }
    
    private void removeProduct() throws FlooringMasteryPersistenceException {
        view.displayRemoveProductBanner();
        String productType = view.getProductTypeChoice();
        productService.removeProduct(productType);
        view.displayRemoveProductSuccessBanner();
    }
    
    public void editProduct() throws FlooringMasteryPersistenceException {
        view.displayEditProductBanner();
        String productType = view.getProductTypeChoice();
        Product product = productService.getProduct(productType);
        Product editProduct = view.getNewProductInfo();
        productService.editProduct(product, editProduct);
        view.displayEditProductSuccessBanner();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
