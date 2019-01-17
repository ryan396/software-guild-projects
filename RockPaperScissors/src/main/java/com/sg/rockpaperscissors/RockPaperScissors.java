/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author rianu
 */
public class RockPaperScissors {
    
    public static void main(String[] args) {
        
        Scanner userInput = new Scanner(System.in);
        Random rand = new Random();
        
        int playerOneScore = 0;
        int playerTwoScore = 0;
        int roundCount = 0;
        int ties = 0;
        boolean keepPlaying = true;
        while (keepPlaying == true) {
            System.out.println("Welcome to ROCK PAPER SCISSORS GAME!");
            System.out.print("How many rounds would you like to play? Choose a number between 1 and 10: ");
            String rounds = userInput.nextLine();
            int roundsInt = Integer.parseInt(rounds);

            if (roundsInt > 10 || roundsInt < 1) {
                System.out.println("ERROR ROUNDS OUT OF BOUNDS");
                return;
            }



                while(roundCount < roundsInt){

                   System.out.print("Type 1 for rock, 2 for paper, or 3 for scissors: ");
                   String playerOneChoice = userInput.nextLine();
                   int playerOneChoiceInt = Integer.parseInt(playerOneChoice);

                   int playerTwoChoice = rand.nextInt(3) + 1;

                   System.out.println(playerTwoChoice);

                   if (playerOneChoiceInt == playerTwoChoice) {
                       System.out.println("TIE!");
                       ties++;
                   } else if (playerOneChoiceInt == 1 && playerTwoChoice == 3){
                       System.out.println("You win this round!");
                       System.out.println("Hi this was executed");
                       playerOneScore++;
                   } else if (playerOneChoiceInt == 2 && playerTwoChoice == 1) {
                       System.out.println("You win this round!");
                       playerOneScore++;
                   } else if (playerOneChoiceInt == 3 && playerTwoChoice == 2) {
                       System.out.println("You win this round!");
                       playerOneScore++;
                   } else if (playerOneChoiceInt == 1 && playerTwoChoice == 2){
                       System.out.println("You lose this round");
                       playerTwoScore++;
                   } else if (playerOneChoiceInt == 2 && playerTwoChoice == 3) {
                       System.out.println("You lose this round");
                       playerTwoScore++;
                   } else if (playerOneChoiceInt == 3 && playerTwoChoice == 1) {
                       System.out.println("You lose this round");
                       playerTwoScore++;
                   } 

                    System.out.println("The scores so far are:");
                    System.out.println("Player One Score: " + playerOneScore);
                    System.out.println("Player Two Score: " + playerTwoScore);
                    System.out.println("Ties: " + ties);
                    System.out.println(" ");


                   roundCount++;
            }

            if (playerOneScore > playerTwoScore) {
                System.out.println("You win!");
            } else if (playerTwoScore > playerOneScore) {
            System.out.println("You lose");
            } else {
            System.out.println("The game was a tie. ");
            }
                
            System.out.println(" ");
            System.out.println("The results are: ");
            System.out.println("playerOneScore: " + playerOneScore);
            System.out.println("playerTwoScore: " + playerTwoScore);
            System.out.println("Ties: " + ties);

            System.out.println("Do you want to keep playing? (y/n)");
            String keepPlayingResponse = userInput.nextLine();

            if (keepPlayingResponse.equals("n")) {
                return;
            }
        }    
    }
}
