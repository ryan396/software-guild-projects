/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author rianu
 */
public class VendingMachineInvalidMoneyAmountException extends Exception {
    
     public VendingMachineInvalidMoneyAmountException(String message) {
        super(message);
    }

    public VendingMachineInvalidMoneyAmountException(String message,
            Throwable cause) {
        super(message, cause);
    }
   
}
