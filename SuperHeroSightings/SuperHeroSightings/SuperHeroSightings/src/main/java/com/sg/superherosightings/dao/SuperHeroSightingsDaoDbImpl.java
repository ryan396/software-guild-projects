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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rianu
 */
public class SuperHeroSightingsDaoDbImpl implements SuperHeroSightingsDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
    private static final String SQL_INSERT_HERO
            = "insert into heroes (hero_name, description)"
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
            = "insert into HeroPowers (hero_id, power_id) values = (?, ?)";
    private static final String SQL_DELETE_HERO_POWERS
            = "delete from HeroPowers where hero_id = ?";

    //prepared statements HeroOrganization table
    private static final String SQL_INSERT_HERO_ORGANIZATION
            = "insert into heroorganizations (hero_id, organization_id) values = (?, ?)";
    private static final String SQL_DELETE_HERO_ORGANIZATION
            = "delete from heroorganizations where hero_id = ?";

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

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteHero(int id) {
        jdbcTemplate.update(SQL_DELETE_HERO_POWERS, id);
        jdbcTemplate.update(SQL_DELETE_HERO_ORGANIZATION, id);
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
    public List<Sighting> findAllSightingsByDate(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hero> findAllHeroesByOrganization(int organizationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero h = new Hero();
            h.setHeroName(rs.getString("hero_name"));
            h.setDescription(rs.getString("description"));
            h.setHeroId(rs.getInt("hero_id"));
            return h;
        }
    }

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
    private static final String SQL_INSERT_LOCATION
            = "insert into locations (location_name, latitude, longitude, "
            + "street, city, zip_code) values (?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from locations where location_id = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update locations set location_name = ?, latitude = ?, longitude = ?, "
            + "street = ?, city = ?, zip_code = ? "
            + "where location_id =  ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from locations where location_id = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from locations";

    private static final String SQL_SELECT_LOCATIONS_BY_HERO_ID
            = "select * from locations "
            + "join sightings s on l.location_id = s.location_id "
            + "join herosightings hs on s.location_id = hs.location_id "
            + "where hs.hero_id = ?";

    //prepared statements sighting table
    private static final String SQL_DELETE_SIGHTING_BY_LOCATION
            = "delete from sightings where location_id = ?";

    @Override
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getLatitude(),
                location.getLongitude(),
                location.getStreet(),
                location.getCity(),
                location.getZipCode());

        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        location.setLocation_id(locationId);
    }

    @Override
    public void deleteLocation(int id) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, id);
        jdbcTemplate.update(SQL_DELETE_SIGHTING_BY_LOCATION, id);
        //manage hero sightings bridge
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getLatitude(),
                location.getLongitude(),
                location.getStreet(),
                location.getCity(),
                location.getZipCode(),
                location.getLocation_id());
    }

    @Override
    public Location getLocationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new LocationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    
    
    @Override
    public List<Location> findAllLocationsForHero(int heroId) {

        List<Location> locationList = jdbcTemplate.query(
                SQL_SELECT_LOCATIONS_BY_HERO_ID, new LocationMapper(),
                heroId);
        return locationList;
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS,
                new LocationMapper());
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location l = new Location();
            l.setLocationName(rs.getString("location_name"));
            l.setLatitude(rs.getBigDecimal("latitude"));
            l.setLongitude(rs.getBigDecimal("longitude"));
            l.setStreet(rs.getString("street"));
            l.setCity(rs.getString("city"));
            l.setZipCode(rs.getInt("zip_code"));
            l.setLocation_id(rs.getInt("location_id"));
            return l;
        }
    }

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
    private static final String SQL_INSERT_POWER
            = "insert into powers (description) values (?)";

    private static final String SQL_DELETE_POWER
            = "delete from powers where power_id = ?";

    private static final String SQL_UPDATE_POWER
            = "update powers set description = ?"
            + "where power_id =  ?";

    private static final String SQL_SELECT_POWER
            = "select * from powers where power_id = ?";

    private static final String SQL_SELECT_ALL_POWERS
            = "select * from powers";

    private static final String SQL_SELECT_POWERS_BY_HERO_ID
            = "select * from powers p join heropowers ho "
            + "on p.power_id = ho.power_id "
            + "where ho.hero_id = ?";

    //prepared statements HeroPowers table
    private static final String SQL_INSERT_POWERS_HERO
            = "insert into heropowers (hero_id, power_id) values = (?, ?)";
    private static final String SQL_DELETE_POWERS_HERO
            = "delete from heropowers where power_id = ?";

    @Override
    @Transactional
    public void addPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWER,
                power.getPowerDescription());

        int powerId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        power.setPowerId(powerId);

    }

    @Override
    public void deletePower(int id) {
        jdbcTemplate.update(SQL_DELETE_POWER, id);
        //include bridge table delete
    }

    @Override
    public void updatePower(Power power) {
        jdbcTemplate.update(SQL_UPDATE_POWER,
                power.getPowerDescription(),
                power.getPowerId());
    }

    @Override
    public Power getPowerById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POWER,
                    new PowerMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWERS, new PowerMapper());
    }

    private static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power p = new Power();
            p.setPowerId(rs.getInt("power_id"));
            p.setPowerDescription(rs.getString("description"));
            return p;
        }
    }

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
    private static final String SQL_INSERT_SIGHTING
            = "insert into sightings (sighting_date, location_id) values (?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from sightings where sighting_id = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sightings set description = ?, location_id = ? "
            + "where sighting_id =  ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from sightings where sighting_id = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sightings";

    //prepared statements HeroSighting table
    private static final String SQL_INSERT_SIGHTING_HERO
            = "insert into herosightings (hero_id, sighting_id) values = (?, ?)";
    private static final String SQL_DELETE_SIGHTING_HERO
            = "delete from herosightings where sighting_id = ?";
    private static final String SQL_SELECT_HEROES_BY_SIGHTING_ID
            = "select * h.hero_id, h.hero_name, h.description, s.sighting_id "
            + "from heroes h join herosightings hs on hero_id "
            + "where h.hero_id = hs.hero_id and hs.hero_id = ?";
    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "select * from locations l "
            + "join sightings s on l.location_id = s.location_id "
            + "where s.sighting_id = ?";

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
            jdbcTemplate.update(SQL_INSERT_SIGHTING_HERO, sightingId,
                    currentHero.getHeroId());
        }
    }

    private List<Hero> findHeroesForSighting(Sighting sighting) {
        return jdbcTemplate.query(SQL_SELECT_HEROES_BY_SIGHTING_ID,
                new HeroMapper(), sighting.getSightingId());
    }

    private List<Sighting>
            associateLocationAndHeroesWithBooks(List<Sighting> sightingList) {

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
                sighting.getDate(),
                sighting.getLocation().getLocation_id());

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
                sighting.getDate(),
                sighting.getLocation().getLocation_id(),
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
    public List<Sighting> getAllSightings() {

        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
        return this.associateLocationAndHeroesWithBooks(sightingList);
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingId(rs.getInt("sighting_id"));
            s.setDate(rs.getTimestamp("date").toLocalDateTime().toLocalDate());
            return s;
        }
    }

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
    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organizations (organization_name, organization_description, "
            + "street, city, zip_code, phone, email) values "
            + "(?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organizations where organization_id = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organizations set organization_name = ?, organization_description = ?, "
            + "street = ?, city = ?, zip_code = ?, phone = ?, email = ? where organization_id =  ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from organizations where organization_id = ?";
    private static final String SQL_SELECT_ORGANIZATIONS_BY_HERO_ID
            = "select * from organizations o join heroorganizations ho "
            + "on o.organization_id = ho.organization_id "
            + "where ho.hero_id = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from organizations";

    @Override
    @Transactional
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getDescription(),
                organization.getStreet(),
                organization.getCity(),
                organization.getZipCode(),
                organization.getPhone(),
                organization.getEmail());

        int organizationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        organization.setOrganizationId(organizationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(int id) {
        jdbcTemplate.update(SQL_DELETE_HERO_ORGANIZATION, id);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, id);
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getDescription(),
                organization.getStreet(),
                organization.getCity(),
                organization.getZipCode(),
                organization.getPhone(),
                organization.getEmail(),
                organization.getOrganizationId());
    }

    @Override
    public Organization getOrganizationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrganizationMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrganizationMapper());
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization o = new Organization();
            o.setOrganizationName(rs.getString("organization_name"));
            o.setDescription(rs.getString("organization_description"));
            o.setStreet(rs.getString("street"));
            o.setCity(rs.getString("city"));
            o.setZipCode(rs.getInt("zip_code"));
            o.setPhone(rs.getString("phone"));
            o.setEmail(rs.getString("email"));
            o.setOrganizationId(rs.getInt("organization_id"));
            return o;
        }
    }

    /*
    
                                                                       
                                                                       
 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ 
|______|______|______|______|______|______|______|______|______|______|
                                                                       
                                                                       
                                                                       
                                                                       
   ___  ____              _  ___  ___     _   _               _        
   |  \/  (_)            | | |  \/  |    | | | |             | |       
   | .  . |___  _____  __| | | .  . | ___| |_| |__   ___   __| |___    
   | |\/| | \ \/ / _ \/ _` | | |\/| |/ _ \ __| '_ \ / _ \ / _` / __|   
   | |  | | |>  <  __/ (_| | | |  | |  __/ |_| | | | (_) | (_| \__ \   
   \_|  |_/_/_/\_\___|\__,_| \_|  |_/\___|\__|_| |_|\___/ \__,_|___/   
                                                                       
                                                                       
                                                                       
                                                                       
 ______ ______ ______ ______ ______ ______ ______ ______ ______ ______ 
|______|______|______|______|______|______|______|______|______|______|
                                                                       
                                                                       
                                                                       
                                                                       

     */
    private static final String SQL_SELECT_HEROES_BY_LOCATION
            = "select h.hero_id, h.hero_name, h.description from heroes h "
            + "join herosightings hs on h.hero_id = hs.hero_id "
            + "join sightings s on hs.sighting_id = s.sighting_id "
            + "join location l on s.location_id = l.location_id where "
            + "l.location_id = ?";

    private static final String SQL_SELECT_HEROES_BY_ORGANIZATION
            = "select h.hero_id, h.hero_name, h.description from heroes h "
            + "join heroorganizations ho on h.hero_id = oh.hero_id "
            + "where ho.organization_id = ?";



    private static final String SQL_SELECT_LOCATIONS_BY_HERO
            = "select h.hero_id, h.hero_name, l.location_id, l.location_name, "
            + "l.latitude, l.longitude from locations "
            + "join sightings s on l.location_id = s.sighting_id "
            + "join herosightings hs on s.hero_id = hs.hero_id "
            + "join heroes h on hs.hero_id = h.hero_id where "
            + "h.hero_id = ?";

    private static String SQL_SELECT_SIGHTINGS_BY_DATE
            = "select h.hero_id, h.hero_name, l.location_id, l.location_name "
            + "from locations l join sightings s on l.location_id = location_id "
            + "join herosightings hs on s.sighting_id = hs.sighting_id "
            + "join hero h on hs.hero_id = h.hero_id "
            + "where s.date = ?";


    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
