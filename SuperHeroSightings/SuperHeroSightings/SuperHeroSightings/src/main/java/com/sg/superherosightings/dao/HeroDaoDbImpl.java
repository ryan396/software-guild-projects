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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rianu
 */
public class HeroDaoDbImpl implements HeroDao {

    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
  

    private static final String SQL_INSERT_HERO
            = "insert into heroes (hero_name, description) "
            + "values (?, ?)";

    private static final String SQL_DELETE_HERO
            = "delete from heroes where hero_id = ?";

    private static final String SQL_UPDATE_HERO
            = "update heroes set hero_name = ?, description = ?"
            + "where hero_id =  ?";

    private static final String SQL_SELECT_HERO
            = "select * from heroes where hero_id = ?";

    private static final String SQL_SELECT_ALL_HEROES
            = "select * from heroes";

    //prepared statements HeroPowers table
    private static final String SQL_INSERT_HERO_POWERS
            = "insert into HeroPowers (hero_id, power_id) values(?, ?)";
    private static final String SQL_DELETE_HERO_POWERS
            = "delete from HeroPowers where hero_id = ?";

    //prepared statements HeroOrganization table
    private static final String SQL_INSERT_HERO_ORGANIZATION
            = "insert into heroorganizations (hero_id, organization_id) values (?, ?)";
    private static final String SQL_DELETE_HERO_ORGANIZATION
            = "delete from heroorganizations where hero_id = ?";
    private static final String SQL_SELECT_ALL_HEROES_BY_LOCATION
            = "select h.hero_id, h.hero_name, h.description "
            + "from heroes h "
            + "join herosightings hs on hs.hero_id = h.hero_id "
            + "join sightings s on hs.sighting_id = s.sighting_id "
            + "join locations l on s.location_id = l.location_id "
            + "where l.location_id = ?";
    private static final String SQL_SELECT_ORGANIZATIONS_BY_HERO_ID
            = "select * from organizations o join heroorganizations ho "
            + "on o.organization_id = ho.organization_id "
            + "where ho.hero_id = ?";
    private static final String SQL_SELECT_POWERS_BY_HERO_ID
            = "select * from powers p join heropowers ho "
            + "on p.power_id = ho.power_id "
            + "where ho.hero_id = ?";
    private static final String SQL_DELETE_SIGHTING_HERO
            = "delete from herosightings where sighting_id = ?";
    private static final String SQL_SELECT_LOCATIONS_BY_HERO_ID
            = "select * "
            + "from locations l "
            + "join sightings s on l.location_id = s.location_id "
            + "join herosightings hs on s.sighting_id = hs.sighting_id "
            + "where hs.hero_id = ?";
    private static final String SQL_SELECT_HEROES_BY_ORGANIZATION_ID
            = "SELECT h.hero_id, h.hero_name, h.description "
            + "from heroes h "
            + "join heroorganizations ho on h.hero_id = ho.hero_id "
            + "join organizations o on ho.organization_id = o.organization_id "
            + "where o.organization_id = ?";

    //helper methods
    
    private void insertHeroPowers(Hero hero) {
        final int heroId = hero.getHeroId();
        final List<Power> powers = hero.getPowers();

        for (Power currentPower : powers) {
            jdbcTemplate.update(SQL_INSERT_HERO_POWERS,
                    heroId, currentPower.getPowerId());

        }
    }
    
    private void insertHeroOrganizations(Hero hero) {
        final int heroId = hero.getHeroId();
        final List<Organization> organizations = hero.getOrganizations();
        for (Organization currentOrganization : organizations) {
            jdbcTemplate.update(SQL_INSERT_HERO_ORGANIZATION,
                    heroId, currentOrganization.getOrganizationId());

        }
    }

    @Override
    public List<Organization> findOrganizationsForHero(Hero hero) {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_HERO_ID,
                new OrganizationMapper(), hero.getHeroId());
    }
    
    @Override
    public List<Power> findPowersForHero(Hero hero) {
        return jdbcTemplate.query(SQL_SELECT_POWERS_BY_HERO_ID,
                new PowerMapper(), hero.getHeroId());
    }
    

    private List<Hero>
            associateOrganizationsAndPowersWithHeroes(List<Hero> heroList) {
        for (Hero currentHero : heroList) {
            currentHero.setOrganizations(findOrganizationsForHero(currentHero));
            currentHero.setPowers(findPowersForHero(currentHero));
        }
        return heroList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHero(Hero hero) {
        jdbcTemplate.update(SQL_INSERT_HERO,
                hero.getHeroName(),
                hero.getDescription());
        hero.setHeroId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        this.insertHeroPowers(hero);
        this.insertHeroOrganizations(hero);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteHero(int id) {
        jdbcTemplate.update(SQL_DELETE_HERO_POWERS, id);
        jdbcTemplate.update(SQL_DELETE_HERO_ORGANIZATION, id);
        jdbcTemplate.update(SQL_DELETE_SIGHTING_HERO, id);
        jdbcTemplate.update(SQL_DELETE_HERO, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateHero(Hero hero) {
        jdbcTemplate.update(SQL_UPDATE_HERO,
                hero.getHeroName(),
                hero.getDescription(),
                hero.getHeroId());
        jdbcTemplate.update(SQL_DELETE_HERO_POWERS, hero.getHeroId());
        jdbcTemplate.update(SQL_DELETE_HERO_ORGANIZATION, hero.getHeroId());
        this.insertHeroOrganizations(hero);
        this.insertHeroPowers(hero);

    }

    @Override
    public Hero getHeroById(int id) {
        try {
            Hero hero = jdbcTemplate.queryForObject(SQL_SELECT_HERO,
                    new HeroMapper(), id);
            hero.setOrganizations(this.findOrganizationsForHero(hero));
            hero.setPowers(this.findPowersForHero(hero));
            return hero;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        List<Hero> heroList
                = jdbcTemplate.query(SQL_SELECT_ALL_HEROES,
                        new HeroMapper());
        return this.associateOrganizationsAndPowersWithHeroes(heroList);

    }

    @Override
    public List<Hero> findAllHeroesForLocation(int locationId) {
        List<Hero> heroList = jdbcTemplate.query(SQL_SELECT_ALL_HEROES_BY_LOCATION,
                new HeroMapper(), locationId);
        for (Hero currentHero : heroList) {
            currentHero.setPowers(this.findPowersForHero(currentHero));
            currentHero.setOrganizations(this.findOrganizationsForHero(currentHero));
        }

        return heroList;

    }

    @Override
    public List<Location> findAllLocationsForHero(int heroId) {

        List<Location> locationList = jdbcTemplate.query(
                SQL_SELECT_LOCATIONS_BY_HERO_ID, new LocationMapper(),
                heroId);
        return locationList;
    }

    @Override
    public List<Hero> findAllHeroesByOrganization(int organizationId) {
        List<Hero> heroList = jdbcTemplate.query(SQL_SELECT_HEROES_BY_ORGANIZATION_ID,
                new HeroMapper(), organizationId);

        for (Hero currentHero : heroList) {
            currentHero.setPowers(this.findPowersForHero(currentHero));
            currentHero.setOrganizations(this.findOrganizationsForHero(currentHero));
        }

        return heroList;
    }
   
}
