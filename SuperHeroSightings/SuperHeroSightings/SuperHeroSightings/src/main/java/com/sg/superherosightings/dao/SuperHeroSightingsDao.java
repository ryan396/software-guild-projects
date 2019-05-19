/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Power;
import com.sg.superherosightings.dto.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rianu
 */
public interface SuperHeroSightingsDao {

    /*                                                                 
                                                                       
 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ 
|______|______|______|______|______|______|______|______|______|______|
                                                                       
                                                                       
                                                                       
                                                                       
        _   _                                                          
       | | | |                                                         
       | |_| | ___ _ __ ___                                            
       |  _  |/ _ \ '__/ _ \                                           
       | | | |  __/ | | (_) |                                          
       \_| |_/\___|_|  \___/                                           
                                                                       
                                                                       
                                                                       
                                                                       
 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ 
|______|______|______|______|______|______|______|______|______|______|
                                                                       
                                                                       
     */
    public void addHero(Hero hero);

    public void deleteHero(int id);

    public void updateHero(Hero hero);

    public Hero getHeroById(int id);

    public List<Hero> getAllHeroes();
    
    public List<Organization> findOrganizationsForHero(Hero hero);
    
    public List<Hero> findAllHeroesForLocation(int locationId);

    /*
    
                                                                       
                                                                       
 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ 
|______|______|______|______|______|______|______|______|______|______|
                                                                       
                                                                       
                                                                       
                                                                       
        _     _____ _____   ___ _____ _____ _____ _   _                
       | |   |  _  /  __ \ / _ \_   _|_   _|  _  | \ | |               
       | |   | | | | /  \// /_\ \| |   | | | | | |  \| |               
       | |   | | | | |    |  _  || |   | | | | | | . ` |               
       | |___\ \_/ / \__/\| | | || |  _| |_\ \_/ / |\  |               
       \_____/\___/ \____/\_| |_/\_/  \___/ \___/\_| \_/               
                                                                       
                                                                       
                                                                       
                                                                       
 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ 
|______|______|______|______|______|______|______|______|______|______|
                                                                       
                                                                       
                                                                       
                                                                       

     */
    public void addLocation(Location location);

    public void deleteLocation(int id);

    public void updateLocation(Location location);

    public Location getLocationById(int id);

    public List<Location> getAllLocations(); 
    
    public List<Location> findAllLocationsForHero(int heroId);
    

    /*
    
                                                                       
                                                                       
 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ 
|______|______|______|______|______|______|______|______|______|______|
                                                                       
                                                                       
                                                                       
                                                                       
                 ______                                                
                 | ___ \                                               
                 | |_/ /____      _____ _ __                           
                 |  __/ _ \ \ /\ / / _ \ '__|                          
                 | | | (_) \ V  V /  __/ |                             
                 \_|  \___/ \_/\_/ \___|_|                             
                                                                       
                                                                       
                                                                       
                                                                       
 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ 
|______|______|______|______|______|______|______|______|______|______|
                                                                       
                                                                       
                                                                       
                                                                       

     */
    public void addPower(Power power);

    public void deletePower(int id);

    public void updatePower(Power power);

    public Power getPowerById(int id);

    public List<Power> getAllPowers();

    /*
    
                                                                       
                                                                       
 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ 
|______|______|______|______|______|______|______|______|______|______|
                                                                       
                                                                       
                                                                       
                                                                       
                  _____ _       _     _   _                            
                 /  ___(_)     | |   | | (_)                           
                 \ `--. _  __ _| |__ | |_ _ _ __   __ _                
                  `--. \ |/ _` | '_ \| __| | '_ \ / _` |               
                 /\__/ / | (_| | | | | |_| | | | | (_| |               
                 \____/|_|\__, |_| |_|\__|_|_| |_|\__, |               
                           __/ |                   __/ |               
                          |___/                   |___/                
                                                                       
                                                                       
 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ 
|______|______|______|______|______|______|______|______|______|______|
                                                                       
                                                                       
                                                                       
                                                                       


     */
    public void addSighting(Sighting sighting);

    public void deleteSighting(int id);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingById(int id);

    public List<Sighting> getAllSightings();
    
    public List<Sighting> findAllSightingsByDate(LocalDate date);

    /*
    
                                                                       
                                                                       
 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ 
|______|______|______|______|______|______|______|______|______|______|
                                                                       
                                                                       
                                                                       
                                                                       
        _____                       _          _   _                   
       |  _  |                     (_)        | | (_)                  
       | | | |_ __ __ _  __ _ _ __  _ ______ _| |_ _  ___  _ __        
       | | | | '__/ _` |/ _` | '_ \| |_  / _` | __| |/ _ \| '_ \       
       \ \_/ / | | (_| | (_| | | | | |/ / (_| | |_| | (_) | | | |      
        \___/|_|  \__, |\__,_|_| |_|_/___\__,_|\__|_|\___/|_| |_|      
                   __/ |                                               
                  |___/                                                
                                                                       
                                                                       
 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ 
|______|______|______|______|______|______|______|______|______|______|
                                                                       
                                                                       
                                                                       
                                                                       

     */
    public void addOrganization(Organization organization);

    public void deleteOrganization(int organizationId);

    public void updateOrganization(Organization organization);

    public Organization getOrganizationById(int id);

    public List<Organization> getAllOrganizations();
    
    public List<Hero> findAllHeroesByOrganization(int organizationId);




}
