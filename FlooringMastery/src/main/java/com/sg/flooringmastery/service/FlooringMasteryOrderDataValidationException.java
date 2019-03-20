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
public class FlooringMasteryOrderDataValidationException extends Exception {
    
    public FlooringMasteryOrderDataValidationException(String message) {
        super(message);
    }

    public FlooringMasteryOrderDataValidationException(String message,
            Throwable cause) {
        super(message, cause);
    }
    
}
