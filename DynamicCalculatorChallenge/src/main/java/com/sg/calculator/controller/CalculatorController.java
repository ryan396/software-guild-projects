/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.calculator.controller;

import com.sg.calculator.dao.CalculatorDao;
import com.sg.calculator.dto.Calculation;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author rianu
 */
@Controller
public class CalculatorController {

    CalculatorDao dao;

    @Inject
    public CalculatorController(CalculatorDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        return "index";
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public String calculate(HttpServletRequest request) {

        Calculation calculation = new Calculation();
        calculation.setDate(LocalDate.now());
        Integer operand1 = Integer.parseInt(request.getParameter("operand1"));
        Integer operand2 = Integer.parseInt(request.getParameter("operand2"));
        String[] operator = request.getParameterValues("operator");
        String result = dao.calculate(operand1, operand2, operator[0]);
        calculation.setResult(result);
        dao.addCalc(calculation);

        return "redirect:/index";

    }

    @RequestMapping(value = "/calculations", method = RequestMethod.GET)
    @ResponseBody
    public List<Calculation> getAllCalculations() {
        return dao.getAllCalcs();
    }
}
