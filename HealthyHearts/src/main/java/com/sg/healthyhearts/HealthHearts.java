/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.healthyhearts;

import java.util.Scanner;

/**
 *
 * @author rianu
 */
public class HealthHearts {
    
    public static void main(String[] args) {
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.print("What is your age? ");
        int age = inputReader.nextInt();
        
        int maxHR = 220 - age;
        int minHRZone = (int) Math.round(maxHR * 0.5);
        int maxHRZone = (int) Math.round(maxHR * 0.85);
        
        System.out.println("Your maximum heart rate should be " + maxHR + " beats per minute.");
        System.out.println("Your target HR zone is " + maxHRZone + " - " + minHRZone + " beats per minute.");
        
    }
}
