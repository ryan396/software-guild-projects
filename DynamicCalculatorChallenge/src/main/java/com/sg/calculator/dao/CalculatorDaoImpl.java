/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.calculator.dao;

import com.sg.calculator.dto.Calculation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rianu
 */
public class CalculatorDaoImpl implements CalculatorDao {

    private static int idCounter = 0;

    private Map<Integer, Calculation> calculationMap = new HashMap<>();

    @Override
    public Calculation addCalc(Calculation calculation) {
        calculation.setCalculationId(idCounter);
        idCounter++;
        calculationMap.put(calculation.getCalculationId(), calculation);
        return calculation;

    }

    @Override
    public void deleteCalc(int calculationId) {
        calculationMap.remove(calculationId);
    }

    @Override
    public Calculation getCalc(int calculationId) {
        return calculationMap.get(calculationId);
    }

    @Override
    public List<Calculation> getAllCalcs() {
        Collection<Calculation> c = calculationMap.values();
        return new ArrayList(c);
    }

    @Override
    public String calculate(int operand1, int operand2, String operator) {

        String resultString = Integer.toString(operand1) + operator + Integer.toString(operand2) + "=";

        if (operator.equals("+")) {
            float result = (float) operand1 + operand2;
            resultString += Float.toString(result);
        } else if (operator.equals("-")) {
            float result = (float) operand1 - operand2;
            resultString += Float.toString(result);
        } else if (operator.equals("/")) {
            float result = (float) operand1 / operand2;
            resultString += Float.toString(result);
        } else if (operator.equals("*")) {
            float result = (float) operand1 * operand2;
            resultString += Float.toString(result);
        }
        return resultString;
    }

}
