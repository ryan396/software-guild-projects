/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.userio.FlooringMasteryView;
import com.sg.flooringmastery.userio.UserIO;
import com.sg.flooringmastery.userio.UserIOConsoleImpl;

/**
 *
 * @author rianu
 */
public class FlooringMasteryController {
   
    
    FlooringMasteryView view;
    FlooringMasteryAdminController adminController;
    FlooringMasteryProductionController productionController;
    private UserIO io = new UserIOConsoleImpl();
    
    public FlooringMasteryController(FlooringMasteryView view, 
            FlooringMasteryAdminController adminController,
            FlooringMasteryProductionController productionController) {
        this.view = view;
        this.adminController = adminController;
        this.productionController = productionController;
    }
  

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    productionController.productionRun();
                    break;
                case 2:
                    adminController.adminRun();
                    break;
                case 3:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("GOOD BYE");
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    
    
    
            
}
