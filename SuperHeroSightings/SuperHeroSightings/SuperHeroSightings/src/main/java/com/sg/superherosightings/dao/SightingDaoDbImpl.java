/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Sighting;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rianu
 */
public class SightingDaoDbImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;
    HeroDao hDao;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public SightingDaoDbImpl(HeroDao hDao) {
        this.hDao = hDao;
    }

    private static final String SQL_INSERT_SIGHTING
            = "insert into sightings (sighting_date, location_id) values (?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from sightings where sighting_id = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sightings set sighting_date = ?, location_id = ? "
            + "where sighting_id =  ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from sightings where sighting_id = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sightings";

    //prepared statements HeroSighting table
    private static final String SQL_INSERT_SIGHTING_HERO
            = "insert into herosightings (hero_id, sighting_id) values (?, ?)";
    private static final String SQL_DELETE_SIGHTING_HERO
            = "delete from herosightings where sighting_id = ?";
    private static final String SQL_SELECT_HEROES_BY_SIGHTING_ID
            = "select h.hero_id, h.hero_name, h.description, s.sighting_id, s.sighting_date "
            + "from sightings s "
            + "join herosightings hs on s.sighting_id = hs.sighting_id "
            + "join heroes h on hs.hero_id = h.hero_id "
            + "where s.sighting_id = ?";
    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "select * from locations l "
            + "join sightings s on l.location_id = s.location_id "
            + "where s.sighting_id = ?";
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            = "select s.sighting_id, l.location_name, l.location_id, h.hero_name, s.sighting_date "
            + "from locations l "
            + "join sightings s on l.location_id = s.location_id "
            + "join herosightings hs on s.sighting_id = hs.sighting_id "
            + "join heroes h on hs.hero_id = h.hero_id "
            + "where s.sighting_date = ?";

    //helper method for finding location
    private Location findLocationForSighting(Sighting sighting) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID,
                new LocationMapper(),
                sighting.getSightingId());
    }

    //helper methods
    private void insertHeroSightings(Sighting sighting) {
        final int sightingId = sighting.getSightingId();
        final List<Hero> heroes = sighting.getHeroes();

        for (Hero currentHero : heroes) {
            jdbcTemplate.update(SQL_INSERT_SIGHTING_HERO, currentHero.getHeroId(),
                    sightingId);
        }
    }

    private List<Hero> findHeroesForSighting(Sighting sighting) {
        List<Hero> heroes = jdbcTemplate.query(SQL_SELECT_HEROES_BY_SIGHTING_ID,
                new HeroMapper(), sighting.getSightingId());
        for (Hero currentHero : heroes) {
            currentHero.setPowers(hDao.findPowersForHero(currentHero));
            currentHero.setOrganizations(hDao.findOrganizationsForHero(currentHero));
        }

        return heroes;
    }

    private List<Sighting>
            associateLocationAndHeroesWithSighting(List<Sighting> sightingList) {

        for (Sighting currentSighting : sightingList) {

            currentSighting.setLocation(this.findLocationForSighting(currentSighting));
            currentSighting.setHeroes(this.findHeroesForSighting(currentSighting));
        }

        return sightingList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getDate().toString(),
                sighting.getLocation().getLocationId());

        sighting.setSightingId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));

        this.insertHeroSightings(sighting);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(int id) {
        //have to delete reference in bridge table first
        jdbcTemplate.update(SQL_DELETE_SIGHTING_HERO, id);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting sighting) {

        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getDate().toString(),
                sighting.getLocation().getLocationId(),
                sighting.getSightingId());
        jdbcTemplate.update(SQL_DELETE_SIGHTING_HERO, sighting.getSightingId());
        this.insertHeroSightings(sighting);
    }

    @Override
    public Sighting getSightingById(int id) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(),
                    id);

            sighting.setLocation(findLocationForSighting(sighting));

            sighting.setHeroes(findHeroesForSighting(sighting));
            return sighting;

        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> findAllSightingsByDate(LocalDate date) {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE,
                new SightingMapper(), Date.valueOf(date));

        this.associateLocationAndHeroesWithSighting(sightings);
        return sightings;
    }

    @Override
    public List<Sighting> getAllSightings() {

        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
        return this.associateLocationAndHeroesWithSighting(sightingList);
    }


}
