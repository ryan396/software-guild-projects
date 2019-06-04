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
import static java.lang.Integer.parseInt;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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

    @RequestMapping(value = "/displaySightingPage", method = RequestMethod.GET)
    public String displaySightingPage(Model model) {

        List<Sighting> sightingList = sService.getAllSightings();
        model.addAttribute("sightingList", sightingList);

        return "sightingPage";
    }

    @RequestMapping(value = "/displayAddSightingPage", method = RequestMethod.GET)
    public String displayAddHeroPage(Model model) {

        List<Hero> heroList = hService.getAllHeroes();
        List<Location> locationList = lService.getAllLocations();

        model.addAttribute("heroList", heroList);
        model.addAttribute("locationList", locationList);

        return "addSightingPage";
    }

    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);

        Sighting sighting = sService.getSightingById(sightingId);

        model.addAttribute("Sighting", sighting);

        List<Hero> heroList = sighting.getHeroes();

        Location location = sighting.getLocation();

        model.addAttribute("sighting", sighting);
        model.addAttribute("heroList", heroList);
        model.addAttribute("location", location);

        return "sightingDetails";
    }

    @RequestMapping(value = "/addSighting", method = RequestMethod.POST)
    public String addHero(HttpServletRequest request) {

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse(request.getParameter("date")));

        //find location using value of select location
        String[] locationString = request.getParameterValues("locationList");
        Location location = lService.getLocationById(parseInt(locationString[0]));
        sighting.setLocation(location);

        //find heroes by values select in the selectpicker
        //by converting their string values (which are string representations
        //of numbers)
        String[] heroes = request.getParameterValues("heroList");
        List<Hero> heroList = new ArrayList();
        for (int i = 0; i < heroes.length; i++) {
            heroList.add(hService.getHeroById(parseInt(heroes[i])));
        }

        sighting.setHeroes(heroList);

        sService.addSighting(sighting);

        return "redirect:displaySightingPage";
    }

    @PostMapping("/searchByDate")
    public String searchByDate(String date, Model model) {
        //grab date string from user input, convert to date, then
        //find all sightings by date given
        List<Sighting> sightingList = sService.findAllSightingsByDate(LocalDate.parse(date));
        model.addAttribute("sightingList", sightingList);

        return "sightingPage";
    }

}
