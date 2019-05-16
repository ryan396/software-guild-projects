/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Power;
import java.math.BigDecimal;
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
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("superHeroSightingsDao", SuperHeroSightingsDao.class);

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
            dao.deleteLocation(currentLocation.getLocation_id());
        }
    }

    @After
    public void tearDown() {
    }

    //Power tests
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

    //Orgnization tests
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

    //location tests
    public Location createLocation1() {
        Location l = new Location();
        l.setLocationName("Colorado");
        l.setLatitude(new BigDecimal("37.275"));
        l.setLongitude(new BigDecimal("107.880"));
        l.setStreet(null);
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
        l.setStreet(null);
        l.setCity(null);
        l.setStreet(null);
        l.setZipCode(0);
        return l;
    }

    @Test
    public void addGetDeleteLocation() {
        Location l = createLocation1();

        dao.addLocation(l);

        Location fromDao = dao.getLocationById(l.getLocation_id());
        assertEquals(l.getLocationName(), fromDao.getLocationName());
        assertTrue(l.getLatitude().compareTo(fromDao.getLatitude()) == 0);
        assertTrue(l.getLongitude().compareTo(fromDao.getLongitude()) == 0);
        assertEquals(l.getStreet(), fromDao.getStreet());
        assertEquals(l.getCity(), fromDao.getCity());;
        assertEquals(l.getLocation_id(), fromDao.getLocation_id());

        dao.deleteOrganization(l.getLocation_id());
        assertNull(dao.getOrganizationById(0));
    }

    @Test
    public void updateLocation() {
        Location l = createLocation1();

        dao.addLocation(l);
        l.setCity("new city");

        dao.updateLocation(l);

        Location fromDao = dao.getLocationById(l.getLocation_id());
        assertEquals(l.getLocationName(), fromDao.getLocationName());
        assertTrue(l.getLatitude().compareTo(fromDao.getLatitude()) == 0);
        assertTrue(l.getLongitude().compareTo(fromDao.getLongitude()) == 0);
        assertEquals(l.getStreet(), fromDao.getStreet());
        assertEquals(l.getCity(), fromDao.getCity());;
        assertEquals(l.getLocation_id(), fromDao.getLocation_id());
    }

}
