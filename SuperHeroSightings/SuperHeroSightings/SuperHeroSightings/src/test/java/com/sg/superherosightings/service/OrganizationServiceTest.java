/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

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
public class OrganizationServiceTest {

    private HeroService hService;

    private OrganizationService oService;

    private LocationService lService;

    private PowerService pService;

    private SightingService sService;

    public OrganizationServiceTest() {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        hService = ctx.getBean("heroService", HeroService.class);
        lService = ctx.getBean("locationService", LocationService.class);
        oService = ctx.getBean("organizationService", OrganizationService.class);
        pService = ctx.getBean("powerService", PowerService.class);
        sService = ctx.getBean("sightingService", SightingService.class);

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Sighting> sightings = sService.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sService.deleteSighting(currentSighting.getSightingId());
        }

        List<Hero> heroes = hService.getAllHeroes();
        for (Hero currentHero : heroes) {
            hService.deleteHero(currentHero.getHeroId());
        }

        List<Power> powers = pService.getAllPowers();
        for (Power currentPower : powers) {
            pService.deletePower(currentPower.getPowerId());
        }

        List<Organization> organizations = oService.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            oService.deleteOrganization(currentOrganization.getOrganizationId());
        }

        List<Location> locations = lService.getAllLocations();
        for (Location currentLocation : locations) {
            lService.deleteLocation(currentLocation.getLocationId());
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

        oService.addOrganization(o);

        Organization fromService = oService.getOrganizationById(o.getOrganizationId());
        assertEquals(fromService, o);
    }

    @Test
    public void deleteOrganization() {
        Organization o = createOrg1();

        oService.addOrganization(o);

        Organization fromService = oService.getOrganizationById(o.getOrganizationId());

        assertEquals(fromService, o);
        oService.deleteOrganization(o.getOrganizationId());
        assertNull(oService.getOrganizationById(o.getOrganizationId()));

        //test if deletes entries in organizations 
    }

    @Test
    public void getAllOrganizations() {
        Organization o = createOrg1();

        oService.addOrganization(o);

        Organization o2 = createOrg2();

        oService.addOrganization(o2);

        assertEquals(2, oService.getAllOrganizations().size());
    }

    @Test
    public void updateOrganization() {
        Organization o = createOrg1();

        oService.addOrganization(o);
        o.setCity("a new city");

        oService.updateOrganization(o);

        assertEquals(o, oService.getOrganizationById(o.getOrganizationId()));
    }

    @Test
    public void findAllHeroesByOrganization() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");
        pService.addPower(power);
        Organization o1 = createOrg1();
        oService.addOrganization(o1);

        Hero thor = createHero1();
        //create organization lists and power lists
        List<Organization> organizations = new ArrayList<>();
        organizations.add(o1);
        List<Power> powers = new ArrayList<>();
        powers.add(power);

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        hService.addHero(thor);

        Hero ironMan = createHero2();

        ironMan.setOrganizations(organizations);
        ironMan.setPowers(powers);
        hService.addHero(thor);

        List<Hero> fromService = hService.findAllHeroesByOrganization(o1.getOrganizationId());
        assertEquals(2, fromService.size());
    }

}
