/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author rianu
 */
public interface DVDLibraryDao {
    
    
    //adds dvd to collection and associates it with given title if it exists
    DVD addDVD(String title, DVD dvd)
     throws DVDLibraryDaoException;
    
    //returns string array containing all dvds in list 
    List<DVD> getAllDVDs()
     throws DVDLibraryDaoException;
    
    //returns dvd object associated with given title if it exists
    DVD getDVD(String title)
     throws DVDLibraryDaoException;
    
    
    //removes dvd associated with given title if it exists
    DVD removeDVD(String title)
     throws DVDLibraryDaoException;
    
    
    //edits dvd associated with given title if it exists
    
    DVD editDVD(String title, DVD dvd)
     throws DVDLibraryDaoException;
    
}
