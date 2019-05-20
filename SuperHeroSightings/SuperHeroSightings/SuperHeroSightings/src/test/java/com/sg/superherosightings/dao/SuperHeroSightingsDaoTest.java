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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rianu
 */
public class SuperHeroSightingsDaoTest {

    SuperHeroSightingsDao dao;

    public SuperHeroSightingsDaoTest() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("superHeroSightingsDao", SuperHeroSightingsDao.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            dao.deleteSighting(currentSighting.getSightingId());
        }

        List<Hero> heroes = dao.getAllHeroes();
        for (Hero currentHero : heroes) {
            dao.deleteHero(currentHero.getHeroId());
        }

        List<Power> powers = dao.getAllPowers();
        for (Power currentPower : powers) {
            dao.deletePower(currentPower.getPowerId());
        }

        List<Organization> organizations = dao.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            dao.deleteOrganization(currentOrganization.getOrganizationId());
        }

        List<Location> locations = dao.getAllLocations();
        for (Location currentLocation : locations) {
            dao.deleteLocation(currentLocation.getLocationId());
        }

    }

    @After
    public void tearDown() {
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
    public Hero createHero1() {
        Hero h = new Hero();
        h.setHeroName("Thor");
        h.setDescription("God of Thunder");
        return h;
    }

    public Hero createHero2() {
        Hero h = new Hero();
        h.setHeroName("Iron Man");
        h.setDescription("Tech Wizard");
        return h;
    }

    @Test
    public void addGetHero() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);

        Hero fromDao = dao.getHeroById(thor.getHeroId());

        assertEquals(fromDao, thor);

    }

    @Test
    public void deleteHero() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);;

        Hero fromDao = dao.getHeroById(thor.getHeroId());
        assertEquals(fromDao, thor);
        dao.deleteHero(thor.getHeroId());
        assertNull(dao.getHeroById(thor.getHeroId()));
    }

    @Test
    public void getAllHeroes() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);

        assertEquals(1, dao.getAllHeroes().size());
    }

    @Test
    public void updateHero() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);

        thor.setDescription("Asgardian God of Thunder");

        dao.updateHero(thor);

        Hero fromDao = dao.getHeroById(thor.getHeroId());
        assertEquals(fromDao, thor);
    }

    @Test
    public void findOrganizationsForHero() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Organization o2 = createOrg2();
        dao.addOrganization(o2);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        organizations.add(o2);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);

        List<Organization> fromDao = dao.findOrganizationsForHero(thor);

        assertEquals(fromDao, organizations);
    }
    
    @Test 
    public void findHeroesForLocation() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Organization o2 = createOrg2();
        dao.addOrganization(o2);

        Hero thor = createHero1();

        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        organizations.add(o2);
        List<Power> powers = new ArrayList<>();
        powers.add(power);


        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);
        
        Hero ironMan = createHero2();
        
        ironMan.setOrganizations(organizations);
        ironMan.setPowers(powers);
        dao.addHero(ironMan);
        
        Location l = createLocation1();
        dao.addLocation(l);
        
        List<Hero> heroList = new ArrayList<>();
        heroList.add(thor);
        heroList.add(ironMan);

        Sighting s = new Sighting();
        s.setDate(LocalDate.parse("2019-05-01", DateTimeFormatter.ISO_DATE));
        s.setHeroes(heroList);
        s.setLocationId(dao.getLocationById(l.getLocationId()).getLocationId());
        dao.addSighting(s);
        
        List<Hero> fromDao = dao.findAllHeroesForLocation(l.getLocationId());
        assertTrue(heroList.equals(fromDao));
        
        
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
    @Test
    public void addGetPower() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");

        dao.addPower(power);

        Power fromDao = dao.getPowerById(power.getPowerId());
        assertEquals(fromDao, power);

    }

    @Test
    public void deletePower() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");

        dao.addPower(power);

        Power fromDao = dao.getPowerById(power.getPowerId());
        assertEquals(fromDao, power);
        dao.deletePower(power.getPowerId());
        assertNull(dao.getPowerById(power.getPowerId()));
    }

    @Test
    public void getAllPowers() {
        Power power1 = new Power();
        power1.setPowerDescription("Eye Lasers");
        dao.addPower(power1);

        Power power2 = new Power();
        power2.setPowerDescription("Flight");
        dao.addPower(power2);

        assertEquals(2, dao.getAllPowers().size());
    }

    @Test
    public void updatePower() {
        Power power1 = new Power();
        power1.setPowerDescription("Eye Lasers");
        dao.addPower(power1);

        power1.setPowerDescription("Flight");

        dao.updatePower(power1);

        assertEquals(power1, dao.getPowerById(power1.getPowerId()));
    }

    public Organization createOrg1() {
        Organization o = new Organization();
        o.setOrganizationName("Avengers");
        o.setDescription("Heroes of Earth");
        o.setCity("New York");
        o.setStreet("1222 Main Street");
        o.setZipCode(55555);
        o.setPhone("222-222-222");
        o.setEmail("avengers@hotmail.com");

        return o;
    }

    //Orgnization tests
    public Organization createOrg2() {
        Organization o = new Organization();
        o.setOrganizationName("X-Men");
        o.setDescription("Mutants");
        o.setCity("Los Angeles");
        o.setStreet("1223 Main Street");
        o.setZipCode(666666);
        o.setPhone("222-222-222");
        o.setEmail("xmen@gmail.com");

        return o;
    }

    @Test
    public void addGetOrganization() {
        Organization o = createOrg1();

        dao.addOrganization(o);

        Organization fromDao = dao.getOrganizationById(o.getOrganizationId());
        assertEquals(fromDao, o);
    }

    @Test
    public void deleteOrganization() {
        Organization o = createOrg1();

        dao.addOrganization(o);

        Organization fromDao = dao.getOrganizationById(o.getOrganizationId());

        assertEquals(fromDao, o);
        dao.deleteOrganization(o.getOrganizationId());
        assertNull(dao.getOrganizationById(o.getOrganizationId()));

        //test if deletes entries in organizations 
    }

    @Test
    public void getAllOrganizations() {
        Organization o = createOrg1();

        dao.addOrganization(o);

        Organization o2 = createOrg2();

        dao.addOrganization(o2);

        assertEquals(2, dao.getAllOrganizations().size());
    }

    @Test
    public void updateOrganization() {
        Organization o = createOrg1();

        dao.addOrganization(o);
        o.setCity("a new city");

        dao.updateOrganization(o);

        assertEquals(o, dao.getOrganizationById(o.getOrganizationId()));
    }
    
    @Test 
    public void findAllHeroesByOrganization() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();
        powers.add(power);

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);


        Hero ironMan = createHero2();
        
        ironMan.setOrganizations(organizations);
        ironMan.setPowers(powers);
        dao.addHero(thor);
        
        List<Hero> fromDao = dao.findAllHeroesByOrganization(o1.getOrganizationId());
        assertEquals(2, fromDao.size());
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
    //location tests
    public Location createLocation1() {
        Location l = new Location();
        l.setLocationName("Colorado");
        l.setLatitude(new BigDecimal("37.275"));
        l.setLongitude(new BigDecimal("107.880"));
        l.setStreet("124");
        l.setCity("Durango");
        l.setStreet("527 Main Avenue");
        l.setZipCode(81301);
        return l;
    }

    public Location createLocation2() {
        Location l = new Location();
        l.setLocationName("Wisconsin");
        l.setLatitude(new BigDecimal("43.784"));
        l.setLongitude(new BigDecimal("88.787"));
        l.setStreet("123");
        l.setCity("Minneapolis");
        l.setZipCode(55423);
        return l;
    }

    @Test
    public void addGetDeleteLocation() {
        Location l = createLocation1();

        dao.addLocation(l);

        Location fromDao = dao.getLocationById(l.getLocationId());
        assertEquals(l.getLocationName(), fromDao.getLocationName());
        assertTrue(l.getLatitude().compareTo(fromDao.getLatitude()) == 0);
        assertTrue(l.getLongitude().compareTo(fromDao.getLongitude()) == 0);
        assertEquals(l.getStreet(), fromDao.getStreet());
        assertEquals(l.getCity(), fromDao.getCity());;
        assertEquals(l.getLocationId(), fromDao.getLocationId());

        dao.deleteLocation(l.getLocationId());
        assertNull(dao.getLocationById(0));
    }

    @Test
    public void updateLocation() {
        Location l = createLocation1();

        dao.addLocation(l);
        l.setCity("new city");

        dao.updateLocation(l);

        Location fromDao = dao.getLocationById(l.getLocationId());
        assertEquals(l.getLocationName(), fromDao.getLocationName());
        assertTrue(l.getLatitude().compareTo(fromDao.getLatitude()) == 0);
        assertTrue(l.getLongitude().compareTo(fromDao.getLongitude()) == 0);
        assertEquals(l.getStreet(), fromDao.getStreet());
        assertEquals(l.getCity(), fromDao.getCity());;
        assertEquals(l.getLocationId(), fromDao.getLocationId());
    }

    @Test
    public void findAllLocationsForHero() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(thor);

        Location l = createLocation1();
        dao.addLocation(l);

        Sighting s = new Sighting();
        s.setDate(LocalDate.parse("2019-05-01", DateTimeFormatter.ISO_DATE));
        s.setHeroes(heroes);
        s.setLocationId(dao.getLocationById(l.getLocationId()).getLocationId());

        dao.addSighting(s);
        
        Location l2 = createLocation2();
        dao.addLocation(l2);
        
        Sighting s2 = new Sighting();
        s2.setDate(LocalDate.parse("2019-05-02", DateTimeFormatter.ISO_DATE));
        s2.setHeroes(heroes);
        s2.setLocationId(dao.getLocationById(l2.getLocationId()).getLocationId());
        
        dao.addSighting(s2);
        
        List<Location> locations = dao.findAllLocationsForHero(thor.getHeroId());
        
        assertEquals(2, locations.size());
        
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
    @Test
    public void addGetSighting() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(thor);

        Location l = createLocation1();
        dao.addLocation(l);

        Sighting s = new Sighting();
        s.setDate(LocalDate.parse("2019-05-01", DateTimeFormatter.ISO_DATE));
        s.setHeroes(heroes);
        s.setLocationId(dao.getLocationById(l.getLocationId()).getLocationId());

        dao.addSighting(s);

        Sighting fromDao = dao.getSightingById(s.getSightingId());
        assertTrue(fromDao.equals(s));
    }

    @Test
    public void deleteSighting() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(thor);

        Location l = createLocation1();
        dao.addLocation(l);

        Sighting s = new Sighting();
        s.setDate(LocalDate.parse("2019-05-01", DateTimeFormatter.ISO_DATE));
        s.setHeroes(heroes);
        s.setLocationId(dao.getLocationById(l.getLocationId()).getLocationId());

        dao.addSighting(s);

        Sighting fromDao = dao.getSightingById(s.getSightingId());
        assertTrue(fromDao.equals(s));

        dao.deleteSighting(s.getSightingId());

        assertNull(dao.getSightingById(s.getSightingId()));
    }

    @Test
    public void getAllSightings() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(thor);

        Location l = createLocation1();
        dao.addLocation(l);

        Sighting s = new Sighting();
        s.setDate(LocalDate.parse("2019-05-01", DateTimeFormatter.ISO_DATE));
        s.setHeroes(heroes);
        s.setLocationId(dao.getLocationById(l.getLocationId()).getLocationId());

        dao.addSighting(s);

        Power power2 = new Power();
        power2.setPowerDescription("Flight");
        dao.addPower(power2);
        Organization o2 = createOrg2();
        dao.addOrganization(o2);

        Hero ironMan = createHero2();
        //create organization lists and power lists
        List<Organization> organizations2 = new ArrayList<>();
        organizations2.add(o2);
        List<Power> powers2 = new ArrayList<>();

        //set organizations and powers to thor object
        ironMan.setOrganizations(organizations2);
        ironMan.setPowers(powers2);
        dao.addHero(ironMan);

        List<Hero> heroes2 = new ArrayList<>();
        heroes2.add(thor);

        Location l2 = createLocation2();
        dao.addLocation(l2);

        Sighting s2 = new Sighting();
        s2.setDate(LocalDate.parse("2019-05-01", DateTimeFormatter.ISO_DATE));
        s2.setHeroes(heroes2);
        s2.setLocationId(dao.getLocationById(l2.getLocationId()).getLocationId());

        dao.addSighting(s2);

        assertEquals(2, dao.getAllSightings().size());
    }

    @Test
    public void updateSighting() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(thor);

        Location l = createLocation1();
        dao.addLocation(l);

        Sighting s = new Sighting();
        s.setDate(LocalDate.parse("2019-05-01", DateTimeFormatter.ISO_DATE));
        s.setHeroes(heroes);
        s.setLocationId(dao.getLocationById(l.getLocationId()).getLocationId());

        dao.addSighting(s);
        s.setDate(LocalDate.parse("2019-05-02", DateTimeFormatter.ISO_DATE));
        dao.updateSighting(s);

        Sighting fromDao = dao.getSightingById(s.getSightingId());
        assertTrue(fromDao.equals(s));

    }

    @Test
    public void findAllSightingsByDate() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        dao.addPower(power);
        Organization o1 = createOrg1();
        dao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        dao.addHero(thor);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(thor);

        Location l = createLocation1();
        dao.addLocation(l);

        Sighting s = new Sighting();
        s.setDate(LocalDate.parse("2019-05-01", DateTimeFormatter.ISO_DATE));
        s.setHeroes(heroes);
        s.setLocationId(dao.getLocationById(l.getLocationId()).getLocationId());

        dao.addSighting(s);

        Power power2 = new Power();
        power2.setPowerDescription("Flight");
        dao.addPower(power2);
        Organization o2 = createOrg2();
        dao.addOrganization(o2);

        Hero ironMan = createHero2();
        //create organization lists and power lists
        List<Organization> organizations2 = new ArrayList<>();
        organizations2.add(o2);
        List<Power> powers2 = new ArrayList<>();

        //set organizations and powers to thor object
        ironMan.setOrganizations(organizations2);
        ironMan.setPowers(powers2);
        dao.addHero(ironMan);

        List<Hero> heroes2 = new ArrayList<>();
        heroes2.add(thor);

        Location l2 = createLocation2();
        dao.addLocation(l2);

        Sighting s2 = new Sighting();
        s2.setDate(LocalDate.parse("2019-05-01", DateTimeFormatter.ISO_DATE));
        s2.setHeroes(heroes2);
        s2.setLocationId(dao.getLocationById(l2.getLocationId()).getLocationId());
        
        dao.addSighting(s2);

        List<Sighting> fromDao
                = dao.findAllSightingsByDate(LocalDate.parse("2019-05-01"));

        assertEquals(2, fromDao.size());
    }

}
