package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VendingMachineController {
    
    VendingMachineServiceLayer service;
    
    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }
       
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String displayItems(Model model) {
        
        List<Item> itemList = service.getAllItems();
        String currentBalance = service.getBalance().toString();
        String itemMessage = service.getItemMessage();
        Change changeDue = service.getChange();
        String message = service.getMessage();
        String changeDueString;
        
        if (changeDue.getQuarter() == 0 && changeDue.getPenny() == 0 && 
                changeDue.getDime() == 0 && changeDue.getPenny() == 0) {
            changeDueString = "";
        } else {
            changeDueString = "Quarters: " + Integer.toString(changeDue.getQuarter())
               + " Dimes: " + Integer.toString(changeDue.getDime())
               + " Nickel: " + Integer.toString(changeDue.getNickel())
               + " Penny: " + Integer.toString(changeDue.getPenny());
        }
        
        model.addAttribute("message", message);
        model.addAttribute("changeDue", changeDueString);
        model.addAttribute("itemMessage", itemMessage);
        model.addAttribute("currentBalance", currentBalance);
        model.addAttribute("itemList", itemList);
        return "vendingMachine";
        
    }
    
    @RequestMapping(value="/addMoney/{type}", method=RequestMethod.GET)
    public String addMoney(@PathVariable String type) {
        service.addMoney(type);
        return "redirect:/";
        
    }
    
    @RequestMapping(value="/pickItem/{itemId}", method=RequestMethod.GET) 
        public String pickItem(@PathVariable int itemId) {
            service.pickItem(itemId);
            return "redirect:/";
    }
        
    @RequestMapping(value="/makeChange", method=RequestMethod.GET)
        public String makeChange() {
            service.makeChange();
            return "redirect:/";
    }
        
    @RequestMapping(value="/purchaseItem", method=RequestMethod.GET)
        public String purchaseItem() {
            if (!service.getItemMessage().equals("")) {
                int itemId = Integer.parseInt(service.getItemMessage());
                service.purchaseItem(itemId);
            }
            return "redirect:/";
        }
        
   
}
