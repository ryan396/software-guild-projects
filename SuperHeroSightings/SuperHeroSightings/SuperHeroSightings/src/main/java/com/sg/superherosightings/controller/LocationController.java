/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.service.LocationService;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author rianu
 */
@Controller
public class LocationController {

    LocationService lService;

    @Inject
    public LocationController(LocationService lService) {
        this.lService = lService;
    }

    @RequestMapping(value = "/displayLocationPage", method = RequestMethod.GET)
    public String displayLocationPage(Model model) {

        List<Location> locationList = lService.getAllLocations();
        model.addAttribute("locationList", locationList);

        return "locationPage";
    }
    
    @RequestMapping(value = "/displayAddLocationPage", method = RequestMethod.GET)
    public String displayAddLocationPage() {

        return "addLocationPage";
    }

    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);

        Location location = lService.getLocationById(locationId);

        model.addAttribute("location", location);

        return "locationDetails";
    }

    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request) {

        Location location = new Location();
        location.setLocationName(request.getParameter("locationName"));
        location.setStreet(request.getParameter("street"));
        location.setCity(request.getParameter("city"));
        location.setZipCode(Integer.parseInt(request.getParameter("zipCode")));
        location.setLatitude(new BigDecimal(request.getParameter("latitude")));
        location.setLongitude(new BigDecimal(request.getParameter("longitude")));

        lService.addLocation(location);

        return "redirect:displayLocationPage";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        lService.deleteLocation(locationId);
        return "redirect:displayLocationPage";
    }

    @RequestMapping(value = "/displayEditLocationPage", method = RequestMethod.GET)
    public String displayEditLocationPage(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        Location location = lService.getLocationById(locationId);
        model.addAttribute("location", location);
        return "editLocationPage";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@ModelAttribute("location") Location location) {

        lService.updateLocation(location);

        return "redirect:displayLocationPage";
    }

}
