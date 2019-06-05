/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dto.Hero;
import com.sg.superherosightings.dto.Location;
import com.sg.superherosightings.dto.Organization;
import com.sg.superherosightings.dto.Power;
import com.sg.superherosightings.service.HeroService;
import com.sg.superherosightings.service.OrganizationService;
import com.sg.superherosightings.service.PowerService;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping(value = "/displayHeroDetails", method = RequestMethod.GET)
    public String displayHeroDetails(HttpServletRequest request, Model model) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);

        Hero hero = hService.getHeroById(heroId);

        List<Power> powerList = hero.getPowers();
        List<Organization> organizationList = hero.getOrganizations();
        List<Location> locationList = hService.findAllLocationsForHero(heroId);
        
        model.addAttribute("locationList", locationList);
        model.addAttribute("hero", hero);
        model.addAttribute("powerList", powerList);
        model.addAttribute("organizationList", organizationList);

        return "heroDetails";
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
    public String addHero(HttpServletRequest request) {

        Hero hero = new Hero();
        hero.setHeroName(request.getParameter("heroName"));
        hero.setDescription(request.getParameter("description"));
        String[] powers = request.getParameterValues("powerList");

        List<Power> powerList = new ArrayList();
        for (int i = 0; i < powers.length; i++) {
            powerList.add(pService.getPowerById(parseInt(powers[i])));
        }
        hero.setPowers(powerList);
        
        String[] organizations = request.getParameterValues("organizationList");
        List<Organization> organizationList = new ArrayList();
        for (int i = 0; i < organizations.length; i++) {
            organizationList.add(oService.getOrganizationById(parseInt(organizations[i])));
        }
        
        hero.setOrganizations(organizationList);

        hService.addHero(hero);

        return "redirect:displayHeroPage";
    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        hService.deleteHero(heroId);
        return "redirect:displayHeroPage";
    }

    @RequestMapping(value = "/displayEditHeroPage", method = RequestMethod.GET)
    public String displayEditHeroPage(HttpServletRequest request, Model model) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        Hero hero = hService.getHeroById(heroId);
        List<Power> powerList = pService.getAllPowers();
        List<Organization> organizationList = oService.getAllOrganizations();

        model.addAttribute("powerList", powerList);
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("hero", hero);
        return "editHeroPage";
    }

    @RequestMapping(value = "/editHero", method = RequestMethod.POST)
    public String editHero(HttpServletRequest request, @ModelAttribute("hero") Hero hero) {

        String[] powers = request.getParameterValues("powerList");

        List<Power> powerList = new ArrayList();
        for (int i = 0; i < powers.length; i++) {
            powerList.add(pService.getPowerById(parseInt(powers[i])));
        }
        hero.setPowers(powerList);
        
        String[] organizations = request.getParameterValues("organizationList");
        List<Organization> organizationList = new ArrayList();
        for (int i = 0; i < organizations.length; i++) {
            organizationList.add(oService.getOrganizationById(parseInt(organizations[i])));
        }
        
        hero.setOrganizations(organizationList);

        hService.updateHero(hero);
        return "redirect:displayHeroPage";
    }

}
