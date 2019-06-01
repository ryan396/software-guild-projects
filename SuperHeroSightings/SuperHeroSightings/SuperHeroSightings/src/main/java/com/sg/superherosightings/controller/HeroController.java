/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Power;
import com.sg.superherosightings.service.HeroService;
import com.sg.superherosightings.service.OrganizationService;
import com.sg.superherosightings.service.PowerService;
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
public class HeroController {

    HeroService hService;
    OrganizationService oService;
    PowerService pService;

    @Inject
    public HeroController(HeroService hService, OrganizationService oService,
            PowerService pService) {
        this.hService = hService;
        this.oService = oService;
        this.pService = pService;
    }

    @RequestMapping(value = "/displayHeroPage", method = RequestMethod.GET)
    public String displayHeroPage(Model model) {

        List<Organization> organizationList = oService.getAllOrganizations();
        List<Hero> heroList = hService.getAllHeroes();

        model.addAttribute("organizationList", organizationList);
        model.addAttribute("heroList", heroList);

        return "heroPage";
    }

    @RequestMapping(value = "/displayAddHeroPage", method = RequestMethod.GET)
    public String displayAddHeroPage(Model model) {

        List<Power> powerList = pService.getAllPowers();
        List<Organization> organizationList = oService.getAllOrganizations();

        model.addAttribute("powerList", powerList);
        model.addAttribute("organizationList", organizationList);

        return "addHeroPage";
    }

    @PostMapping("/search")
    public String search(int organizationId, Model model) {
        if (organizationId == 0) {
            return "redirect:displayHeroPage";
        } else {
            List<Hero> heroList = hService.findAllHeroesByOrganization(organizationId);
            model.addAttribute("heroList", heroList);

            List<Organization> organizationList = oService.getAllOrganizations();
            model.addAttribute("organizationList", organizationList);

            return "heroPage";
        }
    }

    @RequestMapping(value = "/addHero", method = RequestMethod.POST)
    public String addHero(HttpServletRequest request, ArrayList<Integer> powerList, ArrayList<Integer> organizationList) {
 
        Hero hero = new Hero();
        hero.setHeroName(request.getParameter("heroName"));
        hero.setDescription(request.getParameter("description"));
        
        List<Power> currentHeroPowers = new ArrayList();
        for (int currentPowerInt : powerList) {
            currentHeroPowers.add(pService.getPowerById(currentPowerInt));
        }
        hero.setPowers(currentHeroPowers);
        
        List<Organization> currentHeroOrganizations = new ArrayList();
        for (int currentOrganizationInt: organizationList) {
            currentHeroOrganizations.add(oService.getOrganizationById(currentOrganizationInt));
        }
        hero.setOrganizations(currentHeroOrganizations);
        
        hService.addHero(hero);
        

        return "redirect:displayHeroPage";
    }
    
    
}
