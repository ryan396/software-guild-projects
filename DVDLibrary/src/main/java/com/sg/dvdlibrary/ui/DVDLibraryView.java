/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author rianu
 */
public class DVDLibraryView {
    
    private UserIO io; 
    
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. Display all DVDs");
        io.print("2. Create DVD");
        io.print("3. View DVD");
        io.print("4. Remove DVD");
        io.print("5. Edit DVD");
        io.print("6. Exit");
        
        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter DVD release date");
        String rating = io.readString("Please enter the MPAA rating for DVD");
        String directorName = io.readString("Please enter the director name");
        String studio = io.readString("Please enter the studio name");
        String userRating = io.readString("Please enter a user review");
        DVD currentDVD = new DVD(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setRating(rating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        return currentDVD;
    }
    
    public void displayCreateDVDBanner() {
        io.print("=== Create DVD === ");
    }
    
    public void displayCreateDVDSuccessBanner() {
        io.print("DVD successfully added to the collection. Please hit enter to"
                + " continue");
    }
    
    public void displayDVDList(List<DVD> DVDList) {
        for (DVD currentDVD : DVDList) {
            io.print(currentDVD.getTitle() + " : " + currentDVD.getReleaseDate()
            + " " + currentDVD.getRating() + " " + currentDVD.getDirectorName()
            + " " + currentDVD.getStudio() + " " + currentDVD.getUserRating());
        }
    }
    
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
    
    public void displayDisplayDVDBanner() {
        io.print("=== View DVD ===");
    }
    
    public String getDVDTitle() {
        return io.readString("Please enter the DVD name");
    }
    
    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle() + " : " + dvd.getReleaseDate()
            + " " + dvd.getRating() + " " + dvd.getDirectorName()
            + " " + dvd.getStudio() + " " + " " + dvd.getUserRating());
        } else {
            io.print("No such DVD in collection.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void editDVD(DVD dvd) {
        
        
    }
    
    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }
    
    public void displayRemoveSuccessBanner() {
        io.print("DVD successfully removed. Please hit enter to continue.");
    }
    
    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }
    
    public void displayEditDVDSuccessBanner() {
        io.print("DVD successfully edited. Please hit enter to continue.");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    

}
