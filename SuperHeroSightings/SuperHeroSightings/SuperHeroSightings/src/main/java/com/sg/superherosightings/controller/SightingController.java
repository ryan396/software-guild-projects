/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Sighting;
import com.sg.superherosightings.service.HeroService;
import com.sg.superherosightings.service.LocationService;
import com.sg.superherosightings.service.SightingService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rianu
 */
@Controller
public class SightingController {
    
    HeroService hService;
    LocationService lService;
    SightingService sService;
    
    @Inject
    public SightingController(HeroService hService, LocationService lService, 
            SightingService sService) {
        this.hService = hService;
        this.lService = lService;
        this.sService = sService;
    }
    
    @RequestMapping(value="/displaySightingPage", method=RequestMethod.GET)
    public String displaySightingPage(Model model) {
        
        List<Sighting> sightingList = sService.getAllSightings();
        model.addAttribute("sightingList", sightingList);
        
        return "sightingPage";
    }
    
    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);

        Sighting sighting = sService.getSightingById(sightingId);

        model.addAttribute("Sighting", sighting);

        List<Hero> heroList = sighting.getHeroes();
        
        Location location = lService.getLocationById(sighting.getLocationId());
        
        model.addAttribute("sighting", sighting);
        model.addAttribute("heroList", heroList);
        model.addAttribute("location", location);

        return "sightingDetails";
    }
    
}
