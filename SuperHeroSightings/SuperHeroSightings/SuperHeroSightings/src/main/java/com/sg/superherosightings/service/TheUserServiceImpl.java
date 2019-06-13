/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.UserDao;
import com.sg.superherosightings.dto.User;
import java.util.List;

/**
 *
 * @author rianu
 */
public class TheUserServiceImpl implements TheUserService {
    
    private UserDao uDao;
    
    public TheUserServiceImpl(UserDao uDao) {
        this.uDao = uDao;
    }
    
    @Override
    public User addUser(User newUser) {
        return uDao.addUser(newUser);
    }

    @Override
    public void deleteUser(String username) {
        uDao.deleteUser(username);
    }

    @Override
    public List<User> getAllUsers() {
        return uDao.getAllUsers();
    }
    
}
