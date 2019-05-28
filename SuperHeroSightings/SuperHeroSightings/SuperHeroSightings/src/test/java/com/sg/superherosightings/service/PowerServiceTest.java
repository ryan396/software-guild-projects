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
public class PowerServiceTest {

    private HeroService hService;

    private OrganizationService oService;

    private LocationService lService;

    private PowerService pService;

    private SightingService sService;

    public PowerServiceTest() {

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

    @Test
    public void addGetPower() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");

        pService.addPower(power);

        Power fromService = pService.getPowerById(power.getPowerId());
        assertEquals(fromService, power);

    }

    @Test
    public void deletePower() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");

        pService.addPower(power);

        Power fromService = pService.getPowerById(power.getPowerId());
        assertEquals(fromService, power);
        pService.deletePower(power.getPowerId());
        assertNull(pService.getPowerById(power.getPowerId()));
    }

    @Test
    public void getAllPowers() {
        Power power1 = new Power();
        power1.setPowerDescription("Eye Lasers");
        pService.addPower(power1);

        Power power2 = new Power();
        power2.setPowerDescription("Flight");
        pService.addPower(power2);

        assertEquals(2, pService.getAllPowers().size());
    }

    @Test
    public void updatePower() {
        Power power1 = new Power();
        power1.setPowerDescription("Eye Lasers");
        pService.addPower(power1);

        power1.setPowerDescription("Flight");

        pService.updatePower(power1);

        assertEquals(power1, pService.getPowerById(power1.getPowerId()));
    }

}
