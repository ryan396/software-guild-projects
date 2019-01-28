/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author rianu
 */
public class DVDLibraryController {
    
      DVDLibraryView view;
      DVDLibraryDao dao;
      
      private UserIO io = new UserIOConsoleImpl();

	    public void run() {
	        boolean keepGoing = true;
	        int menuSelection = 0;
                try {
	        while (keepGoing) {
	            
                  
	            menuSelection = getMenuSelection();

	            switch (menuSelection) {
	                case 1:
	                    listDVDs();
	                    break;
	                case 2:
	                    createDVD();
	                    break;
	                case 3:
	                    viewDVD();
	                    break;
	                case 4:
	                    removeDVD();
	                    break;
	                case 5:
	                    editDVD();
	                    break;
                        case 6: 
                            keepGoing = false;
                            break;
	                default:
	                    unknownCommand();
	            }

	        }
	        exitMessage();
            } catch (DVDLibraryDaoException e) {
                view.displayErrorMessage(e.getMessage());
            }
	}
            
    //method to orchestrate the menu selection         
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    //method to orchestrate the creation of new DVD
    private void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateDVDSuccessBanner();
    }
    
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    private void viewDVD() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        String title = view.getDVDTitle();
        DVD dvd = dao.getDVD(title);
        view.displayDVD(dvd);
    }
    
    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String title = view.getDVDTitle();
        dao.removeDVD(title);
        view.displayRemoveSuccessBanner();
    }
    
    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditDVDBanner();
        String title = view.getDVDTitle();
        DVD editDVD = view.getNewDVDInfo();
        dao.editDVD(editDVD.getTitle(),editDVD);
        view.displayEditDVDSuccessBanner();
    }
    
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
}
