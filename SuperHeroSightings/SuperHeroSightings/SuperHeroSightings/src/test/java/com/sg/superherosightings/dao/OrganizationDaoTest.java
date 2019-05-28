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
public class OrganizationDaoTest {

    private HeroDao hDao;

    private OrganizationDao oDao;

    private LocationDao lDao;

    private PowerDao pDao;

    private SightingDao sDao;

    public OrganizationDaoTest() {

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

    @Test
    public void addGetOrganization() {
        Organization o = createOrg1();

        oDao.addOrganization(o);

        Organization fromDao = oDao.getOrganizationById(o.getOrganizationId());
        assertEquals(fromDao, o);
    }

    @Test
    public void deleteOrganization() {
        Organization o = createOrg1();

        oDao.addOrganization(o);

        Organization fromDao = oDao.getOrganizationById(o.getOrganizationId());

        assertEquals(fromDao, o);
        oDao.deleteOrganization(o.getOrganizationId());
        assertNull(oDao.getOrganizationById(o.getOrganizationId()));

        //test if deletes entries in organizations 
    }

    @Test
    public void getAllOrganizations() {
        Organization o = createOrg1();

        oDao.addOrganization(o);

        Organization o2 = createOrg2();

        oDao.addOrganization(o2);

        assertEquals(2, oDao.getAllOrganizations().size());
    }

    @Test
    public void updateOrganization() {
        Organization o = createOrg1();

        oDao.addOrganization(o);
        o.setCity("a new city");

        oDao.updateOrganization(o);

        assertEquals(o, oDao.getOrganizationById(o.getOrganizationId()));
    }

    @Test
    public void findAllHeroesByOrganization() {
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
        powers.add(power);

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        hDao.addHero(thor);

        Hero ironMan = createHero2();

        ironMan.setOrganizations(organizations);
        ironMan.setPowers(powers);
        hDao.addHero(thor);

        List<Hero> fromDao = hDao.findAllHeroesByOrganization(o1.getOrganizationId());
        assertEquals(2, fromDao.size());
    }

}
