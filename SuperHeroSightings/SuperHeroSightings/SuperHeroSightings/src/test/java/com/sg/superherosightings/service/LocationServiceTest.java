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
public class LocationServiceTest {

    private HeroService hService;

    private OrganizationService oService;

    private LocationService lService;

    private PowerService pService;

    private SightingService sService;

    public LocationServiceTest() {

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
    public void addGetDeleteLocation() {
        Location l = createLocation1();

        lService.addLocation(l);

        Location fromService = lService.getLocationById(l.getLocationId());
        assertEquals(l.getLocationName(), fromService.getLocationName());
        assertTrue(l.getLatitude().compareTo(fromService.getLatitude()) == 0);
        assertTrue(l.getLongitude().compareTo(fromService.getLongitude()) == 0);
        assertEquals(l.getStreet(), fromService.getStreet());
        assertEquals(l.getCity(), fromService.getCity());;
        assertEquals(l.getLocationId(), fromService.getLocationId());

        lService.deleteLocation(l.getLocationId());
        assertNull(lService.getLocationById(0));
    }

    @Test
    public void updateLocation() {
        Location l = createLocation1();

        lService.addLocation(l);
        l.setCity("new city");

        lService.updateLocation(l);

        Location fromService = lService.getLocationById(l.getLocationId());
        assertEquals(l.getLocationName(), fromService.getLocationName());
        assertTrue(l.getLatitude().compareTo(fromService.getLatitude()) == 0);
        assertTrue(l.getLongitude().compareTo(fromService.getLongitude()) == 0);
        assertEquals(l.getStreet(), fromService.getStreet());
        assertEquals(l.getCity(), fromService.getCity());;
        assertEquals(l.getLocationId(), fromService.getLocationId());
    }

    @Test
    public void findAllLocationsForHero() {
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

        //set organizations and powers to thor object
        thor.setOrganizations(organizations);
        thor.setPowers(powers);
        hService.addHero(thor);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(thor);

        Location l = createLocation1();
        lService.addLocation(l);

        Sighting s = new Sighting();
        s.setDate(LocalDate.parse("2019-05-01", DateTimeFormatter.ISO_DATE));
        s.setHeroes(heroes);
        s.setLocation(lService.getLocationById(l.getLocationId()));

        sService.addSighting(s);

        Location l2 = createLocation2();
        lService.addLocation(l2);

        Sighting s2 = new Sighting();
        s2.setDate(LocalDate.parse("2019-05-02", DateTimeFormatter.ISO_DATE));
        s2.setHeroes(heroes);
        s2.setLocation(lService.getLocationById(l2.getLocationId()));

        sService.addSighting(s2);

        List<Location> locations = hService.findAllLocationsForHero(thor.getHeroId());

        assertEquals(2, locations.size());

    }

}
