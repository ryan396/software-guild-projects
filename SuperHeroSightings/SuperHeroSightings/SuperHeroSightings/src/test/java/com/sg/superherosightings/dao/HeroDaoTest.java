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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rianu
 */
public class HeroDaoTest {

    private HeroDao hDao;

    private OrganizationDao oDao;

    private LocationDao lDao;

    private PowerDao pDao;

    private SightingDao sDao;

    public HeroDaoTest() {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        hDao = ctx.getBean("heroDao", HeroDao.class);
        lDao = ctx.getBean("locationDao", LocationDao.class);
        oDao = ctx.getBean("organizationDao", OrganizationDao.class);
        pDao = ctx.getBean("powerDao", PowerDao.class);
        sDao = ctx.getBean("sightingDao", SightingDao.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {


        List<Sighting> sightings = sDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sDao.deleteSighting(currentSighting.getSightingId());
        }

        List<Hero> heroes = hDao.getAllHeroes();
        for (Hero currentHero : heroes) {
            hDao.deleteHero(currentHero.getHeroId());
        }

        List<Power> powers = pDao.getAllPowers();
        for (Power currentPower : powers) {
            pDao.deletePower(currentPower.getPowerId());
        }

        List<Organization> organizations = oDao.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            oDao.deleteOrganization(currentOrganization.getOrganizationId());
        }

        List<Location> locations = lDao.getAllLocations();
        for (Location currentLocation : locations) {
            lDao.deleteLocation(currentLocation.getLocationId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addHero method, of class HeroDao.
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
    public void addGetHero() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        pDao.addPower(power);
        Organization o1 = createOrg1();
        oDao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        hDao.addHero(thor);

        Hero fromDao = hDao.getHeroById(thor.getHeroId());

        assertEquals(fromDao, thor);

    }


    @Test
    public void deleteHero() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        pDao.addPower(power);
        Organization o1 = createOrg1();
        oDao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        hDao.addHero(thor);;

        Hero fromDao = hDao.getHeroById(thor.getHeroId());
        assertEquals(fromDao, thor);
        hDao.deleteHero(thor.getHeroId());
        assertNull(hDao.getHeroById(thor.getHeroId()));
    }

    @Test
    public void getAllHeroes() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        pDao.addPower(power);
        Organization o1 = createOrg1();
        oDao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        hDao.addHero(thor);

        assertEquals(1, hDao.getAllHeroes().size());
    }

    @Test
    public void updateHero() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        pDao.addPower(power);
        Organization o1 = createOrg1();
        oDao.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        hDao.addHero(thor);

        thor.setDescription("Asgardian God of Thunder");

        hDao.updateHero(thor);

        Hero fromDao = hDao.getHeroById(thor.getHeroId());
        assertEquals(fromDao, thor);
    }

    @Test
    public void findOrganizationsForHero() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        pDao.addPower(power);
        Organization o1 = createOrg1();
        oDao.addOrganization(o1);

        Organization o2 = createOrg2();
        oDao.addOrganization(o2);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        organizations.add(o2);
        List<Power> powers = new ArrayList<>();

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        hDao.addHero(thor);

        List<Organization> fromDao = hDao.findOrganizationsForHero(thor);

        assertEquals(fromDao, organizations);
    }

    @Test
    public void findHeroesForLocation() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        pDao.addPower(power);
        Organization o1 = createOrg1();
        oDao.addOrganization(o1);

        Organization o2 = createOrg2();
        oDao.addOrganization(o2);

        Hero thor = createHero1();

        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        organizations.add(o2);
        List<Power> powers = new ArrayList<>();
        powers.add(power);

        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        hDao.addHero(thor);

        Hero ironMan = createHero2();

        ironMan.setOrganizations(organizations);
        ironMan.setPowers(powers);
        hDao.addHero(ironMan);

        Location l = createLocation1();
        lDao.addLocation(l);

        List<Hero> heroList = new ArrayList<>();
        heroList.add(thor);
        heroList.add(ironMan);

        Sighting s = new Sighting();
        s.setDate(LocalDate.parse("2019-05-01", DateTimeFormatter.ISO_DATE));
        s.setHeroes(heroList);
        s.setLocation(lDao.getLocationById(l.getLocationId()));
        sDao.addSighting(s);

        List<Hero> fromDao = hDao.findAllHeroesForLocation(l.getLocationId());
        assertTrue(heroList.equals(fromDao));

    }
}
