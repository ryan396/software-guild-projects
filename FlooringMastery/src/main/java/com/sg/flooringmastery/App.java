/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringMasteryController;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rianu
 */
public class App {
    
    public static void main(String[] args) throws FlooringMasteryPersistenceException {
        
        
        /*
        UserIO myIO = new UserIOConsoleImpl();
        FlooringMasteryView myView = new FlooringMasteryView(myIO);
        
        FlooringMasteryOrderDao myOrderDao = new FlooringMasteryOrderDaoFileImpl();
        
        FlooringMasteryTaxDao myTaxDao = new FlooringMasteryTaxDaoFileImpl();
        
        FlooringMasteryProductDao myProductDao = new FlooringMasteryProductDaoFileImpl();
        
        FlooringMasteryAuditDao myAuditDao = new FlooringMasteryAuditDaoFileImpl();
        
        FlooringMasteryProductServiceLayer myProductService = 
                new FlooringMasteryProductServiceLayerImpl(myProductDao, myAuditDao);
        
        FlooringMasteryTaxServiceLayer myTaxService = 
                new FlooringMasteryTaxServiceLayerImpl(myTaxDao, myAuditDao);

        FlooringMasteryOrderServiceLayer myOrderService = 
                new FlooringMasteryOrderServiceLayerImpl(myOrderDao, myProductDao
                , myTaxDao, myAuditDao);
        FlooringMasteryAdminController adminController = 
                new FlooringMasteryAdminController(myView, myTaxService, myProductService);
        
        FlooringMasteryProductionController productionController = 
                new FlooringMasteryProductionController(myOrderService, myView);
        
        FlooringMasteryController controller = new FlooringMasteryController(
                myView, adminController, productionController);
        
        
        controller.run();
        */
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = 
                ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();
    }
    
}
