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
public class VendingMachineNoItemExistsException extends Exception {
    public VendingMachineNoItemExistsException(String message) {
        super(message);
    }

    public VendingMachineNoItemExistsException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
