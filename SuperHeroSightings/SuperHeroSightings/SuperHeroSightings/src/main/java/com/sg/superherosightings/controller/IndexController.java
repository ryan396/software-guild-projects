/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dto.Sighting;
import com.sg.superherosightings.service.HeroService;
import com.sg.superherosightings.service.LocationService;
import com.sg.superherosightings.service.SightingService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rianu
 */
@Controller
public class IndexController {
    
    HeroService hService;
    LocationService lService;
    SightingService sService;

    @Inject
    public IndexController(HeroService hService, LocationService lService,
            SightingService sService) {
        this.hService = hService;
        this.lService = lService;
        this.sService = sService;
    }
    
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        List<Sighting> sightingList = sService.getAllSightings().subList(0,10);
        model.addAttribute("sightingList", sightingList);

        return "index";
    }
    
}
