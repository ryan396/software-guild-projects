/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.HeroDao;
import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Power;
import java.util.List;

/**
 *
 * @author rianu
 */
public class HeroServiceImpl implements HeroService {
    
    private HeroDao hDao;
    
    public HeroServiceImpl(HeroDao hDao) {
        this.hDao = hDao;
    }
    
    
    @Override
    public void addHero(Hero hero) {
        hDao.addHero(hero);
    }

    @Override
    public void deleteHero(int id) {
        hDao.deleteHero(id);
    }

    @Override
    public void updateHero(Hero hero) {
        hDao.updateHero(hero);
    }

    @Override
    public Hero getHeroById(int id) {
        return hDao.getHeroById(id);
    }

    @Override
    public List<Hero> getAllHeroes() {
        return hDao.getAllHeroes();
    }

    @Override
    public List<Organization> findOrganizationsForHero(Hero hero) {
        return hDao.findOrganizationsForHero(hero);
    }

    @Override
    public List<Hero> findAllHeroesForLocation(int locationId) {
        return hDao.findAllHeroesForLocation(locationId);
    }

    @Override
    public List<Hero> findAllHeroesByOrganization(int organizationId) {
        return hDao.findAllHeroesByOrganization(organizationId);
    }

    @Override
    public List<Location> findAllLocationsForHero(int heroId) {
        return hDao.findAllLocationsForHero(heroId);
    }

    @Override
    public List<Power> findPowersForHero(Hero hero) {
        return hDao.findPowersForHero(hero);
    }
    
}
