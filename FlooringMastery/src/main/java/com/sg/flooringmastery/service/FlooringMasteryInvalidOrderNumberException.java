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
public class FlooringMasteryInvalidOrderNumberException extends Exception {
    
    public FlooringMasteryInvalidOrderNumberException(String message) {
        super(message);
    }

    public FlooringMasteryInvalidOrderNumberException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
