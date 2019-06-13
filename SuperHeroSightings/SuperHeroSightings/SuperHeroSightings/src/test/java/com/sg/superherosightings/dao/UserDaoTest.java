/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

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
public class UserDaoTest {
    
    private UserDao uDao;
    
    public UserDaoTest() {
        
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        
        uDao = ctx.getBean("userDao", UserDao.class);
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        List<User> users = uDao.getAllUsers();
        for(User currentUser : users) {
            uDao.deleteUser(currentUser.getUsername());
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
        
        uDao.addUser(user);
        
        List<User> fromDao = uDao.getAllUsers();
        assertEquals(1, fromDao.size());
        
        uDao.deleteUser(user.getUsername());
        
        List<User> fromDao2 = uDao.getAllUsers();
        assertEquals(0, fromDao2.size());
        
        
    }
    
}
