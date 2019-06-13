/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dto.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rianu
 */
public class TheUserServiceTest {

    private TheUserService uService;

    public TheUserServiceTest() {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        uService = ctx.getBean("theUserService", TheUserService.class);

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<User> users = uService.getAllUsers();
        for(User currentUser : users) {
            uService.deleteUser(currentUser.getUsername());
        }
    }

    @After
    public void tearDown() {
    }

     @Test
    public void addGetDeleteUser() {
        
        User user = new User();
        ArrayList<String> authorities = new ArrayList();
        authorities.add("ROLE_USER");
        user.setAuthorities(authorities);
        user.setUsername("user");
        user.setPassword("password");
        
        uService.addUser(user);
        
        List<User> fromService = uService.getAllUsers();
        assertEquals(1, fromService.size());
        
        uService.deleteUser(user.getUsername());
        
        List<User> fromService2 = uService.getAllUsers();
        assertEquals(0, fromService2.size());
        
        
    }

}
