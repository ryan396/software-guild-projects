/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dto.Power;
import com.sg.superherosightings.service.PowerService;
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
public class PowerController {
    
    PowerService pService;
    
    @Inject
    public PowerController(PowerService pService){
        this.pService = pService;
    }
    
    @RequestMapping(value="/displayPowerPage", method=RequestMethod.GET)
    public String displayPowerPage(Model model) {
        
        List<Power> powerList = pService.getAllPowers();
        
        model.addAttribute("powerList", powerList);
        
        return "powerPage";
    }
    
    @RequestMapping(value="/displayAddPowerPage", method=RequestMethod.GET)
    public String displayAddPowerPage() {
        
        return "addPowerPage";
    }
    
    @RequestMapping(value="/addPower", method=RequestMethod.POST)
    public String addPower(HttpServletRequest request) {
        Power power = new Power();
        power.setPowerDescription(request.getParameter("powerDescription"));
        
        pService.addPower(power);
        
        return "redirect: displayPowerPage";
    }
    
    @RequestMapping(value = "/deletePower", method = RequestMethod.GET)
    public String deletePower(HttpServletRequest request) {
        String powerIdParameter = request.getParameter("powerId");
        Integer powerId = Integer.parseInt(powerIdParameter);
        pService.deletePower(powerId);
        return "redirect: displayPowerPage";
    }
    
    @RequestMapping(value = "/displayEditPowerPage", method = RequestMethod.GET)
    public String displayEditPowerPage(HttpServletRequest request, Model model) {
        String powerIdParameter = request.getParameter("powerId");
        int powerId = Integer.parseInt(powerIdParameter);
        Power power = pService.getPowerById(powerId);
        model.addAttribute("power", power);
        return "editPowerPage";
        
    }
    
    @RequestMapping(value = "/editPower", method = RequestMethod.POST)
    public String editPower(HttpServletRequest request, Power power) {
        
        pService.updatePower(power);
        
        return "redirect: displayPowerPage";
        
    }
            
}
