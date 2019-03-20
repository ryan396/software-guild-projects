/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.userio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.IllegalFormatException;
import java.util.Scanner;

/**
 *
 * @author rianu
 */
public class UserIOConsoleImpl implements UserIO {
    Scanner userInput = new Scanner(System.in);
    @Override
    public void print(String message) {
        System.out.println(message);
    }
    
    @Override
    public double readDouble(String prompt) {
        print(prompt);
        String input = userInput.next();
        double inputDouble = Double.parseDouble(input);
        
        return inputDouble;
         
    }
    
    @Override
    public double readDouble(String prompt, double min, double max) {
        
        double valueDouble = Double.parseDouble(prompt);
        while (true) {
            if (valueDouble < min || valueDouble > max) {
                System.out.println("Please enter a value between " + min + " - " + max + ".");
                prompt = userInput.next();
                valueDouble = Double.parseDouble(prompt);
            } else if (valueDouble > min && valueDouble < max) {
                return valueDouble;
            }
        }
    }
    
    @Override
    public float readFloat(String prompt) {
        float valueFloat = Float.parseFloat(prompt);
        return valueFloat;
    }
    
    @Override
    public float readFloat(String prompt, float min, float max) {
        float valueFloat = Float.parseFloat(prompt);
        while (true) {
            if (valueFloat < min || valueFloat > max) {
                System.out.println("Please enter a value between " + min + " - " + max + ".");
                prompt = userInput.next();
                valueFloat = Float.parseFloat(prompt);
            } else if (valueFloat > min && valueFloat < max) {
                return valueFloat;
            }
        }
    }
    
    @Override
    public int readInt(String prompt) {
        Boolean hasErrors = false;
        int inputInt = 0;
        do {
           System.out.println(prompt);
           String inputStr = userInput.nextLine();
        try {
            inputInt = Integer.parseInt(inputStr);
            hasErrors = false;
        }
        catch (NumberFormatException e) {
            hasErrors = true;
            System.out.printf("%s is not a parsable!", inputStr);
        }    
        } while(hasErrors);
        return inputInt;
    }
    
    @Override
    public int readInt(String prompt, int min, int max) {
        Boolean hasErrors = false;
        int valueInt = 0;
        do{
            System.out.println(prompt);
            String inputInt = userInput.nextLine();
        try{
            valueInt = Integer.parseInt(inputInt);
            hasErrors = false;
        }
        catch (NumberFormatException e) {
            hasErrors = true;
            System.out.printf("%s is not parsable!" , inputInt);
        }
        } while(hasErrors);
        
        while (true) {
            if (valueInt >= min && valueInt <= max) {
                return valueInt;
            } else {
                return 0;
            }
            }
        }
        
            
    @Override
    public long readLong(String prompt) {
        long valueLong = Long.parseLong(prompt);
        return valueLong;
    } 
    
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String stringRead = userInput.nextLine();
        return stringRead;
    }

    @Override
    public LocalDate parseDate() {
        boolean hasErrors = false;
        LocalDate date = null;
        do {
           String releaseDateStr = readString("Please enter the date in form MM/dd/yyyy");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            date = LocalDate.parse(releaseDateStr, formatter);
            hasErrors = false;
        }
        catch (DateTimeParseException e) {
            hasErrors = true;
            System.out.printf("%s is not a parsable!", releaseDateStr);
        }    
        } while(hasErrors);
        
       return date;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        Boolean hasErrors = false;
        BigDecimal decimal = null;
        do {
           System.out.println(prompt);
           String decimalString = userInput.nextLine();
        try {
            decimal = new BigDecimal(decimalString);
            hasErrors = false;
        }
        catch (NumberFormatException e) {
            hasErrors = true;
            System.out.printf("%s is not a parsable!", decimalString);
        }    
        } while(hasErrors);
        return decimal;
    }

        
    @Override
    public boolean checkAdminPassword(String userPass, String password) {
        if (userPass.equals(password) == true) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean checkIfTrue(String prompt) {
        Boolean hasErrors = false;
        Boolean continueAction = false;
        do {
           System.out.println(prompt);
           String stringRead = userInput.nextLine();
        try {
            if (stringRead.equals("Y") || stringRead.equals("y")){
                continueAction = true;
                hasErrors = false;
            } else if (stringRead.equals("N") || stringRead.equals("n")) {
                continueAction = false;
                hasErrors = false;
            } else {
                hasErrors = true;
            }
        }
        catch (IllegalFormatException e) {
            hasErrors = true;
            System.out.printf("%s is not a parsable!", stringRead);
        }    
        } while(hasErrors);
        return continueAction;
    }

    @Override
    public void printTrueOrFalse(String promptIfTrue, String promptIfFalse, Boolean check) {
        if (check == true) {
            System.out.println(promptIfTrue);
        } else {
            System.out.println(promptIfFalse);
        }
    }

    @Override
    public BigDecimal readBigDecimalTwo(String prompt) {
         System.out.println(prompt);
         String decimalString = userInput.nextLine();
         if (decimalString.equals("")) {
             decimalString = "0";
         }
         BigDecimal decimal = new BigDecimal(decimalString);
         return decimal;
    }
}
    
