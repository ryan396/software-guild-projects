/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author rianu
 */
public class FlooringMasteryDuplicateTaxException extends Exception {
    
    public FlooringMasteryDuplicateTaxException(String message) {
        super(message);
    }

    public FlooringMasteryDuplicateTaxException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
