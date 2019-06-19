/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.calculator.dao;

import com.sg.calculator.dto.Calculation;
import java.util.List;

/**
 *
 * @author rianu
 */
public interface CalculatorDao {

    public Calculation addCalc(Calculation calculation);

    public void deleteCalc(int calculationId);

    public Calculation getCalc(int calculationId);

    public List<Calculation> getAllCalcs();
    
    public String calculate(int operand1, int operand2, String operator);

}
