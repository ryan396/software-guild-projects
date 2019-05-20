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
import java.util.List;

/**
 *
 * @author rianu
 */
public interface HeroDao {
    
    public void addHero(Hero hero);

    public void deleteHero(int id);

    public void updateHero(Hero hero);

    public Hero getHeroById(int id);

    public List<Hero> getAllHeroes();
    
    public List<Organization> findOrganizationsForHero(Hero hero);
    
    public List<Hero> findAllHeroesForLocation(int locationId);
    
    public List<Hero> findAllHeroesByOrganization(int organizationId);
    
    public List<Location> findAllLocationsForHero(int heroId);
    
    public List<Power> findPowersForHero(Hero hero);
    
}
