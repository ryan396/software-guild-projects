/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.doggenetics;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author rianu
 */
public class DogGenetics {
    
    public static void main(String[] args) {
        
        Scanner userInput = new Scanner(System.in);
        Random rand = new Random();
        
       
        System.out.print("What is your dog's name? ");
        String dogName = userInput.nextLine();
        System.out.println("We will generate a dog genetic report right now for " + dogName + ".");
        System.out.println(" ");
        System.out.println(dogName + " is: ");
        System.out.println(" ");
        
        
        int i = 0;
        int randPercentage;
        int remainingPercentage = 0;
        int sum = 0;
       
        
        while (i < 5) {
            
            //generate a random percentage from 100 - the remainingPercentage, which is the percentage left after each interation. this way the numbers don't add up over 100
            randPercentage = rand.nextInt((100 - remainingPercentage)/ (5 - i)) + 1;
            
            //pick case dependent on value of i, which is the iteration. this displays the percentage of each dog breed + dog breed name
            switch(i) {
                case 0:
                     System.out.println(randPercentage + "% Labrador");
                     remainingPercentage += randPercentage;
                     break;
                case 1:
                    System.out.println(randPercentage + "% Poodle");
                    remainingPercentage += randPercentage;
                    break;
                case 2:
                    System.out.println(randPercentage + "% Hussky");
                    remainingPercentage += randPercentage;
                    break;
                case 3:
                     System.out.println(randPercentage + "% German Shepard");
                     remainingPercentage += randPercentage;
                    break;
                case 4:
                    remainingPercentage = 100 - remainingPercentage;
                    // the remaining percentage is added to the random percentage generated so that all the percentages add up to 100. this is not a good distribution, but it will add up to 100
                    System.out.println((remainingPercentage) + "% Hound");
                    break;
            }
            
           
            i++;
        }
    }
}
