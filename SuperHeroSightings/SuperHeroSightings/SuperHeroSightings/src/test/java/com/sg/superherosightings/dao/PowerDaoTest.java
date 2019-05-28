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
public class PowerDaoTest {

    private HeroDao hDao;

    private OrganizationDao oDao;

    private LocationDao lDao;

    private PowerDao pDao;

    private SightingDao sDao;

    public PowerDaoTest() {

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

    @Test
    public void addGetPower() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");

        pDao.addPower(power);

        Power fromDao = pDao.getPowerById(power.getPowerId());
        assertEquals(fromDao, power);

    }

    @Test
    public void deletePower() {
        Power power = new Power();
        power.setPowerDescription("Eye Lasers");

        pDao.addPower(power);

        Power fromDao = pDao.getPowerById(power.getPowerId());
        assertEquals(fromDao, power);
        pDao.deletePower(power.getPowerId());
        assertNull(pDao.getPowerById(power.getPowerId()));
    }

    @Test
    public void getAllPowers() {
        Power power1 = new Power();
        power1.setPowerDescription("Eye Lasers");
        pDao.addPower(power1);

        Power power2 = new Power();
        power2.setPowerDescription("Flight");
        pDao.addPower(power2);

        assertEquals(2, pDao.getAllPowers().size());
    }

    @Test
    public void updatePower() {
        Power power1 = new Power();
        power1.setPowerDescription("Eye Lasers");
        pDao.addPower(power1);

        power1.setPowerDescription("Flight");

        pDao.updatePower(power1);

        assertEquals(power1, pDao.getPowerById(power1.getPowerId()));
    }

}
