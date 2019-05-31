/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.service.HeroService;
import com.sg.superherosightings.service.OrganizationService;
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
public class HeroController {
    
    HeroService hService;
    OrganizationService oService;
    
    @Inject
    public HeroController(HeroService hService, OrganizationService oService) {
        this.hService = hService;
        this.oService = oService;
    }
    
    @RequestMapping(value="/displayHeroPage", method=RequestMethod.GET)
    public String displayHeroPage(Model model) {
        
        List<Organization> organizationList = oService.getAllOrganizations();
        List<Hero> heroList = hService.getAllHeroes();
        
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("heroList", heroList);
        
        return "heroPage";
    }
    
    @RequestMapping(value = "/displayAddHeroPage", method = RequestMethod.GET)
    public String displayAddHeroPage() {

        return "addHeroPage";
    }
    
    
    
}
