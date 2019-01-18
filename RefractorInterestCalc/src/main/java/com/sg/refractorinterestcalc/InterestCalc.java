/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.refractorinterestcalc;

import java.util.Scanner;

/**
 *
 * @author rianu
 */
public class InterestCalc {
    
    public static void calcInterest(){
        
        System.out.println("What is your initial balance? ");
        double initialBalance = convertString();
        
        System.out.println("What is the interest rate? ");
        double interestRate = convertString();
        
        System.out.println("How many years do you want to stay in the fund? ");
        double yearsInFund = convertString();
        
        double currentBalance = initialBalance;
        
        double quarterlyInterest = interestRate / 4;
        
        double principalAtBegYear = 0;
       
        double interestAccumulatedYear = 0;
        
        
        
        for (int i = 1; i <= yearsInFund; i++) {
            principalAtBegYear = currentBalance;
            interestAccumulatedYear = 0;
            for (int j = 1; j < 5; j++ ) {
                currentBalance = currentBalance * (1 + ((quarterlyInterest)/100d));
            }
            interestAccumulatedYear = currentBalance - principalAtBegYear; 
            System.out.format("Year #" + i + " Principal at Beginning of Year: $%.2f ",principalAtBegYear);
            System.out.format("Interest Earned this year: $%.2f ",interestAccumulatedYear);
            System.out.format("Balance at end of year: $%.2f",currentBalance);
            System.out.println(" ");
            
        }
    }
    
    public static double convertString() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        double userInputDouble = Double.parseDouble(userInput);
        
        return userInputDouble;
    }
}
