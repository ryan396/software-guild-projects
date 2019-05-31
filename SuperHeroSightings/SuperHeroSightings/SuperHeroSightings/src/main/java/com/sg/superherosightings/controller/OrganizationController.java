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
public class OrganizationController {

    OrganizationService oService;
    HeroService hService;

    @Inject
    public OrganizationController(OrganizationService oService, HeroService hService) {
        this.oService = oService;
        this.hService = hService;
    }

    @RequestMapping(value = "/displayOrganizationPage", method = RequestMethod.GET)
    public String displayOrganizationPage(Model model) {

        List<Organization> organizationList = oService.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);

        return "organizationPage";
    }

    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);

        Organization organization = oService.getOrganizationById(organizationId);

        model.addAttribute("organization", organization);

        List<Hero> heroList = hService.findAllHeroesByOrganization(organizationId);

        model.addAttribute("heroList", heroList);

        return "organizationDetails";
    }

    @RequestMapping(value = "/displayAddOrganizationPage", method = RequestMethod.GET)
    public String displayAddOrganizationPage() {

        return "addOrganizationPage";
    }

    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public String addOrganization(HttpServletRequest request) {

        Organization organization = new Organization();
        organization.setOrganizationName(request.getParameter("name"));
        organization.setDescription(request.getParameter("description"));
        organization.setStreet(request.getParameter("street"));
        organization.setCity(request.getParameter("city"));
        organization.setZipCode(Integer.parseInt(request.getParameter("zipCode")));
        organization.setPhone(request.getParameter("phone"));
        organization.setEmail(request.getParameter("email"));

        oService.addOrganization(organization);

        return "redirect:displayOrganizationPage";

    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        oService.deleteOrganization(organizationId);
        return "redirect:displayOrganizationPage";
    }

    @RequestMapping(value = "/displayEditOrganizationPage", method = RequestMethod.GET)
    public String displayEditOrganizationPage(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        Organization organization = oService.getOrganizationById(organizationId);
        model.addAttribute("organization", organization);
        return "editOrganizationPage";
    }

    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@ModelAttribute("organization") Organization Organization) {

        oService.updateOrganization(Organization);

        return "redirect:displayOrganizationPage";
    }

}
