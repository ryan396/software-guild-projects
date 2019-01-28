/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author rianu
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    
    public static final String LIBRARY_FILE = "library.text";
    public static final String DELIMITER = "::";
    
    private Map<String, DVD> dvds = new HashMap<>();
    
    @Override
    public DVD addDVD(String title, DVD dvd) 
        throws DVDLibraryDaoException {
        DVD newDVD = dvds.put(title, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs()
       throws DVDLibraryDaoException {
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String title)
     throws DVDLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title)
     throws DVDLibraryDaoException {
        DVD removedDVD = dvds.remove(title);
        writeLibrary();
        return removedDVD;
    }

   
    @Override
    public DVD editDVD(String title, DVD dvd) 
     throws DVDLibraryDaoException {
        DVD editDVD = dvds.replace(title, dvd);
        writeLibrary();
        return editDVD;
        
    }
    
    	private void loadLibrary() throws DVDLibraryDaoException {
	    Scanner scanner;
	    
	    try {
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(LIBRARY_FILE)));
	    } catch (FileNotFoundException e) {
	        throw new DVDLibraryDaoException(
	                "-_- Could not load roster data into memory.", e);
	    }

	    String currentLine;
	  
	    String[] currentTokens;
	  
	    while (scanner.hasNextLine()) {
	     
	        currentLine = scanner.nextLine();
	        
	        currentTokens = currentLine.split(DELIMITER);
	        
	        DVD currentDVD = new DVD(currentTokens[0]);
	       
	        currentDVD.setReleaseDate(currentTokens[1]);
	        currentDVD.setRating(currentTokens[2]);
	        currentDVD.setDirectorName(currentTokens[3]);
                currentDVD.setStudio(currentTokens[4]);
                currentDVD.setUserRating(currentTokens[5]);

	        dvds.put(currentDVD.getTitle(), currentDVD);
	    }
	  
	    scanner.close();
	}
        
        private void writeLibrary() throws DVDLibraryDaoException {
	
	    PrintWriter out;
	    
	    try {
	        out = new PrintWriter(new FileWriter(LIBRARY_FILE));
	    } catch (IOException e) {
	        throw new DVDLibraryDaoException(
	                "Could not save student data.", e);
            }
            
	    List<DVD> DVDList = this.getAllDVDs();
	    for (DVD currentDVD : DVDList) {
	      
	        out.println(currentDVD.getTitle() + DELIMITER
	                + currentDVD.getReleaseDate() + DELIMITER 
	                + currentDVD.getRating() + DELIMITER
	                + currentDVD.getDirectorName() + DELIMITER 
                        + currentDVD.getStudio() + DELIMITER
                        + currentDVD.getUserRating());
	        
	        out.flush();
	    }
	    // Clean up
	    out.close();
	}
	
    
}
